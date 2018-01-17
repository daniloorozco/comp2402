package comp2402a2;

import java.util.AbstractList;
import java.util.List;
import java.util.ArrayList;

/**
 */
public class RootishArrayDeque<T> extends AbstractList<T> {
	/**
	 * You decide on the instance variables you need.
	 */
	 Factory<T> type;
	 List<T>  frontList;
	 List<T> backList;


	public RootishArrayDeque(Class<T> t) {
		// Put your own code here
		type = new Factory(t);
		frontList = new RootishArrayStack(t);
		backList = new RootishArrayStack(t);
		//throw new UnsupportedOperationException("Constructor not yet implemented");
	}

	public T get(int i) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		if (i< frontList.size()){
			return frontList.get(frontList.size()-i-1);
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
			return frontList.set(frontList.size()-i-1, x);
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
			frontList.add(frontList.size()-i, x);
		}
		else{
			backList.add(i-frontList.size(), x);
		}
		balance();


		//throw new UnsupportedOperationException("add(i,x) not yet implemented")
	}



	public T remove(int i) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here
		T x;
		if (i < frontList.size()){
			x = frontList.remove(frontList.size()-i-1);
		}
		else{
			x = backList.remove(i-frontList.size());
		}
		balance();
		return x;
		//throw new UnsupportedOperationException("size(i) not yet implemented");
	}


	public int size() {
		// Put your own code here
		return frontList.size() + backList.size();
		//throw new UnsupportedOperationException("size() not yet implemented");
	}

	public void balance(){
		int size = size();
		int middle = size/2;
		if (frontList.size() + backList.size() > 1){
			if (frontList.size() > 3* backList.size()){
				List<T> list1 = new RootishArrayStack<>(type.type());
				List<T> list2 = new RootishArrayStack<>(type.type());
				for (int i=0; i < middle; i++){
					list1.add(i, get(middle-i-1));
				}
				for (int j=0; j < size - middle; j++){
					list2.add(j,get(middle+j));
				}
				frontList = list1;
				backList = list2;
			}
			else if (backList.size() > 3*frontList.size()){
				List<T> list1 = new RootishArrayStack<>(type.type());
				List<T> list2 = new RootishArrayStack<>(type.type());
				for (int i=0; i < middle; i++){
					list1.add(i, get(middle-i-1));
				}
				for (int j=0; j < size - middle; j++){
					list2.add(j, get(middle+j));
				}
				frontList = list1;
				backList = list2;
			}
		}
	}

	public static void main(String[] args) {
		//List<Integer> rad = new ArrayDeque<Integer>(Integer.class);
		List<Integer> rad = new RootishArrayDeque<Integer>(Integer.class);
		int K = 1000000;
		Stopwatch s = new Stopwatch();
		System.out.print("Appending " + K + " items...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.add(i);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Prepending " + K + " items...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.add(0, i);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Removing " + K + " items from the back...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.remove(rad.size()-1);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Removing " + K + " items from the front...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.remove(0);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");
	}



}
