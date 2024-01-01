package cz.mg.file.page;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.file.FileException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public @Service class PageReader {
    private static volatile @Service PageReader instance;

    public static @Service PageReader getInstance() {
        if (instance == null) {
            synchronized (Service.class) {
                if (instance == null) {
                    instance = new PageReader();
                }
            }
        }
        return instance;
    }

    private PageReader() {
    }

    public void read(@Mandatory Page page) {
        try (BufferedReader reader = new BufferedReader(new FileReader(page.getPath().toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                page.getLines().addLast(line);
            }
        } catch (IOException e) {
            throw new FileException("Could not read file '" + page.getPath().getFileName() + "'.", e);
        }
    }
}
