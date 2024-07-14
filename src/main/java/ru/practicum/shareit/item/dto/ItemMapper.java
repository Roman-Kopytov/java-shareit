package ru.practicum.shareit.item.dto;

import ru.practicum.shareit.item.model.Item;

public class ItemMapper {
    public static ItemDto mapToItemDto(Item item) {
        return ItemDto.builder().id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .request(item.getRequest() != null ? item.getRequest() : null)
                .build();
    }

    public static Item mapToItem(ItemDto item, long owner) {
        return Item.builder()
                .id(item.getId())
                .name(item.getName())
                .owner(owner)
                .description(item.getDescription())
                .available(item.getAvailable())
                .request(item.getRequest() != null ? item.getRequest() : null)
                .build();
    }
}

