package com.co.sofka.api.tourfrance.cyclingteam.routes;

import com.co.sofka.api.tourfrance.cyclingteam.collection.CyclingTeam;
import com.co.sofka.api.tourfrance.cyclingteam.dto.CyclingTeamDTO;
import com.co.sofka.api.tourfrance.cyclingteam.mapper.CyclingTeamMapper;
import com.co.sofka.api.tourfrance.cyclingteam.repository.CyclingTeamRepository;
import com.co.sofka.api.tourfrance.cyclingteam.usecase.CreateCyclingTeamUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import static org.mockito.Mockito.verify;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {CyclingTeamCreateRoute.class, CreateCyclingTeamUseCase.class, CyclingTeamMapper.class})
class CyclingTeamCreateRouteTest {

    @MockBean
    private CyclingTeamRepository repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void usuarioRutasTest() {

        var cyclingTeam = new CyclingTeam();
        cyclingTeam.setId("T-001");
        cyclingTeam.setTeamName("Lotto");
        cyclingTeam.setCodeTeam("F01");
        cyclingTeam.setTeamLocation("Holanda");

        var cyclingTeamDTO = new CyclingTeamDTO();
        cyclingTeamDTO.setId(cyclingTeam.getId());
        cyclingTeamDTO.setTeamName(cyclingTeam.getTeamName());
        cyclingTeamDTO.setCodeTeam(cyclingTeam.getCodeTeam());
        cyclingTeamDTO.setTeamLocation(cyclingTeam.getTeamLocation());

        Mockito.when(repositorio.save(Mockito.any(CyclingTeam.class))).thenReturn(Mono.just(cyclingTeam));

        webTestClient.post()
                .uri("/createTeam")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(cyclingTeamDTO), CyclingTeamDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CyclingTeamDTO.class)
                .value(response -> {
                    Assertions.assertEquals(response.getId(), cyclingTeam.getId());
                    Assertions.assertEquals(response.getTeamName(), cyclingTeam.getTeamName());
                    Assertions.assertEquals(response.getCodeTeam(), cyclingTeam.getCodeTeam());
                    Assertions.assertEquals(response.getTeamLocation(), cyclingTeam.getTeamLocation());
                });
        verify(repositorio).save(Mockito.any(CyclingTeam.class));
    }
}