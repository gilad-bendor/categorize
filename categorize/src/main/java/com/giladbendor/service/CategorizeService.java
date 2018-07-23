package com.giladbendor.service;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;
import java.io.InputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import com.giladbendor.utils.WordsSet;
import org.springframework.stereotype.Service;

@Service
public class CategorizeService {
	
	private static final String dictionaryPath = "/app/dictionary.txt";

	private WordsSet dictionary = new WordsSet();

	/**
	 * Constructor: read the dictionary file into a WordsSet.
	 */
	public CategorizeService() throws IOException {
		// Scan the dictionary file - line by line.
		BufferedReader reader = new BufferedReader(new FileReader(dictionaryPath));
		while(reader.ready()) {
			String word = reader.readLine().trim();
			if (word.length() > 0) {
				// A dictionary word is found - add to WordsSet.
				dictionary.addWord(word);
			}
		}
	}

	/**
	 * Given a phrase (a big string), return a mapping from found dictionary
	 *  words to the first offset-in-phrase that they were found in.
	 */
	public Map<String, Integer> categorize(String phrase) {
		Map<String, Integer> mapping = new TreeMap<String, Integer>();

		// Scan phrase - character by character.
		for (int phraseOffset = 0; phraseOffset < phrase.length(); phraseOffset++) {
			// Check if any dictionary word starts in this offset.
			int foundWordLength = dictionary.getLongestWord(phrase, phraseOffset);
			if (foundWordLength != -1) {
				// A word is found. Add it to the result mapping.
				String foundWord = phrase.substring(phraseOffset, phraseOffset + foundWordLength);
				if (mapping.get(foundWord) == null) {    // ...ignore already found words - we want first offset
					mapping.put(foundWord, phraseOffset);
				}

				// Skip to after the found word (we don't want to find words inside a found word.
				phraseOffset += foundWordLength - 1;     // ...the "-1" is because the loop will soon do "+1"
			}
		}

		return mapping;
	}
}
