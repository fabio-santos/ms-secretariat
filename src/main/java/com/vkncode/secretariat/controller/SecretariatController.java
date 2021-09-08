package com.vkncode.secretariat.controller;

import com.vkncode.secretariat.domain.dto.SecretariatDTO;
import com.vkncode.secretariat.domain.dto.UnderInvestigationDTO;
import com.vkncode.secretariat.domain.entity.Secretariat;
import com.vkncode.secretariat.domain.services.SecretariatService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/secretariat")
@AllArgsConstructor
@Api(value = "Secretariat", tags = { "Secretariat" })
public class SecretariatController {

    private SecretariatService service;

    @GetMapping
    public List<Secretariat> getList() {
        return service.get();
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiParam(
            name =  "id",
            type = "Long",
            value = "Secretariat id",
            example = "2",
            required = true)
    @GetMapping("/{id}")
    public Secretariat getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add Secretariat",
            notes = "This method adds a new secretariat")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Secretariat added"),
            @ApiResponse(code = 500, message = "Internal Error"),
    })
    @PostMapping
    public Secretariat create(@Valid @RequestBody SecretariatDTO secretariatDTO) {
        return service.save(secretariatDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update secretariat",
            notes = "This method updated a secretariat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Secretariat updated"),
            @ApiResponse(code = 500, message = "Internal Error"),
    })
    @PutMapping("/{id}")
    public Secretariat update(@Valid @RequestBody SecretariatDTO secretariatDTO, @PathVariable Long id) {
        secretariatDTO.setId(id);
        return service.save(secretariatDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remove secretariat",
            notes = "This method removes a secretariat")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Secretariat updated"),
            @ApiResponse(code = 500, message = "Internal Error"),
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Updated secretariat investigation",
            notes = "This method updates a secretariat's investigation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Secretariat updated"),
            @ApiResponse(code = 500, message = "Internal Error"),
    })
    @PatchMapping("/{id}/investigation")
    public Secretariat expense(@Valid @RequestBody UnderInvestigationDTO underInvestigationDTO, @PathVariable Long id) {
        return service.saveUnderInvestigation(underInvestigationDTO, id);
    }
}
