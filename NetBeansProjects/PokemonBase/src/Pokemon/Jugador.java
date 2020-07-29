/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pokemon;

import java.time.LocalDate;

/**
 *
 * @author alumnot
 */
public class Jugador {
    
   private String nombre;
   private int victorias;
   private LocalDate ingreso;
   private int cod1,cod2,cod3;
         
   
    
  public Jugador(String nombre,int victorias,LocalDate ingreso,int cod1,int cod2,int cod3){
  this.nombre=nombre;
  this.ingreso=ingreso;
  this.victorias=victorias;
  this.cod1=cod1;
  this.cod2=cod2;
  this.cod3=cod3;
  };  
  
  public Jugador(){}; 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public LocalDate getIngreso() {
        return ingreso;
    }

    public void setIngreso(LocalDate ingreso) {
        this.ingreso = ingreso;
    }
    
 

    public int getCod1() {
        return cod1;
    }

    public void setCod1(int cod1) {
        this.cod1 = cod1;
    }

    public int getCod2() {
        return cod2;
    }

    public void setCod2(int cod2) {
        this.cod2 = cod2;
    }

    public int getCod3() {
        return cod3;
    }

    public void setCod3(int cod3) {
        this.cod3 = cod3;
    }

    private static class date {

        public date() {
        }
    }
    
    
    
    
    
    
    
    
    
}
