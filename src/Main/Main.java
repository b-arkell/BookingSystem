package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import Booking.*;
import ResourceStates.*;
import Users.User;
import Scheduling.*;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException {
		
		Scanner scanner = new Scanner(System.in);
		BookingService bookingService = new BookingService();
		Scheduler scheduler = new Scheduler();
		User user1 = new User("user1", "SammyBoy");
		User user2 = new User("user2", "Brobody");
		
		
		File file = new File("scheduler.dat");
		if (!file.exists() || file.length() == 0) {
			System.out.println("No Previous Scheduler Data. Starting Fresh Schedule...");
		}else {
			scheduler.loadFromFile("scheduler.dat");			
		}
		

		// create resources
		// - rooms
		IResource room1 = ResourceFactory.createResource("Study Room 101", new RoomBookingStrategy(scheduler));
		
		// - laptops
		IResource laptop1 = ResourceFactory.createResource("Gaming Laptop - 1", new LaptopBookingStrategy(scheduler));
		IResource laptop2 = ResourceFactory.createResource("Programming Laptop - 1", new LaptopBookingStrategy(scheduler));		
		
		
		boolean runDemo = true;
		
		do
		{
			
			int menuChoice = 4;	// defaulting to 4 to avoid tripping any choice accidentally
			System.out.println("Please select an option below:");
			System.out.println("0. Fresh Start - Clear all file contents.");
			System.out.println("1. RUN - DEMO PART 1");
			System.out.println("2. RUN - DEMO PART 2");
			System.out.println("3. Save and Exit");
			System.out.println("4. Exit without Saving");
			menuChoice = scanner.nextInt();
			
			switch (menuChoice)
			{
				case 0:
				{
					// DEMO PART 0
					// Clear schedule.dat
					// clear user1.dat
					// clear user2.dat	
					System.out.println("[Menu 0] Clearing files");
					scheduler.clearScheduleData();
					user1.clearUserData("user1.dat");
					user2.clearUserData("user2.dat");
					System.out.println("[Menu 0] Files Cleared");
					break;
				}
				case 1: 
				{
					// DEMO PART 1
					// ----------------
					// User1 logs in
					System.out.println("___User1 Logged in: " + user1.getName() + "___");
					
					// - books room1	- in the future, this would we a helper function.
					TimeSlot slot1 = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), user1.getName());
					user1.setDraft(room1, slot1);
					bookingService.BookResource(room1, user1.getUserID(), slot1);
					room1.setState(new PendingState());
					
					// - books laptop1		
					TimeSlot slot2 = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), user1.getName());
					user1.setDraft(laptop1, slot2);
					bookingService.BookResource(laptop1, user1.getUserID(), slot2);
					laptop1.setState(new PendingState());
					
					// Admin logs in
					// In a menu system, you would select that you were an admin instead of user.
					System.out.println("___Admin Logged in___");
					
					// - approve room1
					if (scheduler.canApprove(room1.getName(), slot1)){
						//bookingService.BookResource(room1, user1.getUserID(), slot1);
						scheduler.approve(room1.getName(), slot1);
						//System.out.println("Admin approves booking...");
					}
					else {
						//System.out.println("Admin rejects Booking...");
						scheduler.reject(room1.getName(), slot1);
						//room1.setState(new AvailableState());
					}
					// - approve laptop1 booking
					if (scheduler.canApprove(laptop1.getName(), slot2)){
						//bookingService.BookResource(laptop1, user1.getName());
						scheduler.approve(laptop1.getName(), slot2);
						//System.out.println("Admin approves booking...");
					}
					else {
						//System.out.println("Admin rejects Booking...");
						scheduler.reject(laptop1.getName(), slot2);
						//laptop1.setState(new AvailableState());
					}
					
					break;
				}
				case 2:
				{ 
					// DEMO PART 2
					// ----------------		
					// User2 logs in
					System.out.println("___User2 Logged in: " + user2.getName() + "___");
					
					// - books room1
					TimeSlot slot1 = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), user2.getName());
					user2.setDraft(room1, slot1);
					bookingService.BookResource(room1, user2.getUserID(), slot1);
					room1.setState(new PendingState());
					
					// - books laptop2
					TimeSlot slot2 = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), user2.getName());
					user2.setDraft(laptop2, slot2);					
					bookingService.BookResource(laptop2, user2.getUserID(), slot2);
					laptop2.setState(new PendingState());
					
					// Admin logs in
					System.out.println("___Admin Logged in___");
					// - rejects room1
					if (scheduler.canApprove(room1.getName(), slot1)){
						scheduler.approve(room1.getName(), slot1);
					}
					else {
						//System.out.println("Rejected due to conflict!");
						scheduler.reject(room1.getName(), slot1);
					}
					
					// - approves laptop2
					if (scheduler.canApprove(laptop2.getName(), slot2)){
						scheduler.approve(laptop2.getName(), slot2);
						//System.out.println("Admin approves booking...");
					}
					else {
						//System.out.println("Rejected due to conflict!");
						scheduler.reject(laptop2.getName(), slot2);
					}
						
					break;
				}
				case 3: 
				{
					System.out.println("Have a nice day :)");
					runDemo = false;
					scanner.close();
					scheduler.saveToFile("scheduler.dat");
			        user1.saveToFile("user1.dat");
			        user2.saveToFile("user2.dat");
					break;
				}
				case 4:
				{
					System.out.println("Exiting without Saving. Have a nice day :)");
					runDemo = false;
					scanner.close();
					break;
				}
				default: 
				{
					System.out.println("Invalid input. Please, try again.");
					break;
				}
			}
	
		} while (runDemo);
		
		scanner.close();
		
	}

}
