package ru.pas_zhukov.analysis.strategy;

import ru.pas_zhukov.analysis.QuestionStatistics;

import java.util.List;
import java.util.Map;

/**
 * Класс для анализа, определяющего наименее популярные ответы
 */
public class LeastFrequentAnswerStrategy implements AnalyzeStrategy {
    @Override
    public void makeAnalyze(List<QuestionStatistics> questionStatisticsList) {
        StringBuilder report = new StringBuilder();
        report.append("----- Least Frequent Answers Analysis -----\n");
        for (QuestionStatistics questionStatistics : questionStatisticsList) {
            report.append("Question: ")
                    .append(questionStatistics.getQuestionTitle())
                    .append("\n");
            Map.Entry<String, Integer> mostFrequentAnswer = questionStatistics.getLeastFrequentAnswer();
            report.append("Least Frequent Answer: ")
                    .append(mostFrequentAnswer.getKey())
                    .append("\n")
                    .append(mostFrequentAnswer.getValue())
                    .append(" participants chose this variant\n\n");
        }
        System.out.println(report);
    }
}
