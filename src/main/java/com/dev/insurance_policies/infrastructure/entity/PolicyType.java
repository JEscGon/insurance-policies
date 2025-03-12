package com.dev.insurance_policies.infrastructure.entity;

import lombok.Getter;

@Getter
public enum PolicyType {

    BASIC("Básico", "Cobertura a terceros"),
    FULL("Completo", "Cobertura a todo riesgo");

    private final String name;
    private final String description;

    PolicyType(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
