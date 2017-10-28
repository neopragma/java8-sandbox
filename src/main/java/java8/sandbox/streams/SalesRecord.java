package java8.sandbox.streams;

public class SalesRecord {
	
	private Integer id;
	private Double salesTarget;
	private Double actualSales;
	private Double baseSalary;
	
	public SalesRecord(Integer id,
			Double salesTarget, 
			Double actualSales,
			Double baseSalary) {
		this.id = id;
		this.salesTarget = salesTarget;
		this.actualSales = actualSales;
		this.baseSalary = baseSalary;
	}

	public Integer getId() {
		return id;
	}

	public Double getSalesTarget() {
		return salesTarget;
	}

	public Double getActualSales() {
		return actualSales;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

}
