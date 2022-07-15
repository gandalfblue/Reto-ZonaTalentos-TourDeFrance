package com.co.sofka.api.tourfrance.cyclingteam.usecase;

import com.co.sofka.api.tourfrance.cyclingteam.dto.CyclingTeamDTO;
import com.co.sofka.api.tourfrance.cyclingteam.mapper.CyclingTeamMapper;
import com.co.sofka.api.tourfrance.cyclingteam.repository.CyclingTeamRepository;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityNotFound;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@Service
@Validated
public class CyclingTeamListUseCase implements Supplier<Flux<CyclingTeamDTO>> {

    private final CyclingTeamRepository cyclingTeamRepository;
    private final CyclingTeamMapper cyclingTeamMapper;

    public CyclingTeamListUseCase(CyclingTeamRepository cyclingTeamRepository, CyclingTeamMapper cyclingTeamMapper) {
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.cyclingTeamMapper = cyclingTeamMapper;
    }

    @Override
    public Flux<CyclingTeamDTO> get() {
        return cyclingTeamRepository
                .findAll()
                .map(cyclingTeamMapper.mapperToCyclingTeamDTO())
                .switchIfEmpty(Mono.error(new ExceptionPersonalityNotFound("No hay equipos aun registrados")));
    }
}
