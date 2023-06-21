package med.data.api.domain.paciente.model;

import jakarta.persistence.*;
import lombok.*;
import med.data.api.domain.endereco.Endereco;
import med.data.api.domain.paciente.dtos.request.PacienteUpdateRequest;
import med.data.api.domain.paciente.dtos.request.PacienteRequest;

import java.util.UUID;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public static Paciente of(PacienteRequest dto) {
        return Paciente.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .email(dto.email())
                .endereco(Endereco.of(dto.endereco()))
                .telefone(dto.telefone())
                .ativo(true)
                .build();
    }

    public void atualizarInformacoes(PacienteUpdateRequest dto) {
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
