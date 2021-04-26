package com.kodilla.ecommerce.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StatusOrder {
    @JsonProperty("ACCEPTED")
    ACCEPTED,
    @JsonProperty("IN_PROGRESS")
    IN_PROGRESS,
    @JsonProperty("SENT")
    SENT,
    @JsonProperty("CANCELED")
    CANCELED
}