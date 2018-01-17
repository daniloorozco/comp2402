package comp2402a2;
import java.util.*;


/**
 */
public class Tester {
	public static boolean testPart1(List<Integer> ell) {
		Stopwatch s = new Stopwatch();
		int elements = 100000;
		List<Integer> test = new Treque<Integer>(Integer.class);
		test.addAll(ell);
		//append to te end
		s.start();
		for (int i=0; i < elements; i++){
			ell.add(i);
		}
		s.stop();
		if (s.elapsedSeconds() > 1){
			return false;
		}
		for (int i=0; i < elements; i++){
			test.add(i);
		}
		//append to the front
		s.start();
		for (int i=0; i < elements; i++){
			ell.add(0,1);
		}
		s.stop();
		if (s.elapsedSeconds() > 1){
			return false;
		}
		for (int i=0; i < elements; i++){
			test.add(0,1);
		}
		//append to the middle
		s.start();
		for (int i=0; i < elements; i++){
			ell.add(ell.size()/2, i);
		}
		s.stop();
		if (s.elapsedSeconds() > 1){
			return false;
		}
		for (int i=0; i < elements; i++){
			test.add(test.size()/2, i);
		}
		if (!test.equals(ell)){
			return false;
		}
		return true;
	}

	public static boolean testPart2(List<Integer> ell) {
		Stopwatch s = new Stopwatch();
		int elements = 100000;
		List<Integer> test = new Treque<Integer>(Integer.class);
		test.addAll(ell);
		//start watch and append to the end
		s.start();
		for (int i=0; i < elements; i++){
			ell.add(i);
		}
		s.stop();
		if (s.elapsedSeconds() > 1){
			return false;
		}
		for (int i=0; i < elements; i++){
			test.add(i);
		}
		//append to the front
		s.start();
		for (int i=0; i < elements; i++){
			ell.add(0,1);
		}
		s.stop();
		if (s.elapsedSeconds() > 1){
			return false;
		}
		for (int i=0; i < elements; i++){
			test.add(0,1);
		}
		if (!test.equals(ell)){
			return false;
		}
		return true;
	}

	public static boolean testPart3(AbstractTable<Integer> ell) {
		Stopwatch s = new Stopwatch();
		int numRows = 50;
		int numCols = 30;
		Table test = new Table(Integer.class);
		//rows
		s.start();
		ell.rows();
		s.stop();
		if (s.elapsedSeconds() > 0.1){
			return false;
		}
		//columns
		s.start();
		ell.cols();
		s.stop();
		if (s.elapsedSeconds() > 0.1){
			return false;
		}
		//add row
		s.start();
		for (int i=0; i < numRows; i++){
			ell.addRow(i);
		}
		s.stop();
		if (s.elapsedSeconds() > 1){
			return false;
		}
		for (int i=0; i < numRows; i++){
			test.addRow(i);
		}
		//remove row
		s.start();
		for (int i=0; i < 2; i++){
			ell.removeRow(i);
		}
		s.stop();
		if (s.elapsedSeconds() > 1){
			return false;
		}
		for (int i = 0; i < 2; i++){
			test.removeRow(i);
		}
		//add column i
		s.start();
		for (int i=0; i < numCols; i++){
			ell.addCol(i);
		}
		s.stop();
		if (s.elapsedSeconds() > 1){
			return false;
		}
		for (int i=0; i < numCols; i++){
			test.addRow(i);
		}
		//remove Columns
		s.start();
		for (int i=0; i < numCols; i++){
			ell.removeCol(i);
		}
		s.stop();
		if (s.elapsedSeconds() > 1){
			return false;
		}
		for (int i=0; i < numCols; i++){
			test.removeRow(i);
		}
		if (!test.equals(ell)){
			return false;
		}
		return true;
	}
}
