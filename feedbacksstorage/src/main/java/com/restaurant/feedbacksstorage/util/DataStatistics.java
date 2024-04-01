package com.restaurant.feedbacksstorage.util;


import java.text.DecimalFormat;
import java.util.*;

public class DataStatistics {

    private static DecimalFormat df = new DecimalFormat("#.##");

    public static <E extends Enum<E>> EnumMap<E, String> statisticsDataEnum(List<?> feedbacksData, Class<E> enumType) {
        EnumMap<E, String> statistic = new EnumMap<>(enumType);
        double totalSize = feedbacksData.size();


        for (E enumKey : enumType.getEnumConstants()) {
            Integer count = Collections.frequency(feedbacksData, enumKey.toString());
            statistic.put(enumKey, df.format((count / totalSize) * 100) + "%");
        }

        return statistic;
    }

    public static Map<Boolean, String> statisticDataBoolean(List<Boolean> feedbacksData, String statisticCategory) {
        HashMap<Boolean, String> statistic = new HashMap<>();
        double totalSize = feedbacksData.size();

        Integer frequencyTrue = Collections.frequency(feedbacksData, true);
        Double porcentageFrequencyTrue = (frequencyTrue / totalSize) * 100;
        Double porcentageFrequencyFalse = 100 - porcentageFrequencyTrue;

        statistic.put(true, df.format(porcentageFrequencyTrue));
        statistic.put(false, df.format(porcentageFrequencyFalse));

        return statistic;
    }
}

