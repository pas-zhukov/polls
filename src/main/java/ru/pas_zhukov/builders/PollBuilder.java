package ru.pas_zhukov.builders;

import ru.pas_zhukov.models.Poll;
import ru.pas_zhukov.models.PollQuestion;

import java.util.ArrayList;

public class PollBuilder {
    private final Poll poll;

    public PollBuilder() {
        poll = new Poll("Unnamed poll", new ArrayList<>());
    }

    public PollBuilder withPollName(String pollName) {
        poll.setPollName(pollName);
        return this;
    }

    public PollQuestionBuilder pollQuestion(String title) {
        return PollQuestion.builder(this).withTitle(title);
    }

    public PollQuestionBuilder oneVariantPollQuestion(String title) {
        return PollQuestion.builder(this)
                .withTitle(title)
                .withMinAnswers(1)
                .withMaxAnswers(1);
    }

    public PollBuilder yesNoPollQuestion(String title) {
        return PollQuestion.builder(this)
                .withTitle(title)
                .withAnswerVariant("Yes")
                .withAnswerVariant("No")
                .withMinAnswers(1)
                .withMaxAnswers(1)
                .and();
    }

    public PollBuilder addQuestion(PollQuestion question) {
        poll.addQuestion(question);
        return this;
    }

    public Poll build() {
        return poll;
    }
}
