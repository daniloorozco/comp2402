package comp2402a4;

import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

public class GeometricTree extends BinaryTree<GeometricTreeNode> {

	public GeometricTree() {
		super (new GeometricTreeNode());
	}

	public void inorderDraw() {
		assignLevels();
		// TODO: use your code here instead
		//Random rand = new Random();
		//randomX(r, rand);
		GeometricTreeNode u = firstNode();
		for(int i = 0; u != nil; i++) {
			u.position.x = i;
			u = nextNode(u);
		}
	}

	protected void randomX(GeometricTreeNode u, Random r) {
		if (u == null) return;
		u.position.x = r.nextInt(60);
		randomX(u.left, r);
		randomX(u.right, r);
	}


	/**
	 * Draw each node so that it's x-coordinate is as small
	 * as possible without intersecting any other node at the same level
	 * the same as its parent's
	 */
	public void leftistDraw() {
		assignLevels();

		GeometricTreeNode newNode = r;
		newNode.position.x = 0;
		Queue<GeometricTreeNode> q = new LinkedList<GeometricTreeNode>();
		if (r != nil)
			q.add(r);
		while (!q.isEmpty()) {
			GeometricTreeNode u = q.remove();
			//if nodes level are equal
			if (newNode.position.y == u.position.y){
				//put node next to previous node
				u.position.x = newNode.position.x + 1;
				newNode = u;
			}
			else{
				//first node on level
				u.position.x = 0;
				newNode = u;
			}
			if (u.left != nil){
				q.add(u.left);
			}
			if (u.right != nil){
				q.add(u.right);
			}
		}
		r.position.x = 0;
	}


	public void balancedDraw() {
		assignLevels();
		//Random rand = new Random();
		//randomX(r, rand);
		int x = 0, y = 0;

		GeometricTreeNode newNode = firstNode();

		traverseLeft(r);

		while (newNode != nil) {
			if (newNode.left != nil && newNode.right != nil){
				newNode.size = newNode.left.size + newNode.right.size + 1;
			}
			else if (newNode.left != nil) {
				newNode.size = newNode.left.size + 1;
			}
			else if (newNode.right != nil){
				newNode.size = newNode.right.size + 1;
			}
			else {
				newNode.size = 1;
			}
			newNode = postOrder(newNode);
		}
		newNode = r;

		while(true){
			while (newNode.left != nil || newNode.right != nil) {
				// visit smaller child
				if (newNode.right != nil && newNode.left != nil) {
					newNode = getSmallestChild(newNode);
					y++;
				}
				//Go single child
				else {
					if (newNode.left == nil){
						newNode = newNode.right;
					}
					else {
						newNode = newNode.left;
					}
					x++;
				}
				newNode.position.y = y;
				newNode.position.x = x;
			}
			//switch to parent node
			newNode = newNode.parent;
			// move up
			while (newNode != nil && ((newNode.left == nil || newNode.right == nil) || getLargestChild(newNode).position.x > 0))
				newNode = newNode.parent;
			//see if its complete
			if (newNode == nil)
				break;
			y = newNode.position.y;
			x++;
			newNode = getLargestChild(newNode);
			newNode.position.y = y;
			newNode.position.x = x;
		}
	}

	public GeometricTreeNode postOrder(GeometricTreeNode u) {
		//post order
		if (u.parent != nil && u.parent.left == u) {
			u = u.parent;
			if (u.right != nil) {
				u = u.right;
				while (u.left != nil || u.right != nil) {
					while (u.left != nil)
						u = u.left;
					if (u.right != nil)
						u = u.right;
				}
			}
		}
		else
			u = u.parent;
		return u;
	}

	//helper methods
	public void traverseLeft(GeometricTreeNode u) {
		if (u == null)
			return;
		u.position.x = 0;
		traverseLeft(u.left);
		traverseLeft(u.right);
	}
	public GeometricTreeNode getSmallestChild(GeometricTreeNode u) {
		if (u.left.size < u.right.size)
			return u.left;
		else
			return u.right;
	}
	public GeometricTreeNode getLargestChild(GeometricTreeNode u) {
		if (u.right.size > u.left.size)
			return u.right;
		else
			return u.left;
	}

	protected void assignLevels() {
		assignLevels(r, 0);
	}

	protected void assignLevels(GeometricTreeNode u, int i) {
		if (u == null) return;
		u.position.y = i;
		assignLevels(u.left, i+1);
		assignLevels(u.right, i+1);
	}

	public static void main(String[] args) {
		GeometricTree t = new GeometricTree();
		galtonWatsonTree(t, 100);
		System.out.println(t);
		t.inorderDraw();
		System.out.println(t);
	}

}
