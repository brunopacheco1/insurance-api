package com.dev.bruno.insuranceapi.service;

import com.dev.bruno.insuranceapi.domain.Module;
import com.dev.bruno.insuranceapi.domain.Price;
import com.dev.bruno.insuranceapi.domain.PriceCalculationRequest;
import com.dev.bruno.insuranceapi.domain.User;
import com.dev.bruno.insuranceapi.exception.PriceCalculationException;
import com.dev.bruno.insuranceapi.helper.ValidationHelper;
import com.dev.bruno.insuranceapi.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

@Service
@CacheConfig(cacheNames = {"prices"})
public class PriceService {

    private final PriceRepository priceRepository;
    private final ModuleService moduleService;
    private final UserService userService;

    @Autowired
    public PriceService(PriceRepository priceRepository, ModuleService moduleService, UserService userService) {
        this.priceRepository = priceRepository;
        this.moduleService = moduleService;
        this.userService = userService;
    }


    @CacheEvict(key = "#request.userId")
    public Price calculatePrice(@Validated PriceCalculationRequest request) {
        Price newPrice = buildInitialPrice(request);
        validatePriceAndRequest(newPrice, request);
        calculateValue(newPrice, request);
        return priceRepository.save(newPrice);
    }

    @Cacheable(key = "#userId")
    public Iterable<Price> listPricesByUser(Long userId) {
        return priceRepository.findAllByUserId(userId);
    }

    private Price buildInitialPrice(PriceCalculationRequest request) {
        Module module = moduleService.findOne(request.getModuleId());
        User user = userService.findOne(request.getUserId());
        Price newPrice = new Price();
        newPrice.setModule(module);
        newPrice.setUser(user);
        return newPrice;
    }

    private void calculateValue(Price price, PriceCalculationRequest request) {
        BigDecimal value = request.getCoverage()
                .multiply(price.getModule().getRisk()).divide(new BigDecimal("100"), RoundingMode.DOWN);
        price.setValue(value);
    }

    private void validatePriceAndRequest(Price price, PriceCalculationRequest request) {
        Set<String> constraints = new HashSet<>();
        validateModuleConfiguration(price.getModule(), request.getCoverage(), constraints);
        validateUserConfiguration(price.getUser(), constraints);
        if(!constraints.isEmpty()) {
            throw new PriceCalculationException(constraints);
        }
    }

    private void validateModuleConfiguration(Module module, BigDecimal coverage, Set<String> constraints) {
        if (module == null) {
            constraints.add(ValidationHelper.MODULE_NOT_FOUND);
        } else {
            boolean lesserThanMinimum = coverage.compareTo(module.getMinimumCoverage()) == -1;
            boolean biggerThanMaximum = coverage.compareTo(module.getMaximumCoverage()) == 1;

            if (lesserThanMinimum || biggerThanMaximum) {
                constraints.add(
                        String.format(ValidationHelper.COVERAGE_NOT_VALID, module.getMinimumCoverage(), module.getMaximumCoverage())
                );
            }
        }
    }

    private void validateUserConfiguration(User user, Set<String> constraints) {
        if (user == null) {
            constraints.add(ValidationHelper.USER_NOT_FOUND);
        }
    }
}
