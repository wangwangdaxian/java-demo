package com.shizk.demo.java.tools.picocli;

import picocli.CommandLine;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Option;

class Login implements Callable<Integer> {
    @Option(names = {"-u", "--user"}, description = "User name")
    String user;

    @Option(names = {"-p", "--password"}, description = "Passphrase", arity = "0..1", interactive = true)
    char[] password;

    public Integer call() throws Exception {
        byte[] bytes = new byte[password.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) password[i];
        }

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bytes);

        System.out.printf("Hi %s, your password is hashed to %s.%n", user, base64(md.digest()));

        // null out the arrays when done
        Arrays.fill(bytes, (byte) 0);
        Arrays.fill(password, ' ');

        return 0;
    }

    private String base64(byte[] arr) {
        return Arrays.toString(arr);
    }

    public static void main(String[] args) {
        new CommandLine(new Login()).execute("-u", "user123", "-p");
    }
}