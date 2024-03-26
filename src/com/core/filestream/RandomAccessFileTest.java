package com.core.filestream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * <p>Поддерживает чтение и запись. Реализует интерфейсы DataOutput, DataInput. Принимает File, fileName, mode (r, rw, rws, rwd)
 * <p>
 */
public class RandomAccessFileTest {
    public static void main(String[] args) {
        File f = new File("data/rndAccF.txt");
        f.delete();
        try(RandomAccessFile faf = new RandomAccessFile(f, "rw")) {
            String lineSeparator = java.security.AccessController.doPrivileged(
                    new sun.security.action.GetPropertyAction("line.separator"));

            faf.writeInt(1);
            faf.writeUTF(": Первая строка\n");
            //faf.writeChars(lineSeparator);
            faf.writeInt(2);
            faf.writeChars(": Вторая строка\n");
            //faf.writeChars(lineSeparator);

            faf.seek(0);

/*
            System.out.print(faf.readInt());
            System.out.print(faf.readChar());
            int i;
            while ((i = faf.readInt()) > 0) {
                System.out.print((char)i);
            }
*/
/*
            System.out.println(faf.readInt());
            System.out.println(faf.readUTF());
            System.out.println(faf.readLine());
*/

            String line;
            while((line = faf.readLine()) != null) {
                byte[] bytes = line.getBytes();
                ByteBuffer bb = ByteBuffer.allocate(bytes.length);
                System.out.println(StandardCharsets.UTF_8.decode(bb.put(bytes)).toString());

                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
