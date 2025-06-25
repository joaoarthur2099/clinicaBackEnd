package br.com.clinicamedica.Clinica.controller;

import br.com.clinicamedica.Clinica.dto.MedicoDto;
import br.com.clinicamedica.Clinica.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/")
    public ResponseEntity<List<MedicoDto>> findAll() {
        var medicos = medicoService.findAll();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDto> findById(@PathVariable(name = "id") long id) {
        var medico = medicoService.findById(id);
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicoDto> create(@RequestBody MedicoDto medicoDto) {
        var medico = medicoService.create(medicoDto);
        return new ResponseEntity<>(medico, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MedicoDto> update(@RequestBody MedicoDto medicoDto) {
        var medico = medicoService.update(medicoDto);
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        medicoService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
