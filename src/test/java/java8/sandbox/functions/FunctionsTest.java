package java8.sandbox.functions;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.neopragma.test.timer.Timer;

public class FunctionsTest {
	
	private static Timer timer = new Timer();	
	
	@BeforeClass
	public static void beforeAll() {
		timer = new Timer();
	}
	
	@AfterClass
	public static void afterAll() {
		timer.dumpTimings();
	}
	
	@Test
	public void increment_inline_Integer() {
		timer.startTimer();
		Function<Integer,Integer> incr = (x) -> { return x + 1; };
		assertEquals((Integer)5, incr.apply(4));
		timer.stopTimer();
	}
	
	@Test
	public void increment_class_Integer() {
		timer.startTimer();
		Incr incr = new Incr();
		assertEquals((Integer)5, incr.apply(4));
		timer.stopTimer();
	}
	
	@Test
	public void increment_utility_Integer() {
		timer.startTimer();
		Function<Integer,Integer> incr = Utils::incr;
		assertEquals((Integer)5, incr.apply(4));
		timer.stopTimer();
	}
	
	@Test
	public void addition_inline_Integers() {
		timer.startTimer();
		BiFunction<Integer, Integer, Integer> plus = (x, y) -> { return x + y; };
		assertEquals((Integer)8,plus.apply(3, 5));
		assertEquals((Integer)12, plus.apply(plus.apply(3, 5), 4));
		timer.stopTimer();
	}

	@Test
	public void differenceOfSquares_inline() {
		timer.startTimer();
		BiFunction<Double, Double, Double> diffSq = (a, b) -> ((a + b)*(a - b));
		assertEquals((Double)(-16.0), diffSq.apply(3.0,  5.0));
		timer.stopTimer();
	}
	
	@Test
	public void differenceOfSquares_class() {
		timer.startTimer();
		DiffSq f = new DiffSq();
		assertEquals((Double)(-16.0), f.apply(3.0, 5.0));
		timer.stopTimer();
	}
	
	@Test
	public void differenceOfSquares_utility() {
		timer.startTimer();
		BiFunction<Double, Double, Double> diffSq = Utils::diffSq;
		assertEquals((Double)(-16.0), diffSq.apply(3.0, 5.0));
		timer.stopTimer();
	}

	@Test
	public void exponentiation() {
		timer.startTimer();
		Exp f = new Exp();
		assertEquals((Double)81.0, f.apply(3.0, 4));
		timer.stopTimer();
	}
	
	@Test 
	public void fizzBuzz() {
		timer.startTimer();
		FizzBuzz f = new FizzBuzz();
		assertEquals("Buzz", f.apply(10));
		timer.stopTimer();
	}
	
	@Test
	public void fizzBuzzRange() {
		timer.startTimer();
		FizzBuzz f = new FizzBuzz();
		Object[] result = IntStream.rangeClosed(1, 10).boxed().map(a -> f.apply(a)).toArray();
		assertArrayEquals(new String[] { "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz" }, result);
		timer.stopTimer();
	}

}
