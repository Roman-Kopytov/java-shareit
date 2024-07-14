package ru.practicum.shareit.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.marker.Marker;


@Data
@RequiredArgsConstructor
public class User {
    private Long id;
    private String name;
    @Email
    @NotBlank(groups = Marker.Create.class)
    private String email;

}
