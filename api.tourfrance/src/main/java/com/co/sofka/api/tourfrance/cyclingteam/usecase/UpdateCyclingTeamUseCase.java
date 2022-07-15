package com.co.sofka.api.tourfrance.cyclingteam.usecase;

import com.co.sofka.api.tourfrance.cyclingteam.dto.CyclingTeamDTO;
import com.co.sofka.api.tourfrance.cyclingteam.mapper.CyclingTeamMapper;
import com.co.sofka.api.tourfrance.cyclingteam.repository.CyclingTeamRepository;
import com.co.sofka.api.tourfrance.cyclingteam.usecase.SaveTeam;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityBadRequest;
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
                .save(cyclingTeamMapper.mapperToCyclingTeam(null).apply(cyclingTeamDTO))
                .map(cyclingTeam -> cyclingTeamMapper.mapperToCyclingTeamDTO().apply(cyclingTeam))
                .onErrorResume(error ->
                {
                    return Mono.error(new ExceptionPersonalityBadRequest("Cycling team existente"));
                });
    }
}
