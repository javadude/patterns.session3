package com.javadude.ui;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.javadude.model.TodoList;

public class UITest1 {

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		TodoList todoList = new TodoList();
		TodoListView view1 = new TodoListView();
		view1.setModel(todoList);
		
		new Frame() {{
			setLayout(new GridLayout(0, 1, 5, 5));
			add(view1);
			addWindowListener(new WindowAdapter() {
				@Override public void windowClosing(WindowEvent e) {
					System.exit(0);
				}});
			pack();
			setVisible(true);
		}};
	}

}
