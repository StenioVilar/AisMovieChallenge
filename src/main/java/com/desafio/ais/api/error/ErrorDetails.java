package com.desafio.ais.api.error;

public class ErrorDetails {

    private String status_code;
    private String status_message;
    private String detail_parameter;

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public String getDetail_parameter() {
        return detail_parameter;
    }

    public void setDetail_parameter(String detail_parameter) {
        this.detail_parameter = detail_parameter;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public static final class Builder {
        private String status_code;
        private String status_message;
        private String detail_parameter;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder status_message(String status_message) {
            this.status_message = status_message;
            return this;
        }

        public Builder status_code(String status_code) {
            this.status_code = status_code;
            return this;
        }

        public Builder detail_parameter(String detail_parameter) {
            this.detail_parameter = detail_parameter;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setStatus_code(status_code);
            errorDetails.setStatus_message(status_message);
            errorDetails.setDetail_parameter(detail_parameter);
            return errorDetails;
        }
    }

}
