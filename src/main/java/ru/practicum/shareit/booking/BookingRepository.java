package ru.practicum.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.BookingStatus;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByItemOwnerAndStartAfterOrderByStartDesc(User booker, LocalDateTime now);

    List<Booking> findByItemOwnerAndEndAfterAndStartBeforeOrderByStartDesc(User booker, LocalDateTime now1, LocalDateTime now2);

    List<Booking> findByItemOwnerAndStatusEqualsOrderByStartDesc(User booker, BookingStatus status);

    List<Booking> findByBookerOrderByStartDesc(User booker);

    List<Booking> findByItemOwnerOrderByStartDesc(User owner);

    List<Booking> findByItemOwnerAndEndBeforeOrderByStartDesc(User savedUser, LocalDateTime now);

    List<Booking> findByBookerAndEndAfterAndStartBeforeOrderByStartDesc(User savedUser, LocalDateTime now, LocalDateTime now1);

    List<Booking> findByBookerAndEndBeforeOrderByStartDesc(User savedUser, LocalDateTime now);

    List<Booking> findByBookerAndStartAfterOrderByStartDesc(User savedUser, LocalDateTime now);

    List<Booking> findByBookerAndStatusEqualsOrderByStartDesc(User savedUser, BookingStatus bookingStatus);

    List<Booking> findByItemIdAndStatusEqualsOrderByEndDesc(Long id, BookingStatus status);

    Optional<Booking> findByBookerAndItem(User user, Item item);

    Optional<Booking> findTop1BookingByItem_IdAndStartBeforeAndStatusOrderByEndDesc(Long id, LocalDateTime now, BookingStatus bookingStatus);

    Optional<Booking> findTop1BookingByItem_IdAndStartAfterAndStatusOrderByEndAsc(Long id, LocalDateTime now, BookingStatus bookingStatus);
}
