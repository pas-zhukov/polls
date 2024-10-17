package ru.pas_zhukov.util;

import ru.pas_zhukov.models.Poll;
import ru.pas_zhukov.models.PollFillingData;
import ru.pas_zhukov.models.PollQuestion;
import ru.pas_zhukov.models.PollQuestionResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    public static PollFillingData generateRandomFillingData(Poll poll) {
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

        return new PollFillingData("User №" + random.nextInt(),responses);
    }

    public static Poll generateDefaultPoll() {
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
}
