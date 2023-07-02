## MEDData

O MEDData é um sistema de gerenciamento de médicos, pacientes e consultas médicas desenvolvido em Java e Spring. Ele oferece uma solução completa para facilitar a administração desses dados no contexto de um ambiente médico.

Funcionalidades principais:

- Cadastrar médico: permite adicionar novos médicos ao sistema, incluindo informações como nome, email, CRM, especialidade e se ele está ativo.

- Cadastrar paciente: possibilita registrar informações de pacientes, como nome, email, telefone, cpf, endereço e se ele está ativo.

- Realizar consultas: os médicos podem agendar e registrar consultas médicas com os pacientes. O agendamento só é permitido se o médico estiver disponível e ativo no dia e horário selecionados.

- Autenticação: para acessar as funcionalidades do sistema, é necessário autenticar-se. O método POST para localhost:8080/AutenticaController permite obter um token de autenticação, utilizando o usuário "root" e a senha "123456".

Tecnologias:
- Java 17
- Spring Framework
- Spring Security
- Spring Docs (Swagger)
- MySQL
- Flyway

Como executar o projeto:

1. Clone este repositório: git clone https://github.com/mateuslgomes/medData.git
2. Importe o projeto em sua IDE preferida.
3. Configure as informações de conexão com o banco de dados MySQL no arquivo application.properties.
4. Execute o aplicativo a partir da classe principal: MedDataApplication.java.
5. Acesse a documentação da API em: http://localhost:8080/swagger-ui.html

Contribuição:

- Se você quiser contribuir com este projeto, fique à vontade para fazer um fork e enviar um pull request com suas melhorias.

Entre em contato:

- Se tiver alguma dúvida ou sugestão, sinta-se à vontade para entrar em contato através dos canais listados abaixo:

    - E-mail: dev.mateuslgomes@gmail.com
    - GitHub: https://github.com/mateuslgomes
