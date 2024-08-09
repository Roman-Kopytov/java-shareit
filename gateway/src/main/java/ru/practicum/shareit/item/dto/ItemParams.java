package ru.practicum.shareit.item.dto;

import lombok.Data;

@Data
public class ItemParams {

    Long owner;
    String name;
    String description;
    Boolean available;
}
