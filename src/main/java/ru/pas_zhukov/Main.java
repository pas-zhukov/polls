package ru.pas_zhukov;

import ru.pas_zhukov.analysis.PollAnalyzer;
import ru.pas_zhukov.analysis.PollAnalyzerProxy;
import ru.pas_zhukov.analysis.strategy.AnalyzeStrategy;
import ru.pas_zhukov.analysis.strategy.FullCountStrategy;
import ru.pas_zhukov.analysis.strategy.LeastFrequentAnswerStrategy;
import ru.pas_zhukov.analysis.strategy.MostFrequentAnswerStrategy;
import ru.pas_zhukov.models.Poll;
import ru.pas_zhukov.models.PollFillingData;
import ru.pas_zhukov.util.DataGenerator;

import java.util.List;
import java.util.stream.IntStream;

import static ru.pas_zhukov.PollLifecycleFacade.*;

public class Main {
    public static void main(String[] args) {
        Poll poll = createPoll();
        List<PollFillingData> pollFillingDataList= getUserResponses(poll);
        makeAnalyzePoll(pollFillingDataList);
    }
}
