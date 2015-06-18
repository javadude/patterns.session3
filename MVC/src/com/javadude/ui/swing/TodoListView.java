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
	private StatusBar statusBar;

	public TodoList getModel() {
		return model;
	}

	public void setModel(TodoList model) {
		if (this.model != null) {
			// disconnect from the previous model
			todoListModel.setModel(null); // unhook previous model
			statusBar.setList(null);
		}
		this.model = model;
		if (this.model != null) {
			// connect to the new model
			todoListModel.setModel(model);
			statusBar.setList(model);
		}
	}
	
	public TodoListView() {
		this(null);
	}
	@SuppressWarnings("serial")
	public TodoListView(ListSelectionModel selectionModel) {
		statusBar = new StatusBar();
		todoListModel = new TodoListModel();
		list = new JList<TodoItem>(todoListModel);
		if (selectionModel != null)
			list.setSelectionModel(selectionModel);
		list.setCellRenderer(new TodoRenderer());
		list.addListSelectionListener(e -> {
			TodoItem selectedValue = list.getSelectedValue();
			todoView.setModel(selectedValue);
			statusBar.setItem(selectedValue);
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
			TodoItem selectedValue = list.getSelectedValue();
			model.remove(selectedValue);
			todoView.setModel(selectedValue);
			statusBar.setItem(selectedValue);
		});
		
		setLayout(new BorderLayout());
		add(BorderLayout.WEST, list);
		add(BorderLayout.SOUTH, statusBar);
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
