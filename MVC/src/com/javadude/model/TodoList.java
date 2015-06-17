package com.javadude.model;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
	private List<TodoItem> items = new ArrayList<TodoItem>();
	public List<TodoItem> getItems() {
		return items;
	}
	public void add(TodoItem todoItem) {
		items.add(todoItem);
	}
	public void remove(TodoItem todoItem) {
		items.remove(todoItem);
	}
}
