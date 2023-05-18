package med.data.api.repositories;

import med.data.api.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicoRepository extends JpaRepository<Medico, UUID> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

}
