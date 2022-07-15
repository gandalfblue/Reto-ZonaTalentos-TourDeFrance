package com.co.sofka.api.tourfrance.ciclist.repository;

import com.co.sofka.api.tourfrance.ciclist.collection.Ciclist;
import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CiclistRepository extends ReactiveMongoRepository<Ciclist, String> {

    Mono<Void> deleteByCodeTeam(String codeTeam);
    Flux<Ciclist> findCiclistsByCodeTeam(String codeTeam);
    Flux<Ciclist> findCiclistsByNacionality(String nacionality);

    Mono<Ciclist> findCiclistByNumberCompetitor (String numberCompetitor);
}
