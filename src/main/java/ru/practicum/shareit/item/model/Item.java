package ru.practicum.shareit.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.request.ItemRequest;


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
