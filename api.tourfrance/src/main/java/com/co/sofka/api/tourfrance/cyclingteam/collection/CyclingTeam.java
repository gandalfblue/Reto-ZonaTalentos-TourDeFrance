package com.co.sofka.api.tourfrance.cyclingteam.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Document(collection = "cyclingTeam")
public class CyclingTeam implements Serializable {

    private static final long serialVersionUID = -2281463685953735119L;

    @Id
    private String id;

    @NotBlank
    private String teamName;

    @NotBlank
    @Size(min = 1, max = 3)
    @Indexed(unique = true)
    private String codeTeam;

    @NotBlank
    private String teamLocation;
}
