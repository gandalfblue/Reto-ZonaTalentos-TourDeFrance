package com.co.sofka.api.tourfrance.ciclist.usecase;

import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import com.co.sofka.api.tourfrance.ciclist.mapper.CiclistMapper;
import com.co.sofka.api.tourfrance.ciclist.repository.CiclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@Validated
public class ListCiclistByTeamUseCase implements Function<String, Flux<CiclistDTO>> {

    private final CiclistRepository ciclistRepository;
    private final CiclistMapper ciclistMapper;

    public ListCiclistByTeamUseCase(CiclistRepository ciclistRepository, CiclistMapper ciclistMapper) {
        this.ciclistRepository = ciclistRepository;
        this.ciclistMapper = ciclistMapper;
    }

    @Override
    public Flux<CiclistDTO> apply(String codeTeam) {
        return ciclistRepository
                .findCiclistsByCodeTeam(codeTeam)
                .map(ciclistMapper.mapperToCiclistDTO());
    }
}
