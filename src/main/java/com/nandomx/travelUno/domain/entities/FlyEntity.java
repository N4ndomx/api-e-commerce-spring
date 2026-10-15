package com.nandomx.travelUno.domain.entities;

import com.nandomx.travelUno.util.AeroLine;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

// Todo objeto puede ser convertido en la red
@Data // Implementa Gettt Sett to string
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "fly")
public class FlyEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double originLat;
    private Double originLng;
    private Double destinyLat;
    private Double destinyLng;

    @Column(length = 20)
    private String destinyName;
    @Column(length = 20)
    private String originName;

    private BigDecimal price ;

    @Enumerated(EnumType.STRING)
    private AeroLine aeroLine;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,  // LAZY es un selec solo de Fly y cuando necesites cargas con get y cuando es EAGER hace un join con tikets
            mappedBy = "fly",
            orphanRemoval = true
    ) // Aqui no esta la FK solo es para cargar la relacion
    private Set<TicketEntity> tickets;


}
