package com.dev.bruno.insuranceapi;

import com.dev.bruno.insuranceapi.domain.Price;
import com.dev.bruno.insuranceapi.domain.PriceCalculationRequest;
import com.dev.bruno.insuranceapi.exception.PriceCalculationException;
import com.dev.bruno.insuranceapi.service.PriceService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@ActiveProfiles("develop")
@RunWith(SpringRunner.class)
@SpringBootTest
public class InsuranceApiApplicationTests {

    private static final BigDecimal EXPECTED_PRICE_VALUE = new BigDecimal("30");
    private static final BigDecimal COVERAGE_VALUE = new BigDecimal("100");
    private static final BigDecimal EXCEEDING_MIN_COVERAGE = new BigDecimal("-1");
    private static final BigDecimal EXCEEDING_MAX_COVERAGE = new BigDecimal("10000");
    private static final Long MODULE_ID = 1L;
    private static final Long NOT_EXISTING_MODULE_ID = 100000L;
    private static final Long USER_ID = 5L;
    private static final Long NOT_EXISTING_USER_ID = 500000L;

    @Autowired
    private PriceService priceService;

    @Test
    public void shouldSucceed() {
        PriceCalculationRequest request = new PriceCalculationRequest();
        request.setCoverage(COVERAGE_VALUE);
        request.setModuleId(MODULE_ID);
        request.setUserId(USER_ID);
        Price price = priceService.calculatePrice(request);
        Assert.assertTrue(price.getValue().compareTo(EXPECTED_PRICE_VALUE) == 0);
    }

    @Test(expected = PriceCalculationException.class)
    public void shouldFailed_NullUser() {
        PriceCalculationRequest request = new PriceCalculationRequest();
        request.setCoverage(COVERAGE_VALUE);
        request.setModuleId(MODULE_ID);
        priceService.calculatePrice(request);
    }

    @Test(expected = PriceCalculationException.class)
    public void shouldFailed_NullModule() {
        PriceCalculationRequest request = new PriceCalculationRequest();
        request.setCoverage(COVERAGE_VALUE);
        request.setUserId(USER_ID);
        priceService.calculatePrice(request);
    }

    @Test(expected = PriceCalculationException.class)
    public void shouldFailed_NotExistingUser() {
        PriceCalculationRequest request = new PriceCalculationRequest();
        request.setCoverage(COVERAGE_VALUE);
        request.setModuleId(MODULE_ID);
        request.setUserId(NOT_EXISTING_USER_ID);
        priceService.calculatePrice(request);
    }

    @Test(expected = PriceCalculationException.class)
    public void shouldFailed_NotExistingModule() {
        PriceCalculationRequest request = new PriceCalculationRequest();
        request.setCoverage(COVERAGE_VALUE);
        request.setModuleId(NOT_EXISTING_MODULE_ID);
        request.setUserId(USER_ID);
        priceService.calculatePrice(request);
    }

    @Test(expected = PriceCalculationException.class)
    public void shouldFailed_NullCoverage() {
        PriceCalculationRequest request = new PriceCalculationRequest();
        request.setModuleId(MODULE_ID);
        request.setUserId(USER_ID);
        priceService.calculatePrice(request);
    }

    @Test(expected = PriceCalculationException.class)
    public void shouldFailed_ItExceedsMinimumValue() {
        PriceCalculationRequest request = new PriceCalculationRequest();
        request.setCoverage(EXCEEDING_MIN_COVERAGE);
        request.setModuleId(MODULE_ID);
        request.setUserId(USER_ID);
        priceService.calculatePrice(request);
    }

    @Test(expected = PriceCalculationException.class)
    public void shouldFailed_ItExceedsMaximumValue() {
        PriceCalculationRequest request = new PriceCalculationRequest();
        request.setCoverage(EXCEEDING_MAX_COVERAGE);
        request.setModuleId(MODULE_ID);
        request.setUserId(USER_ID);
        priceService.calculatePrice(request);
    }
}
