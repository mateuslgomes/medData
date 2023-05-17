package med.data.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.data.api.dtos.EnderecoRequest;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public static Endereco of(EnderecoRequest enderecoRequest) {
        return Endereco.builder()
                .logradouro(enderecoRequest.logradouro())
                .bairro(enderecoRequest.bairro())
                .cep(enderecoRequest.cep())
                .numero(enderecoRequest.numero())
                .complemento(enderecoRequest.complemento())
                .cidade(enderecoRequest.cidade())
                .uf(enderecoRequest.uf())
                .build();
    }

}