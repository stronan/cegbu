package org.oracle.cegbu;

public class TextParser implements Parser {
    @Override
    public Build parse(String line) {
       return Build.BuildBuilder.newInstance().build();
    }
}
