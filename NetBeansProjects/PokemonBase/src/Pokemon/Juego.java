/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pokemon;

import java.sql.SQLException;

/**
 *
 * @author alumnot
 */
public class Juego {

    public static void main(String[] arg) throws SQLException, InterruptedException, ClassNotFoundException {

        Ds juego = new Ds();
     
         Pokemon[] poke1 = new Pokemon[15];
         Pokemon[] poke2 = new Pokemon[15];
       
        
System.out.println("\t\t\t+--------------------------------------------------------------------------+");
System.out.println("\t\t\t|\t\t    Bienvenido al maravilloso mundo pokemon                |");
System.out.println("\t\t\t+--------------------------------------------------------------------------+");       
         
         
        juego.seleccion(poke1,poke2);

        

        
    }

}
