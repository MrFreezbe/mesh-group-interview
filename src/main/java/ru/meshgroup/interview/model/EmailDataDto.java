package ru.meshgroup.interview.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDataDto implements BaseDto {
    @NotNull
    @Size(min = 1, max = 200)
    @Email
    private String email;

    @Override
    public String getAttributeValue() {
        return email;
    }
}
