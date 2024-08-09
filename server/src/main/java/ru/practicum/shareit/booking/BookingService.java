package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.BookingFullDTO;
import ru.practicum.shareit.booking.dto.BookingParams;
import ru.practicum.shareit.booking.dto.BookingRequestDto;

import java.util.List;

public interface BookingService {

    BookingFullDTO create(BookingRequestDto booking, long bookerId);

    BookingFullDTO approved(BookingParams params);

    BookingFullDTO getById(BookingParams params);

    List<BookingFullDTO> getUserBookings(BookingState state, Long userId);

    List<BookingFullDTO> getOwnerBookings(BookingState state, Long userId);
}
