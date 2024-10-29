package ru.pas_zhukov.models;

import java.util.List;


public class PollFillingData {
    private final String username;
    private final List<PollQuestionResponse> responses;

    /**
     * Результат заполнения опроса пользователем
     *
     * @param username  логин пользователя
     * @param responses список ответов пользователя
     */
    public PollFillingData(String username, List<PollQuestionResponse> responses) {
        this.username = username;
        this.responses = responses;
    }

    public String getUsername() {
        return username;
    }

    public List<PollQuestionResponse> getResponses() {
        return responses;
    }
}
