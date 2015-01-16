package quikkoo.mt.xptotour.templates;

import java.text.SimpleDateFormat;

import quikkoo.mt.xptotour.model.Trip;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class TripTemplate implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Trip.class)
			.addTemplate("valid", new Rule() { {
				add("date",  randomDate("01/01/2010", "31/01/2014", new SimpleDateFormat("dd/MM/yyyy")));
				add("rating", random(Integer.class, 0, 5));
				//add("customer", one(Customer.class, "valid-witout-trips"));
				//add("destination", one(Destination.class, "valid-witout-trips"));
			} })
			.addTemplate("invalid-rating-less-then-0").inherits("valid", new Rule() { {
				add("rating", -1);
			} })
			.addTemplate("invalid-rating-greather-then-5").inherits("valid", new Rule() { {
				add("rating", 6);
			} });
	}
}
