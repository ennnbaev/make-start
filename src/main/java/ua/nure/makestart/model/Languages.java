package ua.nure.makestart.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "languages")
public class Languages {
    @Id
    @Column(name = "language_id")
    private String languageId;
    @Column(name = "language_name")
    private String languageName;
}
