package com.checkmarx.utils;

import com.checkmarx.configs.ChecksConfiguration;

import java.io.File;
import java.util.ArrayList;

public class DocumentsList {

    ArrayList<File> files = new ArrayList<>();

    public DocumentsList() {
    }

    public ArrayList<File> getDocumentsList(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (File child : children) {

                this.getDocumentsList(child);
            }
        } else {
            if(!Utils.getFileExtension(file).matches(ChecksConfiguration.exclusionRegExpression)){
                files.add(file);
            }
        }
        return files;
    }
}
