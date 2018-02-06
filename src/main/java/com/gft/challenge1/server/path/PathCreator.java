package com.gft.challenge1.server.path;


import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class PathCreator{
    public static void create(Path root, String stringPath2Create) throws IOException {
        String fixedPath2Create = stringPath2Create;
        String separator;

        if (root.toString().contains(":")){
            String pattern = System.getProperty("file.separator");
            separator = pattern+pattern;
            fixedPath2Create = stringPath2Create.replace("/", "\\");
        }else {
            separator = "/";
        }

        Path pathToCreate = root.resolve(fixedPath2Create);

        if (Files.exists(pathToCreate)){
            throw new FileAlreadyExistsException("This file already exist");
        }

        String[] stringPath = fixedPath2Create.split(separator);
        StringBuilder stringBuilder = new StringBuilder();

        //create stringPath2Create before
        for (int i = 0; i < stringPath.length-1; i++){
            stringBuilder
                    .append(stringPath[i])
                    .append(separator);

            Path dir = root.resolve(stringBuilder.toString());

            if (!Files.exists(dir)){
                try {
                    Files.createDirectory(dir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        if (FilenameUtils.getExtension(fixedPath2Create).isEmpty()){
            Files.createDirectory(pathToCreate);
        }else {
            Files.write(pathToCreate, new byte[0]);
        }

    }
}