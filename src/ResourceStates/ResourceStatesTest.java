package ResourceStates;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import Booking.BookingStrategy;
import Booking.LaptopBookingStrategy;
import Booking.RoomBookingStrategy;
import Scheduling.Scheduler;
import Scheduling.TimeSlot;

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
        

        //can't exactly be tested, object ids differ and availablestate doesn't have any values to test against
        assertEquals(l, r.getBookingStrategy());
        assertEquals(x, r.getState());        
    }

    @Test
    void availableState_doesbook(){
        Scheduler s = new Scheduler();
        BookingStrategy q = new RoomBookingStrategy(s);

        LocalDateTime i = LocalDateTime.now();
        TimeSlot t = new TimeSlot(i, i.plusDays(3), "sarco");

        IResource r = new Resource("oedipus", q);
        r.book("sarco", t);


        ArrayList<TimeSlot> k = new ArrayList<TimeSlot>();
        k.add(t);
        Map<String, List<TimeSlot>> exp = new HashMap<>();
        exp.put("oedipus", k);

        //seeing if it was succefully added to pending
        assertEquals(exp, s.getPending());
    }
    @Test
    void bookedState_notbook(){
        Scheduler s = new Scheduler();
        BookingStrategy q = new RoomBookingStrategy(s);

        LocalDateTime i = LocalDateTime.now();
        TimeSlot t = new TimeSlot(i, i.plusDays(3), "sarco");

        IResource r = new Resource("oedipus", q);
        ResourceState x = new BookedState();
        r.setState(x);

        r.book("sarco", t);


        Map<String, List<TimeSlot>> exp = new HashMap<>();

        //checking that it was not added
        //also proves that setstate works
        assertEquals(exp, s.getPending());
    }
    @Test
    void release_MakesAvailable(){
        Scheduler s = new Scheduler();
        BookingStrategy q = new RoomBookingStrategy(s);

        LocalDateTime i = LocalDateTime.now();
        TimeSlot t = new TimeSlot(i, i.plusDays(3), "sarco");

        IResource r = new Resource("oedipus", q);
        ResourceState x = new BookedState();
        r.setState(x);
        r.release("sarco");

        r.book("sarco", t);


        ArrayList<TimeSlot> k = new ArrayList<TimeSlot>();
        k.add(t);
        Map<String, List<TimeSlot>> exp = new HashMap<>();
        exp.put("oedipus", k);

        //proving that release works by making it availalbe again (and thus books)
        assertEquals(exp, s.getPending());
    }

    //Resourcestate + its implementations:
    /*
    available:
        book():
	        -bookingstrategy.book() --done
        release():
	        -prints
    booked:
        book():
	        -print
        release():
	        -resource.setstate() --done
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
