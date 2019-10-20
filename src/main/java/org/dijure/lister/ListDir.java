package org.dijure.lister;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ListDir {
    public static void main(String[] args)throws java.io.IOException {

        boolean listingRequested = Boolean.parseBoolean(System.getProperty("list", "false"));

        String root = ".";
        System.out.println("Walking path: " + Paths.get(root));
        long size[] = { 0 };
        long count[] = { 0 } ;
        try (Stream<Path> paths = Files.walk(Paths.get(root))) {
            paths.filter(Files::isRegularFile).forEach((Path p) -> {
                File f = p.toFile();
                size[0] += f.length();
                count[0]++;
                if (listingRequested) {
                    System.out.println(f.getPath());
                }
            });
        }
        System.out.println(count[0] + " files with " + size[0] + " bytes");
    }
}
