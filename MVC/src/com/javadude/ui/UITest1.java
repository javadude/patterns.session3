package com.javadude.ui;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.javadude.model.TodoItem;

public class UITest1 {

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		TodoItem item = new TodoItem();

		TodoView view1 = new TodoView();
		TodoView view2 = new TodoView();
		TodoView view3 = new TodoView();
		TodoView view4 = new TodoView();
		
		view1.setModel(item);
		view2.setModel(item);
		view3.setModel(item);
		view4.setModel(item);
		
		new Frame() {{
			setLayout(new GridLayout(0,1,5,5));
			add(view1);
			add(view2);
			add(view3);
			add(view4);
			addWindowListener(new WindowAdapter() {
				@Override public void windowClosing(WindowEvent e) {
					System.exit(0);
				}});
			pack();
			setVisible(true);
		}};
	}

}
