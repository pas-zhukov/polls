package ru.pas_zhukov.analysis;

import ru.pas_zhukov.analysis.strategy.AnalyzeStrategy;
import ru.pas_zhukov.models.PollFillingData;

import java.util.List;

import static ru.pas_zhukov.analysis.QuestionStatistics.getQuestionsStatistics;

/**
 * Класс для анализа опросов с использованием стратегии
 */
public class PollAnalyzer {

    private AnalyzeStrategy strategy;

    public PollAnalyzer(AnalyzeStrategy strategy) {
        this.strategy = strategy;
    }

    public void changeAnalyzerStrategy(AnalyzeStrategy strategy) {
        this.strategy = strategy;
    }

    public void analyzePoll(List<PollFillingData> pollFillingDataList) {
        List<QuestionStatistics> statistics = collectStatistics(pollFillingDataList);
        strategy.makeAnalyze(statistics);
    }

    private List<QuestionStatistics> collectStatistics(List<PollFillingData> pollFillingDataList) {
        return getQuestionsStatistics(pollFillingDataList);
    }

}
