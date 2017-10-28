package java8.sandbox.predicate;

import static org.junit.Assert.*;

import org.junit.Test;

public class SkuTest {

	@Test
	public void itCreatesValidSku() {
		SKU sku = new SKU("AB-001-XY-12345");
	}
	
	@Test(expected=InvalidSkuException.class)
	public void ctorThrowsWhenSkuIsInvalid() {
        SKU sku = new SKU("nonconforming value");
	}
}
