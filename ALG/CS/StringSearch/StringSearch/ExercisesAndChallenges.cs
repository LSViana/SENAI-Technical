using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StringSearch
{
    class ExercisesAndChallenges
    {
        public static void Execute(String[] args)
        {
            // Exercício 1
            Console.WriteLine(count("Rafael"));
            // Exercício 2
            upperLowerCase("Rafael");
            // Exercício 3
            initials(" Rafael Thayto de Tani");
            // Exercício 4
            Console.WriteLine(contains("Rafael Thayto", "Thayto"));
            // Exercício 5
            Console.WriteLine(subString("Rafael Thayto", 7, 8));
            // Desafio 6
            Console.WriteLine(replace("Rafael Thayto", "Thayto", "Tani"));
        }
        // Exercício 1
        public static int count(String source)
        {
            return source.ToArray().Length;
        }
        // Exercício 2
        public static void upperLowerCase(String source)
        {
            String upper = "";
            String lower = "";
            foreach (char x in source.ToArray())
            {
                upper += char.ToUpper(x);
                lower += char.ToLower(x);
            }
            Console.WriteLine(upper);
            Console.WriteLine(lower);
        }
        // Exercício 3
        public static void initials(String source)
        {
            String initials = char.IsWhiteSpace(source.ToArray()[0]) ? "" : new String(new char[] { source[0] });
            char[] letters = source.ToArray();
            for (int i = 1; i < letters.Length - 1; i++)
            {
                if (char.IsWhiteSpace(letters[i - 1]))
                    initials += letters[i];
            }
            Console.WriteLine(initials);
        }
        // Exercício 4
        public static int indexOf(String source, String criteria)
        {
            char[] _source = source.ToArray();
            char[] _criteria = criteria.ToArray();
            //
            for (int i = 0; i < _source.Length; i++)
            {
                for (int j = 0; j < _criteria.Length; j++)
                {
                    if (_source[i] != _criteria[j])
                        break;
                    while (_source[i] == _criteria[j])
                    {
                        i++;
                        j++;
                        if (j == _criteria.Length)
                            return i - j;
                    }
                }
            }
            return -1;
        }
        public static bool contains(String source, String criteria)
        {
            return indexOf(source, criteria) != -1 ? true : false;
        }
        // Exercício 5
        /**
         * Parte 1
         */
        public static String subString(String source, int i)
        {
            return subString(source, i, count(source));
        }
        /**
         * Parte 2
         */
        public static String subString(String source, int i, int j)
        {
            char[] letters = source.ToArray();
            String result = "";
            //
            for (int k = i; k < j; k++)
            {
                result += letters[k];
            }
            return result;
        }
        // Desafio 6
        public static String replace(String source, String criteria, String content)
        {
            while (contains(source, criteria))
            {
                char[] _source = source.ToArray();
                char[] _content = content.ToArray();
                String neoSource = "";
                for (int i = 0; i < indexOf(source, criteria); i++)
                {
                    neoSource += _source[i];
                }
                for (int j = 0; j < count(content); j++)
                {
                    neoSource += _content[j];
                }
                source = neoSource;
            }
            return source;
        }
    }
}
