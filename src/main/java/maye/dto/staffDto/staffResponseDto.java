package maye.dto.staffDto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import maye.enums.staffEnum.StaffStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class staffResponseDto {

    private String firstName;


    private String lastName;

    private String middleName;


    private String address;


    private String email;


    private String occupation;


    private LocalDate dob;


    private String gender;


    private int yearsOfExperience;


    private String medicalLicense;


    private String employmentStatus;


    private String telephoneNumber;

    private String staffId;

    @Enumerated(EnumType.STRING)
    private StaffStatus status ;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
