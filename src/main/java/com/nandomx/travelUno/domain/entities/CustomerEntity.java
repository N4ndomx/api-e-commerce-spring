package com.nandomx.travelUno.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data // Implementa Gettt Sett to string
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "Customer")
public class CustomerEntity implements Serializable {
    @Id
    @Column(length = 12)
    private String dni;
    @Column(length = 50)
    private String fullName;
    @Column(length = 20)
    private  String credit_card;

    private Integer totalFlights;
    private Integer totalLodgings;
    private Integer totalTours;
    @Column(length = 12)
    private String phoneNumber;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY

    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TicketEntity> tickets;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY

    )
    private List<ReservationEntity> reservations;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<TourEntity> tours;
}
