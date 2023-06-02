package med.data.api.domain.medico.repositories;

import med.data.api.domain.medico.Medico;
import med.data.api.domain.medico.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
                select m from Medico m
                where
                m.ativo = 1
                and
                m.especialidade = :especialidade
                and
                m.id not in(
                        select c.medico.id from Consulta c
                        where
                        c.data = :data
                )
                order by rand()
                limit 1
                """)
    Medico buscarMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    boolean existsByMedicoIdAndData(UUID uuid, LocalDateTime data);

    boolean findAtivoById(UUID uuid);

}
