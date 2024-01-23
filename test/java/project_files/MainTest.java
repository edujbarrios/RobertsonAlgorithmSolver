package project_files;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import static org.testng.Assert.assertNotNull;

class RobertsonMultiplierTest {

	@Test
	void testMainPanelExists() {
		RobertsonMultiplier multiplier = new RobertsonMultiplier();
		JPanel panel = multiplier.getMainPanel();
		assertNotNull(panel, "Main panel should not be null");
	}
}
