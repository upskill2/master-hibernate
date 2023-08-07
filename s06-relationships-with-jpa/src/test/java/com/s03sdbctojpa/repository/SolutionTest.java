package com.s03sdbctojpa.repository;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

class SolutionTest {

    public int lengthOfLongestSubstring(String s) {

        if (s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        int beg = 0;
        int end = 2;
        int result = 1;

        Set<Character> chars = new HashSet<>();
        chars.add(s.charAt(0));

        while (end <= s.length()) {
            s.substring(beg, end).chars().forEach(c -> chars.add((char) c));
            if (chars.size() == end - beg) {
                end++;
                result = Math.max(result, chars.size());
            } else {
                chars.clear();
                end++;
                beg++;
            }
        }

        return result;
    }


    @Test
    void test() {
        String s = "abcabcbb";
        String s1 = "bbbbb";
        String s2 = "pwwkew";
        String s3 = "";
        String s4 = "q";
        String s5 = "au";

        assertEquals(3, lengthOfLongestSubstring(s));
        assertEquals(1, lengthOfLongestSubstring(s1));
        assertEquals(3, lengthOfLongestSubstring(s2));
        assertEquals(0, lengthOfLongestSubstring(s3));
        assertEquals(1, lengthOfLongestSubstring(s4));
        assertEquals(2, lengthOfLongestSubstring(s5));

    }

}
