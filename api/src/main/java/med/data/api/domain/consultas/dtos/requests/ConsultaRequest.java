package med.data.api.domain.consultas.dtos.requests;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.data.api.domain.medico.enums.Especialidade;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaRequest(

        UUID idMedico,
        @NotNull
        UUID idPaciente,
        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade) {

}
