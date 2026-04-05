package Booking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import ResourceStates.IResource;
import ResourceStates.Resource;
import Scheduling.Scheduler;
import Scheduling.TimeSlot;

public class BookingTest {
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
        s.approve("senno", t);
        //seeing if we can book the same thing on the same timeslot
        boolean result = s.canApprove("senno", t);

        assertEquals(false, result);
    }

    //BookingStrategy tests
    //same issue, these unconditionally return true and call scheduler.addpending()
    @Test
    void bstrat_isValid(){
        Scheduler s = new Scheduler();
        BookingStrategy bi = new RoomBookingStrategy(s);

        assertNotEquals(null, bi);
    }
}
