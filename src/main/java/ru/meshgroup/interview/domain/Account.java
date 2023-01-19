package ru.meshgroup.interview.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BALANCE", precision = 10, scale = 2)
    private BigDecimal balance;

    @Column(name = "INITIAL_BALANCE", precision = 10, scale = 2)
    private BigDecimal initialBalance;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
        if (this.initialBalance == null) {
            this.initialBalance = balance;
        }
    }
}