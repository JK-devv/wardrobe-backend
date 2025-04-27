package com.wardrobeapp.backend.parser;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParserStrategy {
    private final List<Parser> parsers;

    public ParserStrategy(List<Parser> parsers) {
        this.parsers = parsers;
    }

    public Parser resolve(String url) {
        return parsers
                .stream()
                .filter(p -> p.support(url))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("Unsupported domain for parsing : %s", url)));
    }
}
