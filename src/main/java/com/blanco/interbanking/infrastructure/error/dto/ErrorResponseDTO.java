package com.blanco.interbanking.infrastructure.error.dto;

import com.blanco.interbanking.infrastructure.error.enums.ErrorType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO representing errors")
public class ErrorResponseDTO {

    @Schema(description = "Detail of error", example = "NullPointerException ")
    private String detail;
    @Schema(description = "Http Code ", example = "500")
    private int httpCode;
    @Schema(description = "Type Error of API", example = "BUSINESS")
    private ErrorType errorType;


    public ErrorResponseDTO(String detail, int httpCode, ErrorType errorType) {
        this.detail = detail;
        this.httpCode = httpCode;
        this.errorType = errorType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}
