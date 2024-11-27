package com.pratik.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    //name = 'customer_id' is not needed if column name and object name are same
    private long customerId;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String mobileNumber;


}
