package com.xtsoft.kernel.security.shiro.web.filter.authc;

public class Test {
	public static void main(String[] args) {
		String str1 = "HelloWorld";
		String str2 = "HelloWorld";
		String str3 = new String("HelloWorld");
		String str4 = "Hello";
		String str5 = "World";
		String str6 = "Hello" + "World";
		String str7 = str4 + str5;

		System.out.println("str1 == str2 result: " + (str1 == str2)); // 1. true

		System.out.println("str1 == str3 result: " + (str1 == str3)); // 2.
																		// false

		System.out.println("str1 == str6 result: " + (str1 == str6)); // 3. true

		System.out.println("str1 == str7 result: " + (str1 == str7)); // 4.
																		// false

		System.out.println("str1 == str7.intern() result: " + (str1 == str7.intern())); // 5.
																						// true

		System.out.println("str3 == str3.intern() result: " + (str3 == str3.intern())); // 6.false
	}
}
