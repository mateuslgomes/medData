package med.data.api.domain.paciente.Repositories;

import med.data.api.domain.paciente.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
}
