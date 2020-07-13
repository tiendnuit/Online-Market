package edu.miu.cs545.group5.onlinemarket.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Payment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Card holder name is required")
    @Size(min = 2, max = 100, message = "Card holder name length is 2-100 characters")
    private String cardName;

    @CreditCardNumber(message = "Invalid card number. Eg. 4111111111111111")
    private String cardNumber;

    @Pattern(regexp = "^[0-9]{2}\\/[0-9]{4}$", message = "Invalid format expiration. Eg 11/2030")
    private String expiration;

    @Pattern(regexp = "^[0-9]{3}$", message = "Invalid format CVV. Eg 097")
    private String cvv;

    @ManyToOne
    private Buyer buyer;

}
