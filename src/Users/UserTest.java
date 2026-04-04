package Users;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UserTest {

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

//	@Test
//	void testGetName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetDrafts() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUser() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetSpecDraft() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetDraftStringTimeSlot() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetDraftIResourceTimeSlot() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRemoveDraft() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testClearDrafts() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSaveToFile() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testLoadFromFile() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testClearUserData() {
//		fail("Not yet implemented");
//	}

}
