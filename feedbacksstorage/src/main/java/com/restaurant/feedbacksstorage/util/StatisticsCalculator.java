package com.restaurant.feedbacksstorage.util;


import java.text.DecimalFormat;
import java.util.*;

public class StatisticsCalculator {

    private static DecimalFormat df = new DecimalFormat("#.##");

    public static <E extends Enum<E>> EnumMap<E, String> statisticsDataEnum(List<?> feedbacksData, Class<E> enumType) {
        EnumMap<E, String> statistic = new EnumMap<>(enumType);
        double totalSize = feedbacksData.size();


        for (E enumKey : enumType.getEnumConstants()) {
            Integer frequency = Collections.frequency(feedbacksData, enumKey.toString());
            statistic.put(enumKey, df.format((frequency / totalSize) * 100) + "%");
        }

        return statistic;
    }

    public static Map<Boolean, String> statisticDataBoolean(List<Boolean> feedbacksData) {
        Map<Boolean, String> statistic = new HashMap<>();
        double totalSize = feedbacksData.size();

        Integer frequencyTrue = Collections.frequency(feedbacksData, true);
        Double percentageFrequencyTrue = (frequencyTrue / totalSize) * 100;
        Double percentageFrequencyFalse = 100 - percentageFrequencyTrue;

        statistic.put(true, df.format(percentageFrequencyTrue) + "%");
        statistic.put(false, df.format(percentageFrequencyFalse) + "%");

        return statistic;
    }

    public static Map<String, String> statisticDataRating(List<Integer> feedbacksDataRating) {
        Map<String, String> statistic = new HashMap<>();
        double totalSize = feedbacksDataRating.size();
        int[] frequency = new int[11];
        double averageRating = 0;

        for (int rating : feedbacksDataRating) {
            frequency[rating]++;
            averageRating += rating;
        }

        for (int i = 1; i < frequency.length; i++) {
            statistic.put(String.valueOf(i), df.format((frequency[i] / totalSize) * 100) + "%");
        }
        statistic.put("averageRating", df.format(averageRating / totalSize));

        return statistic;
    }

    public static Map<String, String> statisticDataAge(List<Integer> feedbacksDataAge) {
        Map<String, String> statistic = new HashMap<>();
        double totalSize = feedbacksDataAge.size();
        int[] ageGroupFrequencies = new int[5];
        double averageAge = 0;

        for (int age : feedbacksDataAge) {
            if (age <= 24) ageGroupFrequencies[0]++;
            else if (age <= 35) ageGroupFrequencies[1]++;
            else if (age <= 50) ageGroupFrequencies[2]++;
            else if (age <= 70) ageGroupFrequencies[3]++;
            else ageGroupFrequencies[4]++;
            averageAge += age;
        }

        String[] ageGroups = {"16-24", "25-35", "36-50", "51-70", "71-110"};
        for (int i = 0; i < ageGroupFrequencies.length; i++) {
            statistic.put(ageGroups[i], df.format((ageGroupFrequencies[i] / totalSize) * 100) + "%");
        }
        statistic.put("averageAge", df.format(averageAge / totalSize));

        return statistic;
    }
}

