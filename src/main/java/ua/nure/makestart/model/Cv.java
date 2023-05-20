package ua.nure.makestart.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Cv {
    @Id
    @Column(name = "cv_id")
    private String id = UUID.randomUUID().toString();
    @Column
    private String description;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "seniority_id")
    private Seniority seniority;
    @Column(name = "experience_years")
    private int experienceYears;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "language_cv",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<Languages> languages;

}
