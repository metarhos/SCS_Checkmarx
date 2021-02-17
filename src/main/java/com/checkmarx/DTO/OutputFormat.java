package com.checkmarx.DTO;

public class OutputFormat {
    public OutputFormat(String checkName, Integer stringLine, String fileName) {
        this.checkName = checkName;
        this.stringLine = stringLine;
        this.fileName = fileName;
    }

    public String checkName;
    public Integer stringLine;
    public String fileName;




}
