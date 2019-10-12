package com.company;

import java.io.File;

public class ReadFileTest {
    public static void main(String[] args) {
        ZipUtils.zip(new File("/home/rmn/nohup.out"), new File("/home/rmn/sber"));
    }
}
