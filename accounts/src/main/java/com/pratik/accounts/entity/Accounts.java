package com.pratik.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Accounts extends BaseEntity{
    @Id
    @Column
    private long accountNumber;
    @Column
    private long customerId;
    @Column
    private String accountType;
    @Column
    private String branchAddress;

}
