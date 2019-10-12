package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ZipUtils {
    public static void zip(File file, File outputZipFolder) {
        try (ZipOutputStream zos = newZipStream(file, outputZipFolder)) {
            zos.putNextEntry(new ZipEntry(file.getName()));
            Files.copy(file.toPath(), zos);
            zos.closeEntry();
            System.out.println("Successfully zipped! {}"+ file.getName());
        } catch (Exception e) {
            System.out.println("Error saving file: {}"+e.toString());
        }
    }

    private static ZipOutputStream newZipStream(File file, File outputZipFolder) throws FileNotFoundException {
        return new ZipOutputStream(new FileOutputStream(new File(outputZipFolder, file.getName() + ".zip")));
    }
}
