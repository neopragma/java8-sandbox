package java8.sandbox.predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.neopragma.test.timer.Timer;

public class CatalogSearchTest {
	
	private static Catalog catalog;
	private String textToFind = "second";
	private String manufacturerToFind = "Samsung";
	private static Timer timer;
	
	@BeforeClass
	public static void beforeAll() {
		catalog = new Catalog(Fixtures.getCatalogData());
		timer = new Timer();
	}
	
	@AfterClass
	public static void afterAll() {
		timer.dumpTimings();
	}
	
	// Find catalog entries that match a given manufacturer.
	
	@Test
	public void itFindsEntriesByManufacturer_preJava8() {
		timer.startTimer();
		catalog.findByManufacturer_preJava8(manufacturerToFind).forEach(entry-> {
			assertManufacturerSelection(entry);
		});
        timer.stopTimer();
	}
	
	@Test
	public void itFindsEntriesByManufacturer_usingPredicate() {
		timer.startTimer();
		catalog.findByManufacturer_usingPredicate(manufacturerToFind).forEach(entry-> {
			assertManufacturerSelection(entry);
		});
		timer.stopTimer();
	}
	
	@Test
	public void itFindsEntriesByManufacturer_usingStreamsAndFilter() {
		timer.startTimer();
		catalog.findByManufacturer_usingStreamsAndFilter(manufacturerToFind).forEach(entry-> {
			assertManufacturerSelection(entry);
		});
        timer.stopTimer();
	}

	private void assertManufacturerSelection(CatalogEntry entry) {
		assertTrue("Expected all entries to be for " + manufacturerToFind, 
			entry.getManufacturer().equals(manufacturerToFind));
	}

	// Find catalog entries whose descriptions contain the specified text
	
	@Test
	public void itFindsEntriesByTextInDescription_preJava8() {
		timer.startTimer();
		catalog.findByText_preJava8(textToFind).forEach(entry-> {
			assertSearchByTextResult(entry);
		});
        timer.stopTimer();
	}
	
	@Test
	public void itFindsEntriesByTextInDescription_usingPredicate() {
		timer.startTimer();
		catalog.findByText_usingPredicate(textToFind).forEach(entry-> {
			assertSearchByTextResult(entry);
		});
        timer.stopTimer();
	}
	
	@Test
	public void itFindsEntriesByTextInDescription_usingStreamsAndFilter() {
		timer.startTimer();
		catalog.findByText_usingStreamsAndFilter(textToFind).forEach(entry-> {
			assertSearchByTextResult(entry);
		});
        timer.stopTimer();
	}

	private void assertSearchByTextResult(CatalogEntry entry) {
		assertTrue("Expected all entries to contain '" + textToFind + "'", 
			entry.getDescription().contains(textToFind));
	}

	// Sort the results of a catalog search
	
	@Test
	public void itSortsResultsByProductName_preJava8() {
		timer.startTimer();
        assertEquals(Fixtures.expectedResultForSortTests(), 
        	catalog.sortByName_preJava8(catalog.findByManufacturer_usingPredicate(manufacturerToFind)));
        timer.stopTimer();
	}
	
	@Test
	public void itSortsResultsByProductName_usingLambda() {
		timer.startTimer();
        assertEquals(Fixtures.expectedResultForSortTests(), 
        	catalog.sortByName_usingLambda(catalog.findByManufacturer_usingPredicate(manufacturerToFind)));
        timer.stopTimer();
	}

	@Test
	public void itSortsResultsByProductName_usingStreams() {
		timer.startTimer();
        assertEquals(Fixtures.expectedResultForSortTests(), 
        	catalog.sortByName_usingStreams(catalog.findByManufacturer_usingPredicate(manufacturerToFind)));
        timer.stopTimer();
	}

	@Test
	public void itSortsResultsByProductName_usingStreamsAndAnonymousInnerClass() {
		timer.startTimer();
        assertEquals(Fixtures.expectedResultForSortTests(), 
        	catalog.sortByName_usingStreamsAndAnonymousInnerClass(
        		catalog.findByManufacturer_usingPredicate(manufacturerToFind)));
        timer.stopTimer();
	}

}
