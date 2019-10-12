package com.company;


import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class MyReader extends InputStream {
    private FileChannel channel;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private int buffSize = 0;
    private int position = 0;
    private boolean EOF = false;
    private CharBuffer charBuffer;
    private char[] temp = new char[2048];

    private MyReader() {
    }

    static MyReader getFromFile(final String path) throws IOException {
        MyReader myReader = new MyReader();
        myReader.channel = FileChannel.open(Path.of(path),
                StandardOpenOption.READ);
        myReader.initNewBuffer();
        return myReader;
    }

    private void initNewBuffer() {
        try {
            buffSize = channel.read(buffer);
            buffer.position(0);
            //charBuffer = StandardCharsets.UTF_8.decode(buffer);
            charBuffer = StandardCharsets.UTF_8.decode(buffer);
            if (buffSize > 0) {
                byte edgeByte = buffer.array()[buffSize - 1];
                if (edgeByte == (byte) 0xd0 ||
                        edgeByte == (byte) 0xd1 ||
                        edgeByte == (byte) 0xc2 ||
                        edgeByte == (byte) 0xd2 ||
                        edgeByte == (byte) 0xd3
                ) {
                    channel.position(channel.position() - 1);
                    charBuffer.limit(charBuffer.limit() - 1);
                }
            }
            buffer.position(0);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: {}", e);
        }
    }

    @Override
    public int read() throws IOException {
        if (EOF) {
            return -1;
        }
        if (position < charBuffer.length()) {
            return charBuffer.get(position++);
        } else {
            initNewBuffer();
            if (buffSize < 1) {
                EOF = true;
            } else {
                position = 0;
            }
            return read();
        }
    }

    public char[] readLine() throws IOException {
        int tmpPos = 0;
        int readResult = 0;
        while (true) {
            readResult = read();
            if (readResult == -1) {
                break;
            }
            if (readResult == 0x0A) {
                break;
            }
            temp[tmpPos++] = (char) readResult;
        }
        return copy(temp, tmpPos);
    }

    private char[] copy(char[] temp, int tmpPos) {
        if (tmpPos == 0)
            return new char[0];
        final char[] cp = new char[tmpPos];
        System.arraycopy(temp, 0, cp, 0, tmpPos);
        return cp;
    }
}
