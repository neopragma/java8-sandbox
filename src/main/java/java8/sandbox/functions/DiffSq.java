package java8.sandbox.functions;

import java.util.function.BiFunction;

public class DiffSq implements BiFunction<Double, Double, Double> {
	@Override
	public Double apply(Double a, Double b) {
       	return ((a + b)*(a - b));
	}	
}