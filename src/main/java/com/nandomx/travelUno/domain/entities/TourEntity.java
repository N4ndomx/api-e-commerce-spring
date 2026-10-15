package com.nandomx.travelUno.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data // Implementa Gettt Sett to string
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "tour")
public class TourEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "tour",
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<ReservationEntity> reservations = new LinkedList<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "tour",
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<TicketEntity> tickets = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private CustomerEntity customer;

    public void addTicket(TicketEntity ticket) {
        if(!this.tickets.contains(ticket)) {
            this.tickets.add(ticket);
            ticket.setTour(this);
        }
    }

    public void addReservation(ReservationEntity reservation) {
        if(!this.reservations.contains(reservation)) {
            this.reservations.add(reservation);
            reservation.setTour(this);
        }
    }
}
