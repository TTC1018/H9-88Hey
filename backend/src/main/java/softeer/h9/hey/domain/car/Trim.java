package softeer.h9.hey.domain.car;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trim {

	private int id;
	private String name;
	private int price;
	private List<TrimFeature> trimFeatures;

	public Trim(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.trimFeatures = new ArrayList<>();
	}

	public void addTrimFeature(TrimFeature trimFeature) {
		trimFeatures.add(trimFeature);
	}
}
