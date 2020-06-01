package com.goonmeonity.external.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateBoardRequest {
    private String title;
    private String content;
}
