/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pokemon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;

/**
 *
 * @author alumnot
 */
public class Ds {

    Scanner sc = new Scanner(System.in);

    public Ds() {
    }

    public void memoriapokemon(Pokemon[] poke, String jugador) throws SQLException {
        String[] poder = new String[4];
        int n = 0;
        int z = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); ////////////se cargan los objetos de los pokemons

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dani?useSSL=false&serverTimezone=UTC", "dani", "Pokemon1999");
            Statement s = con.createStatement();

            ResultSet x = s.executeQuery("select * from pokemon where jugador='generico';");

            while (x.next()) {

                poke[n] = new Pokemon(x.getInt("cod"), x.getString("nombre"), x.getInt("nivel"), x.getInt("level"), x.getInt("velocidad"), x.getString("tipo"),
                        x.getInt("ps"), x.getInt("ataque"), x.getInt("def"), jugador);
                memoataques(poke[n], "generico");

                n++;
            }

            if (comprueba(jugador) == true) {

                memoriamia(poke, jugador);

            }

        } catch (SQLException e) {

            System.out.println("SQL EXCEPTION" + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Exception: " + cE.toString());

        }

    }

    public void memoataques(Pokemon poke, String jugador) throws SQLException {
        String[] poder = new String[4];
        int z = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");////carga ataques

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dani?useSSL=false&serverTimezone=UTC", "dani", "Pokemon1999");
            Statement s = con.createStatement();

            ResultSet at = s.executeQuery("select nombre from ataques where cod=" + poke.getCod() + " and jugador='" + jugador + "';");

            z = 0;
            while (at.next()) {

                poder[z] = at.getString("nombre");

                z++;

            }
            for (int aa = 0; aa < poder.length; aa++) {
                if (poder[aa] == null) {
                    poder[aa] = "  --------    ";
                }
            }

            poke.setAt1(poder[0]);
            poke.setAt2(poder[1]);
            poke.setAt3(poder[2]);
            poke.setAt4(poder[3]);

        } catch (NullPointerException e) {

            System.out.println("SQL EXCEPTION" + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Exception: " + cE.toString());

        }

    }

    public void memoriamia(Pokemon[] poke, String jugador) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dani?useSSL=false&serverTimezone=UTC", "dani", "Pokemon1999");
        Statement ss = con.createStatement();

        ResultSet xa = ss.executeQuery("select * from pokemon where jugador='" + jugador + "';");

        while (xa.next()) {

            poke[xa.getInt("cod")] = new Pokemon(xa.getInt("cod"), xa.getString("nombre"), xa.getInt("nivel"), xa.getInt("level"), xa.getInt("velocidad"), xa.getString("tipo"),
                    xa.getInt("ps"), xa.getInt("ataque"), xa.getInt("def"), jugador);

            memoataques(poke[xa.getInt("cod")], jugador);

        }

    }

    public boolean comprueba(String jugador) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection kk = DriverManager.getConnection("jdbc:mysql://localhost:3306/dani?useSSL=false&serverTimezone=UTC", "dani", "Pokemon1999");
            Statement s = kk.createStatement();

            ResultSet e = s.executeQuery("select jugador from pokemon where jugador='" + jugador + "';");////////////MIRAR TIENE QUE ACTUALIZAR O INGRESAR

            while (e.next()) {

                return true;
            }

        } catch (SQLException e) {

            System.out.println("SQL EXCEPTION" + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Exception: " + cE.toString());

        }
        return false;

    }

    public void seleccion(Pokemon[] poke1, Pokemon[] poke2) throws InterruptedException, ClassNotFoundException, SQLException {

        System.out.println("\t\t\t\t\tJugador 1 Cual es tu nombre?");
        System.out.print("\t\t\t\t\t-");
        String nom = sc.nextLine();
        String nom2 = "";
        Boolean pul = false;
        Jugador j1 = jugador(nom, poke1);

        while (pul == false) {
            System.out.println("\t\t\t\t\tJugador 2 Cual es tu nombre?");
            System.out.print("\t\t\t\t\t-");
            nom2 = sc.nextLine();
            if (j1.getNombre().equalsIgnoreCase(nom2)) {
                System.out.println("Ese nombre esta repetido,vuelvelo a escribir");
                pul = false;

            } else {
                pul = true;

            }

        }
        Jugador j2 = jugador(nom2, poke2);

        pokesele(j1, poke1);
        pokesele(j2, poke2);

        partida(j1, j2, poke1, poke2, 3, 3, 3);

    }

    public void pokesele(Jugador j, Pokemon[] poke) {   //////////elijes a los pokemons.
        int a, b, c;
        System.out.println("\t\t\t===========================================================");
        System.out.println("\t\t\t||\t    NOMBRE\t||\tNIVEL\t||\tTIPO\t ||        ");
        System.out.println("\t\t\t===========================================================");
        for (int i = 0; i < poke.length; i++) {

            System.out.println("\t\t\t||\t" + i + "_" + poke[i].getNombre() + "\t||\t" + poke[i].getNivel() + "\t||\t" + poke[i].getTipo() + "\t ||");
            if (i == poke.length - 1) {
            } else {
                System.out.println("\t\t\t++-------------------------------------------------------++");
            }
        }
        System.out.println("\t\t\t++=======================================================++");

        System.out.println("\n\t\t\t\tX=====================================X");
        System.out.println("\t\t\t\tX  Seleccione 3 pokemons de la lista  X");
        System.out.println("\t\t\t\tX=====================================X\n\n");

        System.out.println("Primer:");
        System.out.print("\t\t");
        a = intro();
        System.out.println("\t\t\t++-------------------------------------------------------++");
        System.out.println("\t\t\t||\t" + poke[a].getCod() + "_" + poke[a].getNombre() + "\t||\t" + poke[a].getNivel() + "\t||\t" + poke[a].getTipo() + "\t ||");
        System.out.println("\t\t\t++-------------------------------------------------------++");

        System.out.println("Segundo:");
        System.out.print("\t\t");
        b = intro();
        System.out.println("\t\t\t++-------------------------------------------------------++");
        System.out.println("\t\t\t||\t" + poke[b].getCod() + "_" + poke[b].getNombre() + "\t||\t" + poke[b].getNivel() + "\t||\t" + poke[b].getTipo() + "\t ||");
        System.out.println("\t\t\t++-------------------------------------------------------++");

        System.out.println("Tercero:");
        System.out.print("\t\t");
        c = intro();
        System.out.println("\t\t\t++-------------------------------------------------------++");
        System.out.println("\t\t\t||\t" + poke[c].getCod() + "_" + poke[c].getNombre() + "\t||\t" + poke[c].getNivel() + "\t||\t" + poke[c].getTipo() + "\t ||");
        System.out.println("\t\t\t++-------------------------------------------------------++");
        // System.out.print("\t\t\t");

        ingre(j.getNombre(), j, poke, a, b, c);

    }

    public Jugador jugador(String nom, Pokemon[] poke) {  //devuelve un  jugador,si es nuevo lo guarda en la base de datos.

        boolean boton = false;
        final LocalDate now = LocalDate.now();
        Jugador j = new Jugador();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dani?useSSL=false&serverTimezone=UTC", "dani", "Pokemon1999");
            Statement s = con.createStatement();

            ResultSet ju = s.executeQuery("select * from jugador");

            while (ju.next()) {///memorizado

                if (nom.equalsIgnoreCase(ju.getString("nombre"))) {
                    boton = true;
                    j = new Jugador(nom, ju.getInt("victorias"), now, ju.getInt("cod1"), ju.getInt("cod2"), ju.getInt("cod3"));
                    memoriapokemon(poke, nom);

                }

            }
            if (boton == false) {///NUEVO

                s.executeUpdate("insert into jugador values('" + nom + "',sysdate(),0,0,0,0)");
                j = new Jugador(nom, 0, now, 0, 0, 0);
                memoriapokemon(poke, nom);

                // guardado(poke, nom);
            }

            con.close();

        } catch (SQLException e) {

            System.out.println("SQL EXCEPTION" + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Exception: " + cE.toString());

        }

        return j;

    }

    public void ingre(String nom, Jugador play, Pokemon poke[], int a, int b, int c) {////REVISAR
        final LocalDate now = LocalDate.now();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //final LocalDate now = LocalDate.now();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dani?useSSL=false&serverTimezone=UTC", "dani", "Pokemon1999");
            Statement s = con.createStatement();

            ResultSet juu = s.executeQuery("select * from jugador where nombre='" + nom + "';");

            while (juu.next()) {

                play.setCod1(poke[a].getCod());
                play.setCod2(poke[b].getCod());
                play.setCod3(poke[c].getCod());

            }

            con.close();

        } catch (SQLException e) {

            System.out.println("SQL EXCEPTION" + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Exception: " + cE.toString());

        }

    }

    public int choose(Jugador j, Pokemon[] poke) {
        int l1 = 0;
        int l2 = 0;
        int l3 = 0;
        int op1 = 0;
        System.out.println("\t\t\t++-------------------------------------------------------++");
        System.out.println("\t\t\t||                  Elige Pokemon                        ||");
        System.out.println("\t\t\t++-------------------------------------------------------++");
        for (int a = 0; a < poke.length; a++) {
            if (j.getCod1() == poke[a].getCod()) {
                System.out.println("\t\t\t||-------------------------------------------------------||");
                System.out.println("\t\t\t||\t" + a + "_" + poke[a].getNombre() + "\t||\t" + poke[a].getNivel() + "\t||\t" + poke[a].getTipo() + "\t ||");
                System.out.println("\t\t\t||-------------------------------------------------------||");
                l1 = a;
            }
            if (j.getCod2() == poke[a].getCod()) {
                System.out.println("\t\t\t||-------------------------------------------------------||");
                System.out.println("\t\t\t||\t" + a + "_" + poke[a].getNombre() + "\t||\t" + poke[a].getNivel() + "\t||\t" + poke[a].getTipo() + "\t ||");
                System.out.println("\t\t\t||-------------------------------------------------------||");
                l2 = a;
            }
            if (j.getCod3() == poke[a].getCod()) {
                System.out.println("\t\t\t||-------------------------------------------------------||");
                System.out.println("\t\t\t||\t" + a + "_" + poke[a].getNombre() + "\t||\t" + poke[a].getNivel() + "\t||\t" + poke[a].getTipo() + "\t ||");
                System.out.println("\t\t\t||-------------------------------------------------------||");
                l3 = a;
            }

        }
        System.out.print("\t\t\t op:");

        boolean ok = false;
        int op;
        do {
            try {

                op = Integer.parseInt(sc.next());

            } catch (NumberFormatException e) {
                op = 35;
            }

            if (l1 != op && l2 != op && l3 != op) {

                System.out.println("\t\t\t\t\t\t XXXXX SOLO ESTAS OPCIONES MACHO XXXXXX");

            } else if (poke[op].getVida() == 0) {
                System.out.println("Tu " + poke[op].getNombre().trim() + " esta debilitado utiliza otro");
            } else {
                ok = true;
            }
        } while (!ok);
        op1 = op;

        return op1;

    }

    public int intro() {
        boolean ok = false;
        int op;
        do {
            try {

                op = Integer.parseInt(sc.next());

            } catch (NumberFormatException e) {
                op = 35;
            }

            if (op < 0 || op >= 15) {

                System.out.println("\t\t\t\t\t\t XXXXX SOLO ESTAS OPCIONES MACHO XXXXXX");

            } else {
                ok = true;
            }
        } while (!ok);
        return op;
    }

    public int intro4() {
        boolean ok = false;
        int op;
        do {
            try {

                op = Integer.parseInt(sc.next());

            } catch (NumberFormatException e) {
                op = 9;
            }

            if (op < 0 || op >= 5) {

                System.out.println("\t\t\t\t\t\t XXXXX SOLO ESTAS OPCIONES MACHO XXXXXX");

            } else {
                ok = true;
            }
        } while (!ok);
        return op;
    }

    public void partida(Jugador j1, Jugador j2, Pokemon[] poke1, Pokemon[] poke2, int proteina, int vitamina, int pocion) throws InterruptedException, ClassNotFoundException, SQLException {

        boolean revancha = true;

        while (revancha == true) {
            int round1 = 0;
            int round2 = 0;
            while (round1 != 3 && round2 != 3) {
                if (combate(poke1, poke2, j1, j2, proteina, vitamina, pocion) == 1) {
                    round1 += 1;
                } else {
                    round2 += 1;
                }

            }
            if (round1 == 3) {
                System.out.println("\t\t\tGana el torneo " + j1.getNombre());
                j1.setVictorias(j1.getVictorias() + 1);
                guardajugador(j1, j1.getNombre());
            }

            if (round2 == 3) {
                System.out.println("\t\t\tGana el torneo " + j2.getNombre());
                j2.setVictorias(j2.getVictorias() + 1);
                guardajugador(j2, j2.getNombre());

            }

            System.out.print("Quereis echar la revancha?\n\t\t\t\t 1=si   2=no");
            int otra = sc.nextInt();
            if (otra == 1) {
                revancha = true;
            } else {
                revancha = false;
            }

        }
    }

    public int combate(Pokemon[] poke1, Pokemon[] poke2, Jugador j1, Jugador j2, int proteina, int vitamina, int pocion) throws InterruptedException, ClassNotFoundException, SQLException {
        int x = choose(j1, poke1);
        int y = choose(j2, poke2);

        System.out.println(poke1[x].getPs() + "   " + poke2[y].getPs());

        while (poke1[x].getVida() != 0 && poke2[y].getVida() != 0) {
            boolean rapido = velocidad(poke1[x], poke2[y]);
            if (rapido == true) {

                opciones(poke1[x], poke2[y], proteina, vitamina, pocion);
                if (poke2[y].getVida() != 0) {
                    opciones(poke2[y], poke1[x], proteina, vitamina, pocion);
                }
            } else {

                opciones(poke2[y], poke1[x], proteina, vitamina, pocion);
                if (poke1[x].getVida() != 0) {
                    opciones(poke1[x], poke2[y], proteina, vitamina, pocion);
                }

            }

        }

        if (poke1[x].getVida() == 0) {
            gana(j2, poke2[y]);
            pierde(j1, poke1[x]);
            return 2;

        }

        if (poke2[y].getVida() == 0) {
            gana(j1, poke1[x]);
            pierde(j2, poke2[y]);
            return 1;

        }
        return 1;

    }

    public void gana(Jugador j, Pokemon x) throws ClassNotFoundException, SQLException {
        System.out.print(j.getNombre() + " gana el round");

        x.setLevel(x.getLevel() + 400);

        if (x.getLevel() >= 800) {
            x.setNivel(x.getNivel() + 1);
            System.out.println("\t\t\t\t" + x.getNombre() + " ha subido al nivel " + x.getNivel());
            evolucion(x);
            x.setLevel(0);

        }

        guarda(x, j.getNombre());

    }

    public void pierde(Jugador j, Pokemon x) throws ClassNotFoundException, SQLException {
        System.out.print(j.getNombre() + " pierde el round");

        x.setLevel(x.getLevel() + 100);

        if (x.getLevel() >= 800) {
            x.setNivel(x.getNivel() + 1);
            System.out.println("\t\t\t\t" + x.getNombre() + " ha subido al nivel " + x.getNivel());
            evolucion(x);
            x.setLevel(0);

        }
        guarda(x, j.getNombre());
    }

    public void guarda(Pokemon poke, String jugador) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/dani?useSSL=false&serverTimezone=UTC", "dani", "Ismael1988+");
        Statement s = c.createStatement();

        if (x(poke, jugador) == false) {

            s.executeUpdate("insert into pokemon values(" + poke.getCod() + ",'" + poke.getNombre() + "'," + poke.getNivel() + "," + poke.getLevel() + "," + poke.getVelocidad()
                    + ",'" + poke.getTipo() + "'," + poke.getPs() + "," + poke.getAtaque() + "," + poke.getDef() + ",'" + jugador + "');");

            s.executeUpdate("insert into ataques values(" + poke.getCod() + ",'" + poke.getAt1() + "'," + "0" + ",'" + jugador + "');");
            s.executeUpdate("insert into ataques values(" + poke.getCod() + ",'" + poke.getAt2() + "'," + "1" + ",'" + jugador + "');");
            s.executeUpdate("insert into ataques values(" + poke.getCod() + ",'" + poke.getAt3() + "'," + "2" + ",'" + jugador + "');");
            s.executeUpdate("insert into ataques values(" + poke.getCod() + ",'" + poke.getAt4() + "'," + "3" + ",'" + jugador + "');");

        } else {

            s.executeUpdate("update pokemon set nombre='" + poke.getNombre() + "',nivel=" + poke.getNivel() + ",level=" + poke.getLevel() + ",ps=" + poke.getPs()
                    + ",ataque=" + poke.getAtaque() + ",def=" + poke.getDef() + " where jugador='" + jugador + "'and nombre='" + poke.getNombre() + "';");

            s.executeUpdate("update ataques set nombre='" + poke.getAt1() + "' where posicion=0 and jugador='" + jugador + "' and cod=" + poke.getCod() + ";");
            s.executeUpdate("update ataques set nombre='" + poke.getAt2() + "' where posicion=1 and jugador='" + jugador + "' and cod=" + poke.getCod() + ";");
            s.executeUpdate("update ataques set nombre='" + poke.getAt3() + "' where posicion=2 and jugador='" + jugador + "' and cod=" + poke.getCod() + ";");
            s.executeUpdate("update ataques set nombre='" + poke.getAt4() + "' where posicion=3 and jugador='" + jugador + "' and cod=" + poke.getCod() + ";");

        }

    }

    public void guardajugador(Jugador j, String jugador) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/dani?useSSL=false&serverTimezone=UTC", "dani", "Pokemon1999");
        Statement s = c.createStatement();
        s.executeUpdate("update jugador set victorias=" + j.getVictorias() + " where nombre='" + jugador + "';");

    }

    public boolean x(Pokemon poke, String jugador) { //retorna true si gay jugador

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection kk = DriverManager.getConnection("jdbc:mysql://localhost:3306/dani?useSSL=false&serverTimezone=UTC", "dani", "Pokemon1999");
            Statement s = kk.createStatement();

            ResultSet e = s.executeQuery("select * from pokemon inner join ataques on pokemon.cod=ataques.cod where pokemon.nombre='" + poke.getNombre() + "';");////////////MIRAR TIENE QUE ACTUALIZAR O INGRESAR

            while (e.next()) {

                if (e.getString("pokemon.jugador").equalsIgnoreCase(jugador)) {
                    System.out.println("true!!!");

                    return true;

                }
            }

        } catch (SQLException e) {

            System.out.println("SQL EXCEPTION" + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Exception: " + cE.toString());

        }
        return false;

    }

    public void evolucion(Pokemon x) {

        switch (x.getNombre().trim()) {

            case "Bulbasur":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Hoja afilada");
                        System.out.println("\n\n Tu Bulbasur ha aprendido hoja afilada!!");
                        subnivel(x);

                        break;

                    case 3:
                        x.setNombre("Ivisur");
                        System.out.println("\n\n Tu Bulbasur ha evolucionado a Ivisur!!");

                        x.setAtaque(x.getAtaque() + 25);
                        x.setDef(x.getDef() + 30);
                        x.setVelocidad(x.getVelocidad() + 20);
                        x.setPs(x.getPs() + 30);
                        break;

                    case 4:
                        x.setAt3("coletazo");
                        System.out.println("\n\n Tu Ivisur ha aprendido drenadoras!!");
                        subnivel(x);
                        break;

                    case 5:
                        x.setAt4("Rayo solar");
                        System.out.println("\n\n Tu Ivisur ha aprendido Rayo solar!!");
                        subnivel(x);
                        break;

                    case 6:
                        x.setNombre("Venusaur");
                        System.out.println("\n\n Tu Ivisur ha evolucionado a Venusaur!!");
                        x.setAtaque(x.getAtaque() + 40);
                        x.setDef(x.getDef() + 50);
                        x.setVelocidad(x.getVelocidad() + 10);
                        x.setPs(x.getPs() + 40);

                        break;

                    default:
                        subnivel(x);
                        break;

                }

                break;

            case "Charmander":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Arañazo");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 3:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a Charmeleon!!");
                        x.setNombre("Charmeleon");
                        x.setAtaque(x.getAtaque() + 50);
                        x.setDef(x.getDef() + 20);
                        x.setVelocidad(x.getVelocidad() + 30);
                        x.setPs(x.getPs() + 30);
                        break;

                    case 4:
                        x.setAt3("Giro fuego");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 5:
                        x.setAt4("Llamarada");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido " + x.getAt4() + "!!");
                        subnivel(x);
                        break;

                    case 6:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a Charizard!!");
                        x.setNombre("Charizard");
                        x.setAtaque(x.getAtaque() + 50);
                        x.setDef(x.getDef() + 10);
                        x.setVelocidad(x.getVelocidad() + 50);
                        x.setPs(x.getPs() + 30);
                        break;

                    default:
                        subnivel(x);
                        break;

                }

                break;

            case "Squirtle":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("tortazo");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 3:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a Wartortle!!");
                        x.setNombre("Wartorle");
                        x.setAtaque(x.getAtaque() + 20);
                        x.setDef(x.getDef() + 20);
                        x.setVelocidad(x.getVelocidad() + 20);
                        x.setPs(x.getPs() + 20);
                        subnivel(x);
                        break;

                    case 4:
                        x.setAt3("Pistola de Agua");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 5:
                        x.setAt4("Hidrobomba");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido " + x.getAt4() + "!!");
                        subnivel(x);
                        break;

                    case 6:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a BLastoise!!");
                        x.setNombre("BLastoise");
                        x.setAtaque(x.getAtaque() + 20);
                        x.setDef(x.getDef() + 20);
                        x.setVelocidad(x.getVelocidad() + 20);
                        x.setPs(x.getPs() + 20);
                        break;

                    default:
                        subnivel(x);
                        break;

                }

                break;

            case "Ponita":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Ascuas");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 4:
                        x.setAt3("Giro fuego");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 5:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a Rapidash!!");
                        x.setNombre("Rapidash");
                        x.setAtaque(x.getAtaque() + 20);
                        x.setDef(x.getDef() + 20);
                        x.setVelocidad(x.getVelocidad() + 20);
                        x.setPs(x.getPs() + 20);
                        break;

                    case 6:
                        x.setAt3("Llamarada");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    default:
                        subnivel(x);
                        break;

                }

                break;

            case "Psyduck":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Burbuja");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 3:

                        x.setAt3("Pistola de Agua");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 5:
                        x.setAt4("Psiquico");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido " + x.getAt4() + "!!");
                        subnivel(x);
                        break;

                    case 4:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a Golduck!!");
                        x.setNombre("Golduck");
                        x.setAtaque(x.getAtaque() + 30);
                        x.setDef(x.getDef() + 20);
                        x.setVelocidad(x.getVelocidad() + 30);
                        x.setPs(x.getPs() + 10);
                        break;

                    default:
                        subnivel(x);
                        break;

                }

                break;

            case "Odish":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("placaje");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 3:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a Wartortle!!");
                        x.setNombre("Gloom");
                        x.setAtaque(x.getAtaque() + 20);
                        x.setDef(x.getDef() + 20);
                        x.setVelocidad(x.getVelocidad() + 20);
                        x.setPs(x.getPs() + 20);
                        break;

                    case 4:
                        x.setAt3("Latigo cepa");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 5:
                        x.setAt4("Rayo solar");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido " + x.getAt4() + "!!");
                        subnivel(x);
                        break;

                    case 6:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a BLastoise!!");
                        x.setNombre("Belloson");
                        x.setAtaque(x.getAtaque() + 20);
                        x.setDef(x.getDef() + 20);
                        x.setVelocidad(x.getVelocidad() + 20);
                        x.setPs(x.getPs() + 20);
                        subnivel(x);
                        break;

                    default:
                        break;

                }

                break;

            case "Vulpix":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Giro fuego");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 4:
                        x.setAt3("Coletazo");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 5:
                        x.setAt4("placaje");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido " + x.getAt4() + "!!");
                        subnivel(x);
                        break;

                    case 6:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a Ninetales!!");
                        x.setNombre("Ninetales");
                        x.setAtaque(x.getAtaque() + 20);
                        x.setDef(x.getDef() + 20);
                        x.setVelocidad(x.getVelocidad() + 20);
                        x.setPs(x.getPs() + 20);
                        break;

                    default:
                        subnivel(x);
                        break;

                }
                break;

            case "Starmie":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Burbuja");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 3:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a Staryou!!");
                        x.setNombre("Staryou");
                        x.setAtaque(x.getAtaque() + 20);
                        x.setDef(x.getDef() + 20);
                        x.setVelocidad(x.getVelocidad() + 20);
                        x.setPs(x.getPs() + 20);
                        break;

                    case 4:
                        x.setAt3("Pistola de Agua");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 5:
                        x.setAt4("Hidrobomba");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido " + x.getAt4() + "!!");
                        subnivel(x);
                        break;

                    default:
                        subnivel(x);
                        break;

                }
                break;

            case "Chicorita":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Latigo cepa");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 3:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a Bayleaf!!");
                        x.setNombre("Bayleaf");
                        x.setAtaque(x.getAtaque() + 20);
                        x.setDef(x.getDef() + 20);
                        x.setVelocidad(x.getVelocidad() + 20);
                        x.setPs(x.getPs() + 20);
                        break;

                    case 4:
                        x.setAt3("Rayo solar");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 5:
                        x.setAt4("Coletazo");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido " + x.getAt4() + "!!");
                        subnivel(x);
                        break;

                    default:
                        subnivel(x);
                        break;

                }

                break;

            case "Magmar":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Llamarada");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 3:
                        x.setAt3("Tortazo");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 5:
                        x.setAt4("Ascuas");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido " + x.getAt4() + "!!");
                        subnivel(x);
                        break;

                    default:
                        subnivel(x);
                        break;

                }

                break;

            case "Tangela":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Coletazo");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 3:
                        x.setAt3("Hoja afilada");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);

                        break;

                    case 4:
                        x.setAt4("Rayo solar    ");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido " + x.getAt4() + "!!");
                        subnivel(x);
                        break;

                    default:
                        subnivel(x);
                        break;

                }

                break;

            case "Dratini":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Rayo solar");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 3:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a Dragonair!!");
                        x.setNombre("Dragonair");
                        x.setAtaque(x.getAtaque() + 20);
                        x.setDef(x.getDef() + 20);
                        x.setVelocidad(x.getVelocidad() + 20);
                        x.setPs(x.getPs() + 20);
                        break;

                    case 4:
                        x.setAt3("Hidrobomba");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 6:
                        x.setAt4("Coletazo");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido " + x.getAt4() + "!!");
                        subnivel(x);
                        break;

                    case 5:

                        System.out.println("\n\n Tu " + x.getNombre() + " ha evolucionado a BLastoise!!");
                        x.setNombre("BLastoise");
                        x.setAtaque(x.getAtaque() + 20);
                        x.setDef(x.getDef() + 20);
                        x.setVelocidad(x.getVelocidad() + 20);
                        x.setPs(x.getPs() + 20);
                        break;

                    default:
                        subnivel(x);
                        break;

                }

                break;

            case "Celebi":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Rayo solar");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 3:
                        x.setAt3("Psiquico");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 4:
                        x.setAt3("Placaje");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    default:
                        subnivel(x);
                        break;

                }
                break;

            case "Moltres":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Llamarada");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 3:
                        x.setAt3("Placaje");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 5:
                        x.setAt4("Ascuas");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido " + x.getAt4() + "!!");
                        subnivel(x);
                        break;

                    default:
                        subnivel(x);
                        break;

                }
                break;

            case "Lugia":
                switch (x.getNivel()) {

                    case 2:
                        x.setAt2("Burbuja");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido +" + x.getAt2() + "!!");
                        subnivel(x);
                        break;

                    case 3:
                        x.setAt3("Placaje");
                        System.out.println("\n\n Tu" + x.getNombre() + " ha aprendido " + x.getAt3() + "!!");
                        subnivel(x);
                        break;

                    case 4:
                        x.setAt4("Hidrobomba");
                        System.out.println("\n\n Tu " + x.getNombre() + " ha aprendido " + x.getAt4() + "!!");
                        subnivel(x);
                        break;

                    default:
                        subnivel(x);
                        break;

                }
                break;

            default:

                break;

        }

    }

    public void subnivel(Pokemon x) {
        x.setAtaque(x.getAtaque() + 20);
        x.setDef(x.getDef() + 20);
        x.setVelocidad(x.getVelocidad() + 20);
        x.setPs(x.getPs() + 20);

    }

    public void opciones(Pokemon x, Pokemon y, int pocion, int proteina, int vitamina) throws InterruptedException {
        boolean fin = false;

        do {
            System.out.println("\n\t\t\t" + x.getNombre());
            System.out.println("\n\t\t\tElija una opcion");
            System.out.println("\t\t\t |-------------------------------------------------------|----------------------------------------------------------------------|");
            System.out.print("\t\t\t |\t\t" + "  1.ATAQUE " + "\t\t\t\t |\t\t\t      " + " 2.MOCHILA " + "\t\t\t\t|\n");
            System.out.println("\t\t\t |-------------------------------------------------------|----------------------------------------------------------------------|");
            System.out.print("\t\t\t |\t\t" + "  3.ACARICIAR" + "\t\t\t\t |\t\t\t    " + " 4.DATOS   " + "        \t\t\t\t|\n");
            System.out.println("\t\t\t |-------------------------------------------------------|----------------------------------------------------------------------|");

            switch (intro4()) {

                case 1:
                    seleccionaAtaque(x, y); ///ATAQUE;
                    fin = true;
                    break;

                case 2:

                    System.out.println("\t\t\t |-------------------------------------------------------|----------------------------------------------------------------------|");
                    System.out.print("\t\t\t |\t\t" + " 1.Pocion      " + "\t\t\t\t |\t\t\t      " + " 2.Proteinas   " + "\t\t\t\t|\n");
                    System.out.println("\t\t\t |-------------------------------------------------------|----------------------------------------------------------------------|");
                    System.out.print("\t\t\t |\t\t" + " 3.Vitaminas   " + "\t\t\t\t |\t\t\t      " + " 4.Atras       " + "\t\t\t\t|\n");
                    System.out.println("\t\t\t |-------------------------------------------------------|----------------------------------------------------------------------|");

                    switch (intro4()) {

                        case 1:

                            if (pocion >= 1) {

                                int poc = x.getVida() + 120;
                                x.setPs(poc);
                                System.out.println("\t\t\t Has tomato una pocion tu ps es ahora de " + x.getVida());
                                fin = true;
                                pocion--;
                            } else {
                                System.out.println("\t\t\t No te quedan mas pociones tio");
                            }
                            break;

                        case 2:

                            if (proteina >= 1) {
                                int att = x.getAtaque() + (int) (Math.random() * 100);
                                x.setAtaque(att);
                                System.out.println("\t\t\t Tu ataque ha aumentado a " + x.getAtaque());
                                fin = true;
                                proteina--;
                            } else {
                                System.out.println("\t\t\t No te quedan mas proteinas tio");
                            }
                            break;

                        case 3:

                            if (vitamina >= 1) {
                                int defen = x.getDef() + (int) (Math.random() * 100);
                                x.setDef(defen);
                                System.out.println("\t\t\t Tu defensa ha aumentado a " + x.getDef());
                                fin = true;
                                vitamina--;
                            } else {
                                System.out.println("\t\t\t No te quedan mas vitaminas tio");
                            }
                            break;
                        case 4:

                            break;

                    }
                    break;
                case 3:
                    System.out.println("\t\t\t Has acariciado a tu pokemon");
                    x.setPs(x.getDef() + 2);
                    x.setDef(x.getDef() + 5);
                    x.setAtaque(x.getAtaque() + 5);
                    fin = true;
                    break;

                case 4:
                    //  System.out.println("\t\t\t Rendirse nunca es una opcion");
                    info(x);
                    break;

            }

        } while (fin != true);
    }

    public boolean velocidad(Pokemon x, Pokemon y) {   ////FUNCIONA BIEN
        boolean lugar = false;
        if (x.getVelocidad() > y.getVelocidad()) {
            lugar = true;
        }
        return lugar;
    }

    public void info(Pokemon p) {
        System.out.println("\t\t\t +------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("\t\t\t |\t\t\t\t\t\t\t" + p.getNombre().toUpperCase() + "\t\t\t\t\t\t\t        |");
        System.out.println("\t\t\t |------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("\t\t\t |\tNivel= " + p.getNivel() + "\t|\tTipo  =" + p.getTipo().toUpperCase() + "\t|\tJugador=" + p.getJugador() + "\t|\tAtaque= " + p.getAtaque() + "\t|\tDefen= " + p.getDef() + "\t       |");
        System.out.println("\t\t\t |------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("\t\t\t |\t\t1. " + p.getAt1() + "\t\t\t |\t\t\t      2. " + p.getAt2() + "\t\t\t\t|");
        System.out.println("\t\t\t |-------------------------------------------------------|----------------------------------------------------------------------|");
        System.out.println("\t\t\t |\t\t3. " + p.getAt3() + "\t\t\t |\t\t\t      4. " + p.getAt4() + "\t\t\t\t|");
        System.out.println("\t\t\t +------------------------------------------------------------------------------------------------------------------------------+");

    }

    public void seleccionaAtaque(Pokemon x, Pokemon y) throws InterruptedException {

        String ata = "   ----        ";
        boolean ok = false;
        System.out.println("\t\t\t |-------------------------------------------------------|----------------------------------------------------------------------|");
        System.out.print("\t\t\t |\t\t1. " + x.getAt1() + "\t\t\t |\t\t\t      2. " + x.getAt2() + "\t\t\t\t|\n");
        System.out.println("\t\t\t |-------------------------------------------------------|----------------------------------------------------------------------|");
        System.out.print("\t\t\t |\t\t3. " + x.getAt3() + "\t\t\t |\t\t\t      4. " + x.getAt4() + "\t\t\t\t|\n");
        System.out.println("\t\t\t |-------------------------------------------------------|----------------------------------------------------------------------|");

        while (ok != true) {
            switch (intro4()) {

                case 1:

                    ata = x.getAt1().trim();

                    break;

                case 2:

                    ata = x.getAt2().trim();

                    break;

                case 3:

                    ata = x.getAt3().trim();

                    break;

                case 4:

                    ata = x.getAt4().trim();

                    break;
            }
            if (ata == "-------") {
                ok = false;
                System.out.println("(No has seleccionado ataque)");
            } else {
                ok = true;
            }

        }

        todoAtaque(ata, x, y);///////////NO VA 

    } //carga los ataques que tiene el pokemon y eliges el numero que hara un String

    public void todoAtaque(String ata, Pokemon x, Pokemon y) throws InterruptedException {
////BARRA DE VIDA
        switch (ata) {

            case "Hoja afilada":
                HojaAfilada(x, y);
                break;

            case "Latigo cepa":
                LatigoCepa(x, y);
                break;

            case "Rayo solar":
                Rayosolar(x, y);
                break;

            case "Drenadoras":
                Drenadoras(x, y);
                break;

            case "Ascuas":
                Ascuas(x, y);
                break;

            case "Llamarada":
                Llamarada(x, y);
                break;

            case "Giro fuego":
                Girofuego(x, y);
                break;

            case "Burbuja":
                Burbuja(x, y);
                break;

            case "Hidrobomba":
                Hidrobomba(x, y);
                break;

            case "Pistola de agua":
                PistoladeAgua(x, y);
                break;

            case "Coletazo":
                Coletazo(x, y);
                break;

            case "Placaje":
                Placaje(x, y);
                break;

            case "Tortazo":
                Tortazo(x, y);
                break;

            case "Arañazo":
                Arañazo(x, y);
                break;

            case "Psiquico":
                Psiquico(x, y);
                break;

            default:
                System.out.print("No cargo el ataque");

        }

    }  //Del String seleciona que ataque se va a ejecutar.

    ///----------------------------ATAQUES---------------------------------------------------------//
    public void indicador(Pokemon x) {

        int coco = x.getVida() / 10;
        System.out.print("\n\t\t\t");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("\t\t\t|");
        while (coco > 0) {

            System.out.print("XXX");
            coco -= 2;
        }
        System.out.print("=" + x.getVida() + "/" + x.getPs());
        System.out.print("\n\t\t\t");
        System.out.println("--------------------------------------------------------------------------------");

    }

    public int AtBasico(Pokemon p, Pokemon p2, String tipo) {
        int atack = 0;
        switch (tipo) {

            case "fuego":
                if (p2.getTipo().trim().equalsIgnoreCase("agua")) {
                    atack = p.getAtaque() / 2;

                }

                if (p2.getTipo().trim().equalsIgnoreCase("planta")) {
                    atack = p.getAtaque() * 4;

                }
                if (p2.getTipo().trim().equalsIgnoreCase("fuego")) {
                    atack = p.getAtaque();

                }
                break;

            case "agua":
                if (p2.getTipo().trim().equalsIgnoreCase("agua")) {
                    atack = p.getAtaque();

                }

                if (p2.getTipo().trim().equalsIgnoreCase("planta")) {
                    atack = p.getAtaque() / 2;

                }
                if (p2.getTipo().trim().equalsIgnoreCase("fuego")) {
                    atack = p.getAtaque() * 4;

                }
                break;

            case "planta":
                if (p2.getTipo().trim().equalsIgnoreCase("agua")) {
                    atack = p.getAtaque() * 4;

                }

                if (p2.getTipo().trim().equalsIgnoreCase("planta")) {
                    atack = p.getAtaque();

                }
                if (p2.getTipo().trim().equalsIgnoreCase("fuego")) {
                    atack = p.getAtaque() / 2;

                }
                break;

        }

        atack -= (p2.getDef() / 10);
        if (atack < 3) {
            atack = 5;
        }

        return atack;

    }

    public void textAtq(String n, Pokemon p, Pokemon y) {
        System.out.println("\n \t\t\t\t\t\t\t(" + p.getNombre().trim() + " uso " + n + " a " + y.getNombre().trim() + ")");
    }

    public void animacionBasica(String n) throws InterruptedException {
        //Animacion
        System.out.print("\n\t\t\t\t\t\t\t\t");
        for (int i = 0; i < 6; i++) {
            System.out.print(n + " ");
            Thread.sleep(500);
        }
    }

    public void ejecutaAtaque(int x, Pokemon p) {

        if ((p.getVida() - x) < 0) {
            p.setVida(0);
            indicador(p);

        } else {
            p.setVida(p.getVida() - x);
            indicador(p);
        }
        if (p.getVida() == 0) {
            System.out.print(p.getNombre().trim() + " debilitidado");

        }

    } ///ejecuta el atque y deja como minimo 0;

    // ------------------------------PLANTA---------------------------------------
    public void HojaAfilada(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (AtBasico(x, y, "planta") / 4) * 2;
        String ani = "*";
        animacionBasica(ani);
        ejecutaAtaque(hoja, y);
        String ata = "hoja afilada";

        textAtq(ata, x, y);

    }

    public void LatigoCepa(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (AtBasico(x, y, "planta") / 5) * 2;

        String ata = "latigo cepa";
        String ani = "-";
        animacionBasica(ani);
        ejecutaAtaque(hoja, y);
        textAtq(ata, x, y);

    }

    public void Rayosolar(Pokemon p, Pokemon y) throws InterruptedException {
        int llam = (AtBasico(p, y, "planta") * 2);
        String ata = "rayo solar";

        //Animacion
        System.out.print("\n\t\t\t\t\t\t\t\t  \\ | /");
        System.out.print("\n\t\t\t\t\t\t\t\t  -- O --");
        System.out.print("\n\t\t\t\t\t\t\t\t   / |\\");
        ejecutaAtaque(llam, y);
        textAtq(ata, p, y);
    }

    public void Drenadoras(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (AtBasico(x, y, "planta") / 6) * 3;

        String ata = "drenadoras";
        String ani = "----0";
        animacionBasica(ani);
        ejecutaAtaque(hoja, y);
        textAtq(ata, x, y);

    }

    // ------------------------------FUEGO---------------------------------------
    public void Ascuas(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (AtBasico(x, y, "fuego") * 3) / 4;

        String ata = "ascuas";
        String ani = "%";
        animacionBasica(ani);
        ejecutaAtaque(hoja, y);
        textAtq(ata, x, y);

    }

    public void Llamarada(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (AtBasico(x, y, "fuego") * 2);

        String ata = "Llamarada";
        String ani = "O";
        //Animacion
        System.out.print("\n\t\t\t\t\t\t\t\t    ***");
        System.out.print("\n\t\t\t\t\t\t\t\t    ***");
        System.out.print("\n\t\t\t\t\t\t\t\t***********");
        System.out.print("\n\t\t\t\t\t\t\t\t    ***");
        System.out.print("\n\t\t\t\t\t\t\t\t    ***");
        System.out.print("\n\t\t\t\t\t\t\t\t   ** **  ");
        System.out.print("\n\t\t\t\t\t\t\t\t  **    **");
        System.out.print("\n\t\t\t\t\t\t\t\t**       **");
        ejecutaAtaque(hoja, y);
        textAtq(ata, x, y);

    }

    public void Girofuego(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (AtBasico(x, y, "fuego") / 5) * 10;

        String ata = "girofuego";
        String ani = "O&";
        animacionBasica(ani);
        ejecutaAtaque(hoja, y);
        textAtq(ata, x, y);

    }

    // ------------------------------AGUA--------------------------------------
    public void Burbuja(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (AtBasico(x, y, "agua") / 4) * 5;

        String ata = "burbuja";
        String ani = "O";
        animacionBasica(ani);
        ejecutaAtaque(hoja, y);
        textAtq(ata, x, y);

    }

    public void Hidrobomba(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (AtBasico(x, y, "agua") * 2);

        String ata = "hidrobomba";

        //animacion
        System.out.print("\n\t\t\t\t\t\t\t\t");
        for (int i = 0; i < 7; i++) {
            System.out.print("==");
            Thread.sleep(500);
        }
        System.out.print("Booom!!!! ");
        ejecutaAtaque(hoja, y);
        textAtq(ata, x, y);

    }

    public void PistoladeAgua(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (AtBasico(x, y, "agua") / 3) * 4;

        String ata = "Pistola de agua";
        String ani = "---";
        animacionBasica(ani);
        ejecutaAtaque(hoja, y);
        textAtq(ata, x, y);

    }

    // ------------------------------NORMAL---------------------------------------
    public void Coletazo(Pokemon x, Pokemon y) throws InterruptedException {
        String ata = "coletazo";
        int plac = (x.getAtaque() / 3) + (y.getDef() / 10);

        ///-----------------animacion
        System.out.print("\n\t\t\t\t\t\t\t\t");
        System.out.print("8");
        for (int i = 0; i < 7; i++) {
            System.out.print("==");
            Thread.sleep(500);
        }
        System.out.print("D \n");
        ejecutaAtaque(plac, y);
        textAtq(ata, x, y);

    }

    public void Placaje(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (x.getAtaque());///atencion

        String ata = "Placaje";

        System.out.print("P ");
        Thread.sleep(500);
        System.out.print("L ");
        Thread.sleep(500);
        System.out.print("A ");
        Thread.sleep(500);
        System.out.print("C ");
        Thread.sleep(500);
        System.out.print("A ");
        Thread.sleep(500);
        System.out.print("J ");
        Thread.sleep(500);
        System.out.print("E ");
        Thread.sleep(500);

        System.out.print("\n");
        ejecutaAtaque(hoja, y);

        textAtq(ata, x, y);

    }

    public void Tortazo(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (x.getAtaque() / 2) * 3;

        String ata = "Tortazo";

        System.out.print("T ");
        Thread.sleep(500);
        System.out.print("O ");
        Thread.sleep(500);
        System.out.print("R ");
        Thread.sleep(500);
        System.out.print("T ");
        Thread.sleep(500);
        System.out.print("A ");
        Thread.sleep(500);

        System.out.print("Z ");
        Thread.sleep(500);
        System.out.print("O ");
        Thread.sleep(500);

        System.out.print("\n");
        ejecutaAtaque(hoja, y);
        textAtq(ata, x, y);

    }

    public void Arañazo(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (x.getAtaque() / 4) * 2;

        String ata = "Arañazo";

        System.out.print("A ");
        Thread.sleep(500);
        System.out.print("R ");
        Thread.sleep(500);
        System.out.print("A ");
        Thread.sleep(500);
        System.out.print("Ñ ");
        Thread.sleep(500);
        System.out.print("Z ");
        Thread.sleep(500);
        System.out.print("O ");
        Thread.sleep(500);

        System.out.print("\n");
        ejecutaAtaque(hoja, y);
        textAtq(ata, x, y);

    }

    public void Psiquico(Pokemon x, Pokemon y) throws InterruptedException {

        int hoja = (x.getAtaque() * 5);

        String ata = "Psiquico";

        System.out.print("¿ ");
        Thread.sleep(500);
        System.out.print("? ");
        Thread.sleep(500);
        System.out.print("? ");
        Thread.sleep(500);
        System.out.print("¿ ");
        Thread.sleep(500);
        System.out.print("? ");
        Thread.sleep(500);
        System.out.print("¿ ");
        Thread.sleep(500);

        System.out.print("\n");
        ejecutaAtaque(hoja, y);
        textAtq(ata, x, y);

    }

}
