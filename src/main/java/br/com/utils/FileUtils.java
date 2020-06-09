package br.com.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {

    public static boolean createFolder(String folder) {
        File newFolder = new File(folder);
        if (newFolder.exists()) {
            newFolder.delete();
        }
        return newFolder.mkdir();
    }

    public static byte[] readBytesFromFile(String filePath) throws IOException {

        File file = new File(filePath);

        FileInputStream fis = new FileInputStream(file);
        //System.out.println(file.exists() + "!!");
        //InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bytes = bos.toByteArray();


        return bytes;

    }

    public static List<List<String>> readCsv(String filePath) {

        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }

}
