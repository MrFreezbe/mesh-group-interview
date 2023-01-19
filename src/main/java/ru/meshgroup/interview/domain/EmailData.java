package ru.meshgroup.interview.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMAIL_DATA")
public class EmailData extends BaseEntity {
    @Column(name = "EMAIL", length = 200)
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailData emailData = (EmailData) o;
        return email.equals(emailData.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
