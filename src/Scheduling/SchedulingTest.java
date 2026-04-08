package Scheduling;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //Scheduler tests
    //fileIO tested manually
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

    @Test
    void getPendingaddPending_AreValid(){
        Scheduler s = new Scheduler();
        LocalDateTime i = LocalDateTime.now();

        TimeSlot t = new TimeSlot(i, i.plusDays(3), "altoris");
        
        s.addPending("thuban", t);

        ArrayList<TimeSlot> k = new ArrayList<TimeSlot>();
        k.add(t);
        Map<String, List<TimeSlot>> exp = new HashMap<>();
        exp.put("thuban", k);

        assertEquals(exp, s.getPending());

    }

    @Test
    void getPending_conflictnonconflict_isValid(){
        Scheduler s = new Scheduler();
        LocalDateTime i = LocalDateTime.now();

        TimeSlot t = new TimeSlot(i, i.plusDays(3), "altoris");
        TimeSlot tc = new TimeSlot(i.minusDays(2), i.plusDays(2), "senafim");
        TimeSlot tn = new TimeSlot(i.plusDays(5), i.plusDays(7), "regarding");
        TimeSlot tnn = new TimeSlot(i, i.plusHours(5), "ceraton");
        
        s.addPending("thuban", t);
        s.addPending("thuban", tc);
        s.addPending("thuban", tn);
        s.addPending("ritan", tnn);

        ArrayList<TimeSlot> k = new ArrayList<TimeSlot>();
        k.add(t);
        k.add(tc);
        k.add(tn);
        ArrayList<TimeSlot> k2 = new ArrayList<TimeSlot>();
        k2.add(tnn);
        Map<String, List<TimeSlot>> exp = new HashMap<>();
        exp.put("thuban", k);
        exp.put("ritan",k2);

        assertEquals(exp, s.getPending());
    }

    @Test
    void getApproved_isValid(){
        Scheduler s = new Scheduler();
        LocalDateTime i = LocalDateTime.now();

        TimeSlot t = new TimeSlot(i, i.plusDays(3), "altoris");
        
        s.approve("thuban", t);

        ArrayList<TimeSlot> k = new ArrayList<TimeSlot>();
        k.add(t);
        Map<String, List<TimeSlot>> exp = new HashMap<>();
        exp.put("thuban", k);

        assertEquals(exp, s.getApproved());

    }

    @Test 
    void reject_isValid(){
        Scheduler s = new Scheduler();
        LocalDateTime i = LocalDateTime.now();

        TimeSlot t = new TimeSlot(i, i.plusDays(3), "altoris");
        TimeSlot tc = new TimeSlot(i.minusDays(2), i.plusDays(2), "senafim");
        TimeSlot tn = new TimeSlot(i.plusDays(5), i.plusDays(7), "regarding");
        TimeSlot tnn = new TimeSlot(i, i.plusHours(5), "ceraton");
        
        s.addPending("thuban", t);
        s.addPending("thuban", tc);
        s.addPending("thuban", tn);
        s.addPending("ritan", tnn);

        s.reject("thuban", tc);

        ArrayList<TimeSlot> k = new ArrayList<TimeSlot>();
        k.add(t);
        k.add(tn);
        ArrayList<TimeSlot> k2 = new ArrayList<TimeSlot>();
        k2.add(tnn);
        Map<String, List<TimeSlot>> exp = new HashMap<>();
        exp.put("thuban", k);
        exp.put("ritan",k2);

        assertEquals(exp, s.getPending());
    }
}
