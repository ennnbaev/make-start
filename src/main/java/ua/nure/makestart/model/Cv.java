package ua.nure.makestart.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Cv {
    @Id
    @GeneratedValue
    @Column(name = "cv_id")
    private UUID id;
    @Column
    private String description;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "seniority_id")
    private Seniority seniority;
    @OneToOne(mappedBy = "cv")
    private Users users;
}
