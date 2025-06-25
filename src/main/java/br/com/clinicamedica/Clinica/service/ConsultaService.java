package br.com.clinicamedica.Clinica.service;

import br.com.clinicamedica.Clinica.dto.ConsultaDto;
import br.com.clinicamedica.Clinica.exception.ResourceNotFoundException;
import br.com.clinicamedica.Clinica.mapper.CustomModelMapper;
import br.com.clinicamedica.Clinica.model.ConsultaModel;
import br.com.clinicamedica.Clinica.repository.ConsultaRepository;
import br.com.clinicamedica.Clinica.repository.MedicoRepository;
import br.com.clinicamedica.Clinica.repository.PacienteRepository;
import br.com.clinicamedica.Clinica.repository.PrescricaoMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PrescricaoMedicaRepository prescricaoMedicaRepository;

    public ConsultaDto create(ConsultaDto consultaDto) {
        ConsultaModel consultaModel = new ConsultaModel();
        consultaModel.setDia(consultaDto.getDia());
        consultaModel.setHora(consultaDto.getHora());
        consultaModel.setPreco(consultaDto.getPreco());
        consultaModel.setMedicoResponsavel(medicoRepository.findById(consultaDto.getIdMedicoResponsavel())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado!-#1")));

        consultaModel.setPrescricaoMedica(prescricaoMedicaRepository.findById(consultaDto.getIdPrescricaoMedica())
                .orElseThrow(() -> new ResourceNotFoundException("Prescrição Médica não encontrada!")));
        return CustomModelMapper.parseObject(consultaRepository.save(consultaModel), ConsultaDto.class);
    }
    public ConsultaDto findById(long id) {
        ConsultaModel consultaModel = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada!"));
        return CustomModelMapper.parseObject(consultaModel, ConsultaDto.class);
    }
    public List<ConsultaDto> findAll() {
        var consultaList = consultaRepository.findAll();
        return CustomModelMapper.parseObjectList(consultaList, ConsultaDto.class);
    }
    public ConsultaDto update(ConsultaDto consultaDto) {
        ConsultaModel consultaModel = consultaRepository.findById(consultaDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada!-#2"));

        consultaModel.setDia(consultaDto.getDia());
        consultaModel.setHora(consultaDto.getHora());
        consultaModel.setPreco(consultaDto.getPreco());
        consultaModel.setMedicoResponsavel(medicoRepository.findById(consultaDto.getIdMedicoResponsavel())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado!-#3")));

        consultaModel.setPrescricaoMedica(prescricaoMedicaRepository.findById(consultaDto.getIdPrescricaoMedica())
                .orElseThrow(() -> new ResourceNotFoundException("Prescrição Médica não encontrada!")));
        return CustomModelMapper.parseObject(consultaRepository.save(consultaModel), ConsultaDto.class);
    }
    public void delete(long id) {
        ConsultaModel consultaModel = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada!"));
        consultaRepository.delete(consultaModel);
    }
}
