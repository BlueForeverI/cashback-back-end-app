package com.cashback.api.services;

import com.cashback.api.entities.Merchant;
import com.cashback.api.repositories.MerchantRepository;
import com.cashback.api.viewmodels.MerchantViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
public class MerchantsService {

    private MerchantRepository merchantRepository;

    @Autowired
    public MerchantsService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public List<MerchantViewModel> getAllMerchants() {
        return StreamSupport.stream(merchantRepository.findAll().spliterator(), false)
                .map(m -> new MerchantViewModel().fromModel(m))
                .collect(toList());
    }

    public MerchantViewModel getMerchantById(Long id) {
        return new MerchantViewModel().fromModel(
                merchantRepository.findOne(id));
    }

    public MerchantViewModel createMerchant(MerchantViewModel merchant) {
        Merchant merchantModel = merchant.toModel();
        merchantModel = merchantRepository.save(merchantModel);

        return new MerchantViewModel().fromModel(merchantModel);
    }

    public MerchantViewModel updateMerchant(MerchantViewModel merchant) {
        return createMerchant(merchant);
    }

    public void deleteMerchant(Long merchantId) {
        merchantRepository.delete(merchantId);
    }
}
