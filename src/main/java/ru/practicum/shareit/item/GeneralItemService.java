package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.GeneralUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneralItemService implements ItemService {
    private final ItemRepository itemRepository;
    private final GeneralUserService userService;

    @Override
    public ItemDto getById(long itemId) {
        return ItemMapper.mapToItemDto(getItemFromRepository(itemId));
    }

    @Override
    public ItemDto create(ItemDto itemDto, long ownerId) {
        userService.getById(ownerId);
        Item item = ItemMapper.mapToItem(itemDto, ownerId);
        return ItemMapper.mapToItemDto(itemRepository.create(item));
    }

    @Override
    public ItemDto update(ItemDto itemDto, long ownerId) {
        userService.getById(ownerId);
        Item item = ItemMapper.mapToItem(itemDto, ownerId);

        return ItemMapper.mapToItemDto(itemRepository.update(item));
    }

    @Override
    public void deleteItemById(long id) {
    }

    @Override
    public List<ItemDto> search(String text) {
        return itemRepository.search(text).stream()
                .map(ItemMapper::mapToItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getAllUserItems(long userId) {
        return itemRepository.getAllUserItems(userId).stream()
                .map(ItemMapper::mapToItemDto)
                .collect(Collectors.toList());
    }


    private Item getItemFromRepository(long itemId) {
        return itemRepository.getItemById(itemId)
                .orElseThrow(() -> new NotFoundException("Item not found with id: " + itemId));
    }
}
