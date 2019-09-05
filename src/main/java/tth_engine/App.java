package tth_engine;

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

		CellMap.main(args);
	}
}
