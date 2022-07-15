package com.co.sofka.api.tourfrance.cyclingteam.usecase;

import com.co.sofka.api.tourfrance.ciclist.repository.CiclistRepository;
import com.co.sofka.api.tourfrance.cyclingteam.collection.CyclingTeam;
import com.co.sofka.api.tourfrance.cyclingteam.repository.CyclingTeamRepository;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityBadRequest;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityNotFound;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class DeleteCyclingTeamUseCase implements Function<String, Mono<Void>> {

    private final CyclingTeamRepository cyclingTeamRepository;
    private final CiclistRepository ciclistRepository;

    public DeleteCyclingTeamUseCase(CyclingTeamRepository cyclingTeamRepository, CiclistRepository ciclistRepository) {
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.ciclistRepository = ciclistRepository;
    }

    @Override
    public Mono<Void> apply(String codeTeam) {
        return cyclingTeamRepository
                .findByCodeTeam(codeTeam)
                .switchIfEmpty(Mono.error(new ExceptionPersonalityNotFound("El codigo del equipo no existe")))
                .flatMap(team -> cyclingTeamRepository.deleteByCodeTeam(team.toString()))
                .switchIfEmpty(Mono.defer(() -> ciclistRepository.deleteByCodeTeam(codeTeam)));
    }
}
