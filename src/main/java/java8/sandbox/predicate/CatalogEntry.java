package java8.sandbox.predicate;

public class CatalogEntry {
	
	private SKU sku;
	private String manufacturer;
	private String name;
	private String description;
	
	public CatalogEntry(SKU sku, String manufacturer, String name, String description) {
		this.sku = sku;
		this.manufacturer = manufacturer;
		this.name = name;
		this.description = description;
	}

	public SKU getSku() {
		return sku;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	@Override
	public boolean equals(CatalogEntry this, Object that) {
		if (this == that) {
			return true;
		}
		if (that instanceof CatalogEntry == false) {
			return false;
		}
		if (this.getManufacturer().equals(((CatalogEntry) that).getManufacturer()) &&
			this.getManufacturer().equals(((CatalogEntry) that).getManufacturer()) &&
			this.getManufacturer().equals(((CatalogEntry) that).getManufacturer())) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 1;
	}

}
