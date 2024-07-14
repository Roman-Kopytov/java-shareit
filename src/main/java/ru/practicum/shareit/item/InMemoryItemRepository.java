package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.AccessDeniedException;
import ru.practicum.shareit.item.model.Item;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class InMemoryItemRepository implements ItemRepository {

    private final HashMap<Long, Item> itemsRepository;
    private final HashMap<Long, Set<Long>> userItems;
    private long seq = 0;

    @Override
    public Optional<Item> getItemById(long itemId) {
        return Optional.ofNullable(itemsRepository.get(itemId));
    }

    @Override
    public Item create(Item item) {
        item.setId(calculateItemId());
        itemsRepository.put(item.getId(), item);
        userItems.computeIfAbsent(item.getOwner(), id -> new HashSet<>()).add(item.getId());
        return item;
    }

    @Override
    public List<Item> getAllUserItems(long userId) {
        return userItems.get(userId).stream()
                .map(itemsRepository::get)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> search(String text) {
        if (text.isEmpty()) {
            return List.of();
        }
        String textLower = text.toLowerCase();
        List<Item> searchedItems = new ArrayList<>();
        for (Item item : itemsRepository.values()) {
            String itemName = item.getName().toLowerCase();
            String itemDescription = item.getDescription().toLowerCase();
            if (item.getAvailable() && (itemName.contains(textLower) || itemDescription.contains(textLower))) {
                searchedItems.add(item);
            }
        }
        return searchedItems;
    }

    @Override
    public Item update(Item item) {
        Item savedItem = itemsRepository.get(item.getId());
        if (item.getOwner().equals(savedItem.getOwner())) {
            savedItem.setOwner(item.getOwner());
        } else {
            throw new AccessDeniedException("Incorrect owner");
        }
        if (item.getName() != null) {
            savedItem.setName(item.getName());
        }
        if (item.getDescription() != null) {
            savedItem.setDescription(item.getDescription());
        }
        if (item.getAvailable() != null) {
            savedItem.setAvailable(item.getAvailable());
        }
        if (item.getAvailable() != null) {
            savedItem.setAvailable(item.getAvailable());
        }

        itemsRepository.put(item.getId(), savedItem);
        return savedItem;
    }

    private long calculateItemId() {
        return ++seq;
    }
}
