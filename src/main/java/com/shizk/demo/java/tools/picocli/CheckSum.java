package com.shizk.demo.java.tools.picocli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.Ansi.Style;
import picocli.CommandLine.Help.ColorScheme;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command(name = "checksum",
        sortOptions = false,
        mixinStandardHelpOptions = true,
        version = "checksum 4.0",
        requiredOptionMarker = '*',
        optionListHeading = "@|bold,underline %nOptions|@:%n",
        parameterListHeading = "@|bold %nParameters|@:%n",
        description = "Prints the checksum (MD5 by default) of a file to STDOUT.")
class CheckSum implements Callable<Integer> {

    @Parameters(index = "0", description = "The file whose checksum to calculate.")
    private File file;

    @Option(names = {"-a", "--algorithm"}, required = true, description = "MD5, SHA-1, SHA-256, ...")
    private String algorithm = "MD5";

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        String[] argList = {"-h"};
        ColorScheme scheme = new ColorScheme.Builder().optionParams(Style.fg_yellow).commands(Style.fg_green).build();
        int exitCode = new CommandLine(new CheckSum()).setColorScheme(scheme).execute(argList);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        byte[] fileContents = Files.readAllBytes(file.toPath());
        byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
        System.out.printf("%0" + (digest.length * 2) + "x%n", new BigInteger(1, digest));
        return 0;
    }
}
