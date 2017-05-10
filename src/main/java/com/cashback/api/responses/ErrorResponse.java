package com.cashback.api.responses;

/**
 * Created by George on 9.5.2017 г..
 */
public class ErrorResponse<T> extends BaseResponse<T> {
    public ErrorResponse(String errorMessage) {
        super(null, false, errorMessage);
    }
}
