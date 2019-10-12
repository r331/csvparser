package com.company;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileCopy {

  public static void main(String[] args) {
    long time = System.currentTimeMillis();
    Path source = Path.of("/home/rmn/Documents/Rab/Other/export.csv");
    Path newdir = Path.of("/home/rmn/Documents/Rab/Other/tmp");
    try {
      Files.move(source, newdir.resolve(source.getFileName()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(System.currentTimeMillis() - time);

    /*long time = System.currentTimeMillis();
    Path source = Path.of("/home/rmn/Documents/Rab/Other/export.csv");
    Path newdir = Path.of("/home/rmn/Documents/Rab/Other/tmp");
    moveFile(source, newdir.resolve(source.getFileName()));
    System.out.println(System.currentTimeMillis() - time);*/
  }

  private static void moveFile(Path path, Path fileName) {
    try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);) {
      long size = channel.size();
      final FileChannel outChannel = FileChannel
          .open(fileName, StandardOpenOption.CREATE_NEW,
              StandardOpenOption.WRITE);
      channel.transferTo(0, size, outChannel);
    } catch (IOException e) {
      System.out.println("Error move file: " + fileName + e.toString());
    }
  }
}
