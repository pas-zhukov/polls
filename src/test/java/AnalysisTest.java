import org.junit.Test;
import ru.pas_zhukov.analysis.PollAnalyzer;
import ru.pas_zhukov.analysis.strategy.AnalyzeStrategy;
import ru.pas_zhukov.analysis.strategy.FullCountStrategy;
import ru.pas_zhukov.analysis.strategy.LeastFrequentAnswerStrategy;
import ru.pas_zhukov.analysis.strategy.MostFrequentAnswerStrategy;
import ru.pas_zhukov.models.Poll;
import ru.pas_zhukov.models.PollFillingData;
import ru.pas_zhukov.util.DataGenerator;

import java.util.List;
import java.util.stream.IntStream;

public class AnalysisTest {
    @Test
    public void analyzerBasicUsage() {
        Poll poll = DataGenerator.generateDefaultPoll();
        List<PollFillingData> pollFillingDataList = IntStream.range(0, 50)
                .mapToObj(i -> DataGenerator.generateRandomFillingData(poll))
                .toList();

        AnalyzeStrategy fullCountStrategy = new FullCountStrategy();
        AnalyzeStrategy leastFrequentAnswerStrategy = new LeastFrequentAnswerStrategy();
        AnalyzeStrategy mostFrequentAnswerStrategy = new MostFrequentAnswerStrategy();

        PollAnalyzer analyzer = new PollAnalyzer(fullCountStrategy);
        analyzer.analyzePoll(pollFillingDataList);

        analyzer.changeAnalyzerStrategy(leastFrequentAnswerStrategy);
        analyzer.analyzePoll(pollFillingDataList);

        analyzer.changeAnalyzerStrategy(mostFrequentAnswerStrategy);
        analyzer.analyzePoll(pollFillingDataList);
    }
}
