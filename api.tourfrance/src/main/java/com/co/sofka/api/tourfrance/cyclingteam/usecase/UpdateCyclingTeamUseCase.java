package com.co.sofka.api.tourfrance.cyclingteam.usecase;

import com.co.sofka.api.tourfrance.cyclingteam.dto.CyclingTeamDTO;
import com.co.sofka.api.tourfrance.cyclingteam.mapper.CyclingTeamMapper;
import com.co.sofka.api.tourfrance.cyclingteam.repository.CyclingTeamRepository;
import com.co.sofka.api.tourfrance.cyclingteam.usecase.SaveTeam;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityBadRequest;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityNotFound;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UpdateCyclingTeamUseCase implements SaveTeam {

    private final CyclingTeamRepository cyclingTeamRepository;
    private final CyclingTeamMapper cyclingTeamMapper;

    public UpdateCyclingTeamUseCase(CyclingTeamRepository cyclingTeamRepository, CyclingTeamMapper cyclingTeamMapper) {
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.cyclingTeamMapper = cyclingTeamMapper;
    }

    @Override
    public Mono<CyclingTeamDTO> createTeam(CyclingTeamDTO cyclingTeamDTO) {
        return cyclingTeamRepository
                .findById(cyclingTeamDTO.getId())
                .map(cyclingTeamMapper.mapperToCyclingTeamDTO())
                .switchIfEmpty(Mono.error(new ExceptionPersonalityNotFound("El equipo de cilcismo no existe")))
                .then(cyclingTeamRepository.save(cyclingTeamMapper.mapperToCyclingTeam(cyclingTeamDTO.getId()).apply(cyclingTeamDTO)))
                .map(cyclingTeam -> cyclingTeamMapper.mapperToCyclingTeamDTO().apply(cyclingTeam))
                .onErrorResume(error ->
                {
                    return Mono.error(new ExceptionPersonalityBadRequest("El equipo existe pero no puede usar ese codigo de equipo porque ya esta en uso"));
                });
    }
}
