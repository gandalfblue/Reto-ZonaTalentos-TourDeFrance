package com.co.sofka.api.tourfrance.ciclist.usecase;

import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import com.co.sofka.api.tourfrance.ciclist.mapper.CiclistMapper;
import com.co.sofka.api.tourfrance.ciclist.repository.CiclistRepository;
import com.co.sofka.api.tourfrance.cyclingteam.repository.CyclingTeamRepository;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityNotFound;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class ListCiclistByTeamUseCase implements Function<String, Flux<CiclistDTO>> {

    private final CiclistRepository ciclistRepository;
    private final CyclingTeamRepository cyclingTeamRepository;
    private final CiclistMapper ciclistMapper;

    public ListCiclistByTeamUseCase(CiclistRepository ciclistRepository, CyclingTeamRepository cyclingTeamRepository, CiclistMapper ciclistMapper) {
        this.ciclistRepository = ciclistRepository;
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.ciclistMapper = ciclistMapper;
    }

    @Override
    public Flux<CiclistDTO> apply(String codeTeam) {
        return cyclingTeamRepository
                .findByCodeTeam(codeTeam)
                .switchIfEmpty(Mono.error(new ExceptionPersonalityNotFound("El codigo del equipo no existe")))
                .thenMany(ciclistRepository.findCiclistsByCodeTeam(codeTeam))
                .map(ciclistMapper.mapperToCiclistDTO())
                .switchIfEmpty(Mono.error(new ExceptionPersonalityNotFound("No hay ningun ciclista que pertenece a ese equipo")));
    }
}
