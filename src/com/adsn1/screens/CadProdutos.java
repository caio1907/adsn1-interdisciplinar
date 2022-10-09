package com.adsn1.screens;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;

public class CadProdutos extends JInternalFrame {
	private static CadProdutos screen = null;
	private static final long serialVersionUID = 1L;
	
	public static CadProdutos getScreen() {
		if (screen == null) {
			screen = new CadProdutos();
		}
		return screen;
	}

	/**
	 * Create the frame.
	 */
	public CadProdutos() {
		setClosable(true);
		setTitle("Cadastro de Produtos");
		setBounds(100, 100, 600, 500);

		JTextField txtCodDeBarras = new JTextField();
		txtCodDeBarras.setColumns(10);

		JTextField txtDescricao = new JTextField();
		txtDescricao.setColumns(10);

		JTextField txtValor = new JTextField();
		txtValor.setColumns(10);

		JTextField txtQtdEstoque = new JTextField();
		txtQtdEstoque.setColumns(10);

		JLabel lblCodigoDeBarras = new JLabel("Código de barras");

		JLabel lblDescricao = new JLabel("Descrição");

		JLabel lblValor = new JLabel("Valor");

		JLabel lblQtdEstoque = new JLabel("Em estoque");

		JTextField txtFiltrar = new JTextField();
		txtFiltrar.setColumns(10);

		JLabel lblFiltrar = new JLabel("Filtrar");

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{"1", "192168025", "Bermuda Jeans - PP", "350.00", "25"},
					{"2", "786221038", "Cal\u00E7a Moletom - G", "150.00", "17"},
				},
				new String[] {
						"Id", "C\u00F3d. de Barras", "Descri\u00E7\u00E3o", "Valor", "Qtd. Estoque"
				}
				) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setAlignmentX(Component.RIGHT_ALIGNMENT);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblFiltrar, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(txtFiltrar, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCodigoDeBarras, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
									.addGap(2))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtCodDeBarras, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
									.addGap(14)))
							.addGap(2)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtDescricao, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
									.addGap(6))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDescricao, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
									.addGap(17)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblValor, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
									.addGap(20))
								.addComponent(txtValor, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblQtdEstoque, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(6))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtQtdEstoque, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
									.addGap(22)))
							.addGap(152))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCadastrar)
							.addGap(18)
							.addComponent(btnEditar)
							.addGap(18)
							.addComponent(btnExcluir)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCodigoDeBarras, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCodDeBarras, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblValor, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblQtdEstoque, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtQtdEstoque, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
					.addGap(11)
					.addComponent(lblFiltrar, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addComponent(txtFiltrar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
