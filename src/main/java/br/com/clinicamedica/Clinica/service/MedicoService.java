package br.com.clinicamedica.Clinica.service;

import br.com.clinicamedica.Clinica.dto.MedicoDto;
import br.com.clinicamedica.Clinica.exception.ResourceNotFoundException;
import br.com.clinicamedica.Clinica.mapper.CustomModelMapper;
import br.com.clinicamedica.Clinica.model.MedicoModel;
import br.com.clinicamedica.Clinica.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public MedicoDto create(MedicoDto medicoDto) {
        MedicoModel medicoModel = CustomModelMapper.parseObject(medicoDto, MedicoModel.class);
        return CustomModelMapper.parseObject(medicoRepository.save(medicoModel), MedicoDto.class);
    }

    public MedicoDto findById(long id) {
        MedicoModel medicoModel = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado! pelo ID"));
        return CustomModelMapper.parseObject(medicoModel, MedicoDto.class);
    }

    public List<MedicoDto> findAll() {
        var medicoList = medicoRepository.findAll();
        return CustomModelMapper.parseObjectList(medicoList, MedicoDto.class);
    }

    public MedicoDto update(MedicoDto medicoDto) {
        var medico = medicoRepository.findById(medicoDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado!"));
        medico.setNome(medicoDto.getNome());
        medico.setCpf(medicoDto.getCpf());
        medico.setEspecialidade(medicoDto.getEspecialidade());
        medico.setCrm(medicoDto.getCrm());
        medico.setTelefone(medicoDto.getTelefone());
        medico.setEmail(medicoDto.getEmail());
        return CustomModelMapper.parseObject(medicoRepository.save(medico), MedicoDto.class);
    }

    public void delete(long id) {
        var medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado!"));
        medicoRepository.delete(medico);
    }
}
