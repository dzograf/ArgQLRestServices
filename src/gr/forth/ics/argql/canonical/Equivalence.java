package gr.forth.ics.argql.canonical;

import java.util.ArrayList;

import org.openrdf.sail.rdbms.algebra.factories.BooleanExprFactory;
import org.stringtemplate.v4.compiler.STParser.ifstat_return;

public class Equivalence {

	private ArrayList<INode> iNodes;
	private int canonicalElement = -1;

	public Equivalence() {
		iNodes = new ArrayList<INode>();
	}
	
	public ArrayList<INode> getINodes() {
		return iNodes;
	}

	public void setINodes(ArrayList<INode> iNodes) {
		this.iNodes = iNodes;
	}

	private boolean exists(INode node) {
		for (INode iNode : iNodes) {
			if(node.getUri().compareTo(iNode.getUri()) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean add(INode inode) {
		if(!exists(inode))
			return iNodes.add(inode);
		return false;
	}
	
	
	public int getCanonicalElement() {
		return canonicalElement;
	}

	public void setCanonicalElement(int canonicalElement) {
		this.canonicalElement = canonicalElement;
	}
	
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Canonical: "+ canonicalElement);
		for(INode i : iNodes) {
			str.append(i.toString() + "\n");
		}
		return str.toString();
	}
}
