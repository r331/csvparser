package com.company;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

  public void sendHttp() {
    HttpClient client = HttpClient.newBuilder()
        .version(Version.HTTP_2)
        .followRedirects(Redirect.ALWAYS)
        .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
        .authenticator(Authenticator.getDefault())
        .build();
  }

  public static void main(String[] args) throws Exception {

    //watchFile();
    //parse();
    fileChan2();

  }

  private static void fileChan2() throws Exception {
    FileChannel channel = FileChannel.open(Path.of("/home/rmn/sber/loadFile/U01-20170918-001.csv"),
        StandardOpenOption.READ);
    System.out.println("File size is: " + channel.size());
    ByteBuffer buffer = ByteBuffer.allocate(4096);
    long time = System.currentTimeMillis();
    int a1 = 0;

    int stroki = 0;
    a1 = channel.read(buffer);
    buffer.flip();
    System.out.println((byte)'\n');
    for (int i = 0; i < buffer.limit(); i++) {
      /*if ((char) buffer.get() == '\n') {
        stroki = i;
        break;
      }*/if (buffer.get(i) == -92) {
        stroki++;
      }
      if (buffer.get(i) == 10) {
        //stroki++;
      }
    }
    byte [] arr = Arrays.copyOf(buffer.array(), a1);
    System.out.println(new String(arr, StandardCharsets.UTF_8));
    System.out.println("Stroki " + stroki + " Pro4itano " + a1);
    channel.close();
    System.out.println("Read all " + (System.currentTimeMillis() - time) / 1000);
  }

  private static void parse() {
    var rowProcessor = new BeanListProcessor<SapEntity>(SapEntity.class);

    CsvParserSettings parserSettings = new CsvParserSettings();
    parserSettings.getFormat().setLineSeparator("\n");
    parserSettings.getFormat().setDelimiter('¤');
    parserSettings.setProcessor(rowProcessor);
    parserSettings.setHeaderExtractionEnabled(true);

    CsvParser parser = new CsvParser(parserSettings);
    parser.parse(getReader("/home/rmn/sber/loadFile/U01-20170918-001.csv"));

// The BeanListProcessor provides a list of objects extracted from the input.
    List<SapEntity> beans = rowProcessor.getBeans();
    beans.forEach(System.out::println);
  }

  private static InputStream getReader(String s) {
    try {
      return Channels.newInputStream(FileChannel.open(Path.of(s)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }


  private static void watchFile() {
    try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
      Path logDir = Paths.get("/home/rmn/Documents/Rab/Other/tmp");
      logDir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

      while (true) {
        WatchKey key = watcher.take();
        for (WatchEvent<?> event : key.pollEvents()) {
          WatchEvent.Kind<?> kind = event.kind();

          System.out.println("--->");
          System.out
              .println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
          System.out.println("");
          System.out.println("");

          if (ENTRY_CREATE.equals(kind)) {
            System.out.println("Entry was created on log dir.");
          } else if (ENTRY_MODIFY.equals(kind)) {
            System.out.println("Entry was modified on log dir.");
          } else if (ENTRY_DELETE.equals(kind)) {
            System.out.println("Entry was deleted from log dir.");
          }


        }
        key.reset();
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void other() throws IOException {
    //fileChan();
    //bufReader();
    //InputStream is = Channels.newInputStream(channel);
    //inputSream();
/*
    File file = new File("filename");
    FileChannel channel = new RandomAccessFile(file, "rw").getChannel();

    InputStream is = Channels.newInputStream(channel);*/
    FileChannel channel = FileChannel.open(Path.of("/home/rmn/Documents/Rab/Other/export.csv"),
        StandardOpenOption.READ);
    System.out.println(channel.lock());
  }

  private static void inputSream() throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(
        new FileInputStream("/home/rmn/Documents/Rab/Other/export.csv"), "UTF-8");
    long time = System.currentTimeMillis();
    int data = inputStreamReader.read();
    while (data != -1) {
      char theChar = (char) data;
      data = inputStreamReader.read();
    }

    inputStreamReader.close();
    System.out.println("Read all " + " " + (System.currentTimeMillis() - time) / 1000);
  }

  private static void bufReader() {
    try {

      File f = new File("/home/rmn/Documents/Rab/Other/export.csv");

      BufferedReader b = new BufferedReader(new FileReader(f));

      String readLine = "";

      System.out.println("Reading file using Buffered Reader");

      long time = System.currentTimeMillis();
      int a = 0;
      while ((readLine = b.readLine()) != null) {
        //System.out.println(readLine);
        a++;
      }
      System.out.println("Read all " + a + " " + (System.currentTimeMillis() - time) / 1000);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void fileChan() throws Exception {
    FileChannel channel = FileChannel.open(Path.of("/home/rmn/Documents/Rab/Other/export.csv"),
        StandardOpenOption.READ);
    System.out.println("File size is: " + channel.size());
    ByteBuffer buffer = ByteBuffer.allocate(4_096);
    System.out.println("Reading content and printing ... ");
    long time = System.currentTimeMillis();
    int a = 0;

    //channel.tryLock();

    FileOutputStream fos = new FileOutputStream("hello-world.zip");

/*    RandomAccessFile writer = new RandomAccessFile("hello.zip", "rw");
    FileChannel och = writer.getChannel();
    OutputStream out = Channels.newOutputStream(och);*/
    BufferedOutputStream bos = new BufferedOutputStream(fos, 4_096);
    ZipOutputStream zos = new ZipOutputStream(bos);
    zos.putNextEntry(new ZipEntry("file.csv"));

    int stroki = 0;
    while ((a = channel.read(buffer)) > 0) {
      //System.out.println(a);
      buffer.flip();

      zos.write(buffer.array(), 0, a);
      //Thread.sleep(1000);
      //buffer.clear(); // do something with the data and clear/compact it.
    }
    System.out.println("Stroki " + stroki);
    zos.closeEntry();
    zos.close();
    channel.close();
    System.out.println("Read all " + (System.currentTimeMillis() - time) / 1000);
  }

  public static byte[] zipBytes(String filename, byte[] input) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ZipOutputStream zos = new ZipOutputStream(baos);
    ZipEntry entry = new ZipEntry(filename);
    entry.setSize(input.length);
    zos.putNextEntry(entry);
    zos.write(input);
    zos.closeEntry();
    zos.close();
    return baos.toByteArray();
  }
}
