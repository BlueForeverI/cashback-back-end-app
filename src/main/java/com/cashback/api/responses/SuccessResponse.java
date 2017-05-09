package com.cashback.api.responses;

/**
 * Created by George on 9.5.2017 г..
 */
public class SuccessResponse<T> extends BaseResponse<T> {
    public SuccessResponse(T data) {
        super(data, true, null);
    }
}
