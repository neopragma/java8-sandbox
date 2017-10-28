package java8.sandbox.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fixtures {
	
	private static final CatalogEntry entry1 = 
			new CatalogEntry(new SKU("AB-001-XY-00001"), "Apple", "Charlie", "First entry for an Apple phone");
	private static final CatalogEntry entry2 =
			new CatalogEntry(new SKU("AB-001-XY-00002"), "Apple", "Elephant", "Second entry for an Apple phone");
	private static final CatalogEntry entry3 =
			new CatalogEntry(new SKU("AB-002-XY-00001"), "Samsung", "Dog", "First entry for a Samsung phone");
	private static final CatalogEntry entry4 =
			new CatalogEntry(new SKU("AB-002-XY-00002"), "Samsung", "Alpha", "Second entry for a Samsung phone");
	private static final CatalogEntry entry5 =
			new CatalogEntry(new SKU("AB-002-XY-00003"), "Samsung", "Baker", "Third entry for a Samsung phone");
				
	private static CatalogEntry[] catalogData = new CatalogEntry[] { entry1, entry2, entry3, entry4, entry5 };
	
	/**
	 * @return a populated catalog for testing.
	 */
	static List<CatalogEntry> getCatalogData() {
		return new ArrayList<CatalogEntry>(Arrays.asList(catalogData));
	}

	/**
	 * @return the expected results for a search for manufacturer 'Samsung'
	 */
	static List<CatalogEntry> expectedResultForSortTests() {
		return new ArrayList<CatalogEntry>(Arrays.asList(new CatalogEntry[] { entry3, entry4, entry5 }));
	}
	
}
