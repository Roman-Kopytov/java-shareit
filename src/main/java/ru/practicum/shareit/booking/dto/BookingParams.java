package ru.practicum.shareit.booking.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingParams {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Item item;
    private Long userId;
    private Boolean approved;
}
