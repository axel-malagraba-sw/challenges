package com.smallworldfs.anagrams;

import java.util.HashMap;
import java.util.Map;

/**
 * Escribe una función que reciba dos String y retorne true o false según sean o no anagramas.
 * 
 * <li>Un Anagrama consiste en formar una palabra reordenando TODAS las letras de otra palabra
 * inicial.</li>
 * 
 * <li>NO hace falta comprobar que ambas palabras existan.</li>
 * 
 * <li>Dos palabras exactamente iguales no son anagrama.</li> </li>
 */
public class AnagramChallenge {

    public boolean isAnagram(String left, String right) {
        return candidatesAreValid(left, right) && candidatesAreAnagrams(left, right);
    }

    private boolean candidatesAreValid(String left, String right) {
        return left != null && right != null && left.length() == right.length();
    }

    private boolean candidatesAreAnagrams(String left, String right) {
        Map<Character, Integer> leftCharacters = new HashMap<>(left.length());
        Map<Character, Integer> rightCharacters = new HashMap<>(right.length());

        for (int index = 0; index < left.length(); index++) {
            char leftChar = getUppercaseCharAt(left, index);
            char rightChar = getUppercaseCharAt(right, index);

            if (leftChar != rightChar) {
                addCharacter(leftCharacters, leftChar);
                addCharacter(rightCharacters, rightChar);
            }
        }
        return !leftCharacters.isEmpty() && leftCharacters.equals(rightCharacters);
    }

    private void addCharacter(Map<Character, Integer> frequencyMap, char character) {
        frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
    }

    private char getUppercaseCharAt(String string, int index) {
        return Character.toUpperCase(string.charAt(index));
    }
}
