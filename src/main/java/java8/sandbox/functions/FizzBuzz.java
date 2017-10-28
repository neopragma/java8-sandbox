package java8.sandbox.functions;

import java.util.function.Function;

public class FizzBuzz implements Function<Integer, String>
{
	@Override
	public String apply(Integer a) {
		return (a % 15 == 0) ? "FizzBuzz" : (a % 5 == 0) ? "Buzz" : (a % 3 == 0) ? "Fizz" : String.valueOf(a);
	}
}