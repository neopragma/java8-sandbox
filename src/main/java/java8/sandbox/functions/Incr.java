package java8.sandbox.functions;

import java.util.function.Function;

public class Incr implements Function<Integer, Integer>
{
	@Override
	public Integer apply(Integer a) {
       	return a + 1;
	}	
}
