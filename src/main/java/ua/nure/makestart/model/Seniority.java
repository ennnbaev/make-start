package ua.nure.makestart.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Seniority {
    @Id
    @GeneratedValue
    @Column(name = "seniority_id")
    private String id;
    @Column(name = "seniority_name")
    private String name;
}
