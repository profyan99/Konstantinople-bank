package com.konstantinoplebank.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTransaction {
    private long userid;
    private long billid;
    private String userName;
    private String description;
    private long amount;
}
