package org.oracle.cegbu;

public interface Parser {
    Build parse(String line) throws ParseException;
}
