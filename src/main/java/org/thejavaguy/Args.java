package org.thejavaguy;

import com.beust.jcommander.Parameter;

public final class Args {
    @Parameter(names = "-verbose")
    private int verbose = 1;
    @Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
    private String groups;

    public final int getVerbose() {
        return verbose;
    }

    public final String getGroups() {
        return groups;
    }
}
