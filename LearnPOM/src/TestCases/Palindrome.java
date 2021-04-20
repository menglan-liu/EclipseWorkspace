package TestCases;

public class Palindrome {
	
	public static boolean isPalindrome_one(String original) {
		
		String reversed = new StringBuilder(original).reverse().toString();
		System.out.println("");
		System.out.println(reversed);
		return original.equals(reversed);
		
	}
	
	public static boolean isPalindrome_two(String original){
		String reversed = "";
		int len = original.length();
		for(int i=len-1;i>=0;i--) {
			reversed = reversed + original.charAt(i);	
			
		}
		System.out.print(reversed+"     ");
		return original.equals(reversed);

		
	}
	
	public static void main(String[] args) {
		    System.out.println(isPalindrome_one("dad"));
	        System.out.println(isPalindrome_one("kayak"));
	        System.out.println(isPalindrome_one("forest"));
	        System.out.println("       ");
	        System.out.println(isPalindrome_two("mom"));
		
	}

}
