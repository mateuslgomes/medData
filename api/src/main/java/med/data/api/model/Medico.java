package med.data.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.data.api.dtos.requests.AtualizacaoMedicoRequest;
import med.data.api.dtos.requests.MedicoRequest;
import med.data.api.model.enums.Especialidade;

import java.util.UUID;

@Table(name = "medicos")
@Entity(name = "Medico")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String email;
    private Boolean ativo;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public static Medico of(MedicoRequest request) {
        return Medico.builder()
                .nome(request.nome())
                .email(request.email())
                .ativo(true)
                .crm(request.crm())
                .telefone(request.telefone())
                .especialidade(request.especialidade())
                .endereco(Endereco.of(request.endereco()))
                .build();
    }

    public void excluir() {
        this.ativo = false;
    }

    public void atualizarInformacoes(AtualizacaoMedicoRequest request) {
        this.nome = request.nome() != null ? request.nome() : this.nome;
        this.telefone = request.telefone() != null ? request.telefone() : this.telefone;
        if (request.endereco() != null) {
            this.endereco.atualizarInformacoes(request.endereco());
        }
    }

}
