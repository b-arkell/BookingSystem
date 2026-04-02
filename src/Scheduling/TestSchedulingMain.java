package Scheduling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import Booking.*;
import ResourceStates.*;
import Users.User;

public class TestSchedulingMain {

	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException {
		
		Scanner scanner = new Scanner(System.in);
		BookingService bookingService = new BookingService();
		Scheduler scheduler = new Scheduler();
		User user1 = new User();
		User user2 = new User();
		
		
		File file = new File("scheduler.dat");
		if (!file.exists() || file.length() == 0) {
			System.out.println("No Previous Scheduler Data. Starting Fresh Schedule...");
		}else {
			scheduler.loadFromFile("scheduler.dat");			
		}
		
//		File user1File = new File("user1.dat");
//		if (!user1File.exists() || user1File.length() == 0) {
//			System.out.println("No Previous User Data. Starting Fresh user file...");
//			user1.setUserID("007");
//			user1.setName("Samwise");
//		}else {
//			user1.loadFromFile("user1.dat");			
//		}
		
//		File user2File = new File("userj.dat");
//		if (!user2File.exists() || user2File.length() == 0) {
//			System.out.println("No Previous User Data. Starting Fresh user file...");
//			user2.setUserID("420");
//			user2.setName("Brodilicious");
//		}else {
//			user2.loadFromFile("user2.dat");			
//		}
		
		
//		try {	// Sam added for manual test
//			TimeSlot myTimeslot = user1.getSpecDraft("Study Room 101");
//			System.out.println("[TestSchdulingMain] Resource Name: " + "Study Room 101" + " Time Slot: " + myTimeslot);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		// create resources
		// - rooms
		IResource room1 = ResourceFactory.createResource("Study Room 101", new RoomBookingStrategy());
//		IResource room2 = ResourceFactory.createResource("Study Room 102", new RoomBookingStrategy());
//		IResource room3 = ResourceFactory.createResource("Reading Room 103", new RoomBookingStrategy());
		
		// - laptops
		IResource laptop1 = ResourceFactory.createResource("Gaming Laptop - 1", new LaptopBookingStrategy());
		IResource laptop2 = ResourceFactory.createResource("Programming Laptop - 1", new LaptopBookingStrategy());
		
		
		// create time slot			// int year, int month, int dayOfMonth, int hour, int minute
//		TimeSlot slot1 = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), user1.getName());
//		TimeSlot slot2 = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), user2.getName());
		
		
		boolean runDemo = true;
		
		do
		{
			
			int menuChoice = 0;
			System.out.println("Please select an option below:");
			System.out.println("0. Fresh Start - Clear all file contents.");
			System.out.println("1. RUN - DEMO PART 1");
			System.out.println("2. RUN - DEMO PART 2");
			System.out.println("3. Exit");
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
					File user1File = new File("user1.dat");
					if (!user1File.exists() || user1File.length() == 0) {
						System.out.println("No Previous User Data. Starting Fresh user file...");
						user1.setUserID("007");
						user1.setName("Samwise");
					}else {
						user1.loadFromFile("user1.dat");			
					}
					System.out.println("___User1 Logged in: " + user1.getName() + "___");
					
					// - books room1	- in the future, this would we a helper function.
					TimeSlot slot1 = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), user1.getName());
					user1.setDraft(room1, slot1);
					scheduler.addPending(room1.getName(), slot1);
					room1.setState(new PendingState());
					
