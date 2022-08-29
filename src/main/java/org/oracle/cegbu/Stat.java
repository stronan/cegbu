package org.oracle.cegbu;

import java.util.List;

/**
 * Statistics about a table.
 */
public class Stat {
    List<String> customers;
    long buildDuration;
    int buildCount;

    public List<String> getCustomerList() {
        return customers;
    }

    public int getCustomerCount() {
        return customers.size();
    }

    public float getBuildAverage() {
        return (float) buildDuration / buildCount;
    }
}
