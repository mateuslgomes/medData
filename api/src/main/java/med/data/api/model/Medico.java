package med.data.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.data.api.dtos.MedicoRequest;
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
                .crm(request.crm())
                .telefone(request.telefone())
                .especialidade(request.especialidade())
                .endereco(Endereco.of(request.endereco()))
                .build();
    }

}
