package com.co.sofka.api.tourfrance.ciclist.usecase;

import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import com.co.sofka.api.tourfrance.ciclist.mapper.CiclistMapper;
import com.co.sofka.api.tourfrance.ciclist.repository.CiclistRepository;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityNotFound;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class ListCiclistByNacionalityUseCase implements Function<String, Flux<CiclistDTO>> {

    private final CiclistRepository ciclistRepository;
    private final CiclistMapper ciclistMapper;

    public ListCiclistByNacionalityUseCase(CiclistRepository ciclistRepository, CiclistMapper ciclistMapper) {
        this.ciclistRepository = ciclistRepository;
        this.ciclistMapper = ciclistMapper;
    }

    @Override
    public Flux<CiclistDTO> apply(String nacionality) {
        return ciclistRepository
                .findCiclistsByNacionality(nacionality)
                .switchIfEmpty(Mono.error(new ExceptionPersonalityNotFound("No existe ningun ciclista con esa nacionalidad")))
                .map(ciclistMapper.mapperToCiclistDTO());
    }
}