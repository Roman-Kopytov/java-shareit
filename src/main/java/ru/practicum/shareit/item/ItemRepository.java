package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Optional<Item> getItemById(long itemId);

    Item create(Item item);

    List<Item> getAllUserItems(long userId);

    List<Item> search(String text);

    Item update(Item item);
}
