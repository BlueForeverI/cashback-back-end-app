package com.cashback.api.responses;

/**
 * Created by George on 9.5.2017 г..
 */
public class BaseResponse<T> {
    private T data;
    private boolean isSuccessful;
    private String errorMessage;

    public BaseResponse() {}

    public BaseResponse(T data, boolean isSuccessful, String errorMessage) {
        this.data = data;
        this.isSuccessful = isSuccessful;
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
