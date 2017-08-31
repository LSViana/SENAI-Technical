package execution;
import business.*;
public class start {
	public static void main(String[] args) {
		Person roberto = new Person("Roberto", false);
		Person luana = new Person("Luana", true);
		roberto.setSituation(luana);
		//
		roberto.showSituation();
	}
}
