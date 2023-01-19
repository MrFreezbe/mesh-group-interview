package ru.meshgroup.interview.model;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountDto extends BaseDto{
    @DecimalMin("0.00")
    private BigDecimal balance;

    @Override
    public String getAttributeValue() {
        return balance.toString();
    }
}
