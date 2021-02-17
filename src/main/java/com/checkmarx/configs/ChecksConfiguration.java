package com.checkmarx.configs;

import com.checkmarx.DTO.CheckDefinition;

public class ChecksConfiguration {
    public static String pathFile = "files";

    public static String exclusionRegExpression = "test";

    public static CheckDefinition[] checkDefinitions = {
            new CheckDefinition("CSS", "html|js", ".*alert().*"),
            new CheckDefinition("Sensitive data exposure", ".*", ".*checkmarx.*hellman.*&.*friedman.*\\$1\\.15b"),
            new CheckDefinition("SQL injection", ".*", ".*\".*select.*where.*\\%s.*\".*")};

}
