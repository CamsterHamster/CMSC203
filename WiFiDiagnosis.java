package WiFiDiagnosis;

import java.util.Scanner;

public class WiFiDiagnosis {
	public static void main(String[] args)
	{
		String response;
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("If you have a problem with" +
		" internet connectivity, this WiFi Diagnosis might work.");
		
		System.out.println("First step: reboot you computer");
		
		System.out.println("Are you able to connect with the" +
		" internet? (yes or no)" );
		
		response = keyboard.nextLine();
		
		if (response.equals("yes"))
		{
			System.out.println("Rebooting your computer seemed "
			+ "to work" );
		}
		else if (response.equals("no"))
		{
			System.out.println("Second step: reboot you router "
			+ "and try to connect");
			
			System.out.println("Now are you able to connect with the "
			+ "internet? (yes or no)" );
			
			response = keyboard.nextLine();
			
			if (response.equals("yes"))
			{
				System.out.println("Rebooting your router seemed "
				+ "to work" );
			}
			else if (response.equals("no"))
			{
				System.out.println("Third step: make sure the cables to"
				+ " your router are plugged in firmly and your router"
				+ " is getting power");
				
				System.out.println("Now are you able to connect with the "
				+ "internet? (yes or no)" );
				
				response = keyboard.nextLine();
				
				if (response.equals("yes"))
				{
					System.out.println("Checking the router's "
					+ "cables seemed to work");
				}
				else if (response.equals("no"))
				{
					System.out.println("Fourth step: move your computer "
					+ "closer to your router");
					
					System.out.println("Now are you able to connect with the "
					+ "internet? (yes or no)" );
					
					response = keyboard.nextLine();
					
					if (response.equals("yes"))
					{
						System.out.println("Moving your computer "
						+ "closer to your router seemed to work");
					}
					else if (response.equals("no"))
					{
						System.out.println("Fifth step: contact your "
								+ "ISP\nMake sure your ISP is hooked "
								+ "up to your router.");
					}
					else
						System.out.println("Incorrect entry\n"
						+ "Please restart the program and try again");
				}
				else
					System.out.println("Incorrect entry\n"
					+ "Please restart the program and try again");
			}
			else
				System.out.println("Incorrect entry\n"
				+ "Please restart the program and try again");
		}
		else
			System.out.println("Incorrect entry\n"
			+ "Please restart the program and try again");
	}
}
						