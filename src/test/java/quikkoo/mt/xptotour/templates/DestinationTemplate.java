package quikkoo.mt.xptotour.templates;

import quikkoo.mt.xptotour.model.Destination;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class DestinationTemplate implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Destination.class)
			.addTemplate("valid-witout-trips", new Rule() { {
				add("name", random(destinations()));
				add("rating", random(Float.class, 0f, 5f));
			} });
	}

	private Object[] destinations() {
		return new String[] {
				"Afogados da Ingazeira",
				"Baixa Grande do Ribeiro",
				"Biribal da Ximboca",
				"Braço do Trombudo",
				"Brejo das Freiras",
				"Buranhém Velho",
				"Cacha Pregos",
				"Corrego do Pirapora",
				"Derribadinha",
				"Entroncamento de Jampruca",
				"Nhecolândia",
				"Passo do Sabão",
				"Quilombo Catorze",
				"Ribeirão das Catilanga",
				"Santana do Tabuleiro",
				"São Catatau do Mato Dentro",
				"São José do Batatal",
				"Tanque D'Arca",
				"Trombudo Central",
				"Varre-Sai",
			};
	}
}
