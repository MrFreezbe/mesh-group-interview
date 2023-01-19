package ru.meshgroup.interview.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmailDataDto extends BaseDto{
    @NotNull
    @Size(min = 1, max = 200)
    @Email
    private String email;

    @Override
    public String getAttributeValue() {
        return email;
    }
}
