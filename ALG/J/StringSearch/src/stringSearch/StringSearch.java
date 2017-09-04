package stringSearch;

/**
 * This class perform operations over String objects, like search,
 * modifications, verifying existing texts
 * 
 * @author Lucas Viana Soares de Souza
 */
public class StringSearch {
	public static void main(String[] args) {
		String source = "Lucas Viana Soares de Souza";
		// charAt
		System.out.println(charAt(source, 1));
		// equals
		System.out.println(equals(source, "Lucas Viana Soares de Souza"));
		System.out.println(equals(source, "Lucas Viana Soares de SouzA"));
		// equalsIgnoreCase
		System.out.println(equalsIgnoreCase(source, "Lucas Viana Soares de Souza"));
		System.out.println(equalsIgnoreCase(source, "Lucas Viana Soares de SouzA"));
		// indexOf
		System.out.println(indexOf(source, "A"));
		// contains
		// replace
		// substring (Both of the overloads)
		// toUpperCase
		// toLowerCase
		// trim
		// removeInternalSpaces
		System.out.println(removeInternalSpaces("Lucas      A"));
	}

	public static char charAt(String source, int position) {
		return source.toCharArray()[position];
	}

	public static boolean equals(String source, String neoSource) {
		return _equals(source, neoSource, false);
	}

	public static int length(String source) {
		return source.toCharArray().length;
	}

	public static boolean equalsIgnoreCase(String source, String neoSource) {
		return _equals(source, neoSource, true);
	}

	public static boolean _equals(String source, String neoSource, boolean ignoreCase) {
		if (source.length() != neoSource.length())
			return false;
		for (int i = 0; i < source.length(); i++) {
			if ((charAt(source, i) != charAt(neoSource, i)) && (ignoreCase
					&& (Character.toLowerCase(charAt(source, i)) != Character.toLowerCase(charAt(neoSource, i)))))
				return false;
		}
		return true;
	}

	public static int indexOf(String source, String criteria) {
		for (int i = 0; i < length(source); i++) {
			for (int j = 0; j < length(criteria); j++) {
				while(charAt(source, i) == charAt(criteria, j)) {
					i++;
					j++;
					if(j == length(criteria))
						return i - j;
				}
			}
		}
		return -1;
	}

	public static String removeInternalSpaces(String source) {
		// First Way
		// return source.replaceAll("[ ]+", " ");
		// Second Way
		while (source.contains("  "))
			source.replace("  ", " ");
		return source;
	}
}
