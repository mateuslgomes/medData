package med.data.api.domain.paciente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.data.api.domain.endereco.Endereco;
import med.data.api.domain.paciente.dtos.request.AtualizacaoPacienteDto;
import med.data.api.domain.paciente.dtos.request.CadastroPacienteDto;

import java.util.UUID;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String nome;
    private String email;

    private String telefone;

    private String cpf;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(CadastroPacienteDto dto) {
        this.ativo = true;
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.cpf = dto.cpf();
        this.endereco = Endereco.of(dto.endereco());
    }

    public void atualizarInformacoes(AtualizacaoPacienteDto dto) {
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
        if (dto.telefone() != null) {
            this.telefone = dto.telefone();
        }
        if (dto.endereco() != null) {
            this.endereco.atualizarInformacoes(dto.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
