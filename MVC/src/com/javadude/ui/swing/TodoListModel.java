package com.javadude.ui.swing;

import java.beans.PropertyChangeListener;

import javax.swing.AbstractListModel;

import com.javadude.model.TodoItem;
import com.javadude.model.TodoList;

public class TodoListModel extends AbstractListModel<TodoItem> {
	private static final long serialVersionUID = 1L;
	private TodoList todoList;
	private PropertyChangeListener listener = e -> fireContentsChanged(this, 0, getSize());
	
	public void setModel(TodoList todoList) {
		if (this.todoList != null) {
			todoList.removePropertyChangeListener(listener);
		}
		this.todoList = todoList;
		if (this.todoList != null) {
			todoList.addPropertyChangeListener(listener);
		}
		fireContentsChanged(this, 0, getSize());
	}

	@Override public TodoItem getElementAt(int index) {
		return todoList.getItems().get(index);
	}

	@Override
	public int getSize() {
		return todoList.getItems().size();
	}
}
