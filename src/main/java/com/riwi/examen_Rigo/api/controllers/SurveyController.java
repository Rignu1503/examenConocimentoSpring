package com.riwi.examen_Rigo.api.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.examen_Rigo.api.dto.errors.ErrorResponse;
import com.riwi.examen_Rigo.api.dto.request.SurveyReq;
import com.riwi.examen_Rigo.api.dto.response.SurveyResp;
import com.riwi.examen_Rigo.infrastructure.adtract_services.ISurveyService;
import com.riwi.examen_Rigo.util.enums.SortType;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/surveys")
@AllArgsConstructor
public class SurveyController {

    private final ISurveyService surveyService;

     
    @ApiResponse(responseCode = "400", description = "No fue posible enviar la informacion", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    @GetMapping
    public ResponseEntity<Page<SurveyResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.surveyService.getAll(page - 1, size, sortType));
    }

    @ApiResponse(responseCode = "400", description = "no se pudo obtener el registro con el id suministrado", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    @GetMapping(path = "/{id}")
    public ResponseEntity<SurveyResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.surveyService.get(id));
    }

    @ApiResponse(responseCode = "400", description = "No fue posible enviar la informacion", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    @PostMapping
    public ResponseEntity<SurveyResp> insert(
            @Validated @RequestBody SurveyReq request) {
        return ResponseEntity.ok(this.surveyService.create(request));
    }


}
