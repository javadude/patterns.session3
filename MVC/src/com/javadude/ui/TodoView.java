package com.javadude.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.beans.PropertyChangeListener;

import com.javadude.model.TodoItem;

public class TodoView extends Panel {
	private static final long serialVersionUID = 1L;

	private TextField descriptionField = new TextField();
	private TextField priorityField = new TextField();
	
	private TodoItem model;
	private PropertyChangeListener listener = e -> updateFields();
	
	public TodoItem getModel() {
		return model;
	}

	private void updateField(TextField textField, String value) {
		// don't update fields that already have the correct value
		if (!textField.getText().equals(value))
			textField.setText(value);
	}
	private void updateFields() {
		updateField(descriptionField, model.getDescription());
		updateField(priorityField, model.getPriority());
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
			updateFields();
		}
		descriptionField.setEnabled(this.model != null);
		priorityField.setEnabled(this.model != null);
	}

	@SuppressWarnings("serial")
	public TodoView() {
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, new Panel(new BorderLayout()) {{
			add(BorderLayout.WEST, new Panel(new GridLayout(0, 1, 5, 5)) {{
				add(new Label("Description"));
				add(new Label("Priority"));
			}});
			add(BorderLayout.CENTER, new Panel(new GridLayout(0, 1, 5, 5)) {{
				add(descriptionField);
				add(priorityField);
			}});
		}});
		
		descriptionField.addTextListener( e -> model.setDescription(descriptionField.getText()) );
		priorityField.addTextListener( e -> model.setPriority(priorityField.getText()) );
		setModel(null);
	}
}
