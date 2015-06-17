package com.javadude.ui;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.beans.PropertyChangeListener;

import com.javadude.model.TodoItem;

public class TodoView extends Panel {
	// NOTE - we could just have this extend TextField but this is more
	//        general in case we add more data to the item
	private static final long serialVersionUID = 1L;

	// could have other fields for other data in todo item (if it had more data)
	private TextField itemField = new TextField();
	
	private TodoItem model;
	private PropertyChangeListener listener = 
			e -> {
				// update UI when model changes
				// don't update fields that already have the correct value
				if (!itemField.getText().equals(model.getDescription()))
					itemField.setText(model.getDescription());
			};
	
	public TodoItem getModel() {
		return model;
	}

	public void setModel(TodoItem model) {
		if (this.model != null) {
			// disconnect from previous model
			this.model.removePropertyChangeListener(listener);
		}
		this.model = model;
		if (this.model != null) {
			// connect to new model
			this.model.addPropertyChangeListener(listener);

			// initialize fields in the UI
			itemField.setText(model.getDescription());
		}
	}

	public TodoView() {
		setLayout(new BorderLayout());
		add(itemField, BorderLayout.NORTH);
		
		itemField.addTextListener(
				e -> {
						if (model==null) return;
						model.setDescription(itemField.getText());
					});
	}
}
