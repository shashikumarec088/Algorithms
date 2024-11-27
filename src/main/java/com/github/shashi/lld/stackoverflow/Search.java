package com.github.shashi.lld.stackoverflow;

import java.util.List;

public interface Search {
    public List<Question> searchByTags(String tagName);
    public List<Question> searchByUsers(String username);
    public List<Question> searchByWords(String word);
}
