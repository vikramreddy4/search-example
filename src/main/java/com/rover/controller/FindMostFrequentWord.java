package com.rover.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class FindMostFrequentWord {

	public List<String> getFrequentWord(String sentence) {
		System.out.print("Sentence : "+sentence);
		if(sentence == null) 
			return new ArrayList<String>(1);
		sentence = sentence.toLowerCase().replaceAll("[^a-z\\s]", "");
		String[] words = sentence.split(" ");
		Map<String,Integer> wordCount = new HashMap<String,Integer>();
		for(String word : words) {
			word = word.trim();
			if(word.length() > 0) {
				if(wordCount.containsKey(word)) {
					wordCount.put(word, (wordCount.get(word)+1));
				}else {
					wordCount.put(word, 1);
				}
			}
		}
		return processMapForFrequentWord(wordCount);
	}
	
	private List<String> processMapForFrequentWord(Map<String,Integer> wordCount) {
		int frequency = 0;
		List<String> frequentWords = new ArrayList<String>();
		for(String word : wordCount.keySet()) {
			if(wordCount.get(word) > frequency) {
				frequentWords.clear();
				frequentWords.add(word);
				frequency = wordCount.get(word);
			}else if(wordCount.get(word) == frequency) {
				frequentWords.add(word);
			}
		}
		System.out.println(String.format(" =>> The word(s) with highest frequency(%s) : %s", frequency, frequentWords.toString()));
		return frequentWords;
	}
	
	@Test
	public void testGetFrequentWordSimpleEmptyOrNULL() {
		String sentence = "";
		List<String> words = getFrequentWord(sentence);
		Assert.assertTrue(words.size() == 0);
		words = getFrequentWord(null);
		Assert.assertTrue(words.size() == 0);
	}

	@Test
	public void testGetFrequentWordSimpleHappyPath() {
		String sentence = "test";
		List<String> words = getFrequentWord(sentence);
		Assert.assertTrue(words.size() == 1);
		Assert.assertTrue(words.contains("test"));
	}

	@Test
	public void testGetFrequentWordMoreThanOneOccurance() {
		String sentence = "test test text texta";
		List<String> words = getFrequentWord(sentence);
		Assert.assertTrue(words.size() == 1);
		Assert.assertTrue(words.contains("test"));
	}

	@Test
	public void testGetFrequentWordTwoWords() {
		String sentence = "text! test text Test.";
		List<String> words = getFrequentWord(sentence);
		Assert.assertTrue(words.size() == 2);
		Assert.assertTrue(words.contains("test"));
		Assert.assertTrue(words.contains("text"));
	}

	@Test
	public void testGetFrequentWordGivenTest() {
		String sentence = "A blue shirt cost is twenty-four dollars but a white shirt is only twenty so I bought the white shirt.";
		List<String> words = getFrequentWord(sentence);
		Assert.assertTrue(words.size() == 1);
		Assert.assertTrue(words.contains("shirt"));
	}

	@Test
	public void testGetFrequentWordComplexCase() {
		String sentence = "text test text Test TEST";
		List<String> words = getFrequentWord(sentence);
		Assert.assertTrue(words.size() == 1);
		Assert.assertTrue(words.contains("test"));
	}

	@Test
	public void testGetFrequentWordThreeWords() {
		String sentence = "text test ten teXt TeSt TEN TeN tEXt tESt";
		List<String> words = getFrequentWord(sentence);
		Assert.assertTrue(words.size() == 3);
		Assert.assertTrue(words.contains("test"));
		Assert.assertTrue(words.contains("text"));
		Assert.assertTrue(words.contains("ten"));
	}

	@Test
	public void testGetFrequentWordThreeWordsSpecialCharacters() {
		String sentence = "text test ten teXt! TeSt TEN. TeN. tEXt tESt !!! ! ! !";
		List<String> words = getFrequentWord(sentence);
		Assert.assertTrue(words.size() == 3);
		Assert.assertTrue(words.contains("test"));
		Assert.assertTrue(words.contains("text"));
		Assert.assertTrue(words.contains("ten"));
	}
}
