package ru.meshgroup.interview.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDataDto implements BaseDto {
    @NotNull
    @Size(min = 11, max = 13)
    private String phone;

    @Override
    public String getAttributeValue() {
        return phone;
    }
}
