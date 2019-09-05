package tth_engine;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
	static Scanner console = new Scanner(System.in);
	
	public static void main( String[] args )
	{
		
		DBConnector connect = new DBConnector();	
		
		//System.out.println("Please Type an Instance Name: ");
		//String instance = console.nextLine();
		//List<Location> locations = connect.getAllCells();
		//List<Cell> cells = connect.getCellsByInstance(instance);
		List<Cell> cells = connect.getAllCells();

		CellMap.main(args);
	}
}
