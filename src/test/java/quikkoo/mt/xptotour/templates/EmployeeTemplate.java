package quikkoo.mt.xptotour.templates;

import quikkoo.mt.xptotour.model.Employee;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class EmployeeTemplate implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Employee.class)
		.addTemplate("valid", new Rule() { {
			add("name", firstName());
			add("email", "{name}@email.org");
			add("password", "password");
		} });
	}
}