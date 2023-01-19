package ru.meshgroup.interview.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PhoneDataDto extends BaseDto{
    @NotNull
    @Size(min = 11, max = 13)
    private String phone;

    @Override
    public String getAttributeValue() {
        return phone;
    }
}
