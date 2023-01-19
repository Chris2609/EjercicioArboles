package GestionArboles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GestorArboles {
	
	private static final String HOST = "localhost";
	private static final String BBDD = "eh_garden";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public void run() throws ClassNotFoundException, SQLException{
		
		final int OPCION_UNO = 1;
        final int OPCION_DOS = 2;
        final int OPCION_TRES = 3;
        final int OPCION_CUATRO = 4;
        final int SALIR = 0;
        
        
        Scanner scan = new Scanner(System.in);
        
        Connection conexion;
        Class.forName("com.mysql.cj.jdbc.Driver");
		conexion = DriverManager.getConnection("jdbc:mysql://"+HOST+"/"+BBDD, USERNAME, PASSWORD);
		Statement st = conexion.createStatement();
        
		Arbol arbol = new Arbol();
		
        int opcion_menu;
        do {
            System.out.println(" ------MENU------");
            System.out.println(OPCION_UNO + ". Insertar árbol");
            System.out.println(OPCION_DOS + ". Eliminar árbol");
            System.out.println(OPCION_TRES + ". Modificar información del árbol");
            System.out.println(OPCION_CUATRO + ". Visualizar árboles");
            System.out.println(SALIR + ". Salir");
            System.out.println("Elije una de las opciones");
            opcion_menu = Integer.parseInt(scan.nextLine());
            
            String nombrecomun=null;
            String nombrecientifico=null;
            String habitat=null;
            int altura=0;
            String origen=null;
            
            
            switch (opcion_menu) {
            case OPCION_UNO:
                System.out.println("Introduce el nombre común del árbol");
        		nombrecomun = scan.nextLine();     
        		System.out.println("Introduce el nombre científico del árbol");
        		nombrecientifico = scan.nextLine();
                System.out.println("Introduce el habitat del árbol");
                habitat = scan.nextLine();
                System.out.println("Introduce la altura del árbol");
                altura = Integer.parseInt(scan.nextLine());
                System.out.println("Introduce el origen del árbol");
                origen = scan.nextLine();
                
                st.execute("INSERT INTO tablaarbol (nombre_comun, nombre_cientifico, habitat, altura, origen) VALUES ('"+nombrecomun+"',"+" '"+nombrecientifico+"',"+" '"+habitat+"',"+" "+altura+","+ " '"+origen+"')");

                System.out.println("Árbol introducido correctamente");
                
                break;
            case OPCION_DOS:
                System.out.println("Introduce el nombre científico del arbol que quieres eliminar");
                String borrar = scan.nextLine();
                
                String sentenciaDelete = "DELETE FROM tablaarbol WHERE nombre_comun = '"+borrar+"'";
        		st.execute(sentenciaDelete);
                
                break;
            case OPCION_TRES:
                System.out.println("Introduce el nombe científico del arbol que quieres actualizar");
                String actualizar = scan.nextLine();
                
                System.out.println("Introduce el nuevo nombre común del árbol");
        		nombrecomun = scan.nextLine();     
        		System.out.println("Introduce el nuevo nombre científico del árbol");
        		nombrecientifico = scan.nextLine();
                System.out.println("Introduce el nuevo habitat del árbol");
                habitat = scan.nextLine();
                System.out.println("Introduce la nueva altura del árbol");
                altura = Integer.parseInt(scan.nextLine());
                System.out.println("Introduce el nuevo origen del árbol");
                origen = scan.nextLine();
                
                String sentenciaUpdate = "UPDATE tablaarbol SET nombre_comun='"+nombrecomun+"', nombre_cientifico= '"+nombrecientifico+"', habitat='"+habitat+"', altura='"+altura+"', origen='"+origen+"' WHERE nombre_cientifico = '"+actualizar+"'";
        		st.executeUpdate(sentenciaUpdate);
        		
                break;
               
            case OPCION_CUATRO:
                System.out.println("Cargando árboles...\n");
                
        		String sentenciaSelect = "SELECT * FROM tablaarbol";
        		ResultSet resultado = st.executeQuery(sentenciaSelect);
        		while(resultado.next()) {
        			System.out.println(resultado.getInt(1) + "|" + resultado.getString(2) + "|" + resultado.getString(3) + "|" + resultado.getString(4) + "|" + resultado.getInt(5)+ "|" + resultado.getString(6));
        		}
        		System.out.println("\n");
        		
        		
                break;
                
            case SALIR:
                System.out.println("ADIOS");
                break;
            default:
                System.out.println("Opcion incorrecta!");
            }
        } while (opcion_menu != SALIR);	
        
		conexion.close();

	}
}
