package cz.mg.file;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.components.StringJoiner;
import cz.mg.file.page.Page;
import cz.mg.file.page.PageReader;

import java.nio.file.Path;

public @Service class FileReader {
    private static volatile @Service FileReader instance;

    public static @Service FileReader getInstance() {
        if (instance == null) {
            synchronized (Service.class) {
                if (instance == null) {
                    instance = new FileReader();
                    instance.reader = PageReader.getInstance();
                }
            }
        }
        return instance;
    }

    private @Service PageReader reader;

    private FileReader() {
    }

    /**
     * Reads content from given file.
     * System dependent line endings are converted to '\n'.
     */
    public void read(@Mandatory File file) {
        Page page = new Page(file.getPath());
        reader.read(page);
        file.setContent(new StringJoiner<>(page.getLines()).withDelimiter("\n").join());
    }

    public @Mandatory File read(@Mandatory Path path) {
        File file = new File(path);
        read(file);
        return file;
    }
}
