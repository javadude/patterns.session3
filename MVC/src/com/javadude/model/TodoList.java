package com.javadude.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoList {
	// Use PropertyChangeSupport for Observer support of bound properties
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
	}

	private List<TodoItem> items = new ArrayList<TodoItem>();
	
	// CAUTION!!! THIS EXPOSES THE REAL LIST!!! USERS CAN CHANGE IT WITHOUT
	//   US KNOWING; WE CANNOT FIRE PROPERTY CHANGE!!! 
	public List<TodoItem> getItems() {
		// OPTION 1 - make the list immutable
		return Collections.unmodifiableList(items);
		
		// OPTION 2 - create a list wrapper that fires changes on our behalf
		//   left as an exercise for the interested (read "masochistic") reader
		//   Note: if this is done, we don't need the add() and remove() methods below
		//         users can just call getItems().add(xxx) or getItems().remove(xxx)
	}
	
	public void add(TodoItem todoItem) {
		items.add(todoItem);
		// use null for old value - always fire event, 
		//   and rarely do listeners care about old value
		propertyChangeSupport.firePropertyChange("items", null, items);
	}
	public void remove(TodoItem todoItem) {
		items.remove(todoItem);
		// use null for old value - always fire event, 
		//   and rarely do listeners care about old value
		propertyChangeSupport.firePropertyChange("items", null, items);
	}
}
