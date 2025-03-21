package com.dev.insurance_policies.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class State {

    private Long id;
    private String name;
    private String description;

}
