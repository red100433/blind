package com.nts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PreTest {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		StringBuffer res;
		int[] result = new int[4];
		int[] arrtemp;
		int count = 0;
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 13; i++) {
			list.add("S" + (i + 1));
			list.add("D" + (i + 1));
			list.add("H" + (i + 1));
			list.add("C" + (i + 1));
		}

		while (true) {
			Collections.shuffle(list);

			for (int i = 0; i < 28; i++) {
				if (list.get(i).length() == 2) {
					result[count] += Integer.parseInt(list.get(i).substring(1, 2));
				} else {
					result[count] += Integer.parseInt(list.get(i).substring(1, 3));
				}

				if (i % 7 == 6) {
					count++;
				}
			}

			arrtemp = Arrays.copyOf(result, result.length);
			Arrays.sort(arrtemp);
			count = 0;
			if (arrtemp[0] == arrtemp[1]) {
				for (int i = 0; i < result.length; i++) {
					result[i] = 0;
				}
			} else {
				break;
			}
		}

		res = new StringBuffer("Player" + (count + 1) + " : ");
		for (int i = 0; i < 28; i++) {
			res.append(list.get(i) + ", ");
			if (i % 7 == 6) {
				res.deleteCharAt(res.length() - 2);
				res.append(": sum = " + result[count]);
				System.out.println(res);
				res = new StringBuffer("Player" + (count + 2) + " : ");
				count++;
			}
		}

		for (int i = 0; i < 4; i++) {
			if (arrtemp[0] == result[i]) {
				System.out.println("Winner : Player" + (i + 1));
			}
		}

		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("실행시간 : " + elapsedTime / 1000.0 + " ms");
	}
}