package com.bhanuchaddha.myenergy.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    PRICE_PLAN_NOT_FOUND("Given price plan is not available."),
    PRICE_PLAN_ALREADY_EXIST("Given price plan already exist."),
    METER_NOT_FOUND("Given meter is not available."),
    METER_ALREADY_ENROLLED("Given meter is already enrolled."),
    METER_READING_NOT_AVAILABLE("Meter readings not available to calculate meter usage."),
    ;

    String description;
}
