package ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

@Data
public class BookingCreateDTO {

    @NotNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;
    @NotNull
    private Long itemId;

    private User booker;

    @AssertFalse
    public boolean isDateValid() {
        if (start != null && end != null) {
            return (end.isBefore(start) || end.isBefore(LocalDateTime.now()) ||
                    start.isBefore(LocalDateTime.now()) || end.isEqual(start));
        } else {
            return true;
        }
    }

}
