package com.neopragma.test.timer;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TimerTest {

	@Test
	public void itTimesAMethod() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(output);
		Timer timer = new Timer(ps);
		
		timer.startTimer();
		try {
			Thread.sleep(1L);
		} catch (InterruptedException e) {}
		timer.stopTimer();
		timer.dumpTimings();
		
		assertTrue(
				Pattern.compile(".*(itTimesAMethod)\\.{10}.+\\d{3} (ns)", Pattern.DOTALL)
					   .matcher(new String(output.toByteArray(), StandardCharsets.UTF_8))
					   .lookingAt());
	}
	
	@Test(expected=TimerException.class)
	public void itThrowsWhenStopTimerIsCalledOutOfSequence() {
		new Timer().stopTimer();
	}
	
	@Test(expected=TimerException.class)
	public void itThrowsWhenStartTimerIsCalledOutOfSequence() {
		Timer timer = new Timer();
		timer.startTimer();
		timer.startTimer();
	}

}
