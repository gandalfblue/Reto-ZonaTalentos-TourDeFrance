package com.co.sofka.api.tourfrance.cyclingteam.usecase;

import com.co.sofka.api.tourfrance.cyclingteam.collection.CyclingTeam;
import com.co.sofka.api.tourfrance.cyclingteam.dto.CyclingTeamDTO;
import com.co.sofka.api.tourfrance.cyclingteam.mapper.CyclingTeamMapper;
import com.co.sofka.api.tourfrance.cyclingteam.repository.CyclingTeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
class CreateCyclingTeamUseCaseTest {

    @Autowired
    CyclingTeamRepository cyclingTeamRepository;

    @Autowired
    CreateCyclingTeamUseCase createUseCase;

    @Autowired
    CyclingTeamMapper cyclingTeamMapper = new CyclingTeamMapper();

    @BeforeEach
    public void setUp() {

        cyclingTeamRepository = mock(CyclingTeamRepository.class);
        createUseCase = new CreateCyclingTeamUseCase(cyclingTeamRepository, cyclingTeamMapper);
    }

    @Test
    void getValidationCreateTest() {
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

        Mockito.when(cyclingTeamRepository.save(Mockito.any(CyclingTeam.class))).thenReturn(Mono.just(cyclingTeam));

        StepVerifier.create(createUseCase.createTeam(cyclingTeamDTO))

                .expectNextMatches(q -> {

                    assert cyclingTeamDTO.getId().equals(cyclingTeam.getId());
                    assert cyclingTeamDTO.getTeamName().equals("Lotto");
                    assert cyclingTeamDTO.getCodeTeam().equals("F01");
                    assert cyclingTeamDTO.getTeamLocation().equals("Holanda");
                    return true;
                }).verifyComplete();

        verify(cyclingTeamRepository).save(Mockito.any(CyclingTeam.class));
    }
}