package maye.service;

import jakarta.transaction.Transactional;
import maye.dto.staffDto.newStaffRequest;
import maye.dto.staffDto.paginatedStaffResponseDto;
import maye.dto.staffDto.staffResponseDto;
import maye.entity.Staff;
import maye.enums.staffEnum.StaffStatus;
import maye.exception.CustomApiException;
import maye.repository.staffRepository;
import maye.utils.GenericApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class staffService {


    @Autowired
    private staffRepository staffRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Transactional
    public ResponseEntity<?> newStaff(newStaffRequest newStaff) {

        var existsByEmail = staffRepository.existsByEmail(newStaff.getEmail());



        if (existsByEmail){
            throw new CustomApiException(HttpStatus.BAD_REQUEST,
                    "Emails already exist");
        }



        var staff = new Staff();
        staff.setFirstName(newStaff.getFirstName());
        staff.setLastName(newStaff.getLastName());
        staff.setMiddleName(newStaff.getMiddleName());
        staff.setAddress(newStaff.getAddress());
        staff.setEmail(newStaff.getEmail());
        staff.setOccupation(newStaff.getOccupation());
        staff.setDob(newStaff.getDob());
        staff.setGender(newStaff.getGender());
        staff.setYearsOfExperience(newStaff.getYearsOfExperience());
        staff.setMedicalLicense(newStaff.getMedicalLicense());
        staff.setEmploymentStatus(newStaff.getEmploymentStatus());
        staff.setTelephoneNumber(newStaff.getTelephoneNumber());
        staff.setStaffId(generateUniqueIdentifier(newStaff.getFirstName(),newStaff.getLastName()));

        Staff Staff = modelMapper.map(staff, Staff.class);
        staffRepository.save(Staff);
        
        return ResponseEntity.ok(new GenericApiResponse(true,"New Staff created Successfully",null));
    }

    public  paginatedStaffResponseDto findPendingStaff(
            Pageable pageable

            ) {

        Page<Staff> staffPage = staffRepository.findByStatus(StaffStatus.PENDING, pageable);

        List<staffResponseDto> staffDtoList = staffPage.getContent().stream()
                .map(staff -> modelMapper.map(staff, staffResponseDto.class))
                .collect(Collectors.toList());

        paginatedStaffResponseDto response = new paginatedStaffResponseDto();
        response.setContent(staffDtoList);
        response.setPageNumber(staffPage.getNumber());
        response.setPageSize(staffPage.getSize());
        response.setTotalPages(staffPage.getTotalPages());
        response.setTotalElements(staffPage.getTotalElements());
        response.setLast(staffPage.isLast());

        return response;

    }


    public  paginatedStaffResponseDto findAcceptedStaff(
            Pageable pageable

    ) {

        Page<Staff> staffPage = staffRepository.findByStatus(StaffStatus.ACCEPTED, pageable);

        List<staffResponseDto> staffDtoList = staffPage.getContent().stream()
                .map(staff -> modelMapper.map(staff, staffResponseDto.class))
                .collect(Collectors.toList());

        paginatedStaffResponseDto response = new paginatedStaffResponseDto();
        response.setContent(staffDtoList);
        response.setPageNumber(staffPage.getNumber());
        response.setPageSize(staffPage.getSize());
        response.setTotalPages(staffPage.getTotalPages());
        response.setTotalElements(staffPage.getTotalElements());
        response.setLast(staffPage.isLast());

        return response;

    }

    public  paginatedStaffResponseDto findSuspendedStaff(
            Pageable pageable

    ) {

        Page<Staff> staffPage = staffRepository.findByStatus(StaffStatus.SUSPENDED, pageable);

        List<staffResponseDto> staffDtoList = staffPage.getContent().stream()
                .map(staff -> modelMapper.map(staff, staffResponseDto.class))
                .collect(Collectors.toList());

        paginatedStaffResponseDto response = new paginatedStaffResponseDto();
        response.setContent(staffDtoList);
        response.setPageNumber(staffPage.getNumber());
        response.setPageSize(staffPage.getSize());
        response.setTotalPages(staffPage.getTotalPages());
        response.setTotalElements(staffPage.getTotalElements());
        response.setLast(staffPage.isLast());

        return response;

    }
    private String generateUniqueIdentifier(String firstName, String lastName) {
        String initials = (firstName.substring(0, 2) + lastName.substring(0, 2)).toUpperCase();
        int randomNumber = new Random().nextInt(90000) + 10000; // generates a random 5-digit number
        return initials + randomNumber;
    }
}
