package ua.nure.makestart.dto;

import lombok.Data;
import java.util.Set;

@Data
public class TeamDto {
    private String teamName;
    private Set<UserInfoDto> users;
}
