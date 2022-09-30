package com.adsn1.screens;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaExemplo extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public TelaExemplo() {
		setTitle("Cadastrar");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblInternalFrame = new JLabel("Nome do campo:");
		lblInternalFrame.setBounds(33, 47, 120, 15);
		getContentPane().add(lblInternalFrame);
		
		textField = new JTextField();
		textField.setBounds(33, 74, 114, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(160, 198, 117, 25);
		getContentPane().add(btnSalvar);

	}
}
