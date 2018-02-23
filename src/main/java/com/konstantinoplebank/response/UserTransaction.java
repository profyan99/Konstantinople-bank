package com.konstantinoplebank.response;

import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.User;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTransaction {
    private long id;

    private long userId;

    private long billId;

    private long amount;

    Date date;

    private String description;

    public UserTransaction(long userId, long billId, long amount, Date date, String description) {
        this(0, userId, billId, amount, date, description);
    }
}
