package cz.mg.file;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;

import java.io.BufferedReader;
import java.io.IOException;

public @Service class FileReader {
    private static @Optional FileReader instance;

    public static @Mandatory FileReader getInstance() {
        if (instance == null) {
            instance = new FileReader();
        }
        return instance;
    }

    private FileReader() {
    }

    /**
     * Reads content from given file.
     * System dependent line endings are converted to '\n'.
     */
    public void read(@Mandatory File file) {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file.getPath().toFile()))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }
            file.setContent(builder.toString());
        } catch (IOException e) {
            throw new FileException("Could not read file '" + file.getPath().getFileName() + "'.", e);
        }
    }
}
