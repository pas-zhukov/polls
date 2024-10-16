package ru.pas_zhukov.models;

import ru.pas_zhukov.builders.PollBuilder;
import ru.pas_zhukov.builders.PollQuestionBuilder;

import java.util.List;

public class PollQuestion {
    private String title;
    private Integer minAnswers;
    private Integer maxAnswers;
    private final List<String> answers;

    public PollQuestion(String title, Integer minAnswers, Integer maxAnswers, List<String> answers) {
        this.title = title;
        this.minAnswers = minAnswers;
        this.maxAnswers = maxAnswers;
        this.answers = answers;
    }

    public static PollQuestionBuilder builder(PollBuilder pollBuilder) {
        return new PollQuestionBuilder(pollBuilder);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinAnswers() {
        return minAnswers;
    }

    public void setMinAnswers(Integer minAnswers) {
        this.minAnswers = minAnswers;
    }

    public Integer getMaxAnswers() {
        return maxAnswers;
    }

    public void setMaxAnswers(Integer maxAnswers) {
        this.maxAnswers = maxAnswers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void addAnswer(String answer) {
        answers.add(answer);
    }

    @Override
    public String toString() {
        return "PollQuestion{" +
                "title='" + title + '\'' +
                ", minAnswers=" + minAnswers +
                ", maxAnswers=" + maxAnswers +
                ", answers=" + answers +
                '}';
    }
}
