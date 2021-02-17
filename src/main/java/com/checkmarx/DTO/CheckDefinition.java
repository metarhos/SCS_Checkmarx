package com.checkmarx.DTO;

public class CheckDefinition {
    public String checkName; //name appearing in an error statement like [SQL injection]
    public String extensionRegExpression; //html, js, ts
    public String checkRegularExpression; //regular exp for

    public CheckDefinition(String checkName, String extensionRegExpression, String checkRegularExpression) {
        this.checkName = checkName;
        this.extensionRegExpression = extensionRegExpression;
        this.checkRegularExpression = checkRegularExpression;
    }
}
