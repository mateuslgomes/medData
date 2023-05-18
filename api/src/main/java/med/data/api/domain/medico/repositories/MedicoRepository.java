package med.data.api.domain.medico.repositories;

import med.data.api.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

}
