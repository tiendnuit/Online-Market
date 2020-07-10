package edu.miu.cs545.group5.onlinemarket.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Payment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Past
    private LocalDate createdDate;

    @ManyToOne
    private Buyer buyer;

}
