package ru.meshgroup.interview.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class UserDto {

    @NotNull
    @Size(min = 1, max = 500)
    private String name;

    @NotNull
    @Past
    private Date dateOfBirth;

    @NotNull
    @Size(min = 8, max = 500)
    private String password;

    @Valid
    private AccountDto account;

    @NotNull
    @Size(min = 1)
    @Valid
    private List<EmailDataDto> emailData;

    @NotNull
    @Size(min = 1)
    @Valid
    private List<PhoneDataDto> phoneData;
}
