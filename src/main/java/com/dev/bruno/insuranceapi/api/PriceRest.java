package com.dev.bruno.insuranceapi.api;

import com.dev.bruno.insuranceapi.domain.Price;
import com.dev.bruno.insuranceapi.domain.PriceCalculationRequest;
import com.dev.bruno.insuranceapi.domain.User;
import com.dev.bruno.insuranceapi.service.PriceService;
import com.dev.bruno.insuranceapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class PriceRest {

    private final PriceService priceService;

    @Autowired
    public PriceRest(PriceService priceService) {
        this.priceService = priceService;
    }

    @RequestMapping(value = "/users/{id}/prices", method = RequestMethod.GET)
    public Iterable<Price> listPricesByUser(@PathVariable(value = "id") Long id) {
        return priceService.listPricesByUser(id);
    }

    @RequestMapping(value = "/users/{id}/prices", method = RequestMethod.POST)
    public Price calculatePrice(@Validated @RequestBody PriceCalculationRequest request) {
        return priceService.calculatePrice(request);
    }
}
