package com.shizk.demo.java.tools.picocli;

import picocli.CommandLine;

import java.io.File;
import java.util.Arrays;

import static picocli.CommandLine.Option;
import static picocli.CommandLine.Parameters;

public class Tar {
    @Option(names = "-c", description = "create a new archive")
    boolean create;

    @Option(names = {"-f", "--file"}, paramLabel = "ARCHIVE", description = "the archive file")
    File archive;

    @Parameters(paramLabel = "FILE", description = "one ore more files to archive")
    File[] files;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display a help message")
    private boolean helpRequested = false;

    public static void main(String[] args) {
        String[] params = {"-c", "--file", "result.tar", "file1.txt", "file2.txt"};
        Tar tar = new Tar();
        new CommandLine(tar).parseArgs(params);

        assert !tar.helpRequested;
        assert tar.create;
        assert tar.archive.equals(new File("result.tar"));
        assert Arrays.equals(tar.files, new File[]{new File("file1.txt"), new File("file2.txt")});
    }
}
