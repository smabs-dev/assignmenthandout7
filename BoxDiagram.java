package week5;

import acm.graphics.*;
import acm.program.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class BoxDiagram extends GraphicsProgram {
	public void init() {
		contents = new HashMap<String, GObject>();
		createController();
		addActionListeners();
		addMouseListeners();
	}

	private void createController() {
		nameField = new JTextField(MAX_NAME);
		nameField.addActionListener(this);
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		clearButton = new JButton("Clear");
		
		add(new JLabel("Name"), SOUTH);
		add(new JTextField(25), SOUTH);
		add(new JButton("Add"), SOUTH);
		add(new JButton("Remove"), SOUTH);
		add(new JButton("Clear"), SOUTH);
	}

	private void addBox(String name) {
		GCompound box = new GCompound();
		GRect outline = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(name);
		box.add(outline, -BOX_WIDTH / 2, -BOX_HEIGHT / 2);
		box.add(label, -label.getWidth() / 2, label.getAscent() / 2);
		add(box, getWidth() / 2, getHeight() / 2);
		contents.put(name, box);
	}

	private void removeBox(String name) {
		GObject obj = contents.get(name);
		if (obj != null) {
			remove(obj);
		}
	}

	private void removeContents() {
		Iterator<String> iterator = contents.keySet().iterator();
		while (iterator.hasNext()) {
			removeBox(iterator.next());
		}
		contents.clear(); 
		}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if((source == addButton) || source == nameField) {
			GCompound box = new GCompound();
			GRect outline = new GRect(120, 50);

			GLabel label = new GLabel(nameField.getText());
			box.add(outline, -120/2, -50/2);
			box.add(label, -label.getWidth()/2, -label.getAscent()/2);
			box.add(label, box.getWidth()/2, box.getHeight()/2);
		}
	}

	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		currentObject = getElementAt(last);
	}

	public void mouseDragged(MouseEvent e) {
		if (currentObject != null) {
			currentObject.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (currentObject != null)
			currentObject.sendToFront();
	}

	private static final int MAX_NAME = 25;
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	private HashMap<String, GObject> contents;
	private JTextField nameField;
	private JButton addButton;
	private JButton removeButton;
	private JButton clearButton;
	private GObject currentObject;
	private GPoint last;
}