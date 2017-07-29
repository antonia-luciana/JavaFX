/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

/**
 *
 * @author lucia_000
 */
import java.io.*;

public class MakeFile {
    public void make(String nume1,String nume2) {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(nume1);
            fw = new FileWriter(nume2);
            int c = fr.read();
            while(c!=-1) {
                fw.write(c);
                c = fr.read();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            close(fr);
            close(fw);
        }
    }
    public static void close(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch(IOException e) {
            System.out.println("Eroare in MakeFile");
        }
    }
}