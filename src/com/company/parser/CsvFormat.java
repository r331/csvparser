package com.company.parser;


public class CsvFormat {
    char lineSeparator;
    char delimiter;
    char quoteEscape;

    public CsvFormat() {
        this.setLineSeparator('\n');
        this.setDelimiter('¤');
        this.setQuoteEscape('"');
    }

    public CsvFormat(char lineSeparator, char delimiter, char quoteEscape) {
        this.lineSeparator = lineSeparator;
        this.delimiter = delimiter;
        this.quoteEscape = quoteEscape;
    }

    public void setLineSeparator(char lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    public void setQuoteEscape(char quoteEscape) {
        this.quoteEscape = quoteEscape;
    }
}
