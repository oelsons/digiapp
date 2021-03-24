package com.oel.digiapp.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Digimon {
    private String name;
    private String img;
    private String level;
}
