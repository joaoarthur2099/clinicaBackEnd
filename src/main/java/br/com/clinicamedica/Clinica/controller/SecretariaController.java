package br.com.clinicamedica.Clinica.controller;

import br.com.clinicamedica.Clinica.dto.SecretariaDto;
import br.com.clinicamedica.Clinica.service.SecretariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretarias")
public class SecretariaController {

    @Autowired
    private SecretariaService secretariaService;

    @GetMapping("/")
    public ResponseEntity<List<SecretariaDto>> findAll() {
        var secretarias = secretariaService.findAll();
        return new ResponseEntity<>(secretarias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecretariaDto> findById(@PathVariable(name = "id") long id) {
        var secretaria = secretariaService.findById(id);
        return new ResponseEntity<>(secretaria, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SecretariaDto> create(@RequestBody SecretariaDto secretariaDto) {
        var secretaria = secretariaService.create(secretariaDto);
        return new ResponseEntity<>(secretaria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SecretariaDto> update(@PathVariable(name = "id") long id,
                                                @RequestBody SecretariaDto secretariaDto) {
        secretariaDto.setId(id);
        var secretaria = secretariaService.update(secretariaDto);
        return new ResponseEntity<>(secretaria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        secretariaService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
