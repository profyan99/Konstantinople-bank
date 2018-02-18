package com.konstantinoplebank.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBill {
    private long userid;
    private long amount;
    private String userName;
}
