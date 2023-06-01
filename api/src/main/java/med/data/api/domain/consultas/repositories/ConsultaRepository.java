package med.data.api.domain.consultas.repositories;

import med.data.api.domain.consultas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {}
