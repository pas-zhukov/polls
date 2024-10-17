package ru.pas_zhukov.models;

import java.util.List;

public class PollQuestionResponse {
    private PollQuestion pollQuestion;
    List<String> selectedVariants;

    /**
     * Ответ на вопрос опроса
     * @param pollQuestion вопрос
     * @param selectedVariants выбранные варианты ответа
     */
    public PollQuestionResponse(PollQuestion pollQuestion, List<String> selectedVariants) {
        this.pollQuestion = pollQuestion;
        this.selectedVariants = selectedVariants;
    }

    public PollQuestion getPollQuestion() {
        return pollQuestion;
    }

    public List<String> getSelectedVariants() {
        return selectedVariants;
    }
}
