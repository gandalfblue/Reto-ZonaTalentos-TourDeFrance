package com.co.sofka.api.tourfrance.ciclist.usecase;

import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import com.co.sofka.api.tourfrance.ciclist.mapper.CiclistMapper;
import com.co.sofka.api.tourfrance.ciclist.repository.CiclistRepository;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityBadRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UpdateCiclistUseCase implements SaveCiclist {

    private final CiclistRepository ciclistRepository;
    private final CiclistMapper ciclistMapper;

    public UpdateCiclistUseCase(CiclistRepository ciclistRepository, CiclistMapper ciclistMapper) {
        this.ciclistRepository = ciclistRepository;
        this.ciclistMapper = ciclistMapper;
    }

    @Override
    public Mono<CiclistDTO> createCiclist(CiclistDTO ciclistDTO) {
        return ciclistRepository
                .save(ciclistMapper.mapperToCiclist(ciclistDTO.getId()).apply(ciclistDTO))
                .map(ciclistMapper.mapperToCiclistDTO())
                .onErrorResume(error ->
                {
                    return Mono.error(new ExceptionPersonalityBadRequest("Parametros invalidos"));
                });
    }
}
