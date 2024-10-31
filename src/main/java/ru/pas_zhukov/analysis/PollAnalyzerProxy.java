package ru.pas_zhukov.analysis;

import ru.pas_zhukov.analysis.strategy.AnalyzeStrategy;
import ru.pas_zhukov.models.PollFillingData;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

public class PollAnalyzerProxy extends PollAnalyzer {

    private static final Logger logger = Logger.getLogger(PollAnalyzerProxy.class.getName());

    private final PollAnalyzer delegate;

    public PollAnalyzerProxy(PollAnalyzer pollAnalyzer) {
        super(null);
        delegate = pollAnalyzer;
        Field strategyField = null;
        try {
            strategyField = pollAnalyzer.getClass().getDeclaredField("strategy");
            strategyField.setAccessible(true);
            this.changeAnalyzerStrategy((AnalyzeStrategy) strategyField.get(delegate));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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

}
