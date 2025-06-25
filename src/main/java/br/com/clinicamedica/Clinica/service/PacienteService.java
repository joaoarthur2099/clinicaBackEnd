package br.com.clinicamedica.Clinica.service;

import br.com.clinicamedica.Clinica.dto.PacienteDto;
import br.com.clinicamedica.Clinica.exception.ResourceNotFoundException;
import br.com.clinicamedica.Clinica.mapper.CustomModelMapper;
import br.com.clinicamedica.Clinica.model.PacienteModel;
import br.com.clinicamedica.Clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteDto create(PacienteDto pacienteDto) {
        PacienteModel pacienteModel = CustomModelMapper.parseObject(pacienteDto, PacienteModel.class);
        return CustomModelMapper.parseObject(pacienteRepository.save(pacienteModel), PacienteDto.class);
    }

    public PacienteDto findById(long id) {
        PacienteModel pacienteModel = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado!"));
        return CustomModelMapper.parseObject(pacienteModel, PacienteDto.class);
    }

    public List<PacienteDto> findAll() {
        var pacienteList = pacienteRepository.findAll();
        return CustomModelMapper.parseObjectList(pacienteList, PacienteDto.class);
    }

    public PacienteDto update(PacienteDto pacienteDto) {
        var paciente = pacienteRepository.findById(pacienteDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado!"));
        paciente.setNome(pacienteDto.getNome());
        paciente.setCpf(pacienteDto.getCpf());
        paciente.setConvenio(pacienteDto.getConvenio());
        paciente.setResidencia(pacienteDto.getResidencia());
        paciente.setNascimento(pacienteDto.getNascimento());
        paciente.setTelefone(pacienteDto.getTelefone());
        return CustomModelMapper.parseObject(pacienteRepository.save(paciente), PacienteDto.class);
    }

    public void delete(long id) {
        var paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado!"));
        pacienteRepository.delete(paciente);
    }
}
