package java8.sandbox.predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class CatalogTest {

	@Test
	public void newCatalogWithoutDataIsCreatedEmpty() {
		Catalog catalog = new Catalog();
		assertTrue("Expected catalog to be empty", catalog.isEmpty());
	}
	
	@Test
	public void newCatalogWithDataIsCreatedWithContents() {
		CatalogEntry[] entries = new CatalogEntry[] {
				new CatalogEntry(new SKU("T1-123-XX-12345"), "Maker", "Name 1", "Desc 1"),
				new CatalogEntry(new SKU("T2-123-XX-12345"), "Maker", "Name 2", "Desc 2")
		};
		Catalog catalog = new Catalog(new ArrayList<CatalogEntry>(Arrays.asList(entries)));
		assertEquals(2, catalog.size());
	}

}
