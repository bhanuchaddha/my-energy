package com.bhanuchaddha.myenergy.error.model;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Error {
    private Integer status;
    private String errorCode;
    private String description;
}
