package com.co.sofka.api.tourfrance.cyclingteam.routes;

import com.co.sofka.api.tourfrance.cyclingteam.dto.CyclingTeamDTO;
import com.co.sofka.api.tourfrance.cyclingteam.usecase.CreateCyclingTeamUseCase;
import com.co.sofka.api.tourfrance.exceptions.ExceptionPersonalityBadRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CyclingTeamCreateRoute {

    @Bean
    @RouterOperation(beanClass = CreateCyclingTeamUseCase.class, beanMethod = "createTeam", operation = @Operation(operationId = "create", summary = "Create Cycling Team", tags = {
            "CyclingTeam"}, responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
            @ApiResponse(responseCode = "404", description = "CyclingTeam not found")}))
    public RouterFunction<ServerResponse> createCiclingTeam(CreateCyclingTeamUseCase createCyclingTeamUseCase){
        Function<CyclingTeamDTO, Mono<ServerResponse>> createTeam = CyclingTeamDTO -> createCyclingTeamUseCase.createTeam(CyclingTeamDTO)
                .flatMap(serverResponse -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(serverResponse));
        return route(POST("/createTeam").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CyclingTeamDTO.class)
                        .flatMap(createTeam)
                        .onErrorResume(error -> {
                              return Mono.error(new ExceptionPersonalityBadRequest(error.getMessage()));
                        }));
    }
}