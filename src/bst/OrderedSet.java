/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bst;

public interface OrderedSet {
	
	public void add(Comparable data);
	public boolean contains(Comparable data);
	public Comparable first();
	public Comparable next();
	public int size();
	
}
