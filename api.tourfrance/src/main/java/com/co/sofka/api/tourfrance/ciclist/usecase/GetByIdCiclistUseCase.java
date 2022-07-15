package com.co.sofka.api.tourfrance.ciclist.usecase;

import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import com.co.sofka.api.tourfrance.ciclist.mapper.CiclistMapper;
import com.co.sofka.api.tourfrance.ciclist.repository.CiclistRepository;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityNotFound;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetByIdCiclistUseCase implements Function<String, Mono<CiclistDTO>> {

private final CiclistRepository ciclistRepository;
private final CiclistMapper ciclistMapper;

    public GetByIdCiclistUseCase(CiclistRepository ciclistRepository, CiclistMapper ciclistMapper) {
        this.ciclistRepository = ciclistRepository;
        this.ciclistMapper = ciclistMapper;
    }

    @Override
    public Mono<CiclistDTO> apply(String id) {
        return ciclistRepository
                .findById(id)
                .map(ciclistMapper.mapperToCiclistDTO())
                .switchIfEmpty(Mono.error(new ExceptionPersonalityNotFound("El ciclista no existe")));

    }
}
