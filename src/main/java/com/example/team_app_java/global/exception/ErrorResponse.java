package com.example.team_app_java.global.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

    private String code;
    private String message;
    private List<FieldError> errors;

    public ErrorResponse(String code, String message, List<FieldError> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public static ErrorResponse of(String code, String message, List<FieldError> errors) {
        return new ErrorResponse(code, message, errors);
    }

    @Getter
    public static class FieldError {
        private final String field;
        private final String reasonCode;

        public FieldError(String field, String reasonCode) {
            this.field = field;
            this.reasonCode = reasonCode;
        }

        public static FieldError of(org.springframework.validation.FieldError error) {
            return new FieldError(error.getField(), error.getDefaultMessage());
        }
    }
}

