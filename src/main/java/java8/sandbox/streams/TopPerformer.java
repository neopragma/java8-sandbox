package java8.sandbox.streams;

public class TopPerformer implements Comparable<Object>{
	
	private Integer id;
	private Double adjustedSalary;
	private static final Double STANDARD_BONUS_RATE = 0.15;
	
	public TopPerformer(Integer id, Double adjustedSalary) {
		this.id = id;
		this.adjustedSalary = adjustedSalary;
	}
	
	public TopPerformer(SalesRecord r) {
		this.id = r.getId();
		this.adjustedSalary = (r.getBaseSalary() + ((r.getActualSales()-r.getSalesTarget())*STANDARD_BONUS_RATE));
	}

	public Integer getId() {
		return id;
	}
	
	public Double getAdjustedSalary() {
		return adjustedSalary;
	}

	@Override
	public int compareTo(Object o) {
		TopPerformer that = (TopPerformer) o;
		if (this.getAdjustedSalary() > that.getAdjustedSalary()) {
			return -1;
		}
		if (this.getAdjustedSalary() == that.getAdjustedSalary()) {
			return 0;
		}
		return 1;
	}

}
