package hrytsenko;

import java.util.List;

/**
 * Utilities for working with filenames.
 */
public final class Filenames {

    public static final String NAME_COUNT_SEPARATOR = " ";
    public static final String COUNT_EXTENSION_SEPARATOR = ".";
    public static final String COUNT_OPENER_SYMBOL = "(";
    public static final String COUNT_CLOSER_SYMBOL = ")";

    /**
     * Generates the unique name to avoid duplication with the known names.
     * <p>
     * <p>
     * To generate unique name, we add serial number to the end of name.
     * <p>
     * For example: "logo.png", "logo (1).png" and so on.
     *
     * @param originalName the original name.
     * @param knownNames   the list of known names.
     * @return the generated unique name.
     */
    public static String generateUniqueName(String originalName, List<String> knownNames) {
        FileNameParts nameParts = decomposeFileName(originalName);
        String originalFileName = nameParts.getFileName();
        String originalFileExtension = nameParts.getFileExtension();
        int fileNameOccurrencesCount = 0;

        for (String knownName : knownNames) {
            FileNameParts knownNameParts = decomposeFileName(knownName);
            String knownFileName = knownNameParts.getFileName();
            String knownFileExtension = knownNameParts.getFileExtension();

            if (knownFileExtension.equals(originalFileExtension) && knownFileName.contains(originalFileName)) {
                if (knownFileName.contains(COUNT_CLOSER_SYMBOL)) {
                    int countOpenerSymbolIndex = knownFileName.indexOf(COUNT_OPENER_SYMBOL);
                    int fileNumber = Integer.parseInt(knownFileName.substring(countOpenerSymbolIndex + 1,
                            knownFileName.length() - 1));
                    if (fileNumber > fileNameOccurrencesCount) {
                        fileNameOccurrencesCount = fileNumber;
                    }
                }
                fileNameOccurrencesCount++;
            }
        }

        if (fileNameOccurrencesCount == 0) {
            return originalName;
        } else {
            return originalFileName + NAME_COUNT_SEPARATOR + COUNT_OPENER_SYMBOL + fileNameOccurrencesCount
                    + COUNT_CLOSER_SYMBOL + COUNT_EXTENSION_SEPARATOR + originalFileExtension;
        }
    }

    private static FileNameParts decomposeFileName(String fileName) {
        String[] knownNameParts = fileName.split("\\" + COUNT_EXTENSION_SEPARATOR);
        return new FileNameParts(knownNameParts[0].trim(), knownNameParts[1].trim());
    }

    private Filenames() {
    }

}
