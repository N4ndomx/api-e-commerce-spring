package com.nandomx.travelUno.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data // Implementa Gettt Sett to string
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "hotel")
public class HotelEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String address;

    private Integer rating;

    private BigDecimal price;

    // @ToString.Exclude
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ReservationEntity> reservations;
}
