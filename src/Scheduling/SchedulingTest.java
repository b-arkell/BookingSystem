package Scheduling;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

class SchedulingTest {

    //Timeslot tests
    @Test
    void constructor_isValid() {
        TimeSlot t = new TimeSlot(LocalDateTime.now(), LocalDateTime.now().plusYears(1), "asotase");

        assertNotEquals(null, t);
    }   

    @Test
    void getters_areValid(){
        LocalDateTime exp = LocalDateTime.now();
        TimeSlot t = new TimeSlot(exp, exp.plusYears(1), "asotase");
        
        assertEquals(exp,t.getStart());
        assertEquals(exp.plusYears(1),t.getEnd());
        assertEquals("asotase", t.getUserId());
    }

    @Test
    void conflict_isValid(){
        LocalDateTime exp = LocalDateTime.now();
        TimeSlot t = new TimeSlot(exp, exp.plusYears(3), "asotase");
        TimeSlot tc = new TimeSlot(exp.plusYears(1), exp.plusYears(4), "narcolepsy");
        
        assertEquals(true, t.conflictsWith(tc));
        assertEquals(true, tc.conflictsWith(t));
    }
    @Test
    void nonconflict_isValid(){
        LocalDateTime exp = LocalDateTime.now();
        TimeSlot t = new TimeSlot(exp, exp.plusYears(3), "asotase");
        TimeSlot tc = new TimeSlot(exp.minusYears(5), exp.minusYears(3), "narcolepsy");
        
        assertEquals(false, t.conflictsWith(tc));
        assertEquals(false, tc.conflictsWith(t));
    }
    //there isn't much to test with tostring, it just returns a string (lol)

    //scheduler is somewhat difficult to test, as its functions have no visible effects
    //its lists are private, so its difficult to tell if approve or reject work
    //similarly, fileIO tested manually

    //Scheduler tests
    @Test
    void canapprove_isValid(){
        Scheduler s = new Scheduler();

        LocalDateTime i = LocalDateTime.now();
        TimeSlot t = new TimeSlot(i, i.plusYears(1), "anatolia");
        s.addPending("sacremento", t);

        Boolean res = s.canApprove("sacremento", t);

        assertEquals(true, res);
    }
    @Test
    void canapprove_nonempty_isValid(){
        Scheduler s = new Scheduler();

        LocalDateTime i = LocalDateTime.now();

        TimeSlot t = new TimeSlot(i, i.plusYears(1), "anatolia");
        s.addPending("sacremento", t);
        s.canApprove("sacremento", t);
        s.approve("sacremento", t);

        TimeSlot tc = new TimeSlot(i.minusYears(1), i.plusYears(2), "orthoclase");
        s.addPending("sacremento", tc);

        Boolean res = s.canApprove("sacremento", tc);

        assertEquals(false, res);
    }
}
