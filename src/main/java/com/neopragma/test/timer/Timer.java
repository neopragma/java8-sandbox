package com.neopragma.test.timer;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * Track execution times of methods in nanoseconds.
 * 
 * Intended to work with unit test cases. Start a timer at the top of a test case,
 * stop the timer at the end of the test case. At the end of the test run, dump the
 * timing information to a PrintStream. The default PrintStream is System.out.
 * 
 * Example based on JUnit
 * 
 * @BeforeClass
 * public static void beforeAll() {
 *     timer = new Timer();
 * }
 *	
 * @AfterClass
 * public static void afterAll() {
 *     timer.dumpToStdout();
 * } 
 *
 * @Test
 * public void someTestMethod() {
 *     startTimer();
 *     boolean truthy = false;
 *     assertFalse("Stranger than fiction", truthy);
 *     stopTimer();
 * 
 * @author neopragma
 * @since 1.8
 *
 */
public class Timer {
	
	private static final String EMPTY_STRING = "";
	private static final String TIME_FORMAT_PATTERN = "###,###,###,##0";
	private static final String BLANK_LINE = "";
	private static final String DASHED_LINE = "------------------------------------------------------------";

	/** {0} class name */
	private static final String TIMINGS_HEADER = "Timings for {0}";

	/** {0} method name, {1} formatted elapsed time in nanoseconds */
	private static final String TIMING_DETAIL = "{0} {1} ns";
	
	private long startTime;
	private String timedMethodName = EMPTY_STRING;
	private static Map<String, String> timings;
	private DecimalFormat timeFormatter = new DecimalFormat(TIME_FORMAT_PATTERN);
	private PrintStream printStream;
	
	/**
	 * Output will be written to System.out
	 */
	public Timer() {
		this(System.out);
	}
	
	/**
	 * Output will be written to the specified PrintStream
	 * @param printStream
	 */
	public Timer(PrintStream printStream) {
		timings = new TreeMap<String, String>();
		this.printStream = printStream;
	}
	
	/**
	 * Saves current value of System.nanoTime()
	 */
	public void startTimer() {
		if (!timedMethodName.equals(EMPTY_STRING)) {
			throw new TimerException("startTimer() was called but stopTimer() has not been called.");
		}
		timedMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		startTime = System.nanoTime();
	}
	
	/**
	 * Stores elapsed time in nanoseconds since startTimer() was called
	 */
	public void stopTimer() {
		if (timedMethodName.equals(EMPTY_STRING)) {
			throw new TimerException("stopTimer() was called but startTimer() has not been called.");
		}
		timings.put(timedMethodName, String.valueOf(System.nanoTime() - startTime));
		resetTimer();
	}
	
	/**
	 * Write the stored timings to a PrintStream (default is System.out)
	 */
	public void dumpTimings() {
		timingsHeader();
		for(String methodName : timings.keySet()) {
			timingDetailFor(methodName);
		}
		blankLine();
	}
	
	private void resetTimer() {
		timedMethodName = EMPTY_STRING;
		startTime = 0L;		
	}

	private void timingsHeader() {
		print(DASHED_LINE);
		print(MessageFormat.format(
			TIMINGS_HEADER, Thread.currentThread().getStackTrace()[3].getClassName()));		
		blankLine();
	}
	
	private void blankLine() {
		print(BLANK_LINE);
	}
	
	private void timingDetailFor(String methodName) {
		print(MessageFormat.format(
		TIMING_DETAIL, 
			String.format("%-65s", methodName).replace(' ', '.'), 
			String.format("%15s", timeFormatter.format(Long.parseLong(timings.get(methodName))))));		
	}
	
	private void print(String value) {
		printStream.println(value);
	}

}
