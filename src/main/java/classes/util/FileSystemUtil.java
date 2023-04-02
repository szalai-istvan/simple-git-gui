package classes.util;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.*;
import java.util.List;

public class FileSystemUtil {
    private static File SETTINGS_FILE = initSettingsFile();

    private static File initSettingsFile() {
        try {
            return new File(FileSystemUtil.class.
                    getProtectionDomain().getCodeSource().getLocation()
                    .toURI())
                    .toPath()
                    .getParent()
                    .resolve("settings.txt")
                    .toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateSettingsFile(String key, String value) {
        String updatedContent = readFile(SETTINGS_FILE).stream()
                .map(line -> line.split("="))
                .map(pair -> {
                    if (pair[0].equals(key)) {
                        if (pair.length == 2) {
                            pair[1] = value;
                        } else {
                            pair = new String[]{pair[0], value};
                        }
                    }
                    return String.join("=", pair);
                }).reduce((s1, s2) -> s1 + "\n" + s2)
                .orElse(key + "=" + value);

        try (FileWriter writer = new FileWriter(SETTINGS_FILE)) {
            if (!updatedContent.contains(key + "=")) {
                updatedContent = updatedContent + "\n" + key + "=" + value;
            }
            writer.write(updatedContent);
            writer.flush();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static void initializeEnvironment() {
        if (!SETTINGS_FILE.exists()) {
            try {
                SETTINGS_FILE.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(SETTINGS_FILE.getPath() + " cannot be created.");
            }
        }
        readFile(SETTINGS_FILE)
                .stream()
                .map(s -> s.split("="))
                .forEach(s -> System.setProperty(s[0], s.length >= 1 ? s[1] : ""));
    }

    private static List<String> readFile(File file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public static void openWithFileManager(String path) {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
