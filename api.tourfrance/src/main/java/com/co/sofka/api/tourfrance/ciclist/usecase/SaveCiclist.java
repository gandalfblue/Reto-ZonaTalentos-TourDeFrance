package com.co.sofka.api.tourfrance.ciclist.usecase;

import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveCiclist {

    Mono<CiclistDTO> createCiclist(@Valid CiclistDTO ciclistDTO);
}
