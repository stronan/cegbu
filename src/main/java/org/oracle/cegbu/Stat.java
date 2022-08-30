package org.oracle.cegbu;

import java.util.ArrayList;
import java.util.List;

/**
 * Statistics about a table.
 */
public class Stat {
    private List<String> customers = new ArrayList<>();
    private long buildDuration;
    private int buildCount;

    public List<String> getCustomerList() {
        return customers;
    }

    public int getCustomerCount() {
        return customers.size();
    }

    public List<String> getCustomers() {
        return customers;
    }

    public float getBuildAverage() {
        return (float) buildDuration / buildCount;
    }

    public Stat accumulate(String customerId, int duration) {
        customers.add(customerId);
        buildDuration += duration;
        buildCount++;
        return this;
    }
}
