package ru.practicum.shareit.booking;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.BookingStatus;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.marker.Marker;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Booking {
    @NotNull(groups = Marker.Update.class)
    private Long id;
    @NonNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;
    @NotNull
    private Item item;
    @NotNull
    private User booker;
    private BookingStatus status;

}
