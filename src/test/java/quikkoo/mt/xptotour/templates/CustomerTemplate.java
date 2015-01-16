package quikkoo.mt.xptotour.templates;

import quikkoo.mt.xptotour.model.Customer;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CustomerTemplate implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Customer.class)
		.addTemplate("valid-witout-trips", new Rule() { {
			add("name", firstName());
			add("email", "{name}@email.org");
			add("cpf", random(cpfs()));
			add("phone", random(phones()));
		} });
	}

	private Object[] phones() {
		return new String[] {
				"11 1111-1111",
				"22 2222-2222",
				"33 3333-3333",
				"44 4444-4444",
				"55 5555-5555",
				"66 6666-6666",
				"77 7777-7777",
				"88 8888-8888",
				"99 9999-9999",
			};
	}

	private Object[] cpfs() {
		return new String[] {
				"111.111.111-11",
				"222.222.222-22",
				"333.333.333-33",
				"444.444.444-44",
				"555.555.555-55",
				"666.666.666-66",
				"777.777.777-77",
				"888.888.888-88",
				"999.999.999-99",
			};
	}
}
