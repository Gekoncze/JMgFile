package cz.mg.file;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.requirement.Required;
import cz.mg.annotations.storage.Value;

import java.nio.file.Path;

public @Entity class File {
    private Path path;
    private String content;

    public File() {
    }

    public File(Path path, String content) {
        this.path = path;
        this.content = content;
    }

    @Required @Value
    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    @Required @Value
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
