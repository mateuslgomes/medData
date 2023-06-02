package med.data.api.domain.consultas.dtos;


import lombok.Builder;
import med.data.api.domain.consultas.model.Consulta;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
public record DetalhamentoConsultaDto(
        UUID idConsulta,
        UUID idMedico,
        UUID idPaciente,
        LocalDateTime data) {

    public static DetalhamentoConsultaDto of(Consulta consulta) {
        return DetalhamentoConsultaDto.builder()
                .idConsulta(consulta.getId())
                .idMedico(consulta.getMedico().getId())
                .idPaciente(consulta.getPaciente().getId())
                .data(consulta.getData())
                .build();
    }

}
