package com.supportportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.supportportal.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "requested_home")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class RequestedHomes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String recipientsName;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "budget",nullable = false)
    private Long budget;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_number_id", nullable = false)
    private RoomNumber roomNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status", nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "floors", nullable = false)
    private String floors;

    @Column(name = "note", nullable = false)
    private String note;

}
