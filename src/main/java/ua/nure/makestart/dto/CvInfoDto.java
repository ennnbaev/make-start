package ua.nure.makestart.dto;

import lombok.Data;


import java.util.Set;

@Data
public class CvInfoDto {
    private String description;
    private SeniorityDto seniority;
    private int experienceYears;
    private Set<LanguageDto> languages;
}
