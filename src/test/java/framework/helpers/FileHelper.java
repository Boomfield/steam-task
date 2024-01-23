package framework.helpers;

import framework.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {
    public static String getCanonicalPath(String path) {
        File file = new File(path);
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            Logger.logFatal(path + " doesn't exist");
            throw new RuntimeException(e);
        }
    }

    public static boolean isFileExists(String folderPath, String fileName) {
        Path filePath = Paths.get(folderPath, fileName);
        return Files.exists(filePath) && Files.isRegularFile(filePath);
    }
}
