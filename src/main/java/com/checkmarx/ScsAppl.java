package com.checkmarx;

import com.checkmarx.DTO.OutputFormat;
import com.checkmarx.configs.OutputConfig;
import com.checkmarx.configs.OutputType;
import com.checkmarx.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ScsAppl {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<File> documents = Utils.getDocuments();
        ArrayList<OutputFormat> exceptions = Utils.getExceptions(documents);
        display(OutputConfig.outputConfig, exceptions);
    }

    private static void display(OutputType outputConfig, ArrayList<OutputFormat> exceptions) {
        if(outputConfig == OutputType.PLAIN_TEXT){
            displayPlainText(exceptions);
        }else {
            displayJSON(exceptions);
        }
    }

    private static void displayJSON(ArrayList<OutputFormat> exceptions) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        exceptions.forEach(exception -> {
            try {
                String json = ow.writeValueAsString(exception);
                System.out.println(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
    });
    }

    private static void displayPlainText(ArrayList<OutputFormat> exceptions) {
        exceptions.forEach(exception -> {
            System.out.printf("[%s] in file %s on line %d \n", exception.checkName, exception.fileName, exception.stringLine);
        });
    }

}

