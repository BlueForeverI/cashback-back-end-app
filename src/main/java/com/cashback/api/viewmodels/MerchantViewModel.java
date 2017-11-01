package com.cashback.api.viewmodels;

import com.cashback.api.entities.Merchant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Merchant data")
public class MerchantViewModel extends BaseViewModel<Merchant, MerchantViewModel> {

    @NotNull
    @Length(min = 2, message = "The merchant name should be at least 2 characters")
    @ApiModelProperty(notes = "Merchant name", required = true)
    private String name;

    @NotNull
    @Length(min = 2, message = "The merchant description should be at least 2 characters")
    @ApiModelProperty(notes = "Merchant description", required = true)
    private String description;

    @NotNull
    @Email(message = "Invalid email format")
    @ApiModelProperty(notes = "Merchant email", required = true)
    private String email;

    @NotNull
    @ApiModelProperty(notes = "Merchant redirect URL", required = true)
    private String redirectUrl;

    @NotNull
    @ApiModelProperty(notes = "The cashback percentage for the merchant", required = true)
    private double cashbackPercentage;

    @ApiModelProperty(notes = "Merchant image URL")
    private String imageUrl;

    @ApiModelProperty(notes = "Merchant contact name")
    private String contactName;
}
