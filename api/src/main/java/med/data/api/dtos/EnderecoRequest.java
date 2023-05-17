package med.data.api.dtos;

public record   EnderecoRequest(String logradouro, String bairro, String cep, String cidade, String uf, String complemento, String numero) {
}
