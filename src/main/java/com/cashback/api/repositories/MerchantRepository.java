package com.cashback.api.repositories;

import com.cashback.api.entities.Merchant;
import org.springframework.data.repository.CrudRepository;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
}
