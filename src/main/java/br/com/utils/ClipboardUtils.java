package br.com.utils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.testng.Assert.fail;

public class ClipboardUtils {
    public static String getClipboard() {
        String data = "";
        try {
            data = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        return data;
    }

    public static String setClipboard() {
        String data = "";
        try {
            data = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        return data;
    }

}
