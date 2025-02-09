package com.riwi.examen_Rigo.api.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.examen_Rigo.api.dto.errors.ErrorResponse;
import com.riwi.examen_Rigo.api.dto.request.UserReq;
import com.riwi.examen_Rigo.api.dto.response.UserResp;
import com.riwi.examen_Rigo.infrastructure.adtract_services.IUserService;
import com.riwi.examen_Rigo.util.enums.SortType;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/class")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @ApiResponse(responseCode = "400", description = "No fue posble obtener la informacion", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    @GetMapping
    public ResponseEntity<Page<UserResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.userService.getAll(page - 1, size, sortType));
    }

    @ApiResponse(responseCode = "400", description = "No fue posible enviar la informacion con el id suministrado", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.userService.get(id));
    }


    @ApiResponse(responseCode = "400", description = "No fue posible enviar la informacion", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    @PostMapping
    public ResponseEntity<UserResp> insert(
            @Validated @RequestBody UserReq request) {
        return ResponseEntity.ok(this.userService.create(request));
    }

    @ApiResponse(responseCode = "400", description = "No fue posible actualizar la informacion", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResp> update(
            @Validated @RequestBody UserReq request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.userService.update(request, id));
    }
}
