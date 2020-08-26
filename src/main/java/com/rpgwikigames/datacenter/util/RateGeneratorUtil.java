package com.rpgwikigames.datacenter.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

public class RateGeneratorUtil {

	public static float round(float d, int decimalPlace) {
		return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	public static float[] randomRating(float sumRating) {
		float loseRate = round((5.0f - sumRating) * 5, 1);
		int i = 0;
		float[] result = new float[] { 5.0f, 5.0f, 5.0f, 5.0f, 5.0f };
		Random rd = new Random();
		while (i < result.length) {
			float rdLoseRate = 0f;
			if (sumRating >= 4) {
				rdLoseRate = round(rd.nextFloat(), 1);
			} else {
				rdLoseRate = round(rd.nextFloat() + rd.nextInt(1) + 1, 1);
			}
			if (rdLoseRate > loseRate || (i == result.length - 1))
				rdLoseRate = loseRate;

			result[i] = round(5.0f - rdLoseRate, 1);
			loseRate -= rdLoseRate;
			i++;
		}
		return result;
	}

	public static void main(String[] args) {
		float[] result = randomRating(0f);
		System.out.println(Arrays.toString(result));
		float sum = 0;
		for (int j = 0; j < result.length; j++) {
			sum += result[j];
		}
		System.out.println(round(sum / 5, 1));
	}
}
