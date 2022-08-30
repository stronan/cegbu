package org.oracle.cegbu;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Read data from input file into table
        Parser parser = new TextParser();
        BuildTable table = new MemoryBuildTable();
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String buildLine = in.nextLine();
            try {
                Build build = parser.parse(buildLine);
                table.add(build);
            } catch (ParseException e) {
                failInputLine(buildLine);
            }
        }

        // Analyse the build data
        Analyser analyser = new Analyser();
        analyser.analyse(table);

        // Generate a report
        generateReport(analyser, System.out);
    }

    /**
     * Generate a report of the format:
     * Contract ID <id>: <n> customer IDs
     * ...
     * GeoZone <zone>: <n> customer IDs, build duration <avg_duration>s
     * GeoZone <zone> customers: <cust_list>
     * ...
     */
    private static void generateReport(Analyser analyser, PrintStream out) {
        Map<String, Stat> contractStats = analyser.getContractStats();
        contractStats.forEach(
            (contract, stat) -> out.printf("Contract ID %s: %d customer IDs\n", contract, stat.getCustomerCount())
        );

        Map<String, Stat> geoStats = analyser.getGeoStats();
        geoStats.forEach(
            (zone, stat) -> {
                out.printf("GeoZone %s: %d customer IDs, build duration %.1fs\n", zone, stat.getCustomerCount(), stat.getBuildAverage());
                out.printf("GeoZone %s customers: %s\n", zone, stat.getCustomers());
            }
        );
    }

    private static void failInputLine(String buildLine) {
        System.err.println("WARN: Couldn't parse input line");
    }
}