package org.oracle.cegbu;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBuildTableTest {

    // Class under test
    BuildTable table = new MemoryBuildTable();

    @Test
    void queryTableRow() {
        final Build testBuild = new Build("1234", "567", "us", "red", "apple", 100);
        table.add(testBuild);

        final Optional<Build> result = table.query().findFirst();

        assertTrue(result.isPresent(), "Row should exist");
        assertEquals(testBuild, result.get(), "Row should match");
    }

    @Test
    void queryTableCount() {
        table.add(new Build("1234", "567", "us", "red", "apple", 100));
        table.add(new Build("1234", "789", "us", "blue", "apple", 200));

        assertEquals(2, table.query().count(), "Count should match");
    }

    @Test
    void queryEmptyTable() {
        assertEquals(0, table.query().count(), "Count should match");
    }
}