package com.adsn1.screens;


import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Box;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class Venda extends JInternalFrame {
	private static Venda screen = null;
	private static final long serialVersionUID = 1L;
	private JTextField tF_Valor;
	private JTable table;
	private JTextField textField;
	private JTextField txtQuantidade;
	private JTextField textField_1;
	
	public static Venda getScreen() {
		if (screen == null) {
			screen = new Venda();
		}
		return screen;
	}

	/**
	 * Create the frame.
	 */
	public Venda() {
		setTitle("Vendas");
		setBounds(100, 100, 600, 500);
		setClosable(true);
		
		Box hBox_Cliente = Box.createHorizontalBox();
		hBox_Cliente.setBorder(null);
		
		Box verticalBox_Clientes_Buscar = Box.createVerticalBox();
		verticalBox_Clientes_Buscar.setAlignmentX(0.2f);
		hBox_Cliente.add(verticalBox_Clientes_Buscar);
		
		JLabel lblBuscarCliente = new JLabel("Buscar Cliente");
		verticalBox_Clientes_Buscar.add(lblBuscarCliente);
		
		tF_Valor = new JTextField();
		tF_Valor.setColumns(10);
		verticalBox_Clientes_Buscar.add(tF_Valor);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(50);
		hBox_Cliente.add(horizontalStrut_1);
		
		Box verticalBox_Clientes = Box.createVerticalBox();
		verticalBox_Clientes.setAlignmentX(2.0f);
		hBox_Cliente.add(verticalBox_Clientes);
		
		JLabel lblFiltroClientes = new JLabel("Clientes");
		lblFiltroClientes.setAlignmentX(1.0f);
		verticalBox_Clientes.add(lblFiltroClientes);
		
		JComboBox<String> cbClientes = new JComboBox<String>();
		cbClientes.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleciones um cliente"}));
		verticalBox_Clientes.add(cbClientes);
		
		JButton btnNewButton = new JButton("Finalizar pedido");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "PRODUTO DE TESTE 1", "5"},
				{"12", "CAMISA M PRETA", "2"},
			},
			new String[] {
				"C\u00D3D", "DESCRI\u00C7\u00C3O", "QUANT."
			}
		) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.setToolTipText("produtos");
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Pix", "Dinheiro", "Cartão Crédito - MasterCard", "Cartão Débito - MasterCard", "Cartão Crédito - Visa", "Cartão Débito - Visa"}));
		
		JLabel lblTotalAReceber = new JLabel("Total a receber:");
		
		Box hBox_Produto = Box.createHorizontalBox();
		hBox_Produto.setBorder(null);
		
		Box verticalBox_Clientes_Buscar_1 = Box.createVerticalBox();
		verticalBox_Clientes_Buscar_1.setAlignmentX(0.2f);
		hBox_Produto.add(verticalBox_Clientes_Buscar_1);
		
		JLabel lblBuscarCliente_1 = new JLabel("Buscar Produto");
		verticalBox_Clientes_Buscar_1.add(lblBuscarCliente_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		verticalBox_Clientes_Buscar_1.add(textField);
		
		Component horizontalStrut_1_1 = Box.createHorizontalStrut(50);
		hBox_Produto.add(horizontalStrut_1_1);
		
		Box verticalBox_Clientes_Buscar_1_1 = Box.createVerticalBox();
		verticalBox_Clientes_Buscar_1_1.setAlignmentX(0.2f);
		hBox_Produto.add(verticalBox_Clientes_Buscar_1_1);
		
		JLabel lblQuantidadeProduto = new JLabel("Quantidade");
		verticalBox_Clientes_Buscar_1_1.add(lblQuantidadeProduto);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setText("1");
		verticalBox_Clientes_Buscar_1_1.add(txtQuantidade);
		
		JLabel lblProdutos = new JLabel("Produtos:");
		
		JLabel lblTipoDePagamento = new JLabel("Tipo de Pagamento:");
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(table, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
						.addComponent(hBox_Cliente, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
						.addComponent(hBox_Produto, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblTipoDePagamento, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTotalAReceber, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProdutos))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(hBox_Cliente, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(hBox_Produto, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblProdutos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDePagamento)
						.addComponent(lblTotalAReceber))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(129))
		);
		getContentPane().setLayout(groupLayout);

	}
}
