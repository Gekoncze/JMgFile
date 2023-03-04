package cz.mg.file;

import cz.mg.annotations.requirement.Mandatory;

public class FileException extends RuntimeException {
    public FileException(@Mandatory String message, @Mandatory Throwable cause) {
        super(message, cause);
    }
}
