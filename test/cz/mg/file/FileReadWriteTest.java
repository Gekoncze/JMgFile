package cz.mg.file;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.classes.Test;
import cz.mg.test.Assert;

import java.nio.file.Path;

public @Test class FileReadWriteTest {
    private static volatile @Test FileReadWriteTest instance;

    public static @Test FileReadWriteTest getInstance() {
        if (instance == null) {
            synchronized (Service.class) {
                instance = new FileReadWriteTest();
                instance.reader = FileReader.getInstance();
                instance.writer = FileWriter.getInstance();
            }
        }
        return instance;
    }

    private @Service FileReader reader;
    private @Service FileWriter writer;

    public static void main(String[] args) {
        System.out.print("Running " + FileReadWriteTest.class.getSimpleName() + " ... ");

        FileReadWriteTest test = FileReadWriteTest.getInstance();
        test.testWrite();

        System.out.println("OK");
    }

    private void testWrite() {
        String content = "first line;\nsecond line,\nthird line.\nfourth line\\\nfifth line'";
        Path directory = Path.of("test/cz/mg/file/");
        Path path = directory.resolve("testfile");
        File file = new File(path, content);

        writer.write(file);
        file.setContent("");
        reader.read(file);

        Assert.assertEquals(content, file.getContent());
    }
}
