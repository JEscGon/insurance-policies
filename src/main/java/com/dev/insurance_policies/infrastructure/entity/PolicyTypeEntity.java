package com.dev.insurance_policies.infrastructure.entity;

import lombok.Getter;

@Getter
public enum PolicyTypeEntity {

    BASIC("Básico", "Cobertura a terceros"),
    FULL("Completo", "Cobertura a todo riesgo");

    private final String name;
    private final String description;

    PolicyTypeEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
