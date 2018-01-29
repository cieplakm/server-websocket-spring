package com.gft.challenge1.server.path;


import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public final class PathCreator{
    public static void create(Path root, String stringPath2Create) throws IOException {
        Path pathToCreate = root.resolve(stringPath2Create);

        if (Files.exists(pathToCreate)){
            throw new FileAlreadyExistsException("This file already exist");
        }

        //if parent of stringPath2Create doesn't exist
        //we need to create stringPath2Create
        if (!Files.exists(pathToCreate.getParent())){
            String pattern = Pattern.quote(System.getProperty("file.separator"));
            String[] stringPath = stringPath2Create.split(pattern);
            StringBuilder stringBuilder = new StringBuilder();

            //create stringPath2Create before
            for (int i = 0; i < stringPath.length-1; i++){
                stringBuilder
                        .append(stringPath[i])
                        .append("\\");

                Path dir = root.resolve(stringBuilder.toString());

                if (!Files.exists(dir)){
                    try {
                        Files.createDirectory(dir);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (FilenameUtils.getExtension(stringPath2Create).isEmpty()){
            Files.createDirectory(pathToCreate);
        }else {
            Files.write(pathToCreate, new byte[0]);
        }

    }
}