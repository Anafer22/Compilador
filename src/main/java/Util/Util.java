/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author ferar
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Util {

    public static LinkedList<String> cargaArchivo(String path) throws FileNotFoundException, IOException { //RETORNA UNA LISTA A PARTIR DE UN ARCHIVO DE ENTRADA 
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String lector = bufferedReader.readLine();
        LinkedList<String> listOut = new LinkedList<>();

        while (lector != null) {
            listOut.add(lector);
            lector = bufferedReader.readLine();
        }
        bufferedReader.close();
        return listOut;

    }

    public static LinkedList<String> quitarComentarios(LinkedList<String> listIn) { //QUITA COMENTARIOS  Y YA XD 
        boolean flag = false;
        for (int i = 0; i < listIn.size(); i++) {
            String s = listIn.get(i);
            if (s.contains("//") && !flag) {
                String sAux = (s.substring(0, s.indexOf("//")));
                int replace = sAux.length() - sAux.replace(" ", "").length();
                if (sAux.length() == replace) {
                    listIn.set(i, "");
                } else {
                    listIn.set(i, sAux);
                }
            }
            if (s.contains("/*")) {
                flag = true;
            }
            if (flag) {
                listIn.set(i, "");
            }
            if (s.contains("*/")) {
                flag = false;
                String sAux = (s.substring(s.indexOf("*/") + 2, s.length()));
                listIn.set(i, sAux);
            }

        }
        listIn = limpiarLista(listIn);
        return listIn;

    }

    public static LinkedList<String> limpiarLista(LinkedList<String> listIn) { //LIMPIAR LISTA XD, QUITA LOS SALTOS DE LINEA INNECESARIOS
        LinkedList<String> listaAuxiliar = new LinkedList<>();
        for (int i = 0; i < listIn.size(); i++) {
            String s = listIn.get(i);
            int replace = s.length() - s.replace(" ", "").length();
            if (replace != s.length()) {
                listaAuxiliar.add(listIn.get(i));
            }
        }
        return listaAuxiliar;
    }

    public static String imprimirLista(LinkedList<String> listIn) { //IMPRIME LA LISTA XD, RETORNA UN STRING CON EL CONTENIDO DE LA LISTA 
        String salida = "";
        for (String s : listIn) {
            salida += s + "\n";
        }
        return salida;
    }

    public static boolean esLetra(char c) { //CODIGO ASCII
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        }
        return false;
    }

    public static boolean esNumero(char c) { //CODIGO ASCII
        if ((c >= '0' && c <= '9')) {
            return true;
        }
        return false;

    }

    public static boolean esIDValido(String id) { //LO DE LA GRAMATICA, VALIDA UN IDENTIFICADOR CON UNA EXPRESION REGULAR 
        String regex = "^([a-zA-Z_$][a-zA-Z\\d_$]*)$";
        Pattern p = Pattern.compile(regex);
        if (id == null) {
            return false;
        }
        Matcher m = p.matcher(id);
        return m.matches();
    }

    public static boolean esValorNumerico(String in) { //RETORNA SI ES UN VALOR NUMERICO XDXDXD
        try {
            Integer.parseInt(in);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void crearTxt(String textToBeWritten) {
        PrintWriter printWriter = null;
        {
            try {
                printWriter = new PrintWriter("CodigoTraducido.txt");
            } catch (FileNotFoundException e) {
                System.out.println("No se puede localizar el nombre del archivo: " + e.getMessage());
            }
            Objects.requireNonNull(printWriter).println(textToBeWritten);
            printWriter.close();
        }
    }
}
