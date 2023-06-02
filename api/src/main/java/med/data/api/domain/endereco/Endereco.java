package med.data.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.data.api.domain.medico.dtos.requests.EnderecoRequest;

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

    public void atualizarInformacoes(EnderecoRequest request) {
        this.logradouro = request.logradouro() != null ? request.logradouro() : this.logradouro;
        this.bairro = request.bairro() != null ? request.bairro() : this.bairro;
        this.cep = request.cep() != null ? request.cep() : this.cep;
        this.uf = request.uf() != null ? request.uf() : this.uf;
        this.cidade = request.cidade() != null ? request.cidade() : this.cidade;
        this.numero = request.numero() != null ? request.numero() : this.numero;
        this.complemento = request.complemento() != null ? request.complemento() : this.complemento;
    }

}
