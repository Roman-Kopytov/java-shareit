package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.marker.Marker;
import ru.practicum.shareit.request.ItemRequest;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
