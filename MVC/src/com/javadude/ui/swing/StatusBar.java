package com.javadude.ui.swing;

import java.beans.PropertyChangeListener;

import javax.swing.JLabel;

import com.javadude.model.TodoItem;
import com.javadude.model.TodoList;

public class StatusBar extends JLabel {
	private static final long serialVersionUID = 1L;

	private TodoItem item;
	private TodoList list;
	private PropertyChangeListener listener = e -> updateField();
	
	public TodoItem getItem() {
		return item;
	}
	public TodoList getList() {
		return list;
	}

	private void updateField() {
		String text;
		if (item == null)
			text = "Nothing is selected";
		else
			text = item.getDescription() + " is selected";
		
		if (list != null) {
			// count #1 priority items
			int num1 = 0;
			for(TodoItem todoItem : list.getItems()) {
				if (todoItem.getPriority().trim().equals("1")) {
					// really should convert to String but it's 4am and I'm getting tired...
					num1++;
				}
			}
			text += "    (" + num1 + " priority 1 items!)";
		}
		setText(text);
	}
	
	public void setItem(TodoItem item) {
		if (this.item != null) {
			// disconnect from previous model
			this.item.removePropertyChangeListener(listener);
		}
		this.item = item;
		if (this.item != null) {
			// connect to new model
			this.item.addPropertyChangeListener(listener);

			// initialize fields in the UI
			updateField();
		}
	}

	public void setList(TodoList list) {
		if (this.list != null) {
			// disconnect from previous model
			this.list.removePropertyChangeListener(listener);
		}
		this.list = list;
		if (this.list != null) {
			// connect to new model
			this.list.addPropertyChangeListener(listener);
			
			// initialize fields in the UI
			updateField();
		}
	}
	
	public StatusBar() {
		setItem(null);
		setList(null);
	}
}
