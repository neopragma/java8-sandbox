package java8.sandbox.streams;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.neopragma.test.timer.Timer;

public class SalesTest {
	
	private static Timer timer;
	
	@BeforeClass
	public static void beforeAll() {
		timer = new Timer();
	}
	
	@AfterClass
	public static void afterAll() {
		timer.dumpTimings();
	}

	@Test
	public void itReturnsTopPerformersUsingStream() {
		timer.startTimer();
		List<TopPerformer> topPerformers = SalesDb.allSalesRecords().stream()
//	long form:			
//			.filter((SalesRecord r) -> { return r.getActualSales() >= (r.getSalesTarget() * 1.2); })
			.filter(r -> r.getActualSales() >= (r.getSalesTarget() * 1.2))
			.map(r -> new TopPerformer(r))
			.sorted()
			.collect(Collectors.toList());
		assertEquals(2, topPerformers.size());	
		
        timer.stopTimer();		
		listTopPerformers(topPerformers);		
	}
	
	@Test
	public void itReturnsTopPerformersWithoutStream() {
		timer.startTimer();
		List<TopPerformer> topPerformers = new ArrayList<TopPerformer>();
		for(SalesRecord salesRecord : SalesDb.allSalesRecords()) {
			if (salesRecord.getActualSales() >= (salesRecord.getSalesTarget() * 1.2)) {
				topPerformers.add(new TopPerformer(salesRecord));
			}
		}
		Collections.sort(topPerformers);
        assertEquals(2, topPerformers.size());

        timer.stopTimer();		
		listTopPerformers(topPerformers);
	}
	
	private void listTopPerformers(List<TopPerformer> topPerformers) {
		topPerformers.forEach(tp -> {
			SalesPerson p = SalesDb.findSalesPersonById(tp.getId());			
			System.out.println(p.getGivenName() + " " + p.getSurname() + " " + tp.getAdjustedSalary()); }
		);		
	}

}
