package br.com.clinicamedica.Clinica.service;

import br.com.clinicamedica.Clinica.dto.SecretariaDto;
import br.com.clinicamedica.Clinica.exception.ResourceNotFoundException;
import br.com.clinicamedica.Clinica.mapper.CustomModelMapper;
import br.com.clinicamedica.Clinica.model.SecretariaModel;
import br.com.clinicamedica.Clinica.repository.SecretariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretariaService {

    @Autowired
    private SecretariaRepository secretariaRepository;

    public SecretariaDto create(SecretariaDto secretariaDto) {
        SecretariaModel secretariaModel = CustomModelMapper.parseObject(secretariaDto, SecretariaModel.class);
        return CustomModelMapper.parseObject(secretariaRepository.save(secretariaModel), SecretariaDto.class);
    }

    public SecretariaDto findById(long id) {
        SecretariaModel secretariaModel = secretariaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Secretaria não encontrada!"));
        return CustomModelMapper.parseObject(secretariaModel, SecretariaDto.class);
    }

    public List<SecretariaDto> findAll() {
        var secretariaList = secretariaRepository.findAll();
        return CustomModelMapper.parseObjectList(secretariaList, SecretariaDto.class);
    }

    public SecretariaDto update(SecretariaDto secretariaDto) {
        var secretaria = secretariaRepository.findById(secretariaDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Secretaria não encontrada!"));
        secretaria.setNome(secretariaDto.getNome());
        secretaria.setCpf(secretariaDto.getCpf());
        secretaria.setEmail(secretariaDto.getEmail());
        secretaria.setTelefone(secretariaDto.getTelefone());
        secretaria.setSetor(secretariaDto.getSetor());
        return CustomModelMapper.parseObject(secretariaRepository.save(secretaria), SecretariaDto.class);
    }

    public void delete(long id) {
        var secretaria = secretariaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Secretaria não encontrada!"));
        secretariaRepository.delete(secretaria);
    }
}
