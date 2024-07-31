package ru.practicum.shareit.item.dto;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;

@Component
public class ItemMapper {
    public ItemDto mapToDto(Item item) {
        return ItemDto.builder().id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .request(item.getRequest() != null ? item.getRequest() : null)
                .build();
    }

    public ItemShortDto mapToShortDto(Item item) {
        return ItemShortDto.builder().id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .request(item.getRequest() != null ? item.getRequest() : null)
                .build();
    }


//    public ItemShortDto mapToOwnerDto(Item item, List<Comment> comments, Booking lastBooking, Booking nextBooking) {
//
//        return ItemShortDto.builder()
//                .id(item.getId())
//                .name(item.getName())
//                .description(item.getDescription())
//                .available(item.getAvailable())
//                .lastBooking(new ItemShortDto.BookingDto(lastBooking.getId(), lastBooking.getBooker().getId()))
//                .nextBooking(new ItemShortDto.BookingDto(nextBooking.getId(), nextBooking.getBooker().getId()))
//                .comments(comments)
//                .request(item.getRequest() != null ? item.getRequest() : null)
//                .build();
//    }


    public Item mapToItem(ItemCreateDto item) {
        return Item.builder()
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .request(item.getRequest() != null ? item.getRequest() : null)
                .build();
    }
}

