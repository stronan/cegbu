package org.oracle.cegbu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Table of project build data.
 */
public class MemoryBuildTable implements BuildTable {
    List<Build> rows = new ArrayList<>();

    @Override
    public void add(Build build) {
        rows.add(build);
    }

    @Override
    public Stream<Build> query() {
        return rows.stream();
    }
}
