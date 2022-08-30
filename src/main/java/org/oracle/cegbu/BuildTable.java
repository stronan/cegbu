package org.oracle.cegbu;

import java.util.stream.Stream;

public interface BuildTable {
    void add(Build build);

    Stream<Build> query();
}
