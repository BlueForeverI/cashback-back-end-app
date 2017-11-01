package com.cashback.api.controllers;

import com.cashback.api.services.MerchantsService;
import com.cashback.api.viewmodels.MerchantViewModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("!isAnonymous()")
@RequestMapping("/api/merchant")
public class MerchantsController {

    private MerchantsService merchantsService;

    @Autowired
    public MerchantsController(MerchantsService merchantsService) {
        this.merchantsService = merchantsService;
    }

    @ApiOperation(value = "Get all merchants", notes = "Get all merchants")
    @RequestMapping(method = RequestMethod.GET)
    public List<MerchantViewModel> getAllMerchants() {
        return merchantsService.getAllMerchants();
    }

    @ApiOperation(value = "Get a merchant by ID", notes = "Get a merchant by ID")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public MerchantViewModel getMerchantById(
            @ApiParam(value = "The merchant ID", required = true)
            @PathVariable Long id) {
        return merchantsService.getMerchantById(id);
    }

    @ApiOperation(value = "Create a merchant", notes = "Create a merchant")
    @RequestMapping(method = RequestMethod.POST)
    public MerchantViewModel createMerchant(
            @ApiParam(value = "The merchant data", required = true)
            @RequestBody @Valid MerchantViewModel merchant) {
        return merchantsService.createMerchant(merchant);
    }

    @ApiOperation(value = "Update a merchant", notes = "Update a merchant")
    @RequestMapping(method = RequestMethod.PUT)
    public MerchantViewModel updateMerchant(
            @ApiParam(value = "The merchant data", required = true)
            @RequestBody @Valid MerchantViewModel merchant) {
        return merchantsService.updateMerchant(merchant);
    }

    @ApiOperation(value = "Delete a merchant by ID", notes = "Delete a merchant by ID")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteMerchant(
            @ApiParam(value = "The merchant ID", required = true)
            @PathVariable Long id) {
        merchantsService.deleteMerchant(id);
    }
}
