package com.co.sofka.api.tourfrance.cyclingteam.routes;

import com.co.sofka.api.tourfrance.cyclingteam.dto.CyclingTeamDTO;
import com.co.sofka.api.tourfrance.cyclingteam.usecase.UpdateCyclingTeamUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CyclingTeamUpdateRoute {

    @Bean
    @RouterOperation(beanClass = UpdateCyclingTeamUseCase.class, beanMethod = "createTeam",
            operation = @Operation(operationId = "update", summary = "Update Ciclist", tags = {"CyclingTeam"},
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied")}))
    public RouterFunction<ServerResponse> updateTeam(UpdateCyclingTeamUseCase updateCyclingTeamUseCase){
        Function<CyclingTeamDTO, Mono<ServerResponse>> updateTeam = CyclingTeamDTO -> updateCyclingTeamUseCase.createTeam(CyclingTeamDTO)
                .flatMap(serverResponse -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(serverResponse));
        return route(PUT("/updateTeam").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CyclingTeamDTO.class)
                        .flatMap(updateTeam));

    }

}
