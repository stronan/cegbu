package org.oracle.cegbu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextParserTest {
    Parser parser = new TextParser();

    @Test
    void validLine() throws ParseException {
        Build build = parser.parse("2343225,2345,us_east,RedTeam,ProjectApple,3445s");

        assertEquals("2343225", build.getCustomerId(), "Customer should match");
        assertEquals("2345", build.getContractId(), "Contract should match");
        assertEquals("us_east", build.getGeoZone(), "GeoZone should match");
        assertEquals("RedTeam", build.getTeamCode(), "Team code should match");
        assertEquals("ProjectApple", build.getProjectCode(), "Project code should match");
        assertEquals(3445, build.getBuildDuration(), "Build duration should match");
    }

    @Test
    void missingField() {
        assertThrows(ParseException.class, () ->
            parser.parse("2343225,2345,us_east,RedTeam,ProjectApple"));
    }

    @Test
    void extraField() {
        assertThrows(ParseException.class, () ->
            parser.parse("2343225,2345,us_east,RedTeam,ProjectApple,100s,extra"));
    }

    @Test
    void invalidDurationType() {
        assertThrows(ParseException.class, () ->
            parser.parse("2343225,2345,us_east,RedTeam,ProjectApple,34m"));
    }

    @Test
    void invalidDurationValue() {
        assertThrows(ParseException.class, () ->
            parser.parse("2343225,2345,us_east,RedTeam,ProjectApple,x344s"));
    }
}