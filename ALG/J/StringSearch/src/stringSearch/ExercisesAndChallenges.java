package stringSearch;
public class ExercisesAndChallenges {
	public static void main(String[] args) {
		// Exercício 1
		System.out.println(count("Rafael"));
		// Exercício 2
		upperLowerCase("Rafael");
		// Exercício 3
		initials(" Rafael Thayto de Tani");
		// Exercício 4
		System.out.println(contains("Rafael Thayto", "Thayto"));
		// Exercício 5
		System.out.println(subString("Rafael Thayto", 7, 8));
		// Desafio 6
		System.out.println(replace("Rafael Thayto", "Thayto", "Tani"));
	}
	// Exercício 1
	public static int count(String source) {
		return source.toCharArray().length;
	}
	// Exercício 2
	public static void upperLowerCase(String source) {
		String upper = "";
		String lower = "";
		for(char x : source.toCharArray()) {
			upper += Character.toUpperCase(x);
			lower += Character.toLowerCase(x);
		}
		System.out.println(upper);
		System.out.println(lower);
	}
	// Exercício 3
	public static void initials(String source) {
		String initials = Character.isWhitespace(source.toCharArray()[0]) ? "" : new String(new char[] { source.toCharArray()[0] });
		char[] letters = source.toCharArray();
		for(int i = 1; i < letters.length - 1; i++) {
			if(Character.isWhitespace(letters[i - 1]))
				initials += letters[i];
		}
		System.out.println(initials);
	}
	// Exercício 4
	public static int indexOf(String source, String criteria) {
		char[] _source = source.toCharArray();
		char[] _criteria = criteria.toCharArray();
		//
		for (int i = 0; i < _source.length; i++) {
			for (int j = 0; j < _criteria.length; j++) {
				if(_source[i] != _criteria[j])
					break;
				while(_source[i] == _criteria[j]) {
					i++;
					j++;
					if(j == _criteria.length)
						return i - j;
				}
			}
		}
		return -1;
	}
	public static boolean contains(String source, String criteria) {
		return indexOf(source, criteria) != -1 ? true : false;
	}
	// Exercício 5
	/**
	 * Parte 1
	 */
	public static String subString(String source, int i) {
		return subString(source, i, count(source));
	}
	/**
	 * Parte 2
	 */
	public static String subString(String source, int i, int j) {
		char[] letters = source.toCharArray();
		String result = "";
		//
		for (int k = i; k < j; k++) {
			result += letters[k];
		}
		return result;
	}
	// Desafio 6
	public static String replace(String source, String criteria, String content) {
		while(contains(source, criteria)) {
			char[] _source = source.toCharArray();
			char[] _content = content.toCharArray();
			String neoSource = "";
			for(int i = 0; i < indexOf(source, criteria); i++) {
				neoSource += _source[i];
			}
			for (int j = 0; j < count(content); j++) {
				neoSource += _content[j];
			}
			source = neoSource;
		}
		return source;
	}
}
