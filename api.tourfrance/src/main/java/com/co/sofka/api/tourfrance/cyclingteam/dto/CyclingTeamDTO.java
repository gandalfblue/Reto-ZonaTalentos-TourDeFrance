package com.co.sofka.api.tourfrance.cyclingteam.dto;

import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import com.co.sofka.api.tourfrance.exceptions.MaxSizeConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.Constraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class CyclingTeamDTO implements Serializable {

    private static final long serialVersionUID = -1896867392858584778L;

    private String id;

    @NotBlank
    private String teamName;

    @NotBlank
    @Size(min = 1, max = 3)
    @Indexed(unique = true)
    private String codeTeam;

    @NotBlank
    private String teamLocation;

    @MaxSizeConstraint
    private Set<CiclistDTO> ciclistList;

    public Set<CiclistDTO> getCiclistList() {
        this.ciclistList = Optional.ofNullable(ciclistList).orElse(new HashSet<>());
        return ciclistList;
    }

    public CyclingTeamDTO(String id, String teamName, String codeTeam, String teamLocation) {
        this.id = id;
        this.teamName = teamName;
        this.codeTeam = codeTeam;
        this.teamLocation = teamLocation;
    }
}