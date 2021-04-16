package com.kodilla.ecommerce.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StatusUser{
    @JsonProperty("blocked_user")
    BLOCKED_USER,
    @JsonProperty("active_user")
    ACTIVE_USER
}