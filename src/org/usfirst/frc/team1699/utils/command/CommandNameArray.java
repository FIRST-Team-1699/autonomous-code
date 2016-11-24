/**
 * A class that houses an array list and makes sure that each name is unique
 * 
 * @author squirlemaster42
 */
package org.usfirst.frc.team1699.utils.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@SuppressWarnings("rawtypes")
public class CommandNameArray implements List{
	
	private ArrayList<String> list; //Hold list of names

	/**
	 * Constructor
	 */
	public CommandNameArray() { //Constructor to name new instance of an ArrayList that will hold String objects
		list = new ArrayList<String>();
	}

	/**
	 * Adds a name to the array
	 * 
	 * @param name
	 */
	public void addName(String name) { //Adds name to array if it is unique
		if(list.contains(name)){
			System.err.println("Id has already been used.");
			throw new NameUsedException();
		}
		list.add(name);
	}
	
	/**
	 * Returns the name array
	 * 
	 * @return
	 */
	public ArrayList<String> getList() { //Returns list of names
		return list;
	}
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}
	
	@Override
	public Iterator iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public Object[] toArray(Object[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(Object e) {
		return list.add((String) e);
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection c) {
		return list.containsAll(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection c) {
		return list.addAll(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(int index, Collection c) {
		return list.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection c) {
		return list.retainAll(c);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public Object get(int index) {
		return list.get(index);
	}

	@Override
	public Object set(int index, Object element) {
		return list.set(index, (String) element);
	}

	@Override
	public void add(int index, Object element) {
		list.add(index, (String) element); 
	}

	@Override
	public Object remove(int index) {
		return list.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator listIterator() {
		return list.listIterator();
	}
		
	@Override
	public ListIterator listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

}
