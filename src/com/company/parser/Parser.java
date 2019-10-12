package com.company.parser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    long currentLine;
    boolean skipHeader = false;
    Map<Integer, String> headersMap = new HashMap<>();
    Map<Integer, Field> fieldsMap = new HashMap<>();
}
