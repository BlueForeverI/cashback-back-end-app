package com.cashback.api.responses;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by George on 9.5.2017 г..
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    @ApiModelProperty(notes = "The response data")
    private T data;

    @ApiModelProperty(notes = "Whether the response is successful or not")
    private boolean isSuccessful;

    @ApiModelProperty(notes = "The error message (if the response fails)")
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
