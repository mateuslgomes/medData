package med.data.api.domain.consultas.dtos;


import java.time.LocalDateTime;
import java.util.UUID;


public record DetalhamentoConsultaDto(
        UUID idConsulta,
        UUID idMedico,
        UUID idPaciente,
        LocalDateTime data) {

}
