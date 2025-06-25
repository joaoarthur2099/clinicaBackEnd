package br.com.clinicamedica.Clinica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ConsultaDto {

    private long id;
    private LocalDate dia;
    private LocalTime hora;
    private BigDecimal preco;
    private long idMedicoResponsavel;
    private long idPrescricaoMedica;

    @Override
    public String toString() {
        return "ConsultaDto{" +
                "id=" + id +
                ", dia=" + dia +
                ", hora=" + hora +
                ", preco=" + preco +
                ", idMedicoResponsavel=" + idMedicoResponsavel +
                ", idPrescricaoMedica=" + idPrescricaoMedica +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public long getIdMedicoResponsavel() {
        return idMedicoResponsavel;
    }

    public void setIdMedicoResponsavel(long idMedicoResponsavel) {
        this.idMedicoResponsavel = idMedicoResponsavel;
    }

    public long getIdPrescricaoMedica() {
        return idPrescricaoMedica;
    }

    public void setIdPrescricaoMedica(long idPrescricaoMedica) {
        this.idPrescricaoMedica = idPrescricaoMedica;
    }
}
