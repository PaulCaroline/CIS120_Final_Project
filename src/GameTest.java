import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;

import org.junit.*;

import paul.smash.display.Helper;
import paul.smash.display.Stage;
import paul.smash.framework.StageType;
import paul.smash.objects.Platform;

public class GameTest {
	@Test
	public void platformAddedTest() {
		Helper helper = new Helper();
		Stage stage = new Stage(StageType.FINAL_DESTINATION);
		for (Platform p : stage.getPlatforms()) {
			helper.addObject(p);
		}
		assertEquals(1, helper.objects.size());
	}
	
	@Test
	public void boundIntersectionTest() {
		Platform p = new Platform(0, 0, 100, 100);
		Rectangle r = new Rectangle(20, 0, 100, 100);		
		assertTrue(p.getBounds().intersects(r));
	}
}
