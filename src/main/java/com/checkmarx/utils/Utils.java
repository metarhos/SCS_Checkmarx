package com.checkmarx.utils;

import com.checkmarx.DTO.CheckDefinition;
import com.checkmarx.DTO.OutputFormat;
import com.checkmarx.configs.ChecksConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static com.checkmarx.configs.ChecksConfiguration.checkDefinitions;

public class Utils {

    public static String getFileExtension(File document) {
        String name = document.getName();
        int index = name.lastIndexOf('.');
        return index < 0 ? " " : name.substring(index + 1);
    }

    public static ArrayList<File> getDocuments() {
        File myFile = new File(ChecksConfiguration.pathFile);
        DocumentsList documentsList = new DocumentsList();
        return documentsList.getDocumentsList(myFile);
    }

    public static ArrayList<OutputFormat> getExceptions(ArrayList<File> documents) throws FileNotFoundException {
        ArrayList<OutputFormat> exceptions = new ArrayList<>();
        for (File document : documents) {
            Scanner scanner = new Scanner(document);
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber += 1;
                exceptions.addAll(getCheckExceptionsPerLine(line, checkDefinitions, document, lineNumber));
            }
        }
        return exceptions;
    }

    private static ArrayList<OutputFormat> getCheckExceptionsPerLine(String line, CheckDefinition[] checkDefinitions, File document, int lineNumber) {
        line = line.toLowerCase(Locale.ROOT);
        ArrayList<OutputFormat> result = new ArrayList<>();
        String extension;
        extension = Utils.getFileExtension(document);

        for (int i = 0; i < checkDefinitions.length; i++) {
            if (extension.matches(checkDefinitions[i].extensionRegExpression)) {
                if (line.matches(checkDefinitions[i].checkRegularExpression)) {
                    result.add(new OutputFormat(checkDefinitions[i].checkName, lineNumber, document.getName()));
                }
            }
        }
        return result;
    }

}
