package med.data.api.domain.consultas.repositories;

import med.data.api.domain.consultas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {

    boolean existSByPacienteIdAndDataBetween(UUID uuid, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

}
