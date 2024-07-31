package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.request.ItemRequest;

import java.util.List;

@Data
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private ItemRequest request;
    private Booking lastBooking;
    private Booking nextBooking;
    private List<CommentDto> comments;
}
