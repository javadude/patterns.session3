package com.javadude.ui.swing;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.javadude.model.TodoList;

public class UITest2 {

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		TodoList todoList = new TodoList();
		TodoListView view1 = new TodoListView();
		view1.setModel(todoList);
		
		new JFrame() {{
			setLayout(new GridLayout(0, 1, 5, 5));
			add(view1);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			pack();
			setVisible(true);
		}};
	}
}
