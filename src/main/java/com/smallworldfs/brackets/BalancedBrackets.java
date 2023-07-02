package com.smallworldfs.brackets;

import java.util.Map;
import java.util.Stack;
import lombok.RequiredArgsConstructor;

public class BalancedBrackets {

    public static String isBalanced(String brackets) {
        return new Brackets(brackets).isBalanced() ? "YES" : "NO";
    }

    @RequiredArgsConstructor
    static class Brackets {

        private static final Map<Character, Character> BRACKETS = Map.of(
                '(', ')',
                '[', ']',
                '{', '}');

        private final String string;

        boolean isBalanced() {
            Stack<Character> stack = new Stack<>();

            for (char character : string.toCharArray()) {
                if (!processCharacter(stack, character)) {
                    return false;
                }
            }
            return stack.isEmpty();
        }

        private boolean processCharacter(Stack<Character> stack, char character) {
            if (BRACKETS.containsKey(character)) {
                stack.push(character);
                return true;
            }
            return !stack.isEmpty() && character == BRACKETS.get(stack.pop());
        }
    }
}
