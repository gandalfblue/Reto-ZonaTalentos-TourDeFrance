package com.co.sofka.api.tourfrance.cyclingteam.routes;

import com.co.sofka.api.tourfrance.cyclingteam.usecase.DeleteCyclingTeamUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CyclingTeamDeleteRoute {

    @Bean
    @RouterOperation(operation = @Operation(operationId = "delete", summary = "Delete cycling team by codeTeam", tags = {"CyclingTeam"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "codeTeam", description = "cyclingTeam codeTeam")},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
                    @ApiResponse(responseCode = "404", description = "Cycling not found")}))
    public RouterFunction<ServerResponse> deleteTeam(DeleteCyclingTeamUseCase deleteCyclingTeamUseCase){
        return route(DELETE("/deleteTeam/{codeTeam}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                deleteCyclingTeamUseCase.apply(request.pathVariable("codeTeam")),
                                Void.class)));
    }
}
