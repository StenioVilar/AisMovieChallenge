package com.desafio.ais.api.error;

public class ResourceNotFoundDetails extends ErrorDetails{

    public static final class Builder {

        private String status_code;
        private String status_message;
        private String detail_parameter;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder status_code(String status_code) {
            this.status_code = status_code;
            return this;
        }

        public Builder status_message(String status_message) {
            this.status_message = status_message;
            return this;
        }

        public Builder detail_parameter(String detail_parameter) {
            this.detail_parameter = detail_parameter;
            return this;
        }

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setStatus_code(status_code);
            resourceNotFoundDetails.setStatus_message(status_message);
            resourceNotFoundDetails.setDetail_parameter(detail_parameter);
            return resourceNotFoundDetails;
        }
    }
}
