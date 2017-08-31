package business;

public class Person implements Situation {
	public Person(String name, boolean isWoman) {
		this.name = name;
		this.isWoman = isWoman;
	}

	public Person partner;
	private String name;
	public boolean isWoman = false;

	@Override
	public boolean setSituation(Person partner) {
		try {
			if (partner == null) {
				this.partner.partner = null;
				this.partner = null;
			} else {
				this.partner = partner;
				this.partner.partner = this;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void showSituation() {
		String word = "casado";
		if (isWoman)
			word = "casada";
		System.out.printf("%s é %s com %s\n", name, word, partner.name);
	}
}
