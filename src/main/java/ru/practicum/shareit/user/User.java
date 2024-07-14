package ru.practicum.shareit.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.marker.Marker;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@RequiredArgsConstructor
public class User {
    private Long id;
    private String name;
    @Email
    @NotBlank(groups = Marker.Create.class)
    private String email;

}
