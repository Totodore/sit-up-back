package com.situp.backend.backend.dto;

import lombok.Data;

@Data
public class AddMessageDto {
    private String message;
    private Long receiverId;
}
