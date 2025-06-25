package br.com.clinicamedica.Clinica.controller;

import br.com.clinicamedica.Clinica.dto.ConsultaDto;
import br.com.clinicamedica.Clinica.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Operation(summary = "Listar todas as consultas", description = "Retorna uma lista de todas as consultas agendadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de consultas retornada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/")
    public ResponseEntity<List<ConsultaDto>> findAll() {
        var consultas = consultaService.findAll();
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @Operation(summary = "Buscar consulta por ID", description = "Retorna uma consulta específica com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta encontrada."),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDto> findById(
            @Parameter(description = "ID da consulta a ser buscada", required = true)
            @PathVariable(name = "id") long id) {
        var consulta = consultaService.findById(id);
        return new ResponseEntity<>(consulta, HttpStatus.OK);
    }

    @Operation(summary = "Criar nova consulta", description = "Cria uma nova consulta com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consulta criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos.")
    })
    @PostMapping
    public ResponseEntity<ConsultaDto> create(
            @Parameter(description = "Dados da consulta a ser criada", required = true)
            @RequestBody ConsultaDto consultaDto) {
        var consulta = consultaService.create(consultaDto);
        return new ResponseEntity<>(consulta, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar consulta", description = "Atualiza uma consulta existente com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos.")
    })
    @PutMapping
    public ResponseEntity<ConsultaDto> update(
            @Parameter(description = "Dados da consulta a ser atualizada", required = true)
            @RequestBody ConsultaDto consultaDto) {
        var consulta = consultaService.update(consultaDto);
        return new ResponseEntity<>(consulta, HttpStatus.OK);
    }

    @Operation(summary = "Deletar consulta", description = "Remove uma consulta existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Consulta deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @Parameter(description = "ID da consulta a ser deletada", required = true)
            @PathVariable(name = "id") long id) {
        consultaService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
