package java8.sandbox.functions;

import java.util.function.BiFunction;
import java.util.function.IntToDoubleFunction;

public class Exp implements BiFunction<Double, Integer, Double>, 
							IntToDoubleFunction
{
	@Override
	public Double apply(Double a, Integer p) {
		return Math.pow(a, applyAsDouble(p));
	}

	@Override
	public double applyAsDouble(int x) {
		return x;
	}
}