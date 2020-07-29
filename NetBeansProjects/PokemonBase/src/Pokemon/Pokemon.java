/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pokemon;

/**
 *
 * @author alumnot
 */
public class Pokemon {

    private int cod;
    private String nombre;
    private int nivel;
    private int level;
    private int velocidad;
    private String tipo;
    private int ps;
    private int ataque;
    private int def;
    private String at1;
    private String at2="--";
    private String at3="--";
    private String at4="--";
    private int  p1=0;
    private int p2=0;
    private int p3=0;
    private String jugador;
    private int vida;

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }
    
    public Pokemon() {
    }

    public Pokemon(int cod, String nombre, int nivel, int level, int velocidad, String tipo, int ps, int ataque, int def,String jugador) {
        this.cod = cod;
        this.nombre = nombre;
        this.nivel = nivel;
        this.level = level;
        this.velocidad = velocidad;
        this.ps = ps;
        this.tipo = tipo;
        this.ataque = ataque;
        this.def = def;
        this.jugador=jugador;
        this.vida=ps;

    }
    //-----------------------------------------GETTERS AND SETTERS----------------------------------------------------------------

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String getAt1() {
        return at1;
    }

    public void setAt1(String at1) {
        this.at1 = at1;
    }

    public String getAt2() {
        return at2;
    }

    public void setAt2(String at2) {
        this.at2 = at2;
    }

    public String getAt3() {
        return at3;
    }

    public void setAt3(String at3) {
        this.at3 = at3;
    }

    public String getAt4() {
        return at4;
    }

    public void setAt4(String at4) {
        this.at4 = at4;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public Pokemon(int cod) {

        this.cod = cod;

    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    
    
    
    
    
    
}
