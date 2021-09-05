package com.restboilarplate.acl.auth.service;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DevService {

    public void create() throws IOException {
        Path myPath = Paths.get("D:\\Spring boot Master\\Starter Project\\REST-API\\rest-api-boilerplate\\src\\main\\java\\com\\restboilarplate\\controller\\testDev.java");
        if (Files.exists(myPath)) {
            System.out.println("File already exists");
        } else {
            Files.createFile(myPath);
            System.out.println("File created");
        }

        try {
            FileWriter myWriter = new FileWriter("D:\\Spring boot Master\\Starter Project\\REST-API\\rest-api-boilerplate\\src\\main\\java\\com\\restboilarplate\\controller\\testDev.java");
            myWriter.write("@RestController\n" +
                    "@RequestMapping(\"/dev\")\n" +
                    "@CrossOrigin(\"*\")\n" +
                    "\n" +
                    "public class TestDevController {\n" +
                    "\n" +
                    "}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
