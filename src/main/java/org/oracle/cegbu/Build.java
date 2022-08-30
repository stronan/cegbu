package org.oracle.cegbu;

import java.util.Objects;

/**
 * Represents a build of a project.
 */
public class Build {
    String customerId;
    String contractId;
    String geoZone;
    String teamCode;
    String projectCode;
    int buildDuration;

    public Build(String customerId, String contractId, String geoZone, String teamCode, String projectCode,
                 int buildDuration) {
        this.customerId = customerId;
        this.contractId = contractId;
        this.geoZone = geoZone;
        this.teamCode = teamCode;
        this.projectCode = projectCode;
        this.buildDuration = buildDuration;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getContractId() {
        return contractId;
    }

    public String getGeoZone() {
        return geoZone;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public int getBuildDuration() {
        return buildDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Build build = (Build) o;
        return buildDuration == build.buildDuration && customerId.equals(build.customerId) &&
            contractId.equals(build.contractId) && geoZone.equals(build.geoZone) && teamCode.equals(build.teamCode) &&
            projectCode.equals(build.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, contractId, geoZone, teamCode, projectCode, buildDuration);
    }

    /**
     * Builder for the Build class.
     */
    public static class BuildBuilder {
        String customerId;
        String contractId;
        String geoZone;
        String teamCode;
        String projectCode;
        int buildDuration;

        private BuildBuilder() {
        }

        public static BuildBuilder newInstance() {
            return new BuildBuilder();
        }

        public BuildBuilder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public BuildBuilder setContractId(String contractId) {
            this.contractId = contractId;
            return this;
        }

        public BuildBuilder setGeoZone(String geoZone) {
            this.geoZone = geoZone;
            return this;
        }

        public BuildBuilder setTeamCode(String teamCode) {
            this.teamCode = teamCode;
            return this;
        }

        public BuildBuilder setProjectCode(String projectCode) {
            this.projectCode = projectCode;
            return this;
        }

        public BuildBuilder setBuildDuration(int buildDuration) {
            this.buildDuration = buildDuration;
            return this;
        }

        public Build build() {
            return new Build(customerId, contractId, geoZone, teamCode, projectCode, buildDuration);
        }
    }
}
