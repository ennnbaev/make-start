package ua.nure.makestart.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Seniority {
    @Id
    @GeneratedValue
    @Column(name = "seniority_id")
    private UUID id;
    @Column(name = "seniority_name")
    private String name;
    @OneToMany(mappedBy = "seniority")
    private List<Cv> cv;
}
