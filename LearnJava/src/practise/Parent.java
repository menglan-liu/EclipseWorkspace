package practise;

public class Parent {
	String parentname = "Yang Hanxiu";
	
	public Parent() {
		System.out.println("This is constructor of Parent class");
	}

	public void showBirthday() {
		System.out.println("1930-03-19");
	}

	public void showChildren() {
		String childname1 = "dada";
		String childname2 = "tiantian";
		System.out.println(childname1 + " " + "and" + " " + childname2);
	}
}