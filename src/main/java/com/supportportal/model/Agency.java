package com.supportportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agency")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "agency_name", nullable = false, length = 45)
    private String agencyName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "neighbourhood_id", nullable = false)
    private Neighbourhood neighbourhoodId;

    public Agency(String agencyName, Neighbourhood neighbourhoodId) {
        this.agencyName = agencyName;
        this.neighbourhoodId = neighbourhoodId;
    }
}