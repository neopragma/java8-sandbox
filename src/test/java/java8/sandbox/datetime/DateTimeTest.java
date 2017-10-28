package java8.sandbox.datetime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class DateTimeTest {
	
	@Test
	public void itReturnsTheCurrentDateAndTime() {
		LocalDateTime now = LocalDateTime.now();
		assertTrue("Actual: " + now.toString(),
			now.toString().matches("d{4}-d{2}-d{2}Td{2}:d{2}:d{2}.d{3}"));
	}

}
