public class Fibonacci {
	static int first = 0;
	static int second = 1;
	static int counter = 1;
	public static void main(String[] args) {
//		for (int i = 1; i < 10; i++)
//			System.out.printf("%d•", fibonacci(i));
		static_Fibonacci(9);
	}
	
	public static long fibonacci(long n) {
		if(n <= 0)
			return 0;
		if(n <= 2)
			return 1;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}
	
	public static void static_Fibonacci(long n) {
		if(counter++ > n)
			return;
		int temp = second;
		second += first;
		first = temp;
		System.out.printf("%d•", first);
		//
		static_Fibonacci(n);
	}
}
