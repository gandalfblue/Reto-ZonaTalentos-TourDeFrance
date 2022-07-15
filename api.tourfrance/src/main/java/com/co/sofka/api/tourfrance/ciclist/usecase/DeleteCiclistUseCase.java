package com.co.sofka.api.tourfrance.ciclist.usecase;

import com.co.sofka.api.tourfrance.ciclist.repository.CiclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class DeleteCiclistUseCase implements Function<String, Mono<Void>>{

    private final CiclistRepository ciclistRepository;

    public DeleteCiclistUseCase(CiclistRepository ciclistRepository) {
        this.ciclistRepository = ciclistRepository;
    }

    @Override
    public Mono<Void> apply(String id) {
        return ciclistRepository
                .deleteById(id);
    }
}
