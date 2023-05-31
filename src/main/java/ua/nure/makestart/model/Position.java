package ua.nure.makestart.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "positions")
public class Position {
    @Id
    @Column(name = "position_id")
    private String id = UUID.randomUUID().toString();
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "project_id")
    private Project project;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "seniority_id")
    private Seniority seniority;
    @Column(name = "position_status")
    private String positionStatus;
    @Column(name = "experience_years")
    private int experienceYears;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "language_position",
            joinColumns = @JoinColumn(name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<Languages> languages;

}
