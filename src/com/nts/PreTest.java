package com.nts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PreTest {

	public static void main(String[] args) {
		
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
				Arrays.fill(result, 0);
			} else {
				break;
			}
		}

		for(int i = 0; i < result.length; i++)
		{
			String res = "";
			res += "Player" + (count + 1) + ": ";
			res += list.stream().skip(i * 7).limit(7).collect(Collectors.joining(", "));
			res += " : sum = " + result[count++];
			System.out.println(res);
		}

		for (int i = 0; i < result.length; i++) {
			if (arrtemp[0] == result[i]) {
				System.out.println("Winner : Player" + (i + 1));
			}
		}
	}
}