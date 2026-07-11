package com.Ram.backend.common.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        boolean success,
        String errorCode,
        String message,
        String path,
        LocalDateTime timestamp,
        List<FieldErrorResponse> fieldErrors
) {
    public static ErrorResponse of(String errorCode, String message, String path) {
        return new ErrorResponse(false, errorCode, message, path, LocalDateTime.now(), List.of());
    }

    public static ErrorResponse of(String errorCode, String message, String path, List<FieldErrorResponse> fieldErrors) {
        return new ErrorResponse(false, errorCode, message, path, LocalDateTime.now(), fieldErrors);
    }

    public record FieldErrorResponse(String field, String message) {
    }
}