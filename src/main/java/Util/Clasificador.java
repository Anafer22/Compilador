/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author ferar
 */
import Compiladores.Mnemico;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class Clasificador {

    private static LinkedList<String> palre;
    private static LinkedList<String> oprel;
    private static LinkedList<String> oplog;
    private static LinkedList<String> opind;
    private static LinkedList<String> opesp;
    private static LinkedList<String> opasi;
    private static LinkedList<String> opari;
    private static LinkedList<String> entrada;
    private static LinkedList<Mnemico> salida;
    private static String codigoMnemico = ""; //para el codigo ya traducido en mnemicos 

    public static String getCodigoMnemico() {
        return codigoMnemico;
    }

    public static void setCodigoMnemico(String codigoMnemico) {
        Clasificador.codigoMnemico = codigoMnemico;
    }

    public static LinkedList<Mnemico> getSalida() {
        return salida;
    }

    public Clasificador() throws FileNotFoundException, IOException {
        palre = new LinkedList<>();
        oprel = new LinkedList<>();
        oplog = new LinkedList<>();
        opind = new LinkedList<>();
        opesp = new LinkedList<>();
        opasi = new LinkedList<>();
        opari = new LinkedList<>();
        salida = new LinkedList<>();
        loadComponents(); //guarda en memoria todos los operadores etc en listas enlazadas
    }

    public void startProcess() {
        for (String linea : entrada) {
            int len = linea.length();
            int i = 0;
            char c;
            char n;
            String tkn = "";
            String tknNum = "";
            String msg = "";
            boolean comillas = false;
            while (i < len) {
                if (linea.charAt(i) == '"') { //si tiene comillas lo concatena todo hasta encontrar las siguientes comillas, para el mensaje MSG 
                    comillas = !comillas;
                    i++;
                }
                if (comillas) {
                    msg += linea.charAt(i);
                } else if (msg != "") {
                    System.out.println(msg);
                    Mnemico entidad = new Mnemico();
                    entidad.setComponenteLexico("MSG");
                    entidad.setToken(msg);
                    entidad.setLexema("MSG");
                    entidad.setLexema_2(msg);
                    salida.add(entidad);
                    msg = "";//GUARDA MENSAJE COMO MNEMICO EN LA LISTA ENLAZADA 

                } else if (Util.esNumero(linea.charAt(i))) { //SI ES NUMERO LO CONCATENA 
                    n = linea.charAt(i);
                    tknNum += n;
                } else if (Util.esLetra(linea.charAt(i))) { //CONCATENA EN OTR VARIABLE 
                    c = linea.charAt(i);
                    tkn += c;
                } else if (linea.charAt(i) == ' ') { //VALIDA SI ENCUENTRA UN ESPACIO
                    validar(tknNum);
                    validar(tkn);
                    tkn = "";
                    tknNum = "";
                } else if (lexemas(linea.charAt(i) + "") != null && linea.charAt(i) != '"') { // SI NO ES ESPACIO, NUMERO O LETRA VALIDA EN TODOS LOS OPERADORES 
                    validar(tknNum);
                    tknNum = "";
                    validar(tkn);
                    tkn = "";
                    validar(linea.charAt(i) + "");
                }
                i++;
            }
            codigoMnemico += "\n";
        }
    }

    public void validar(String tkn) {
        Mnemico entidad = new Mnemico();
        if (palre.lastIndexOf(tkn) != -1) {// si no existe un elemento en la lista retorna -1
            entidad.setComponenteLexico("PALRE");
            entidad.setToken(tkn);
            entidad.setLexema(tkn);
            entidad.setLexema_2("-");
            salida.add(entidad);
            codigoMnemico += "<" + entidad.getComponenteLexico() + "> ";
        } else if (opesp.lastIndexOf(tkn) != -1) {
            entidad.setComponenteLexico("OPESP");
            entidad.setToken(tkn);
            entidad.setLexema(lexemas(tkn));
            entidad.setLexema_2("-");
            salida.add(entidad);
            codigoMnemico += "<" + entidad.getLexema() + "> ";
        } else if (opasi.lastIndexOf(tkn) != -1) {
            entidad.setComponenteLexico("OPASI");
            entidad.setToken(tkn);
            entidad.setLexema(lexemas(tkn));
            entidad.setLexema_2("-");
            salida.add(entidad);
            codigoMnemico += "<" + entidad.getLexema() + "> ";
        } else if (opind.lastIndexOf(tkn) != -1) {
            entidad.setComponenteLexico("OPIND");
            entidad.setToken(tkn);
            entidad.setLexema(lexemas(tkn));
            entidad.setLexema_2("-");
            salida.add(entidad);
            codigoMnemico += "<" + entidad.getLexema() + "> ";
        } else if (oprel.lastIndexOf(tkn) != -1) {
            entidad.setComponenteLexico("OPREL");
            entidad.setToken(tkn);
            entidad.setLexema(lexemas(tkn));
            entidad.setLexema_2("-");
            salida.add(entidad);
            codigoMnemico += "<" + entidad.getLexema() + "> ";
        } else if (opari.lastIndexOf(tkn) != -1) {
            entidad.setComponenteLexico("OPARI");
            entidad.setToken(tkn);
            entidad.setLexema(lexemas(tkn));
            entidad.setLexema_2("-");
            salida.add(entidad);
            codigoMnemico += "<" + entidad.getLexema() + "> ";
        } else if (Util.esValorNumerico(tkn)) {
            entidad.setComponenteLexico("NUM");
            entidad.setToken(tkn);
            entidad.setLexema("NUM");
            entidad.setLexema_2(tkn);
            salida.add(entidad);
            codigoMnemico += entidad.getComponenteLexico() + " ";
        } else if (Util.esIDValido(tkn)) {
            entidad.setComponenteLexico("ID");
            entidad.setToken(tkn);
            entidad.setLexema("ID");
            entidad.setLexema_2(tkn);
            salida.add(entidad);
            codigoMnemico += "<" + entidad.getComponenteLexico() + "> ";
        }
    }

    private static void loadComponents() throws FileNotFoundException, IOException { //RUTAS DE LOS TXT 
        palre = Util.cargaArchivo("C:\\Users\\ferar\\OneDrive\\Documentos\\NetBeansProjects\\Compilador_AFAM\\src\\main\\java\\Util\\PALRE.txt");
        oprel = Util.cargaArchivo("C:\\Users\\ferar\\OneDrive\\Documentos\\NetBeansProjects\\Compilador_AFAM\\src\\main\\java\\Util\\OPREL.txt");
        oplog = Util.cargaArchivo("C:\\Users\\ferar\\OneDrive\\Documentos\\NetBeansProjects\\Compilador_AFAM\\src\\main\\java\\Util\\OPLOG.txt");
        opind = Util.cargaArchivo("C:\\Users\\ferar\\OneDrive\\Documentos\\NetBeansProjects\\Compilador_AFAM\\src\\main\\java\\Util\\OPIND.txt");
        opesp = Util.cargaArchivo("C:\\Users\\ferar\\OneDrive\\Documentos\\NetBeansProjects\\Compilador_AFAM\\src\\main\\java\\Util\\OPESP.txt");
        opasi = Util.cargaArchivo("C:\\Users\\ferar\\OneDrive\\Documentos\\NetBeansProjects\\Compilador_AFAM\\src\\main\\java\\Util\\OPASI.txt");
        opari = Util.cargaArchivo("C:\\Users\\ferar\\OneDrive\\Documentos\\NetBeansProjects\\Compilador_AFAM\\src\\main\\java\\Util\\OPARI.txt");
    }

    public static void setEntrada(LinkedList<String> entrada) {
        Clasificador.entrada = entrada;
    }

    public static LinkedList<String> getEntrada() {
        return entrada;
    }

    public static String lexemas(String tkn) {
        switch (tkn) {
            // OPERADORES ESPECIALES, DEPENDIENDO DEL CARACTER QUE ENCUENTRE RETORNA SU COMPONENTE LEXICO SI ES QUE EXISTE 
            case "[":
                return "COI";
            case "]":
                return "COD";
            case ".":
                return "PUN";
            case "?":
                return "OCO";
            case ",":
                return "COM";
            case ":":
                return "DPU";
            case ";":
                return "DEL";
            case "(":
                return "PIZ";
            case ")":
                return "PDE";
            case "{":
                return "LLI";
            case "}":
                return "LLD";
            case "ERR":
                return "ERROR";
            // OPERADORES DE ASIGNACIÓN
            case "=":
                return "ASI";
            case "*=":
                return "MULA";
            case "/=":
                return "DIVA";
            case "%=":
                return "MODA";
            case "+=":
                return "SUA";
            case "-=":
                return "REA";
            //OPERADORES INCREMENTALES/DECREMETNALES
            case "++":
                return "INC";
            case "--":
                return "DEC";
            //OPERADORES RELACIONALES
            case "==":
                return "IGU";
            case "!=":
                return "DIF";
            case ">":
                return "MAY";
            case "<":
                return "MEN";
            case ">=":
                return "MAI";
            case "<=":
                return "MEI";
            case "+":
                return "SUM";
            case "-":
                return "RES";
            case "*":
                return "MUL";
            case "/":
                return "DIV";
            case "%":
                return "MOD";
            default:
                return null;
        }

    }
}
