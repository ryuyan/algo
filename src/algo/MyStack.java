package algo;

public class MyStack {
	private LinkedList head;
	public MyStack(){
		head = null;
	}
	public void push(int val){
		LinkedList node = new LinkedList(val);
		node.next = head;
		head = node;
	}
	public int pop() throws Exception{
		if(head == null){
			throw new Exception("The stack is empty");
		}
		int val = head.val;
		head = head.next;
		return val;
	}
	public int top() throws Exception{
		if(head == null){
			throw new Exception("The stack is empty");
		}
		return head.val;
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	public static void main(String[] args){
		MyStack stack = new MyStack();
		stack.push(3);
		stack.push(2);
		stack.push(5);
		try {
			System.out.println(stack.pop());
			System.out.println(stack.pop());
			System.out.println(stack.pop());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
