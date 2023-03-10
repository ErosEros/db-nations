package provajdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String url = "jdbc:mysql://localhost:3306/Nations";
		  String user = "root";
		  String password = "root";
		  Scanner s=new Scanner(System.in);
		  

		  //CONNESSIONE 
		  try (Connection con = DriverManager.getConnection(url, user, password)){
			  System.out.println("CONNESSIONE RIUSCITA");
			  

			  System.out.print("Quale nazione vuoi cercare?:");
			  String find = s.nextLine();


			  String sql="select countries.name , countries.country_id ,regions.name ,regions.region_id ,  continents.name \r\n"
			  		+ "from countries \r\n"
			  		+ "join regions on countries.region_id = regions.region_id \r\n"
			  		+ "join continents on continents.continent_id = regions.continent_id\r\n"
			  		+ "WHERE countries.name LIKE ?"
			  		+ "order by countries.name";


			  try(PreparedStatement ps=con.prepareStatement(sql)) {
				  ps.setString(1, "%"+ find + "%");

				  try(ResultSet rs =ps.executeQuery()) {


					  //RISULTATO
					  System.out.println("\n NAZIONI \t\t\t\t\t CONTINENTE \n");
					  while (rs.next()) { 
						 System.out.println(
								 
								  rs.getString(1) + "\t\t\t\t\t" + 
						 		rs.getString("continents.name"));


					  } 
				  }
				  
			  }
			  
		  } catch (SQLException ex) {
		     ex.printStackTrace();
		  }

	}

}
