package org.oracle.cegbu;

/**
 * Represents a build of a project.
 */
public class Build {
    /**
     * Builder for the Build class.
     */
    public static class BuildBuilder {
        private BuildBuilder() {
        }

        public static BuildBuilder newInstance() {
            return new BuildBuilder();
        }

        public Build build() {
            return new Build();
        }
    }
}
