package com.framework;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
public enum Month {
    JANUARY("Make"),
    FEBRUARY("Material"),
    MODEL("Model"),
    ECCN("ECCN"),
    CUSTOMER_IDENTIFIER("Customer Identifier"),
    ORDER_ID("Order ID");


    @Getter
    public String value;
}
