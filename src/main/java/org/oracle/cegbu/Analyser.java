package org.oracle.cegbu;

import java.util.HashMap;
import java.util.Map;

/**
 * Analyse a build data table to get the required statistics.
 */
public class Analyser {
    public Map<String, Stat> contractStats = new HashMap<>();

    public Map<String, Stat> geoStats = new HashMap<>();

    public void analyse(BuildTable table) {
        table.query().forEach(b -> {
            contractStats.computeIfAbsent(b.getContractId(), k -> new Stat())
                         .accumulate(b.getCustomerId(), b.getBuildDuration());
            geoStats.computeIfAbsent(b.getGeoZone(), k -> new Stat())
                    .accumulate(b.getCustomerId(), b.getBuildDuration());
        });
    }

    public Map<String, Stat> getContractStats() {
        return contractStats;
    }

    public Map<String, Stat> getGeoStats() {
       return geoStats;
    }
}
