package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.BookingStatus;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.BookingRepository;
import ru.practicum.shareit.booking.dto.BookingMapper;
import ru.practicum.shareit.comment.Comment;
import ru.practicum.shareit.comment.CommentRepository;
import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.comment.dto.CommentMapper;
import ru.practicum.shareit.comment.dto.CommentParams;
import ru.practicum.shareit.exception.AccessDeniedException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.*;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GeneralItemService implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ItemMapper itemMapper;
    private final BookingRepository bookingRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final BookingMapper bookingMapper;

    @Override
    public ItemDto getById(long itemId, long userId) {
        Item savedItem = getItemFromRepository(itemId);
        ItemDto itemDTO = itemMapper.mapToDto(savedItem);
        List<CommentDto> comments = commentRepository.findByItem(savedItem).stream()
                .map(commentMapper::mapToCommentDto).toList();
        itemDTO.setComments(comments);
        if (userId != savedItem.getOwner().getId()) {
            return itemDTO;
        }

        return setBookings(List.of(itemDTO)).getFirst();
    }

    private List<ItemDto> setBookings(List<ItemDto> items) {
        LocalDateTime now = LocalDateTime.now();
        for (ItemDto item : items) {
            Optional<Booking> lastBooking = bookingRepository.findTop1BookingByItem_IdAndStartBeforeAndStatusOrderByEndDesc(
                    item.getId(), now, BookingStatus.APPROVED);
            lastBooking.ifPresent(booking -> item.setLastBooking(bookingMapper.mapToBookingInfo(booking)));

            Optional<Booking> nextBooking = bookingRepository.findTop1BookingByItem_IdAndStartAfterAndStatusOrderByEndAsc(
                    item.getId(), now, BookingStatus.APPROVED);
            nextBooking.ifPresent(booking -> item.setNextBooking(bookingMapper.mapToBookingInfo(booking)));
        }
        return items;
    }


    @Override
    public List<ItemDto> getAllOwnerItems(long userId) {
        User user = getUserFromRepository(userId);
        List<Item> items = itemRepository.findByOwner(user);
        List<Long> itemIds = items.stream()
                .map(Item::getId)
                .toList();
        Map<Long, List<Booking>> bookingsGroupedByItemId = itemIds.stream()
                .collect(Collectors.toMap(
                        id -> id,
                        id -> bookingRepository.findByItemIdAndStatusEqualsOrderByEndDesc(id, BookingStatus.APPROVED)
                ));
        List<ItemDto> dtos = items.stream().map(itemMapper::mapToDto).toList();

        dtos.stream()
                .peek(dto -> {
                    List<Comment> comments = commentRepository.findByItem_Id(dto.getId());
                    List<CommentDto> commentDtos = comments.stream().map(commentMapper::mapToCommentDto).toList();
                    dto.setComments(commentDtos);
                })
                .toList();
        return dtos;
    }

    @Transactional
    @Override
    public ItemShortDto create(ItemCreateDto itemCreateDto, long ownerId) {
        User owner = getUserFromRepository(ownerId);
        Item item = itemMapper.mapToItem(itemCreateDto);
        item.setOwner(owner);
        return itemMapper.mapToShortDto(itemRepository.save(item));
    }

    @Transactional
    @Override
    public ItemDto update(ItemUpdateDTO itemCreateDto, long ownerId) {
        User owner = getUserFromRepository(ownerId);
        Item savedItem = getItemFromRepository(itemCreateDto.getId());
        if (!savedItem.getOwner().equals(owner)) {
            throw new AccessDeniedException("Incorrect owner");
        }
        if (itemCreateDto.getName() != null) {
            savedItem.setName(itemCreateDto.getName());
        }
        if (itemCreateDto.getDescription() != null) {
            savedItem.setDescription(itemCreateDto.getDescription());
        }
        if (itemCreateDto.getAvailable() != null) {
            savedItem.setAvailable(itemCreateDto.getAvailable());
        }
        return itemMapper.mapToDto(itemRepository.save(savedItem));
    }

    @Transactional
    @Override
    public void deleteItemById(long id) {
    }

    @Override
    public List<ItemDto> search(String text) {
        return itemRepository.searchByNameOrDescription(text).stream()
                .map(itemMapper::mapToDto)
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public CommentDto createComment(CommentParams params) {
        Item savedItem = getItemFromRepository(params.getItemId());
        User savedUser = getUserFromRepository(params.getAuthorId());
        Booking booking = getBookingFromRepository(savedUser, savedItem);
        if (!booking.getStatus().equals(BookingStatus.APPROVED) || booking.getEnd().isAfter(LocalDateTime.now())) {
            throw new AccessDeniedException("Booking not ended");
        }
        params.setCreated(LocalDateTime.now());
        Comment comment = commentMapper.mapToComment(params, savedUser, savedItem);
        return commentMapper.mapToCommentDto(commentRepository.save(comment));
    }


    private Item getItemFromRepository(long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item not found with id: " + itemId));
    }

    private User getUserFromRepository(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

    }

    private Booking getBookingFromRepository(User booker, Item item) {
        return bookingRepository.findByBookerAndItem(booker, item)
                .orElseThrow(() -> new NotFoundException("Booking item with id " + item.getId() +
                        " for user with id: " + booker.getId() + " not found"));

    }
}
