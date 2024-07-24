package ru.practicum.shareit.item.model;

import lombok.*;
import ru.practicum.shareit.request.ItemRequest;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private Long id;
    private Long owner;
    private String name;
    private String description;
    private Boolean available;
    private ItemRequest request;
}
