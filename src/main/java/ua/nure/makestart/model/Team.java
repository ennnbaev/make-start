package ua.nure.makestart.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Team {
    @Id
    @Column(name = "team_id")
    private String id = UUID.randomUUID().toString();
    @Column(name = "team_name")
    private String teamName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Users> users;
}
