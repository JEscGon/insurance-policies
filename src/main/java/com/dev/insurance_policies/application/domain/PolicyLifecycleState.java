package com.dev.insurance_policies.application.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum PolicyLifecycleState {

    PENDING(1L),
    ACTIVE(2L),
    SUSPENDED(3L),
    CANCELLED(4L),
    EXPIRED(5L);

    private final Long id;

    private static final Map<Long, PolicyLifecycleState> BY_ID = new HashMap<>();

    static {
        for (PolicyLifecycleState state : values()) {
            BY_ID.put(state.id, state);
        }
    }

    PolicyLifecycleState(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static PolicyLifecycleState fromId(Long id) {
        if (id == null) {
            return null;
        }
        return BY_ID.get(id);
    }

    public Set<PolicyLifecycleState> getAllowedTransitions() {
        return switch (this) {
            case PENDING -> EnumSet.of(ACTIVE, CANCELLED);
            case ACTIVE -> EnumSet.of(SUSPENDED, CANCELLED, EXPIRED);
            case SUSPENDED -> EnumSet.of(ACTIVE, CANCELLED);
            case CANCELLED, EXPIRED -> EnumSet.noneOf(PolicyLifecycleState.class);
        };
    }

    public boolean canTransitionTo(PolicyLifecycleState target) {
        if (target == null) {
            return false;
        }
        return getAllowedTransitions().contains(target);
    }
}

