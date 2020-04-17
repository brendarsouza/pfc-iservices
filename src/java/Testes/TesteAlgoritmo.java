/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

/**
 *
 * @author brenda
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TesteAlgoritmo {

    public static void main(String args[]) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String senha = "admin";

        MessageDigest algorithm = MessageDigest.getInstance("Blowfish");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        String senhahex = hexString.toString();

        System.out.println(senhahex);
        
        String senha1 = "admin";

        MessageDigest algorithm1 = MessageDigest.getInstance("Blowfish");
        byte messageDigest1[] = algorithm1.digest(senha1.getBytes("UTF-8"));

        StringBuilder hexString1 = new StringBuilder();
        for (byte b1 : messageDigest1) {
            hexString1.append(String.format("%02X", 0xFF & b1));
        }
        String senhahex1 = hexString1.toString();

        System.out.println(senhahex1);
    }

}
