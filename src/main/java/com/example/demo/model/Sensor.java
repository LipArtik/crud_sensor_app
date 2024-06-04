package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sensors")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    @Column
    private String name;
    @Column
    private String model;
    @JsonProperty("range")
    @ManyToOne()
    @JoinColumn(name = "range_id")
    private Range range;
    @JsonProperty("type")
    @ManyToOne()
    @JoinColumn(name = "type_id")
    private Type type;
    @JsonProperty("unit")
    @ManyToOne()
    @JoinColumn(name = "unit_id")
    private Unit unit;
    @Column
    private String location;
    @Column
    private String description;
}
