package br.com.clinicamedica.Clinica.controller;

import br.com.clinicamedica.Clinica.dto.PacienteDto;
import br.com.clinicamedica.Clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/")
    public ResponseEntity<List<PacienteDto>> findAll() {
        var pacientes = pacienteService.findAll();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> findById(@PathVariable(name = "id") long id) {
        var paciente = pacienteService.findById(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PacienteDto> create(@RequestBody PacienteDto pacienteDto) {
        var paciente = pacienteService.create(pacienteDto);
        return new ResponseEntity<>(paciente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> update(@PathVariable long id, @RequestBody PacienteDto pacienteDto) {
        pacienteDto.setId(id);
        var paciente = pacienteService.update(pacienteDto);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        pacienteService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
