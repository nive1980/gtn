package com.gtn.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FunctionalInterface
interface aa {
	void String();
	boolean equals(Object aa);
	int hashCode();
}

class one implements aa{

	@Override
	public void String() {
		// TODO Auto-generated method stub
		
	}
	
}

class two implements aa{

	@Override
	public void String() {
		// TODO Auto-generated method stub
		
	}
	
	public void show(int[] o){
		
	}
	public void show(String s){
		
	}
}




enum cars{
	PENDING("pending"),
    ACTIVE("active"),
    INACTIVE("inactive"),
    DELETED("deleted");
	
	private String type;
	
	cars(String s){
		this.type = s;
	}
	
	public String getType(){
		return type;
	}
}

public class Test  {
    public static void main(String[] args) throws InterruptedException {
    	
    	
    	two t = new two();
    	//t.show(null);
    	
    	System.out.println(cars.PENDING.getType());
    	
    	
    	/*Test lion = new Test();// compiler can make it null - so finalize can be called before main SOP.
    	
    	//Thread.sleep(3000);
        System.out.println("Main is completed.");
        
        int[] arr = new int[5];
        List<int[]> list = Arrays.asList(arr);
        
        
        System.out.println(arr[0]);*/
        
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    	System.out.println("Rest in Peace!");
    }
}

class Animal implements Cloneable{
	
	private static void fs(int count, int secondLast, int last){
		if(count == 0)
			return;
		
		if(secondLast == 0){
			System.out.print("0 1 ");
		}
		else {
			System.out.print(secondLast+last+" ");
		}
	
		fs(--count, last, secondLast+last);
	}

	@Override
	public boolean equals(Object a){
		return true;
	}
	
	public static void main(String[] args) throws InterruptedException, CloneNotSupportedException{
		
		List<String> input = new ArrayList<String>();
		
		input.add("a");
		input.add("b");
		input.add("c");
		input.add("c");
		input.add("b");
		input.add("d");
		input.add("e");
		
		Set<String> set = new HashSet<String>();
		
		for(int i=0; i<input.size(); i++){
			set.add(input.get(i));
		}
		
		System.out.println(set);
		
		Animal.fs(15, 0, 1);
		
		
		//test2 test2 = new test2();
		//System.out.println(test2.clone());
		
		Test t = new Test();
		//t.clone();
		
		System.out.println("has code -> "+t.hashCode());
		
		aa obj = new two();
		System.out.println(obj.getClass());
		
		Class c = obj.getClass();
		System.out.println(c.getMethods());
		
		String a = "naveen";
		String b = "naveen";
		
		System.out.println(a.equals(b));
		
		aa interf = null;
		System.out.println(interf.equals(null));
		
		Runnable r = new Runnable(){
			public void run(){
				System.out.println("hello");
			}
		};
		
		Runnable rr  = () -> {
				for(int i=0; i<1000; i++)
					System.out.println("from rr ---- "); 
		};
		
		Runnable rr2  = () -> {
				for(int i=0; i<1000; i++)
					System.out.println("from rr1 ---- "); 
		};
		
		Runnable rr3  = () -> {
				for(int i=0; i<1000; i++)
					System.out.println("from rr2 ---- "); 
		};
				
		Thread t1 = new Thread(rr);
		t1.start();
		t1.join();
		
		Thread t2 = new Thread(rr2);
		t2.start();
		t2.join();
		
		Thread t3 = new Thread(rr3);
		t3.start();
		t3.join();
		
		new Thread(
				() -> System.err.println("hello")
		).start();
		
	}


}