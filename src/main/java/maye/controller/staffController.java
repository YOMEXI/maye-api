package maye.controller;

import jakarta.validation.Valid;
import maye.dto.staffDto.newStaffRequest;
import maye.dto.staffDto.paginatedStaffResponseDto;
import maye.entity.Staff;
import maye.enums.staffEnum.StaffStatus;
import maye.exception.CustomApiException;
import maye.service.staffService;
import maye.utils.GenericApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/staff")
public class staffController {

    @Autowired
    private staffService staffService;



    @PostMapping
    public ResponseEntity<?> createStaff(@Valid @RequestBody newStaffRequest staff) {

            return staffService.newStaff(staff);
    }

    @GetMapping("/pending")
    public ResponseEntity<paginatedStaffResponseDto> getPendingStaff(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {


        Pageable pageable = PageRequest.of(page, pageSize);
        paginatedStaffResponseDto response = staffService.findPendingStaff(pageable);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/accepted")
    public ResponseEntity<paginatedStaffResponseDto> getAcceptedStaff(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);
        paginatedStaffResponseDto response = staffService.findAcceptedStaff(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/suspended")
    public ResponseEntity<paginatedStaffResponseDto> getSuspendedStaff(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);
        paginatedStaffResponseDto response = staffService.findSuspendedStaff(pageable);
        return ResponseEntity.ok(response);
    }

}
