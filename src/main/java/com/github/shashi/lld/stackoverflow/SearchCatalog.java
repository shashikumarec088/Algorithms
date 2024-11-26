package com.github.shashi.lld.stackoverflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchCatalog implements Search {
    // Map tag names to lists of questions associated with those tags
    private HashMap<Tag, List<Question>> questionsUsingTags;
    // Map usernames to lists of questions created by those users
    private HashMap<User, List<Question>> questionsUsingUsers;
    // Map words to lists of questions containing those words
    private HashMap<String, List<Question>> questionsUsingWords;

    public List<Question> searchByTags(String tagName) {
        return questionsUsingTags.getOrDefault(tagName, new ArrayList<>());
    }
    public List<Question> searchByUsers(String username) {
        return questionsUsingUsers.getOrDefault(username, new ArrayList<>());
    }
    public List<Question> searchByWords(String word) {
        return questionsUsingWords.getOrDefault(word, new ArrayList<>());
    }
}