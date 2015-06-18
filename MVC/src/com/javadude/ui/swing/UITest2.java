package com.javadude.ui.swing;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.javadude.model.TodoList;

public class UITest2 {

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		TodoList todoList1 = new TodoList();
		TodoList todoList2 = new TodoList();
		TodoListView view1 = new TodoListView();
		TodoListView view2 = new TodoListView();
		TodoListView view3 = new TodoListView();
		view1.setModel(todoList1);
		view2.setModel(todoList1);
		view3.setModel(todoList2);
		
		new JFrame() {{
			setLayout(new GridLayout(0, 1, 5, 5));
			add(view1);
			add(view2);
			add(view3);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			pack();
			setVisible(true);
		}};
	}
}
