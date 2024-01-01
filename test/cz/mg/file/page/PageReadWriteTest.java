package cz.mg.file.page;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.classes.Test;
import cz.mg.collections.list.List;
import cz.mg.test.Assert;

import java.nio.file.Path;

public @Test class PageReadWriteTest {
    private static volatile @Test PageReadWriteTest instance;

    public static @Test PageReadWriteTest getInstance() {
        if (instance == null) {
            synchronized (Service.class) {
                if (instance == null) {
                    instance = new PageReadWriteTest();
                    instance.reader = PageReader.getInstance();
                    instance.writer = PageWriter.getInstance();
                }
            }
        }
        return instance;
    }

    private @Service PageReader reader;
    private @Service PageWriter writer;

    public static void main(String[] args) {
        System.out.print("Running " + PageReadWriteTest.class.getSimpleName() + " ... ");

        PageReadWriteTest test = PageReadWriteTest.getInstance();
        test.testReadAndWrite();

        System.out.println("OK");
    }

    private void testReadAndWrite() {
        List<String> lines = new List<>(
            "first line",
            " second line ",
            "\tthird line"
        );

        Path directory = Path.of("test/cz/mg/file/");
        Path path = directory.resolve("testfile");
        Page page = new Page(path, lines);

        writer.write(page);
        page.setLines(new List<>());
        reader.read(page);

        Assert.assertThatCollections(lines, page.getLines()).areEqual();
    }
}
