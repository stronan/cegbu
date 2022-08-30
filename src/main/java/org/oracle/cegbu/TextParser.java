package org.oracle.cegbu;

public class TextParser implements Parser {

    private static final int NUM_FIELDS = 6;

    @Override
    public Build parse(String line) throws ParseException {
        String[] fields = line.split(",");
        if (fields.length != NUM_FIELDS) {
            throw new ParseException("Wrong number of fields: expected " + NUM_FIELDS + ", but was " + fields.length);
        }
        final Build.BuildBuilder builder = Build.BuildBuilder.newInstance();
        return builder
            .setCustomerId(fields[0])
            .setContractId(fields[1])
            .setGeoZone(fields[2])
            .setTeamCode(fields[3])
            .setProjectCode(fields[4])
            .setBuildDuration(parseDuration(fields[5]))
            .build();
    }

    private int parseDuration(String field) throws ParseException {
        if (field.endsWith("s")) {
            try {
                return Integer.parseInt(field.substring(0, field.length() - 1));
            } catch (NumberFormatException e) {
                throw new ParseException("Invalid duration " + field, e);
            }
        } else {
            throw new ParseException("Invalid duration " + field);
        }
    }
}
