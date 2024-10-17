package ru.pas_zhukov.analysis.strategy;

import ru.pas_zhukov.analysis.QuestionStatistics;

import java.util.List;
import java.util.Map;

/**
 * Класс для анализа, считающего полные ответы
 */
public class FullCountStrategy implements AnalyzeStrategy {
    @Override
    public void makeAnalyze(List<QuestionStatistics> questionStatisticsList) {
        StringBuilder report = new StringBuilder();
        report.append("----- Full Count Analysis -----\n");
        for (QuestionStatistics questionStatistics : questionStatisticsList) {
            report.append("Question: ")
                    .append(questionStatistics.getQuestionTitle())
                    .append("\n");
            int totalUserPolledCount = questionStatistics.getUserSelectedVariantsCount().size();
            for (Map.Entry<String, Integer> variantCount : questionStatistics.getSelectedVariantsCount().entrySet()) {
                report.append(variantCount.getValue())
                        .append(" out of ")
                        .append(totalUserPolledCount)
                        .append(" chose: ")
                        .append(variantCount.getKey())
                        .append("\n");
            }
            report.append("\n");
        }
        System.out.println(report);
    }
}
