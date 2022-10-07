package com.adsn1.screens;


import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.Color;

public class CadProdutos extends JInternalFrame {
	/** TELA NOVA DE CADASTRO DE PRODUTOS
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadProdutos frame = new CadProdutos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadProdutos() {
		setClosable(true);
		setTitle("PRODUTOS");
		setBounds(100, 100, 450, 366);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setBounds(7, 7, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Editar");
		btnNewButton_1.setBounds(106, 7, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Excluir");
		btnNewButton_2.setBounds(205, 7, 89, 23);
		getContentPane().add(btnNewButton_2);
		
		textField = new JTextField();
		textField.setBounds(10, 55, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(109, 55, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(208, 55, 52, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(332, 55, 61, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Código de barras");
		lblNewLabel.setBounds(10, 41, 86, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descrição");
		lblNewLabel_1.setBounds(109, 41, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Valor");
		lblNewLabel_2.setBounds(208, 41, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Em estoque");
		lblNewLabel_3.setBounds(332, 41, 61, 14);
		getContentPane().add(lblNewLabel_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(10, 100, 86, 20);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Filtrar");
		lblNewLabel_4.setBounds(10, 86, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		JList<Object> list = new JList<Object>();
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setForeground(new Color(0, 0, 0));
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setModel(new AbstractListModel<Object>() {
		
			 
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"192168025               Bermuda Cyclone Veludo               R$: 305,00               22", "786221038               Calça Moletom Nike                        R$:150,00               17"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(1);
		list.setBounds(10, 131, 414, 194);
		getContentPane().add(list);
edIndex(1);
		list.setBounds(10, 131, 414, 194);
		getContentPane().add(list);

	}

	private void edIndex(int i) {
		// TODO Auto-generated method stub
		
	}
}
