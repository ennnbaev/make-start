package ua.nure.makestart.dto;

import lombok.Data;

@Data
public class DetailProjectDto {
    private String projectName;
    private String description;
    private double price;
    private UserInfoDto owner;
    private PositionDto positions;
    private TeamDto team;
}
