package com.wardrobeapp.backend.parser;

public interface Parser {
    String parseImage(String link);

    boolean support(String url);
}
