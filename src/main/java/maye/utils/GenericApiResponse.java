package maye.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class GenericApiResponse <T> {

    private boolean isSuccessful;
    private String message;

    @Builder.Default
    private T data = null;
}
