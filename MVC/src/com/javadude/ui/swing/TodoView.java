package com.javadude.ui.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.javadude.model.TodoItem;

public class TodoView extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextField descriptionField = new JTextField();
	private JTextField priorityField = new JTextField();
	
	private TodoItem model;
	private PropertyChangeListener listener = e -> updateFields();
	
	public TodoItem getModel() {
		return model;
	}

	private void updateField(JTextField textField, String value) {
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
		add(BorderLayout.NORTH, new JPanel(new BorderLayout()) {{
			add(BorderLayout.WEST, new JPanel(new GridLayout(0, 1, 5, 5)) {{
				add(new JLabel("Description"));
				add(new JLabel("Priority"));
			}});
			add(BorderLayout.CENTER, new JPanel(new GridLayout(0, 1, 5, 5)) {{
				add(descriptionField);
				add(priorityField);
			}});
		}});

		listen(descriptionField, () -> model.setDescription(descriptionField.getText()) );
		listen(priorityField, () -> model.setPriority(priorityField.getText()) );
		setModel(null);
	}
	private void listen(JTextField field, Runnable runnable) {
		field.getDocument().addDocumentListener(new DocumentListener() {
			public void changed() { runnable.run(); }
			@Override public void changedUpdate(DocumentEvent e) { changed(); }
			@Override public void insertUpdate(DocumentEvent e)  { changed(); }
			@Override public void removeUpdate(DocumentEvent e)  { changed(); }
		});
	}
}
