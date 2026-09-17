package com.fineui.java.examples.thirdparty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** 多值自动完成的远程候选项接口。 */
@RestController
public class AutoCompleteMultiValuesRemoteController {

    private static final List<String> LANGUAGES = List.of(
            "ActionScript", "AppleScript", "Asp", "BASIC", "C", "C++", "Clojure",
            "COBOL", "ColdFusion", "Erlang", "Fortran", "Groovy", "Haskell", "Java",
            "JavaScript", "Lisp", "Perl", "PHP", "Python", "Ruby", "Scala", "Scheme");

    @GetMapping("/third-party/auto-complete-multi-values-remote/search-result")
    public List<String> searchResult(@RequestParam(required = false) String term) {
        if (term == null || term.isBlank()) {
            return List.of();
        }
        String keyword = term.toLowerCase();
        return LANGUAGES.stream()
                .filter(language -> language.toLowerCase().contains(keyword))
                .toList();
    }
}
