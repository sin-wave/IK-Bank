package com.pratik.cards.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cards")
public class Cards extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;

	private String mobileNumber;

	private String cardNumber;

	private String cardType;

	private int totalLimit;

	private int amountUsed;

	private int availableAmount;
	
}
