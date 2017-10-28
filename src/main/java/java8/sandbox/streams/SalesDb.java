package java8.sandbox.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class SalesDb {
	
	private static List<SalesPerson> salesPeople = (Arrays.asList(
		new SalesPerson(1, "Venner", "Jane"),
		new SalesPerson(2, "Ales", "Edgar"),
		new SalesPerson(3, "George", "Sam")));
	
	private static List<SalesRecord> salesRecords = (Arrays.asList(
			new SalesRecord(1, 25000.00, 50000.00, 84000.00),
			new SalesRecord(2, 25000.00, 40000.00, 102000.00),
			new SalesRecord(3, 25000.00, 28000.00, 96000.00)
		));

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> results = new ArrayList<>();
		for(T s: list) {
			if (p.test(s)) {
				results.add(s);
			}
		}
		return results;
	}
	
	public static SalesPerson findSalesPersonById(Integer id) {
		Predicate<SalesPerson> findById = (SalesPerson s) -> s.getId() == id;
		List<SalesPerson> result = SalesDb.filter(salesPeople, findById);
		return result.get(0);
	}
	
	public static List<SalesPerson> findSalesPeopleBy(Predicate<SalesPerson> p) {
		return SalesDb.filter(salesPeople, p);		
	}
	
	public static List<SalesRecord> findSalesRecordsBy(Predicate<SalesRecord> p) {
		Predicate<SalesRecord> findByPredicate = (SalesRecord r) -> { return p.test(r); };
		return SalesDb.filter(salesRecords, findByPredicate);		
	}
	
	public static List<SalesRecord> allSalesRecords() {
		return salesRecords;
	}

}
