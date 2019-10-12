package com.company;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FolderScanner {
  private static void moveFile(Path path, String fileName) {
    try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);) {
      long size = channel.size();
      final FileChannel outChannel = FileChannel
          .open(Path.of("/home/rmn/sber/csv/" + fileName), StandardOpenOption.CREATE_NEW,
              StandardOpenOption.WRITE);
      channel.transferTo(0, size, outChannel);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    moveFile(Path.of("/home/rmn/sber/a.txt"), "a.txt");
  }
}
