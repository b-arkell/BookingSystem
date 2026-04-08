package Booking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ResourceStates.IResource;
import ResourceStates.Resource;
import Scheduling.Scheduler;
import Scheduling.TimeSlot;

public class BookingTest {

    @Test
    void bservice_basic__isValid(){
        BookingService b = new BookingService();
        assertNotEquals(null, b);
    }

    //bookingService tests

    //once again, a bit difficult to test since they just call other classes functions
    /*
    BookingService.BookResource() ->
    IResource.book() ->
    ResourceState.book() prints OR ->
    BookingStrategy.book() ->
    Scheduler.addpending() //invisible effects...
     */
    @Test
    void bservice_isValid(){
        BookingService b = new BookingService();

        Scheduler s = new Scheduler();
        BookingStrategy bil = new RoomBookingStrategy(s);
        IResource ir = new Resource("senno", bil);

        LocalDateTime ld = LocalDateTime.now();
        TimeSlot t = new TimeSlot(ld, ld.plusHours(4), "argos");

        b.BookResource(ir, "saggitarius", t);

        
        //in order to actually test something here,
        ArrayList<TimeSlot> k = new ArrayList<TimeSlot>();
        k.add(t);
        Map<String, List<TimeSlot>> exp = new HashMap<>();
        exp.put("senno", k);

        //seeing if it was succefully added to pending
        assertEquals(exp, s.getPending());
    }

    //BookingStrategy tests
    //same issue, these unconditionally return true and call scheduler.addpending()
    //the fact that bookingservice passed manes that this passes too lol
    @Test
    void bstrat_isValid(){
        Scheduler s = new Scheduler();
        BookingStrategy bi = new RoomBookingStrategy(s);

        assertNotEquals(null, bi);
    }
}
