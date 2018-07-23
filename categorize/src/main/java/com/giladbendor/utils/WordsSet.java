package com.giladbendor.utils;

import java.util.TreeMap;

/**
 * A set of words (strings) - stored in an alphanumeric-tree scheme.
 * Given a position into a big string, this class can quickly find
 *  the longest word that starts in that position.
 */
public class WordsSet {

	/**
	 * If this is true, then this words-set contains the empty-word.
	 */
	private boolean wordEndsHere;

	/**
	 * Map from every character that is the prefix of any added word -
	 *  to the WordsSet of all of these added words - but without that prefix character.
	 */
	private TreeMap<Character, WordsSet> chars;

	/**
	 * Constructor.
	 */
	public WordsSet() {
		chars = new TreeMap<Character, WordsSet>();
	}

	/**
	 * Add a word to this set.
	 */
	public void addWord(String word) {
		addWord(word, 0);
	}

	/**
	 * Add a word - that is the suffix of some string.
	 * The added word is:   prefixAndWord.substring(prefixLength)
	 */
	private void addWord(String prefixAndWord, int prefixLength) {
		if (prefixLength == prefixAndWord.length()) {
			wordEndsHere = true;
		} else {
			char nextChar = prefixAndWord.charAt(prefixLength);
			WordsSet subWordsSet = chars.get(nextChar);
			if (subWordsSet == null) {
				subWordsSet = new WordsSet();
				chars.put(nextChar, subWordsSet);
			}
			subWordsSet.addWord(prefixAndWord, prefixLength + 1);
		}
	}

	/**
	 * If a word that was added by addWord() is found in "source" at offset "offset",
	 *  then return the found word's length.
     * If nothing is found - return -1.
	 */
	public int getLongestWord(String source, int offset) {
		// First check if next character can be matched - as we want the longest match.
		if (offset < source.length()) {
			char nextChar = source.charAt(offset);
			WordsSet subWordsSet = chars.get(nextChar);
			if (subWordsSet != null) {
				int nextCharMatch = subWordsSet.getLongestWord(source, offset + 1);
				if (nextCharMatch != -1) {
					// A sub-word is found - return it.
					return nextCharMatch + 1;
				}
			}
		}

		// No sub-word. Return 0 if this WordsSet ends a word.
		if (wordEndsHere) {
			return 0;
		} else {
			return -1;
		}
	}
}
