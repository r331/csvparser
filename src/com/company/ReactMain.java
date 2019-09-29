package com.company;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ReactMain {

  public static void main(String[] args) throws IOException {
    //Flux<String> lines = Flux.defer(() -> Flux.from();
    //MyReader stream = MyReader.getFromFile("/home/rmn/sber/loadFile/U01-20170918-001.csv");
    MyReader stream = MyReader.getFromFile("/home/rmn/Documents/Rab/Other/export.csv");
    //InputStreamReader a = new InputStreamReader()
    int a = 0;
    //byte b = ;
    final Runtime runtime = Runtime.getRuntime();
    long time = System.currentTimeMillis();
    FileOutputStream fos = new FileOutputStream("hello-world.zip");
    BufferedOutputStream bos = new BufferedOutputStream(fos, 4_096);
    ZipOutputStream zos = new ZipOutputStream(bos);
    zos.putNextEntry(new ZipEntry("file.csv"));
    while (true) {

      //System.out.print((char)stream.read());
       /*if (a == 0x0A) {
         System.out.println("NEW");
       }*/

      char[] tm = stream.readLine();
      //zos.write(tm, 0, a);
      if (tm.length < 1) {
        break;
      }
      if (a % 100_000 == 0) {

        System.out.println("Lines: " + a);
        System.out.printf("Memory in use while reading: %dMB\n",  // <3>
            (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));
      }
      a++;
    }
    System.out.println(System.currentTimeMillis() - time);
    //Math.pow()
    /*System.out.println((int) 0xd0);
    System.out.println(0xd1);
    System.out.println((byte) 0xd2);*/
    /*final Runtime runtime = Runtime.getRuntime();
    while (true) {
      a++;
      char[] temp;
      temp = stream.readLine();
      for (char c : temp) {
        System.out.print(c);
      }
      System.out.printf("Memory in use while reading: %dMB\n",  // <3>
          (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));
      if (temp.length == 0) {
        break;
      }
      if(a>10)
        break;
    }*/
   /* while (true) {
      a++;
      char[] temp;
      temp = stream.readLine();
      if (a % 100_000 == 0) {
        for (char c : temp) {
          System.out.print(c);
        }
        System.out.printf("Memory in use while reading: %dMB\n",  // <3>
            (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));
      }
      if (temp.length == 0) {
        break;
      }
    }*/
   /* System.out.println("Lines " + a);
    System.out.printf("Memory in use while reading: %dMB\n",  // <3>
        (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));*/
  }
}
