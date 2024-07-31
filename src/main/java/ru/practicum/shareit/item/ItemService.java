package ru.practicum.shareit.item;

import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.comment.dto.CommentParams;
import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemShortDto;
import ru.practicum.shareit.item.dto.ItemUpdateDTO;

import java.util.List;


public interface ItemService {

    ItemDto getById(long itemId, long userId);

    ItemShortDto create(ItemCreateDto item, long ownerId);

    ItemDto update(ItemUpdateDTO item, long ownerId);

    void deleteItemById(long id);

    List<ItemDto> search(String text);

    List<ItemDto> getAllOwnerItems(long userId);

    CommentDto createComment(CommentParams params);
}
