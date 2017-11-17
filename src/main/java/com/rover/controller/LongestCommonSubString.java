package com.rover.controller;

import org.junit.Assert;
import org.junit.Test;

public class LongestCommonSubString {

	public String getCommonString(String str1, String str2) {
		if(str1 == null || str2 == null || str1.trim().length() == 0 || str2.trim().length() == 0)
		return "";
		str1 = str1.trim().toLowerCase();
		str2 = str2.trim().toLowerCase();
		if(str1.length() > str2.length()) {
			return findCommonString(str2, str1);
		}
		return findCommonString(str1, str2);
	}

	private String findCommonString(String smallStr, String largeStr) {
		String response = "";
		int length = 1;
		for(int i = 0;i<smallStr.length();i++) {
			if(smallStr.length() <= i+length) {
				break;
			}
			String searchStr = smallStr.substring(i, i+length);
			while(largeStr.indexOf(searchStr) > -1) {
				length++;
				response = searchStr;
				if(smallStr.length() <= i+length) {
					break;
				}
				searchStr = smallStr.substring(i, i+length);
			}
		}
		return response;
	}
	
	@Test
	public void testEmpty() {
		Assert.assertTrue(getCommonString("", "").equalsIgnoreCase(""));
	}

	@Test
	public void testNULL() {
		Assert.assertTrue(getCommonString(null, null).equalsIgnoreCase(""));
	}

	@Test
	public void testHappyPath() {
		Assert.assertEquals(getCommonString("abcdefg", "gntyncdehjy"), "cde");
	}

	@Test
	public void testHappyPathCaseSensitive() {
		Assert.assertEquals(getCommonString("abzdefg", "gntyncDehjy "), "de");
	}

}
