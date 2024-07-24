package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.marker.Marker;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
@Validated
public class ItemController {

    private static final String USER_ID = "X-Sharer-User-Id";
    private final ItemService itemService;

    @GetMapping("/{userId}")
    @Validated
    public ItemDto getById(@PathVariable long userId) {
        log.info("GET ==> /items");
        ItemDto saveItem = itemService.getById(userId);
        log.info("<== GET /items", saveItem);
        return saveItem;
    }

    @GetMapping
    @Validated
    public List<ItemDto> getAllUserItem(@RequestHeader(USER_ID) long ownerId) {
        log.info("GET ==> /items");
        List<ItemDto> saveItems = itemService.getAllUserItems(ownerId);
        log.info("<== GET /items", saveItems);
        return saveItems;
    }

    @PatchMapping("/{itemId}")
    @Validated({Marker.Update.class})
    public ItemDto update(@Valid @RequestBody ItemDto item,
                          @PathVariable long itemId,
                          @NotNull @RequestHeader(USER_ID) long ownerId) {
        item.setId(itemId);
        log.info("PATCH ==> /items {}", item);
        ItemDto updateItem = itemService.update(item, ownerId);
        log.info("<== PATCH /items {}", updateItem);
        return updateItem;
    }

    @PostMapping
    @Validated({Marker.Create.class})
    public ItemDto create(@Valid @RequestBody ItemDto item,
                          @RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("==>POST /items {}", item);
        ItemDto createdItem = itemService.create(item, ownerId);
        log.info("POST /items <== {}", item);
        return createdItem;
    }

    @DeleteMapping("/{id}")
    @Validated
    public void delete(@PathVariable("id") @Min(0) long id) {
        log.info("==>DELETE /items {}", id);
        itemService.deleteItemById(id);
    }

    @GetMapping("/search")
    @Validated
    public List<ItemDto> search(@RequestParam(name = "text") String text) {
        return itemService.search(text);
    }
}
