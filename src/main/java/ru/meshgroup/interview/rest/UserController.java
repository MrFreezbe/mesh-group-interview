package ru.meshgroup.interview.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.meshgroup.interview.mapper.UserMapper;
import ru.meshgroup.interview.model.UserDto;
import ru.meshgroup.interview.service.UserService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@OpenAPIDefinition(info = @Info(title = "User API", version = "1.0"))
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    @Operation(summary = "Search users by criteria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved users",
                            content = @Content(schema = @Schema(implementation = Page.class))),
            })
    @GetMapping
    public Page<UserDto> searchUsers(
            @Parameter(description = "Filter by date of birth")
            @RequestParam(value = "dateOfBirth", required = false) LocalDate dateOfBirth,
            @Parameter(description = "Filter by phone")
            @RequestParam(value = "phone", required = false) String phone,
            @Parameter(description = "Filter by name")
            @RequestParam(value = "name", required = false) String name,
            @Parameter(description = "Filter by email")
            @RequestParam(value = "email", required = false) String email,
            @Parameter(description = "Pagination information", required = true) Pageable pageable) {
        return userService.searchUsers(dateOfBirth, phone, name, email, pageable).map(mapper::toDto);
    }

    @Operation(summary = "Update user by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated",
                            content = @Content(schema = @Schema(implementation = UserDto.class))),
            })
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @Parameter(description = "Id of the user", required = true) @PathVariable Long userId,
            @RequestBody @Valid @Parameter(description = "User data", required = true) UserDto userDTO) {
        UserDto updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }
}
