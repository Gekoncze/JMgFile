package cz.mg.file.page;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.services.StringJoiner;
import cz.mg.file.File;
import cz.mg.file.FileWriter;

public @Service class PageWriter {
    private static volatile @Service PageWriter instance;

    public static @Service PageWriter getInstance() {
        if (instance == null) {
            synchronized (Service.class) {
                if (instance == null) {
                    instance = new PageWriter();
                    instance.joiner = StringJoiner.getInstance();
                    instance.writer = FileWriter.getInstance();
                }
            }
        }
        return instance;
    }

    private @Service StringJoiner joiner;
    private @Service FileWriter writer;

    private PageWriter() {
    }

    public void write(@Mandatory Page page) {
        writer.write(new File(
            page.getPath(),
            joiner.join(page.getLines(), "\n")
        ));
    }
}
