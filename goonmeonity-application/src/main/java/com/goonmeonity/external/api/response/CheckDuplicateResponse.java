package com.goonmeonity.external.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckDuplicateResponse {
    private boolean possible;
    private String message;
}
