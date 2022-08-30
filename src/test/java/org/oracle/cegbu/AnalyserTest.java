package org.oracle.cegbu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnalyserTest {

    // Class under test
    Analyser analyser = new Analyser();
    private BuildTable table;

    @BeforeEach
    void setUp() {
        table = new MemoryBuildTable();
        table.add(new Build("2343225", "2345", "us_east", "RedTeam", "ProjectApple", 3445));
        table.add(new Build("1223456", "2345", "us_west", "BlueTeam", "ProjectBanana", 2211));
        table.add(new Build("3244332", "2346", "eu_west", "YellowTeam3", "ProjectCarrot", 4322));
        table.add(new Build("1233456", "2345", "us_west", "BlueTeam", "ProjectDate", 2221));
        table.add(new Build("3244132", "2346", "eu_west", "YellowTeam3", "ProjectEgg", 4122));
    }

    @Test
    void getContractStats() {
        analyser.analyse(table);
        Map<String, Stat> stats = analyser.getContractStats();

        assertTrue(stats.containsKey("2345"), "Should have stat for the contract");
        assertEquals(3, stats.get("2345").getCustomerCount(), "Contract stat should match");
        assertTrue(stats.containsKey("2346"), "Should have stat for the contract");
        assertEquals(2, stats.get("2346").getCustomerCount(), "Contract stat should match");
        assertFalse(stats.containsKey("2347"), "Should not have stat for the contract");
    }

    @Test
    void getGeoStats() {
        analyser.analyse(table);
        Map<String, Stat> stats = analyser.getGeoStats();

        assertTrue(stats.containsKey("us_east"), "Should have stat for the geozone");
        assertThat(stats.get("us_east").getCustomers(), containsInAnyOrder("2343225"));
        assertEquals(3445, stats.get("us_east").getBuildAverage(), "Geozone stat should match");

        assertTrue(stats.containsKey("us_west"), "Should have stat for the geozone");
        assertThat(stats.get("us_west").getCustomers(), containsInAnyOrder("1223456", "1233456"));
        assertEquals(2216, stats.get("us_west").getBuildAverage(), "Geozone stat should match");

        assertTrue(stats.containsKey("eu_west"), "Should have stat for the geozone");
        assertThat(stats.get("eu_west").getCustomers(), containsInAnyOrder("3244332", "3244132"));
        assertEquals(4222, stats.get("eu_west").getBuildAverage(), "Geozone stat should match");

        assertFalse(stats.containsKey("nz"), "Should not have stat for the geozone");
    }
}