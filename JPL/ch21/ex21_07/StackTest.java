package ch21.ex21_07;

public class StackTest {

	public static void main(String[] args){
		Stack<String> stack = new Stack<String>();
		
		stack.push("1");
		stack.push("2");
		stack.push("3");
		System.out.println(stack);
		
		System.out.println("pop:" + stack.pop());
		System.out.println("pop:" + stack.pop());
		
		stack.push("4");
		System.out.println(stack);

	}
	
}
