package ua.nure.makestart.dto;

import lombok.Data;

import java.util.Set;

@Data
public class DetailProjectDto {
    private String projectName;
    private String description;
    private double price;
    private UserInfoDto owner;
    private PositionDto positions;
    private TeamDto team;
    private Set <CvInfoDto> cv;
}
