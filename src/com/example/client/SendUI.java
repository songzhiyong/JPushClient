package com.example.client;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SendUI extends JFrame {
	private static final long serialVersionUID = 4381002889004430690L;
	JPanel contentPane;

	public SendUI() {
		contentPane = (JPanel) this.getContentPane();
		setSize(400, 300);
		setDefaultCloseOperation(2);
		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(2);
		gridLayout.setRows(2);
		contentPane.setLayout(gridLayout);
		contentPane.add(new JPanel(), null);
		contentPane.add(new JPanel(), null);
		contentPane.add(new JPanel(), null);
		contentPane.add(new JPanel(), null);
		setVisible(true);
	}
}
