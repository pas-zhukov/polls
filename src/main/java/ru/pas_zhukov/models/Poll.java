package ru.pas_zhukov.models;

import ru.pas_zhukov.builders.PollBuilder;

import java.util.List;

public class Poll {
    private String pollName;
    private List<PollQuestion> pollQuestionList;

    public static PollBuilder builder() {
        return null;
    }

}
