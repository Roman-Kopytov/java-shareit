package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;


public interface ItemService {

    ItemDto getById(long itemId);

    ItemDto create(ItemDto itemDto, long ownerId);

    ItemDto update(ItemDto itemDto, long ownerId);

    void deleteItemById(long id);

    List<ItemDto> search(String text);

    List<ItemDto> getAllUserItems(long userId);
}
