package cz.mg.file;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.test.Assert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public @Test class FileReaderTest {
    private static volatile @Test FileReaderTest instance;

    public static @Test FileReaderTest getInstance() {
        if (instance == null) {
            synchronized (Service.class) {
                if (instance == null) {
                    instance = new FileReaderTest();
                    instance.reader = FileReader.getInstance();
                }
            }
        }
        return instance;
    }

    private @Service FileReader reader;

    public static void main(String[] args) {
        System.out.print("Running " + FileReaderTest.class.getSimpleName() + " ... ");

        FileReaderTest test = FileReaderTest.getInstance();
        test.testRead();

        System.out.println("OK");
    }

    private void testRead() {
        String expectedContent = "first line;\nsecond line,\nthird line.\nfourth line\\\nfifth line'";
        Path directory = Path.of("test/cz/mg/file/");
        testLinuxFile(expectedContent, directory);
        testWindowsFile(expectedContent, directory);
        testMacosFile(expectedContent, directory);
        testEmptyFile(directory);
    }

    private void testLinuxFile(@Mandatory String expectedContent, @Mandatory Path directory) {
        Path path = directory.resolve("linuxfile");
        writeContent(path, expectedContent);
        verify(expectedContent, reader.read(path).getContent());
    }

    private void testWindowsFile(@Mandatory String expectedContent, @Mandatory Path directory) {
        Path path = directory.resolve("windowsfile");
        writeContent(path, expectedContent.replace("\n", "\r\n"));
        verify(expectedContent, reader.read(path).getContent());
    }

    private void testMacosFile(@Mandatory String expectedContent, @Mandatory Path directory) {
        Path path = directory.resolve("macosfile");
        writeContent(path, expectedContent.replace("\n", "\r"));
        verify(expectedContent, reader.read(path).getContent());
    }

    private void testEmptyFile(@Mandatory Path directory) {
        Path path = directory.resolve("emptyfile");
        writeContent(path, "");
        verify("", reader.read(path).getContent());
    }

    private void verify(@Mandatory String expectedContent, @Mandatory String actualContent) {
        Assert.assertEquals(toHexString(expectedContent), toHexString(actualContent));
    }

    private void writeContent(@Mandatory Path path, @Mandatory String content) {
        try {
            Files.write(path, content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private @Mandatory String toHexString(@Mandatory String s) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n");

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            builder.append(Integer.toHexString(ch));
            builder.append(" ");
        }

        builder.append("\n");

        return builder.toString();
    }
}
