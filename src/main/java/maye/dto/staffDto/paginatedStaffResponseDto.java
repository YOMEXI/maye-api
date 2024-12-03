package maye.dto.staffDto;

import lombok.Data;

import java.util.List;

@Data
public class paginatedStaffResponseDto {

    private List<staffResponseDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean isLast;
}
