package com.hk.trans;

import java.util.HashMap;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		String s = "x";
		String d = "w";
		System.out.println(s);
		char ch[] = {'q'};
		
		if (s == "kiran") {
			s.equals(ch);
		}
		
//		al.indexOf(i)
//		al.set(1, "b");
//		al.add(1, "d");

		java.util.Map<Integer, String> map = new HashMap<>();
		map.put(1, "value 1");
		map.put(2, "value 2");
		map.put(null, "value 3");
		map.put(1, "value 4");

		for (int i : map.keySet()) {
			System.out.println(map.get(i));

		}

	}
}
