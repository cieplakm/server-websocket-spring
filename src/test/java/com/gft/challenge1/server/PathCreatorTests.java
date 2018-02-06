package com.gft.challenge1.server;

import com.gft.challenge1.server.path.PathCreator;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.*;

public class PathCreatorTests {

    Path tempDirectory;

    @Before
    @SneakyThrows
    public void setup(){
        FileSystem fs = Jimfs.newFileSystem(Configuration.windows());
        tempDirectory = fs.getPath("C:\\temp");
        Files.createDirectory(tempDirectory);
    }

    @Test
    @SneakyThrows
    public void shouldCreateNewDirectoryInPath(){
        Path newFolder = tempDirectory.resolve("New Folder");

        Assertions.assertThat(Files.exists(newFolder)).isEqualTo(false);

        PathCreator.create(tempDirectory, "New Folder");

        Assertions.assertThat((Files.exists(newFolder))).isEqualTo(true);
    }

    @Test
    @SneakyThrows
    public void shouldCreateNewFilePath(){
        Path newFile = tempDirectory.resolve("file.txt");

        Assertions.assertThat(Files.exists(newFile)).isEqualTo(false);

        PathCreator.create(tempDirectory, "file.txt");

        Assertions.assertThat((Files.exists(newFile))).isEqualTo(true);
    }

    @Test
    @SneakyThrows
    public void shouldCreateFullPathToNestedFile(){
        Path deepNestedFile = tempDirectory.resolve("deep");
        Path deeperNestedFile = tempDirectory.resolve("deep\\deeper");

        Assertions.assertThat(Files.exists(deepNestedFile)).isEqualTo(false);
        Assertions.assertThat(Files.exists(deeperNestedFile)).isEqualTo(false);

        PathCreator.create(tempDirectory, "deep\\deeper\\file.txt");

        Assertions.assertThat((Files.exists(deepNestedFile))).isEqualTo(true);
        Assertions.assertThat((Files.exists(deeperNestedFile))).isEqualTo(true);
    }
    @Test
    @SneakyThrows
    public void shouldThrowsFileAlreadyExistException(){
        Path deepNestedFile = tempDirectory.resolve("deep");

        Files.createDirectory(deepNestedFile);

        Assertions
                .assertThatThrownBy(()->PathCreator.create(tempDirectory, "deep"))
                .isInstanceOf(FileAlreadyExistsException.class);
    }

    @Test
    @SneakyThrows
    public void shouldCreateDirInsideExistingSubFolderOfRoot(){
        Path deepNestedFile = tempDirectory.resolve("deep");
        Files.createDirectory(deepNestedFile);

        Path deeperNestedFile = tempDirectory.resolve("deep\\deeper");
        Path moreDeeperNestedFile = tempDirectory.resolve("deep\\deeper\\more_deeper");


        Assertions.assertThat(Files.exists(deeperNestedFile)).isEqualTo(false);
        Assertions.assertThat(Files.exists(moreDeeperNestedFile)).isEqualTo(false);

        PathCreator.create(tempDirectory, "deep/deeper/more_deeper/file.txt");

        Assertions.assertThat((Files.exists(moreDeeperNestedFile))).isEqualTo(true);
        Assertions.assertThat((Files.exists(deeperNestedFile))).isEqualTo(true);
    }

    @Test
    @SneakyThrows
    public void shouldCreateDirInsideExistingSubFolderOfRootForUnix(){
        FileSystem fs = Jimfs.newFileSystem(com.google.common.jimfs.Configuration.unix());
        Path tempDirectory =  fs.getPath("/temp");
        Files.createDirectory(tempDirectory);

        Path deepNestedFile = tempDirectory.resolve("deep");
        Files.createDirectory(deepNestedFile);

        Path deeperNestedFile = tempDirectory.resolve("deep/deeper");
        Path moreDeeperNestedFile = tempDirectory.resolve("deep/deeper/more_deeper");


        Assertions.assertThat(Files.exists(deeperNestedFile)).isEqualTo(false);
        Assertions.assertThat(Files.exists(moreDeeperNestedFile)).isEqualTo(false);

        PathCreator.create(tempDirectory, "deep/deeper/more_deeper/file.txt");

        Assertions.assertThat((Files.exists(moreDeeperNestedFile))).isEqualTo(true);
        Assertions.assertThat((Files.exists(deeperNestedFile))).isEqualTo(true);
    }


}
