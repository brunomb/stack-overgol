package com.github.brunomb.stackovergol.model;

/**
 * Created by brunomb on 6/18/2017.
 */

public enum StackOvergolPreference {
    TELEGRAM_ID("telegram.id"),
    USER_NAME("user.name"),
    FIRST_NAME("user.firstname"),
    LAST_NAME("user.lastname"),
    TYPE("user.type");

    private final String preference;

    StackOvergolPreference(final String preference) {
        this.preference = preference;
    }

    public String getPreference() {
        return preference;
    }
}
