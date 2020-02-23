package com.shizk.demo.java.tools.picocli;

import picocli.CommandLine;

import static picocli.CommandLine.*;

@Command(name = "co-occur")
public class DependentOptionsDemo {

    @ArgGroup(exclusive = false)
    Dependent dependent;

    static class Dependent {
        @Option(names = "-a", required = true)
        int a;
        @Option(names = "-b", required = true)
        int b;
        @Option(names = "-c", required = true)
        int c;
    }

    public static void main(String[] args) {
        DependentOptionsDemo example = new DependentOptionsDemo();
        CommandLine cmd = new CommandLine(example);

        try {
            cmd.parseArgs("-a=1", "-b=2");
        } catch (MissingParameterException ex) {
            assert "Error: Missing required argument(s): -c=<c>".equals(ex.getMessage());
        }
    }
}
