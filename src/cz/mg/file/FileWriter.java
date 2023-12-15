package cz.mg.file;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public @Service class FileWriter {
    private static volatile @Service FileWriter instance;

    public static @Service FileWriter getInstance() {
        if (instance == null) {
            synchronized (Service.class) {
                if (instance == null) {
                    instance = new FileWriter();
                }
            }
        }
        return instance;
    }

    private FileWriter() {
    }

    /**
     * Writes content to given file.
     */
    public void write(@Mandatory File file) {
        Path path = file.getPath();

        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            throw new FileException("Could not create directory '" + path.getParent() + "'.", e);
        }

        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(path.toFile()))) {
            writer.write(file.getContent());
        } catch (IOException e) {
            throw new FileException("Could not write file '" + path.getFileName() + "'.", e);
        }
    }
}
