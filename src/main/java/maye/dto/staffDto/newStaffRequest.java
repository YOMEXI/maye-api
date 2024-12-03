package maye.dto.staffDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Data
public class newStaffRequest {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private String middleName;

    @NotBlank(message = "Address is required")
    private String address;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Occupation is required")
    private String occupation;

    @NotNull(message = "Date of birth is required")
    private LocalDate dob;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Min(value = 0, message = "Years of experience cannot be negative")
    private int yearsOfExperience;

    @NotBlank(message = "Medical license is required")
    private String medicalLicense;

    @NotBlank(message = "Employment status is required")
    private String employmentStatus;


    private String telephoneNumber;
}
