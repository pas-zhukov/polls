package ru.pas_zhukov.analysis.strategy;

import ru.pas_zhukov.analysis.QuestionStatistics;

import java.util.List;

/**
 * Интерфейс стратегии анализа
 */
public interface AnalyzeStrategy {
    void makeAnalyze(List<QuestionStatistics> questionStatisticsList);
}
