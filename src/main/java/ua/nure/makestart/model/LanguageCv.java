package ua.nure.makestart.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "language_cv")
public class LanguageCv {
    @Id
    @ManyToOne
    @JoinColumn(name = "cv_id")
    private Cv cv;

    @Id
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Languages language;
}
