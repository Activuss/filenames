package hrytsenko;

public class FileNameParts {
    private String fileName;
    private String fileExtension;

    public FileNameParts(String fileName, String fileExtension) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
