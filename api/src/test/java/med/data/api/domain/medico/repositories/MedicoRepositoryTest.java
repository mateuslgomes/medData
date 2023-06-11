package med.data.api.domain.medico.repositories;

import med.data.api.domain.consultas.model.Consulta;
import med.data.api.domain.endereco.Endereco;
import med.data.api.domain.medico.Medico;
import med.data.api.domain.medico.dtos.requests.EnderecoRequest;
import med.data.api.domain.medico.dtos.requests.MedicoRequest;
import med.data.api.domain.medico.enums.Especialidade;
import med.data.api.domain.paciente.dtos.request.CadastroPacienteDto;
import med.data.api.domain.paciente.model.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Deveria devolver null quando o medico castrastrado não estiver disponível na data")
    void buscarMedicoAleatorioLivreNaDataCenario1 () {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA, dadosEndereco(), "(11) 98765-4321)");

        var medicoLivre = medicoRepository.buscarMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isEqualTo(medico);
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA, dadosEndereco(), "(11) 98765-4321)");

        var medicoLivre = medicoRepository.buscarMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        testEntityManager.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade, EnderecoRequest endereco, String telefone) {
        var medico = Medico.of(new MedicoRequest(nome, email, crm, especialidade, endereco, telefone));
        testEntityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String telefone, String cpf, EnderecoRequest endereco) {
        var paciente = Paciente.of(new CadastroPacienteDto(nome, email, telefone, cpf, endereco));
        testEntityManager.persist(paciente);
        return paciente;
    }

    private MedicoRequest dadosMedico(String nome, String email, String crm, Especialidade especialidade, EnderecoRequest endereco, String telefone) {
        return new MedicoRequest(
                nome,
                email,
                crm,
                especialidade,
                endereco,
                telefone
        );
    }

    private CadastroPacienteDto dadosPaciente(String nome, String email, String cpf, String telefone, EnderecoRequest endereco) {
        return new CadastroPacienteDto(
                nome,
                email,
                cpf,
                telefone,
                endereco
        );
    }

    private EnderecoRequest dadosEndereco() {
        return new EnderecoRequest(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}
