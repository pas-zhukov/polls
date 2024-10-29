package ru.pas_zhukov.analysis;

import ru.pas_zhukov.analysis.strategy.AnalyzeStrategy;
import ru.pas_zhukov.models.PollFillingData;

import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

public class PollAnalyzerProxy extends PollAnalyzer {

    private static final Logger logger = Logger.getLogger(PollAnalyzerProxy.class.getName());

    private final PollAnalyzer delegate;

    public PollAnalyzerProxy(PollAnalyzer pollAnalyzer) {
        super(pollAnalyzer.getStrategy());
        delegate = pollAnalyzer;
    }

    @Override
    public void changeAnalyzerStrategy(AnalyzeStrategy strategy) {
        delegate.changeAnalyzerStrategy(strategy);
    }

    @Override
    public void analyzePoll(List<PollFillingData> pollFillingDataList) {
        logger.info("Begin poll analysis");
        Instant start = Instant.now();
        delegate.analyzePoll(pollFillingDataList);
        Instant end = Instant.now();
        logger.info("End poll analysis");
        logger.info("Analysis took: " + (end.toEpochMilli() - start.toEpochMilli()) + " ms");
    }

    @Override
    public AnalyzeStrategy getStrategy() {
        return delegate.getStrategy();
    }
}
