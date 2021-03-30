package com.utils.sapElement;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeAsNumberDictionary {
    private String caseExpression = "[a-z]{1,}";
    private String outOfCaseExpression = "[A-Z\\-\\_]{1,}";
    private Map<String, Long> caseDictionary;

    {
        caseDictionary = new HashMap<>();
        add("1", -1);
        add("2", 0);
        add("3", 1);
        add("4", 2);
        add("5", 10);
        add("6", 11);
        add("7", 12);
        add("8", 20);
        add("9", 21);
        add("10", 22);
        add("11", 23);
        add("12", 30);
        add("txt", 31);
        add("ctxt", 32);
        add("pwd", 33);
        add("16", 34);
        add("17", 35);
//        add("18", 40); btnvhmore
        add("19", 41);
        add("20", 42);
        add("21", 43);
        add("22", 50);
        add("23", 51);
        add("24", 62);
        add("25", 70);
        add("26", 71);
        add("27", 72);
        add("28", 73);
        add("29", 74);
        add("30", 75);
        add("31", 80);
        add("32", 81);
        add("33", 82);
        add("34", 90);
        add("tabp", 91);
        add("36", 100);
        add("37", 101);
        add("38", 102);
        add("39", 103);
        add("40", 110);
        add("41", 111);
        add("42", 120);
        add("43", 121);
        add("44", 122);
        add("45", 123);
        add("46", 124);
        add("47", 125);
        add("48", 126);
        add("49", 127);
        add("50", 128);
    }

    void add(String key, int num) {
        caseDictionary.put(key, (long) num);
    }

    public String getName(String name) {
        return patternMatcher(Pattern.compile(outOfCaseExpression), name);
    }

    public Long getNum(String name) {
        return caseDictionary.get(
                patternMatcher(Pattern.compile(caseExpression), name)
        );
    }

    private String patternMatcher(Pattern pattern, String name) {
        Matcher matcher = pattern.matcher(name.trim());
        if (matcher.find())
            return matcher.group();
        else
            throw new IllegalArgumentException("Не найдено ключевое слово в id: " + name + ". Или доработать регулярное выражение");
    }
}