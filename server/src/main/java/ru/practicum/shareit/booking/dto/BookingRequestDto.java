package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingRequestDto {

    private LocalDateTime start;
    private LocalDateTime end;
    private Long itemId;
    private User booker;
}
