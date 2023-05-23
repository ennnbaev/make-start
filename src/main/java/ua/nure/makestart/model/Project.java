package ua.nure.makestart.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private String id = UUID.randomUUID().toString();
    @Column(name = "project_name")
    private String projectName;
    @Column
    private String description;
    @Column
    private double price;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    private Users owner;


}
