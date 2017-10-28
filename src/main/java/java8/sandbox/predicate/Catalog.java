package java8.sandbox.predicate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Catalog {

	private List<CatalogEntry> catalogData;
	
	public Catalog() {
		catalogData = new ArrayList<CatalogEntry>();
	}
	
	public Catalog(List<CatalogEntry> entries) {
		this.catalogData = entries; 
	}

	public boolean isEmpty() {
		return size() == 0 ? true : false;
	}

	public int size() {
		return null == catalogData ? 0 : catalogData.size();
	}
	
	// Various implementations of searching by manufacturer
	
	public List<CatalogEntry> findByManufacturer_preJava8(String value) {
		List<CatalogEntry> result = new ArrayList<CatalogEntry>();
		for(CatalogEntry entry : catalogData) {
			if (entry.getManufacturer().equals(value)) {
				result.add(entry);
			}
		}
		return result;
	}	
	
	public List<CatalogEntry> findByManufacturer_usingPredicate(String value) {
		Predicate<String> manufacturer = v -> v.equals(value);
		List<CatalogEntry> result = new ArrayList<CatalogEntry>();
		catalogData.forEach(entry -> {
			if (manufacturer.test(entry.getManufacturer())) {
				result.add(entry);
			}
		});
		return result;
	}
	
	public List<CatalogEntry> findByManufacturer_usingStreamsAndFilter(String value) {
		return (List<CatalogEntry>) catalogData.stream().filter(entry -> value.equals(entry.getManufacturer())).collect(Collectors.toList());
	}

	// Various implementations of finding entries containing text in the description
	
	public List<CatalogEntry> findByText_preJava8(String text) {
		List<CatalogEntry> result = new ArrayList<CatalogEntry>();
		for(CatalogEntry entry : catalogData) {
			if (entry.getDescription().contains(text)) {
				result.add(entry);
			}
		}
		return result;
	}
	
	public List<CatalogEntry> findByText_usingPredicate(String text) {
		Predicate<String> value = v -> v.contains(text);
		List<CatalogEntry> result = new ArrayList<CatalogEntry>();
		catalogData.forEach(entry -> {
			if (value.test(entry.getDescription())) {
				result.add(entry);
			}
		});
		return result;
	}
	
	public List<CatalogEntry> findByText_usingStreamsAndFilter(String value) {
		return (List<CatalogEntry>) catalogData.stream()
			.filter(entry -> entry.getDescription().contains(value)).collect(Collectors.toList());
	}

	// Various implementations of sorting the result set
	
	public List<CatalogEntry> sortByName_preJava8(List<CatalogEntry> entries) {
		Collections.sort(entries, new Comparator<CatalogEntry>() {
			@Override
			public int compare(CatalogEntry o1, CatalogEntry o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return entries;
	}
	
	public List<CatalogEntry> sortByName_usingLambda(List<CatalogEntry> entries) {
//		entries.sort((CatalogEntry o1, CatalogEntry o2)->o1.getName().compareTo(o2.getName()));
		entries.sort((thisEntry, thatEntry)->thisEntry.getName().compareTo(thatEntry.getName()));
		return entries;
	}

	public List<CatalogEntry> sortByName_usingStreams(List<CatalogEntry> entries) {
		Comparator<CatalogEntry> orderByName = new Comparator<CatalogEntry>() {
			@Override
			public int compare(CatalogEntry o1, CatalogEntry o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
		return entries.stream().sorted(orderByName).collect(Collectors.toList());
	}

	public List<CatalogEntry> sortByName_usingStreamsAndAnonymousInnerClass(List<CatalogEntry> entries) {
		return entries
			.stream()
			.sorted(new Comparator<CatalogEntry>() {
				@Override
				public int compare(CatalogEntry o1, CatalogEntry o2) {
					return o1.getName().compareTo(o2.getName());
				}
			})
			.collect(Collectors.toList());
	}

}