					// - books laptop1		
					TimeSlot slot2 = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), user1.getName());
					user1.setDraft(laptop1, slot2);					
					scheduler.addPending(laptop1.getName(), slot2);
					laptop1.setState(new PendingState());
					
					// Admin logs in
					// In a menu system, you would select that you were an admin instead of user.
					System.out.println("___Admin Logged in___");
					
					// - approve room1
					if (scheduler.canApprove(room1.getName(), slot1)){
						bookingService.BookResource(room1, user1.getName());
						scheduler.approve(room1.getName(), slot1);
						System.out.println("Admin approves booking...");
					}
					else {
						System.out.println("Admin rejects Booking...");
						scheduler.reject(room1.getName(), slot1);
						//room1.setState(new AvailableState());
					}
					// - approve laptop1 booking
					if (scheduler.canApprove(laptop1.getName(), slot2)){
						bookingService.BookResource(laptop1, user1.getName());
						scheduler.approve(laptop1.getName(), slot2);
						System.out.println("Admin approves booking...");
					}
					else {
						System.out.println("Admin rejects Booking...");
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
					File user2File = new File("userj.dat");
					if (!user2File.exists() || user2File.length() == 0) {
						System.out.println("No Previous User Data. Starting Fresh user file...");
						user2.setUserID("420");
						user2.setName("Brodilicious");
					}else {
						user2.loadFromFile("user2.dat");			
					}
					System.out.println("___User2 Logged in: " + user2.getName() + "___");
					
					// - books room1
					TimeSlot slot1 = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), user2.getName());
					user2.setDraft(room1, slot1);
					scheduler.addPending(room1.getName(), slot1);
					room1.setState(new PendingState());
					
					// - books laptop2
					TimeSlot slot2 = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), user2.getName());
					user2.setDraft(laptop2, slot2);					
					scheduler.addPending(laptop2.getName(), slot2);
					laptop2.setState(new PendingState());
					
					// Admin logs in
					System.out.println("___Admin Logged in___");
					// - rejects room1
					if (scheduler.canApprove(room1.getName(), slot1)){
						bookingService.BookResource(room1, user1.getName());
						scheduler.approve(room1.getName(), slot1);
						System.out.println("Admin approves booking...");
					}
					else {
						System.out.println("Rejected due to conflict!");
						scheduler.reject(room1.getName(), slot1);
					}
					
					// - approves laptop2
					if (scheduler.canApprove(laptop2.getName(), slot2)){
						bookingService.BookResource(laptop2, user2.getName());
						scheduler.approve(laptop2.getName(), slot2);
						System.out.println("Admin approves booking...");
					}
					else {
						System.out.println("Rejected due to conflict!");
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
				default: 
				{
					System.out.println("Invalid input. Please, try again.");
					break;
				}
			}
	
		} while (runDemo);
		
		scanner.close();
		
		
		
//		/// USER ACTION
//		
//		System.out.println("___User1 Logged in: " + user1.getName() + "___");
//		//user1.clearDrafts();	// Sam added for manual test
//		user1.setDraft(room1, slot1);	// Sam added for manual test
//		try {	// Sam added for manual test
//			TimeSlot myTimeslot = user1.getSpecDraft(room1.getName());
//			System.out.println("[TestSchdulingMain] Resource Name: " + room1.getName() + " Time Slot: " + myTimeslot);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		scheduler.addPending(room1.getName(), slot1);
//		room1.setState(new PendingState());
//		
//		System.out.println("User Requested Booking for " + room1.getName());
//		
//		
//		/// Admin Action
//		System.out.println("___Admin Logged in___");
//		
//		if (scheduler.canApprove(room1.getName(), slot1)){
//			
//			bookingService.BookResource(room1, user1.getName());
//			scheduler.approve(room1.getName(), slot1);
//			System.out.println("Admin approves booking...");
//
//			
//
//		}else {
//			System.out.println("Admin rejects Booking...");
//			scheduler.reject(room1.getName(), slot1);
//			room1.setState(new AvailableState());
//		}
//		
//		// user tries again with conflict
//        System.out.println("\n=== USER LOGGED IN AGAIN ===");
//
//        TimeSlot slot3 = new TimeSlot(
//                LocalDateTime.of(2026, 4, 1, 11, 0),
//                LocalDateTime.of(2026, 4, 1, 13, 0), "User2"
//        );
//
//        scheduler.addPending(room1.getName(), slot3);
//        room1.setState(new PendingState());
//
//        System.out.println("User requested overlapping booking");
//
//        
//        /// Admin check again
//        
//        System.out.println("\n=== ADMIN LOGGED IN AGAIN ===");
//
//        if (scheduler.canApprove(room1.getName(), slot3)) {
//
//            scheduler.approve(room1.getName(), slot3);
//            bookingService.BookResource(room1, "User2");
//
//        } else {
//
//            System.out.println("Rejected due to conflict!");
//            scheduler.reject(room1.getName(), slot3);
//            room1.setState(new AvailableState());
//        }
        
        
//		scheduler.saveToFile("scheduler.dat");
//        user1.saveToFile("user1.dat");
//        user2.saveToFile("user2.dat");
		
	}

}
