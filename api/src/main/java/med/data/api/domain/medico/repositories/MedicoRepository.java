package med.data.api.domain.medico.repositories;

import med.data.api.domain.medico.Medico;
import med.data.api.domain.medico.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query(value = "SELECT m " +
            "FROM Medico m " +
            "WHERE m.ativo = true " +
            "AND m.especialidade = :especialidade " +
            "AND m.id NOT IN (" +
            "  SELECT c.medico.id " +
            "  FROM Consulta c " +
            "  WHERE c.data = :data" +
            ") " +
            "ORDER BY FUNCTION('RAND') " +
            "LIMIT 1")
    Medico buscarMedicoAleatorioLivreNaData(@Param("especialidade") Especialidade especialidade, @Param("data") LocalDateTime data);


    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Medico m WHERE m.id = :id AND m.ativo = true")
    boolean findAtivoById(@Param("id") UUID id);

}
