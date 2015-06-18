package com.javadude.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.beans.PropertyChangeListener;

import com.javadude.model.TodoItem;
import com.javadude.model.TodoList;

public class TodoListView extends Panel {
	private static final long serialVersionUID = 1L;
	private TodoList model;
	private List list;
	private TodoView todoView;
	private PropertyChangeListener listener = e -> refreshList();

	public TodoList getModel() {
		return model;
	}

	private void refreshList() {
		list.removeAll();
		for(TodoItem item : model.getItems()) {
			list.add(item.getDescription());
		}
	}
	
	public void setModel(TodoList model) {
		if (this.model != null) {
			// disconnect from the previous model
			model.removePropertyChangeListener(listener);
		}
		this.model = model;
		if (this.model != null) {
			// connect to the new model
			model.addPropertyChangeListener(listener);
			// populate the UI
			refreshList();
		}
	}
	
	@SuppressWarnings("serial")
	public TodoListView() {
		list = new List();
		list.addItemListener(e -> {
			todoView.setModel(model.getItems().get(list.getSelectedIndex()));
		});
		todoView = new TodoView();
		
		Button addButton = new Button("Add");
		Button removeButton = new Button("Remove");

		addButton.addActionListener(e -> {
			TodoItem todoItem = new TodoItem();
			model.add(todoItem);
			list.select(model.getItems().size()-1);
			todoView.setModel(todoItem);
		});
		removeButton.addActionListener(e -> {
			model.remove(model.getItems().get(list.getSelectedIndex()));
		});
		
		setLayout(new BorderLayout());
		add(BorderLayout.WEST, list);
		add(BorderLayout.CENTER, new Panel(new BorderLayout()) {{
			add(BorderLayout.CENTER, todoView);
			add(BorderLayout.SOUTH, new Panel(new FlowLayout(FlowLayout.RIGHT)) {{
				add(new Panel(new GridLayout(1,0,3,3)) {{
					add(addButton);
					add(removeButton);
				}});
			}});
		}});
	}
}
