package com.co.sofka.api.tourfrance.cyclingteam.usecase;

import com.co.sofka.api.tourfrance.cyclingteam.dto.CyclingTeamDTO;
import com.co.sofka.api.tourfrance.cyclingteam.mapper.CyclingTeamMapper;
import com.co.sofka.api.tourfrance.cyclingteam.repository.CyclingTeamRepository;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityNotFound;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class ListTeamByLocationUseCase implements Function<String, Flux<CyclingTeamDTO>> {

    private final CyclingTeamRepository cyclingTeamRepository;
    private final CyclingTeamMapper cyclingTeamMapper;


    public ListTeamByLocationUseCase(CyclingTeamRepository cyclingTeamRepository, CyclingTeamMapper cyclingTeamMapper) {
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.cyclingTeamMapper = cyclingTeamMapper;
    }

    @Override
    public Flux<CyclingTeamDTO> apply(String teamLocation) {
        return cyclingTeamRepository.findTeamsByTeamLocation(teamLocation)
                .map(cyclingTeamMapper.mapperToCyclingTeamDTO())
                .switchIfEmpty(Mono.error(new ExceptionPersonalityNotFound("No hay equipos que pertenezcan a este pais")));
    }
}
