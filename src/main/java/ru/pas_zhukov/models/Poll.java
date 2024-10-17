package ru.pas_zhukov.models;

import ru.pas_zhukov.builders.PollBuilder;

import java.util.List;

public class Poll {
    private String pollName;
    private final List<PollQuestion> pollQuestionList;

    public Poll(String pollName, List<PollQuestion> pollQuestionList) {
        this.pollName = pollName;
        this.pollQuestionList = pollQuestionList;
    }

    public String getPollName() {
        return pollName;
    }

    public void setPollName(String pollName) {
        this.pollName = pollName;
    }

    public void addQuestion(PollQuestion question) {
        this.pollQuestionList.add(question);
    }

    public List<PollQuestion> getPollQuestionList() {
        return pollQuestionList;
    }

    public static PollBuilder builder() {
        return new PollBuilder();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Опрос: %s\n", pollName));
        for (PollQuestion question : pollQuestionList) {
            builder.append(question).append("\n");
        }
        return builder.toString();
    }
}
