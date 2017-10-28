package java8.sandbox.predicate;

import java.util.regex.Pattern;

/**
 * Stock Keeping Unit (SKU)
 * 
 * @author dave
 */
public class SKU {
	
	private static final String SKU_PATTERN = "\\w{2}-\\d{3}-\\w{2}-\\d{5}";
	private String sku_value;
	
	public SKU(String sku_value) {
		if (sku_value.matches(SKU_PATTERN)) {
			this.sku_value = sku_value;
		} else {
			throw new InvalidSkuException();
		}
	}

}
