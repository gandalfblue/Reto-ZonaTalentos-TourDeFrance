package com.co.sofka.api.tourfrance.ciclist.usecase;

import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import com.co.sofka.api.tourfrance.ciclist.mapper.CiclistMapper;
import com.co.sofka.api.tourfrance.ciclist.repository.CiclistRepository;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityBadRequest;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityNotFound;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UpdateCiclistUseCase implements SaveCiclist {

    private final CiclistRepository ciclistRepository;
    private final CiclistMapper ciclistMapper;

    private final GetByIdCiclistUseCase getByIdCiclistUseCase;

    public UpdateCiclistUseCase(CiclistRepository ciclistRepository, CiclistMapper ciclistMapper, GetByIdCiclistUseCase getByIdCiclistUseCase) {
        this.ciclistRepository = ciclistRepository;
        this.ciclistMapper = ciclistMapper;
        this.getByIdCiclistUseCase = getByIdCiclistUseCase;
    }

    @Override
    public Mono<CiclistDTO> createCiclist(CiclistDTO ciclistDTO) {
        return
                getByIdCiclistUseCase.apply(ciclistDTO.getId())
                        .then(ciclistRepository.save(ciclistMapper.mapperToCiclist(ciclistDTO.getId()).apply(ciclistDTO)))
                        .map(ciclistMapper.mapperToCiclistDTO())
                        .onErrorResume(error ->
                        {
                            return Mono.error(new ExceptionPersonalityBadRequest("El ciclista existe pero no puede usar ese numero de competidor porque ya esta en uso"));
                        });
    }
}