package med.data.api.controllers;

import med.data.api.domain.consultas.dtos.AgendamentoConsultaDto;
import med.data.api.domain.consultas.dtos.DetalhamentoConsultaDto;
import med.data.api.domain.consultas.services.ConsultasServices;
import med.data.api.domain.medico.enums.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private JacksonTester<AgendamentoConsultaDto> dadosAgendamentoConsultaJson;

    @Autowired
    private JacksonTester<DetalhamentoConsultaDto> dadosDetalhamentoConsulta;

    @MockBean
    private ConsultasServices agendaDeConsultas;


    @Test
    @DisplayName("Deveria devolver codigo http 400 quando as informações estiverem inválidas")
    @WithMockUser
    void agendarCenario1() throws Exception {
        var response = mockMvc.perform(post("/consultas"))
            .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 201 se as informações estiverem válidas")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;
        var idMedico = UUID.randomUUID();
        var idPaciente = UUID.randomUUID();

        var dadosDetalhamento = new DetalhamentoConsultaDto(null, idMedico, idPaciente, data);
        when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mockMvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJson.write(
                                        new AgendamentoConsultaDto(UUID.randomUUID(), UUID.randomUUID(), data, especialidade)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoConsulta.write(
                dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}
