package com.smallworldfs.argon2015;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    private static final int SEASIDE = 0;
    private static final int MOUNTAINSIDE = 1;

    private Map<Integer, Integer> pastSums;
    private int maxLength = 0;
    private int sum = 0;

    private int[] maxSeasideDays;
    private int[] forecast;


    public int solution(int[] forecast) {
        this.forecast = forecast;
        this.maxSeasideDays = maximizeSeasideDays();

        return maximizeVacationDays();
    }

    private int[] maximizeSeasideDays() {
        initialize();
        int[] result = new int[forecast.length];

        for (int index = 0; index < forecast.length; index++) {
            result[index] = getMaxDaysForIndex(index, index, SEASIDE);
        }
        return result;
    }

    private int maximizeVacationDays() {
        initialize();
        int maxDays = 0;

        for (int index = forecast.length - 1; index > 0; index--) {
            int seasideDays = maxSeasideDays[index - 1];
            int mountainDays = getMaxDaysForIndex(index, forecast.length - 1 - index, MOUNTAINSIDE);

            if (seasideDays > 0 && mountainDays > 0) {
                maxDays = Math.max(maxDays, seasideDays + mountainDays);
            }
        }
        return maxDays;
    }

    private int getMaxDaysForIndex(int index, int distanceFromOrigin, int maximized) {
        sum += forecast[index] == maximized ? 1 : -1;

        if (sum >= 1) {
            maxLength = distanceFromOrigin + 1;
        } else if (!pastSums.containsKey(sum)) {
            pastSums.put(sum, distanceFromOrigin);
        }

        if (pastSums.containsKey(sum - 1)) {
            maxLength = Math.max(maxLength, Math.abs(distanceFromOrigin - pastSums.get(sum - 1)));
        }
        return maxLength;
    }

    private void initialize() {
        pastSums = new HashMap<>(forecast.length);
        maxLength = 0;
        sum = 0;
    }
}
