package maye.entity;


import jakarta.persistence.*;
import lombok.*;
import maye.enums.staffEnum.StaffStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "staff", schema = "maye",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "medicalLicense")
})
@NoArgsConstructor // Generates no-args constructor
@AllArgsConstructor // Generates all-args constructor
@Getter
@Setter
@ToString

public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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
    private StaffStatus status = StaffStatus.PENDING;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

}
