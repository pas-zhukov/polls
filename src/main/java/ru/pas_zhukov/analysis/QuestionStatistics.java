package ru.pas_zhukov.analysis;

import ru.pas_zhukov.models.PollFillingData;
import ru.pas_zhukov.models.PollQuestion;

import java.util.*;

/**
 * Класс для хранения статистики по вопросам
 */
public class QuestionStatistics {
    private final String questionTitle;
    private final Map<String, Integer> selectedVariantsCount;
    private final Map<String, Integer> userSelectedVariantsCount;

    private QuestionStatistics(PollQuestion pollQuestion) {
        questionTitle = pollQuestion.getTitle();
        selectedVariantsCount = new HashMap<>();
        userSelectedVariantsCount = new HashMap<>();
        pollQuestion.getAnswers().forEach(answer -> selectedVariantsCount.put(answer, 0));
    }

    public void increaseSelectedVariantCount(String variant) {
        selectedVariantsCount.merge(variant, 1, Integer::sum);
    }

    public void increaseUserSelectedVariantCount(String username) {
        userSelectedVariantsCount.put(username, Optional.ofNullable(userSelectedVariantsCount.get(username)).orElse(0) + 1);
    }

    public static List<QuestionStatistics> getQuestionsStatistics(List<PollFillingData> pollFillingDataList) {
        Map<String, QuestionStatistics> questionsStatistics = new HashMap<>();

        for (PollFillingData pollFillingData : pollFillingDataList) {
            String userName = pollFillingData.getUsername();
            pollFillingData.getResponses()
                    .forEach(pollQuestionResponse -> {
                        QuestionStatistics questionStatistics = questionsStatistics.computeIfAbsent(pollQuestionResponse.getPollQuestion().getTitle(),
                                k -> new QuestionStatistics(pollQuestionResponse.getPollQuestion()));
                        questionStatistics.increaseUserSelectedVariantCount(userName);
                        pollQuestionResponse.getSelectedVariants().forEach(questionStatistics::increaseSelectedVariantCount);
                    });
        }
        return questionsStatistics.values().stream().toList();
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public Map<String, Integer> getSelectedVariantsCount() {
        return selectedVariantsCount;
    }

    public Map<String, Integer> getUserSelectedVariantsCount() {
        return userSelectedVariantsCount;
    }

    public Map.Entry<String, Integer> getMostFrequentAnswer() {
        return selectedVariantsCount.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow();
    }

    public Map.Entry<String, Integer> getLeastFrequentAnswer() {
        return selectedVariantsCount.entrySet().stream().min(Map.Entry.comparingByValue()).orElseThrow();
    }

    @Override
    public String toString() {
        return "QuestionStatistics{" +
                "questionTitle='" + questionTitle + '\'' +
                ", selectedVariantsCount=" + selectedVariantsCount +
                ", userSelectedVariantsCount=" + userSelectedVariantsCount +
                '}';
    }
}
