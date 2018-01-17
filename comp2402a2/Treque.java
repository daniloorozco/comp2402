package comp2402a2;

import java.util.AbstractList;
import java.util.List;
//import java.util.*;
//import comp2402a2.ArrayDeque.*;
/**
 */
public class Treque<T> extends AbstractList<T>{
	/**
	 * You decide on the instance variables you need.
	 */
	public List<T> frontList;
	public List<T> backList;

	public Treque(Class<T> t) {
		// Put your own code here
		frontList = new ArrayDeque(t);
		backList = new ArrayDeque(t);


		//throw new UnsupportedOperationException("Constructor not yet implemented");
	}

	public T get(int i) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		//return a[j+1 % a.length]
		if (i< frontList.size()){
			//return frontList.get(frontList.size()-i-1);
			return frontList.get(i);
		}
		else{
				return backList.get(i-frontList.size());
		}
		//throw new UnsupportedOperationException("get(i) not yet implemented");
	}

	public T set(int i, T x) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		if (i < frontList.size()){
			//return frontList.set(frontList.size()-i-1, x);
			return frontList.set(i, x);
		}
		else{
			return backList.set(i-frontList.size(), x);
		}
		//throw new UnsupportedOperationException("set(i,x) not yet implemented");
	}

	public void add(int i, T x) {
		if (i < 0 || i > size()) throw new IndexOutOfBoundsException();
		// Put your own code here
		if (i < frontList.size()){
			frontList.add(i, x);
			//frontList.add(frontList.size()-i, x);
		}
		else{
			backList.add(i-frontList.size(), x);
		}
		balance();
		//throw new UnsupportedOperationException("add(i,x) not yet implemented");
	}

	public T remove(int i) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here
		T x;
		if (i < frontList.size()){
			x = frontList.remove(i);
			//x = frontList.remove(frontList.size()-i-1);
		}
		else{
			x = backList.remove(i-frontList.size());
		}
		return x;
		//throw new UnsupportedOperationException("remove(i) not yet implemented");
	}

	public int size() {
		// Put your own code here
		return frontList.size() + backList.size();
		//throw new UnsupportedOperationException("size() not yet implemented");
	}

	public void balance(){
		if(frontList.size() > backList.size()+1){
			backList.add(0, frontList.remove(frontList.size()-1));

		}
		else if (backList.size() > frontList.size()+1){
			frontList.add(frontList.size(), backList.remove(0));
		}
	}

	public static void main(String[] args) {
		//List<Integer> tr = new ArrayDeque<Integer>(Integer.class);
		List<Integer> tr = new Treque<Integer>(Integer.class);
		int K = 1000000;
		Stopwatch s = new Stopwatch();
		System.out.print("Appending " + K + " items...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			tr.add(i);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Prepending " + K + " items...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			tr.add(0, i);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Midpending(?!) " + K + " items...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			tr.add(tr.size()/2, i);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");


		System.out.print("Removing " + K + " items from the back...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			tr.remove(tr.size()-1);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Removing " + K + " items from the front...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			tr.remove(0);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Removing " + K + " items from the middle...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			tr.remove(tr.size()/2);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");
	}



}
