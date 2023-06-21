package med.data.api.domain.consultas.dtos.response;

import lombok.Builder;
import med.data.api.domain.consultas.model.Consulta;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ConsultaResponse(
        UUID idConsulta,
        UUID idMedico,
        UUID idPaciente,
        LocalDateTime data) {

    public static ConsultaResponse of(Consulta consulta) {
        return ConsultaResponse.builder()
                .idConsulta(consulta.getId())
                .idMedico(consulta.getMedico().getId())
                .idPaciente(consulta.getPaciente().getId())
                .data(consulta.getData())
                .build();
    }

}
