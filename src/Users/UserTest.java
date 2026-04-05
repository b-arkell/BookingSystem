package Users;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ResourceStates.IResource;
import ResourceStates.Resource;
import Scheduling.TimeSlot;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

class UserTest {

    //User tests
    @Test
    void testGetUserID() {
        User user1 = new User();
        String expected = "NA";
        String userIdString = user1.getUserID();

        assertEquals(expected, userIdString);
    }

	@Test
	void testSetUserID() {
		User user1 = new User();
        String expected = "user1";
        user1.setUserID("user1");
        String userIdString = user1.getUserID();

        assertEquals(expected, userIdString);
	}

    @Test
    void getName_isValid(){
        User user1= new User();
        String expected = "NA";
        String actual = user1.getName();

        assertEquals(expected, actual);
    }
    @Test
    void setName_isValid(){
        User user1 = new User();
        user1.setName("bonjour");
        String expected = "bonjour";

        assertEquals(expected, user1.getName());
    }

	@Test
	void parametrizedConstr_isValid(){
        User user1 = new User("204512","tmylj");
        String expid="204512";
        String expname="tmylj";

        assertEquals(expid, user1.getUserID());
        assertEquals(expname, user1.getName());
    }

    @Test
    void setDraft_isValid(){
        User i = new User();
        TimeSlot time = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), i.getName());
        i.setDraft("nomenclature", time);

        LinkedHashMap<String,TimeSlot> expected = new LinkedHashMap<>();
        expected.put("nomenclature",time);
        assertEquals(expected, i.getDrafts());          
    }
    //the resource version is entrenched in too many dependencies so i'm not testing it here
    // iresource[resource](bookingstrategy[strategy](scheduler))
    // "manual test": all it does is is iresource.getname, so I'll just test that in its own unit tests

    @Test
    void getSpecDraft_isValid() throws Exception{
        User i = new User();
        TimeSlot time = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), i.getName());
        i.setDraft("nomenclature", time);

        assertEquals(time, i.getSpecDraft("nomenclature")); 
    }

    @Test 
    void removeDraft_isValid(){
        User i = new User();

        TimeSlot time = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), i.getName());
        i.setDraft("nomenclature", time);

        TimeSlot timex = new TimeSlot(LocalDateTime.of(2042,4, 1, 10, 0), LocalDateTime.of(2097,  4, 1, 12, 0), i.getName());
        i.setDraft("liturgy", timex);

        i.removeDraft("nomenclature");

        LinkedHashMap<String,TimeSlot> expected = new LinkedHashMap<>();
        expected.put("liturgy",timex);

        assertEquals(expected, i.getDrafts());
    }

        @Test 
    void clearDraft_isValid(){
        User i = new User();

        TimeSlot time = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), i.getName());
        i.setDraft("nomenclature", time);

        TimeSlot timex = new TimeSlot(LocalDateTime.of(2042,4, 1, 10, 0), LocalDateTime.of(2097,  4, 1, 12, 0), i.getName());
        i.setDraft("liturgy", timex);

        i.clearDrafts();

        assertEquals(true, i.getDrafts().isEmpty());
    }

    //fileio tested manually
}
