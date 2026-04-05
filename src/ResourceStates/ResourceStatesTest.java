package ResourceStates;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Booking.BookingStrategy;
import Booking.LaptopBookingStrategy;
import Booking.RoomBookingStrategy;
import Scheduling.Scheduler;

public class ResourceStatesTest {
//liskov subsition says I can test the interface and not give a crap about the different implementations
//or something like that
//in actuality the implementations are identical so its legitimatly fine

    @Test
    void constructor_isValid(){
        Scheduler s = new Scheduler();

        IResource r = new Resource("oedipus", new RoomBookingStrategy(s));

        assertNotEquals(null, r);
    }
    @Test
    void getters_areValid(){
        Scheduler s = new Scheduler();
        RoomBookingStrategy q = new RoomBookingStrategy(s);

        IResource r = new Resource("oedipus", q);
        
        assertEquals("oedipus", r.getName());
        //assertEquals(new AvailableState(), r.getState()); 
        //can't exactly be tested, object ids differ and availablestate doesn't have any values to test against
        assertEquals(q, r.getBookingStrategy());
    }
    @Test
    void setters_areValid(){
        Scheduler s = new Scheduler();
        BookingStrategy q = new RoomBookingStrategy(s);



        IResource r = new Resource("oedipus", q);

        BookingStrategy l = new LaptopBookingStrategy(s);
        ResourceState x = new BookedState();
        r.setBookingStrategy(l);
        r.setState(x);
        

        //assertEquals(new AvailableState(), r.getState()); 
        //can't exactly be tested, object ids differ and availablestate doesn't have any values to test against
        assertEquals(l, r.getBookingStrategy());
        assertEquals(x, r.getState());        
    }
     //other functions are too dependant on other classes or are difficult to test:
    // book/release() simply call the respective resourcestate functions, display() only prints

    //Resourcestate + its implementations tests:
    //not really possible to test, as they are either calling other functions or printing;
    /*
    available:
        book():
	        -bookingstrategy.book()
        release():
	        -prints
    booked:
        book():
	        -print
        release():
	        -resource.setstate()
    pending:
        book():
    	    -prints
        release():
	        -prints
    */

    //ResourceFactory tests:
    @Test
    void ResourceFac_isValid(){
        Scheduler s = new Scheduler();
        BookingStrategy q = new RoomBookingStrategy(s);

        IResource x = ResourceFactory.createResource("laptop", q);
    
        assertEquals("laptop", x.getName());
        //cannot test actual type of x, unfortunatly
        assertEquals(q,x.getBookingStrategy());
    }
    @Test
    void ResFac_caps_areValid(){
        Scheduler s = new Scheduler();
        BookingStrategy q = new RoomBookingStrategy(s);

        IResource x = ResourceFactory.createResource("LaPToP", q);
    
        assertEquals("LaPToP", x.getName());
    }
    @Test
    void invalidRes_throwsError(){
        Scheduler s = new Scheduler();
        BookingStrategy q = new RoomBookingStrategy(s);

        assertThrows(IllegalArgumentException.class, ()->{ 
            ResourceFactory.createResource("abnormality", q);
        });
    }
   }
