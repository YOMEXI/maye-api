package maye.repository;


import maye.entity.Staff;
import maye.enums.staffEnum.StaffStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface staffRepository extends JpaRepository<Staff, UUID>, JpaSpecificationExecutor<Staff> {
    boolean existsByEmail(String email);

    Page<Staff> findByStatus(StaffStatus status, Pageable pageable);
}