package med.data.api.domain.consultas.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.data.api.domain.medico.enums.Especialidade;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoConsultaDto(

        UUID idMedico,

        @NotNull
        UUID idPaciente,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        Especialidade especialidade) {

}
