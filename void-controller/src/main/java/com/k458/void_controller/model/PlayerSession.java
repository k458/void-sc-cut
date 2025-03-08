package com.k458.void_controller.model;

import com.k458.void_controller.config.Session;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class PlayerSession {
    private Long userId;
    private LocalDateTime expireTime = LocalDateTime.now();

    private List<ItemEntity> items;
}
