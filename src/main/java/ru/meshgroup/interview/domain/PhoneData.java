package ru.meshgroup.interview.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PHONE_DATA")
public class PhoneData extends BaseEntity {
    @Column(name = "PHONE", length = 13)
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneData phoneData = (PhoneData) o;
        return phone.equals(phoneData.phone);
    }

    @Override
    public int hashCode() {
        return phone.hashCode();
    }
}
