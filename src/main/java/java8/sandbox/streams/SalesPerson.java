package java8.sandbox.streams;

public class SalesPerson {
	
	private Integer id;
	private String surname;
	private String givenName;
	
	public SalesPerson(Integer id, String surname, String givenName) {
		this.id = id;
		this.surname = surname;
		this.givenName = givenName;
	}

	public Integer getId() {
		return id;
	}

	public String getSurname() {
		return surname;
	}

	public String getGivenName() {
		return givenName;
	}


}
