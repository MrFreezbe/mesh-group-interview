package ru.meshgroup.interview.model;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements BaseDto{
    @DecimalMin("0.00")
    private BigDecimal balance;

    @Override
    public String getAttributeValue() {
        return balance.toString();
    }
}
