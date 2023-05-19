package ua.nure.makestart.dto;


import lombok.Data;

@Data
public class UserInfoDto {
    private String firstName;
    private String lastName;
    private int age;
    private String username;
    private String email;
    private CvInfoDto cv;
}
