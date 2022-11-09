package Compiladores;

/**
 *
 * @author ferar
 */
public class Mnemico {

    private String token;
    private String componenteLexico;
    private String lexema;
    private String lexema_2;

    public String getLexema_2() {
        return lexema_2;
    }

    public String getToken() {
        return token;
    }

    public String getLexema() {
        return lexema;
    }

    public String getComponenteLexico() {
        return componenteLexico;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setComponenteLexico(String componenteLexico) {
        this.componenteLexico = componenteLexico;
    }

    public void setLexema_2(String lexema_2) {
        this.lexema_2 = lexema_2;
    }
}
