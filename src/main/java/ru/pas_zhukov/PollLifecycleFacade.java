package ru.pas_zhukov;

import ru.pas_zhukov.analysis.PollAnalyzer;
import ru.pas_zhukov.analysis.PollAnalyzerProxy;
import ru.pas_zhukov.analysis.strategy.AnalyzeStrategy;
import ru.pas_zhukov.analysis.strategy.FullCountStrategy;
import ru.pas_zhukov.analysis.strategy.LeastFrequentAnswerStrategy;
import ru.pas_zhukov.analysis.strategy.MostFrequentAnswerStrategy;
import ru.pas_zhukov.models.Poll;
import ru.pas_zhukov.models.PollFillingData;
import ru.pas_zhukov.models.PollQuestion;
import ru.pas_zhukov.models.PollQuestionResponse;
import ru.pas_zhukov.util.DataGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class PollLifecycleFacade {
    public static Poll createPoll() {
        return Poll.builder()
                .withPollName("Programming Survey")
                .yesNoPollQuestion("Are you programmer?")
                .oneVariantPollQuestion("How many years of programming experience do you have?")
                .withAnswerVariant("0-1 years")
                .withAnswerVariant("1-3 years")
                .withAnswerVariant("3-5 years")
                .withAnswerVariant("5+ years")
                .and()
                .oneVariantPollQuestion("What is your favorite programming language?")
                .withAnswerVariant("Java")
                .withAnswerVariant("Python")
                .withAnswerVariant("C++")
                .and()
                .oneVariantPollQuestion("Do you like your job?")
                .withMinAnswers(0)
                .withMaxAnswers(1)
                .withAnswerVariant("Who knows..")
                .withAnswerVariant("Of course")
                .and()
                .pollQuestion("What are your strong qualities?")
                .withMinAnswers(0)
                .withMaxAnswers(5)
                .withAnswerVariant("Leadership")
                .withAnswerVariant("Teamwork")
                .withAnswerVariant("Problem-solving")
                .withAnswerVariant("Communication")
                .withAnswerVariant("Creativity")
                .and()
                .build();
    }

    public static List<PollFillingData> getUserResponses(Poll poll) {
        return IntStream.range(0, 50)
                .mapToObj(i -> generateRandomFillingData(poll))
                .toList();
    }

    public static void makeAnalyzePoll(List<PollFillingData> pollFillingDataList) {
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

    private static PollFillingData generateRandomFillingData(Poll poll) {
        Random random = new Random();
        List<PollQuestionResponse> responses = new ArrayList<>();

        for (PollQuestion question : poll.getPollQuestionList()) {
            int minAnswers = question.getMinAnswers();
            int maxAnswers = question.getMaxAnswers();
            List<String> answerVariants = question.getAnswers();

            int numberOfAnswers = minAnswers + random.nextInt(maxAnswers - minAnswers + 1);
            List<String> selectedVariants = new ArrayList<>();

            for (int i = 0; i < numberOfAnswers; i++) {
                String randomVariant = answerVariants.get(random.nextInt(answerVariants.size()));
                if (!selectedVariants.contains(randomVariant)) {
                    selectedVariants.add(randomVariant);
                }
            }

            responses.add(new PollQuestionResponse(question, selectedVariants));
        }

        return new PollFillingData("User №" + random.nextInt(), responses);
    }
}
