package cz.mg.file;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.classes.Test;
import cz.mg.test.Assert;

import java.nio.file.Path;

public @Test class FileWriterTest {
    public static void main(String[] args) {
        System.out.print("Running " + FileWriterTest.class.getSimpleName() + " ... ");

        FileWriterTest test = new FileWriterTest();
        test.testWrite();

        System.out.println("OK");
    }

    private final @Service FileReader reader = FileReader.getInstance();
    private final @Service FileWriter writer = FileWriter.getInstance();

    private void testWrite() {
        String content = "first line;\nsecond line,\nthird line.\nfourth line\\\nfifth line'";
        Path directory = Path.of("test/cz/mg/file/");
        Path path = directory.resolve("testfile");
        File file = new File(path, content);
        writer.write(file);
        reader.read(file);
        Assert.assertEquals(content, file.getContent());
    }
}
