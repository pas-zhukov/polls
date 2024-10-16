package ru.pas_zhukov.builders;

import ru.pas_zhukov.models.PollQuestion;

import java.util.ArrayList;

public class PollQuestionBuilder {
    private final PollQuestion question;
    private final PollBuilder pollBuilder;

    public PollQuestionBuilder(PollBuilder pollBuilder) {
        this.question = new PollQuestion("Empty title question", 0, 1, new ArrayList<>());
        this.pollBuilder = pollBuilder;
    }

    public PollQuestionBuilder withTitle(String title) {
        question.setTitle(title);
        return this;
    }

    public PollQuestionBuilder withMinAnswers(int minAnswers) {
        question.setMinAnswers(minAnswers);
        return this;
    }

    public PollQuestionBuilder withMaxAnswers(int maxAnswers) { // TODO: добавить проверки!
        question.setMaxAnswers(maxAnswers);
        return this;
    }

    public PollQuestionBuilder withAnswerVariant(String answerVariant) {
        question.addAnswer(answerVariant);
        return this;
    }

    public PollBuilder and() {
        PollQuestion pollQuestion = this.buildQuestion();
        return pollBuilder.addQuestion(pollQuestion);
    }

    public PollQuestion buildQuestion() {
        return question;
    }
}
