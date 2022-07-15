package com.co.sofka.api.tourfrance.cyclingteam.usecase;

import com.co.sofka.api.tourfrance.cyclingteam.dto.CyclingTeamDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveTeam {

    Mono<CyclingTeamDTO> createTeam(@Valid CyclingTeamDTO cyclingTeamDTO);
}
