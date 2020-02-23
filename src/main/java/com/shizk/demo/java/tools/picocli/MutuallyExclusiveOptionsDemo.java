package com.shizk.demo.java.tools.picocli;

import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.MutuallyExclusiveArgsException;
import picocli.CommandLine.Option;

@Command(name = "exclusivedemo")
public class MutuallyExclusiveOptionsDemo {

    @ArgGroup(exclusive = true, multiplicity = "1")
    Exclusive exclusive;

    static class Exclusive {
        @Option(names = "-a", required = true)
        int a;
        @Option(names = "-b", required = true)
        int b;
        @Option(names = "-c", required = true)
        int c;
    }

    public static void main(String[] args) {
        MutuallyExclusiveOptionsDemo example = new MutuallyExclusiveOptionsDemo();
        CommandLine cmd = new CommandLine(example);

        try {
            cmd.parseArgs("-a=1", "-b=2");
        } catch (MutuallyExclusiveArgsException ex) {
            assert "Error: -a=<a>, -b=<b> are mutually exclusive (specify only one)"
                    .equals(ex.getMessage());
        }
    }
}