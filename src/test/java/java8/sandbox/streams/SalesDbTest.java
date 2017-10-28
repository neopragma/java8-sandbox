package java8.sandbox.streams;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

public class SalesDbTest {

	@Test
	public void itFindsSalesPersonById() {
		assertEquals((Integer)2, SalesDb.findSalesPersonById(2).getId());
	}
	
	@Test
	public void itFindsSalesPeopleBySurname() {
		assertEquals("Venner", SalesDb.findSalesPeopleBy(p -> p.getSurname().equals("Venner")).get(0).getSurname()); 
	}
	
	@Test
	public void itFindsSalesPerformanceAboveBonusLevel() {
		Predicate<SalesRecord> p = (SalesRecord r) -> { return r.getActualSales() >= (r.getSalesTarget() * 1.2); };
		List<SalesRecord> result = SalesDb.findSalesRecordsBy(p);
		assertEquals(2, result.size());
	}
	
	@Test
	public void itReturnsAllSalesRecords() {
		assertEquals(3, SalesDb.allSalesRecords().size());
	}

}
