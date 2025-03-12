package com.dev.insurance_policies.infrastructure.entity;

import lombok.Getter;

@Getter
public enum State {

    REPORTADO("Reportado", "Parte reportado"),
    EN_PROCESO("En proceso", "Parte en proceso"),
    RECHAZADO("Rechazado", "Parte rechazado"),
    CERRADO("Cerrado", "Parte cerrado");

    private final String name;
    private final String description;

    State(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
