package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.marker.Marker;
import ru.practicum.shareit.request.ItemRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class ItemDto {
    private Long id;
    @Size(max = 100)
    @NotBlank(groups = Marker.Create.class)
    private String name;
    @Size(max = 200)
    @NotBlank(groups = Marker.Create.class)
    private String description;
    @NotNull(groups = Marker.Create.class)
    private Boolean available;
    private ItemRequest request;
}
