package com.javadude.ui.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import com.javadude.model.TodoItem;
import com.javadude.model.TodoList;

public class TodoListView extends JPanel {
	private static final long serialVersionUID = 1L;
	private TodoList model;
	private JList<TodoItem> list;
	private TodoListModel todoListModel;
	private TodoView todoView;

	public TodoList getModel() {
		return model;
	}

	public void setModel(TodoList model) {
		if (this.model != null) {
			// disconnect from the previous model
			todoListModel.setModel(null); // unhook previous model
		}
		this.model = model;
		if (this.model != null) {
			// connect to the new model
			todoListModel.setModel(model);
			// populate the UI
		}
	}
	
	public TodoListView() {
		this(null);
	}
	@SuppressWarnings("serial")
	public TodoListView(ListSelectionModel selectionModel) {
		todoListModel = new TodoListModel();
		list = new JList<TodoItem>(todoListModel);
		if (selectionModel != null)
			list.setSelectionModel(selectionModel);
		list.setCellRenderer(new TodoRenderer());
		list.addListSelectionListener(e -> {
			todoView.setModel(list.getSelectedValue());
		});
		todoView = new TodoView();
		
		JButton addButton = new JButton("Add");
		JButton removeButton = new JButton("Remove");

		addButton.addActionListener(e -> {
			TodoItem todoItem = new TodoItem();
			model.add(todoItem);
			list.setSelectedValue(todoItem, true);
		});
		removeButton.addActionListener(e -> {
			model.remove(list.getSelectedValue());
			todoView.setModel(list.getSelectedValue());
		});
		
		setLayout(new BorderLayout());
		add(BorderLayout.WEST, list);
		add(BorderLayout.CENTER, new JPanel(new BorderLayout()) {{
			add(BorderLayout.CENTER, todoView);
			add(BorderLayout.SOUTH, new JPanel(new FlowLayout(FlowLayout.RIGHT)) {{
				add(new JPanel(new GridLayout(1,0,3,3)) {{
					add(addButton);
					add(removeButton);
				}});
			}});
		}});
	}
}
