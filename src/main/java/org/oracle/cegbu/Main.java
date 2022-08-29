package org.oracle.cegbu;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Read data from input file into table
        Parser parser = new TextParser();
        BuildTable table = new MemoryBuildTable();
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String buildLine = in.nextLine();
            Build build = parser.parse(buildLine);
            table.add(build);
        }

        // Analyse the build data
        Analyser analyser = new Analyser();
        analyser.analyse(table);

        // Generate a report
        PrintStream out = System.out;
        out.printf("%s: %s", "group", "data");
    }
}