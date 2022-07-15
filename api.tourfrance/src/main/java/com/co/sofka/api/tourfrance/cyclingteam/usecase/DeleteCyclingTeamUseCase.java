package com.co.sofka.api.tourfrance.cyclingteam.usecase;

import com.co.sofka.api.tourfrance.ciclist.repository.CiclistRepository;
import com.co.sofka.api.tourfrance.cyclingteam.repository.CyclingTeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
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
                .deleteByCodeTeam(codeTeam)
                .switchIfEmpty(Mono.defer(() -> ciclistRepository.deleteByCodeTeam(codeTeam)));
    }
}
