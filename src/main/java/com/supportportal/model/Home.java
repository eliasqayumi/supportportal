package com.supportportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.supportportal.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "home")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price", nullable = false)
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "neighbourhood_id", nullable = false)
    private Neighbourhood neighbourhood;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_number_id", nullable = false)
    private RoomNumber roomNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "home_type_id", nullable = false)
    private HomeType homeType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "floor_number")
    private Integer floorNumber;

    @Column(name = "total_floor")
    private Integer totalFloor;

    @Column(name = "address")
    private String address;

    @Column(name = "header", nullable = false)
    private String header;

    @Column(name = "details", length = 500)
    private String details;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status", nullable = false)
    private Status status;

}