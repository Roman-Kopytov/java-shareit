package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.request.ItemRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {


    private Long id;
    private Long owner;
    @Size(max = 100)
    @NotBlank
    private String name;
    @Size(max = 200)
    private String description;
    private Boolean available;
    private ItemRequest request;
}
