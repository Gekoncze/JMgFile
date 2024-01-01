package cz.mg.file;

import cz.mg.annotations.classes.Test;
import cz.mg.file.page.PageReadWriteTest;

public @Test class AllTests {
    public static void main(String[] args) {
        // cz.mg.file.page
        PageReadWriteTest.main(args);

        // cz.mg.file
        FileReaderTest.main(args);
        FileReadWriteTest.main(args);
    }
}
