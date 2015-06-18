package com.javadude.ui.swing;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import com.javadude.model.TodoItem;

public class TodoRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list,
			Object value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		TodoItem item = (TodoItem) value;
		label.setText(item.getDescription() + " (" + item.getPriority() + ")");
		return label;
	}
}
