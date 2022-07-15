package com.co.sofka.api.tourfrance.cyclingteam.repository;

import com.co.sofka.api.tourfrance.cyclingteam.collection.CyclingTeam;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CyclingTeamRepository extends ReactiveMongoRepository<CyclingTeam, String> {

    Mono<Void> deleteByCodeTeam(String codeTeam);

    Flux<CyclingTeam> findTeamsByTeamLocation (String teamLocation);
}
