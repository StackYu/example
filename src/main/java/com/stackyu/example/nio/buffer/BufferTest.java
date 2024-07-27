package com.stackyu.example.nio.buffer;

import com.stackyu.example.nio.util.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * TODO
 *
 * @author stackyu
 * @version 1.0
 */
@Slf4j
public class BufferTest {

    /**
     * 使用ByteBuffer读取文件
     */
    public static void readFile() {
        log.info("read file start...");
        // 获得文件流
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("test.txt", "rw")) {
            // 获得文件channel
            FileChannel channel = randomAccessFile.getChannel();
            // 创建buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);

            do {
                // 从channel中读数据写入buffer
                int read = channel.read(byteBuffer);
                if (read == -1) {
                    break;
                }

                // 切换读模式
                byteBuffer.flip();
                // 读取内容
                while (byteBuffer.hasRemaining()) {
                    System.out.println((char) byteBuffer.get());
                }
                // 切换写
                byteBuffer.clear();
            } while (true);
            log.info("read file end...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        readFile();
    }
}
