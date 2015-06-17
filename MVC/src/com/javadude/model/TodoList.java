package com.javadude.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
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
	public List<TodoItem> getItems() {
		return items;
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
