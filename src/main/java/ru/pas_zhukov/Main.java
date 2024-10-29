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

public class Main {
    public static void main(String[] args) {
        Poll poll = DataGenerator.generateDefaultPoll();
        List<PollFillingData> pollFillingDataList = IntStream.range(0, 50)
                .mapToObj(i -> DataGenerator.generateRandomFillingData(poll))
                .toList();

        AnalyzeStrategy fullCountStrategy = new FullCountStrategy();
        AnalyzeStrategy leastFrequentAnswerStrategy = new LeastFrequentAnswerStrategy();
        AnalyzeStrategy mostFrequentAnswerStrategy = new MostFrequentAnswerStrategy();

        PollAnalyzer delegateAnalyzer = new PollAnalyzer(fullCountStrategy);
        PollAnalyzer analyzer = new PollAnalyzerProxy(delegateAnalyzer);
        analyzer.analyzePoll(pollFillingDataList);

        analyzer.changeAnalyzerStrategy(leastFrequentAnswerStrategy);
        analyzer.analyzePoll(pollFillingDataList);

        analyzer.changeAnalyzerStrategy(mostFrequentAnswerStrategy);
        analyzer.analyzePoll(pollFillingDataList);
    }
}
