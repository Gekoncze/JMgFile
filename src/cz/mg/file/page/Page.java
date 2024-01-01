package cz.mg.file.page;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.requirement.Required;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;

import java.nio.file.Path;

public @Entity class Page {
    private Path path;
    private List<String> lines = new List<>();

    public Page() {
    }

    public Page(Path path) {
        this.path = path;
    }

    public Page(Path path, List<String> lines) {
        this.path = path;
        this.lines = lines;
    }

    @Required @Value
    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    @Required @Value
    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
