package com.co.sofka.api.tourfrance.ciclist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CiclistDTO implements Serializable {

    private static final long serialVersionUID = 7697807173071027893L;

    private String id;

    @NotBlank
    private String nameComplete;

    @NotBlank
    @Size(min = 1, max = 3)
    @Indexed(unique = true)
    private String numberCompetitor;

    @NotBlank
    private String codeTeam;

    @NotBlank
    private String nacionality;
}
