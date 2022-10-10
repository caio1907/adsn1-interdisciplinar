package com.adsn1.screens;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;


public class RelatorioVendas extends JInternalFrame {
	private static RelatorioVendas screen = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtDataInicial;
	private JTextField txtFiltro;
	private JLabel lblDataFinal;
	private JTextField txtDataFinal;
	private JLabel lblProduto;
	private JTextField txtR;
	
	public static RelatorioVendas getScreen() {
		if (screen == null) {
			screen = new RelatorioVendas();
		}
		return screen;
	}

	/**
	 * Create the frame.
	 */
	public RelatorioVendas() {
		setTitle("Relatório de vendas");
		setClosable(true);
		setBounds(100, 100, 700, 600);
		
		// Disabilita edição da célula
		table = new JTable() {
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
                return false;
	        };
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = new DefaultTableModel(
			new Object[][] {
				{"01/01/2022 12:44:12", "Camisa Polo PP - Branca", "8 - João", "2 - Dinheiro", "R$49.90", "0.00%", "R$49.90"},
				{"01/01/2022 13:11:05", "Bermuda Jeans - 44", "5 - Maria", "3 - Mastercard Crédito", "R$99.90", "1.15%", "R$98.75"},
				{"01/01/2022 13:11:05", "Bermuda Jeans - 44", "5 - Maria", "3 - Mastercard Crédito", "R$99.90", "1.15%", "R$98.75"},
			},
			new String[] {
				"Data/Hora", "Produto", "Vendedor", "Tipo de Pagamento", "Valor", "Taxa", "Valor à Receber"
			}
		);
		table.setModel(model);
		
		JLabel lblDescricao = new JLabel("Data Inical");
		lblDescricao.setFont(new Font("Dialog", Font.BOLD, 12));
		
		txtDataInicial = new JTextField();
		txtDataInicial.setColumns(10);
		
		JLabel lblFiltro = new JLabel("");
		
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		
		txtFiltro = new JTextField();
		txtFiltro.setToolTipText("Pressione Enter para filtrar");
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = txtFiltro.getText();
				if (!filtro.isEmpty()) {
					sorter.setRowFilter(RowFilter.regexFilter(filtro));
				} else {
					sorter.setRowFilter(null);
				}
			}
		});
		txtFiltro.setColumns(10);
		
		JLabel lblBusque = new JLabel("Filtro");
		lblBusque.setFont(new Font("Dialog", Font.BOLD, 12));
		
		lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setFont(new Font("Dialog", Font.BOLD, 12));
		
		txtDataFinal = new JTextField();
		txtDataFinal.setColumns(10);
		
		lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JComboBox<String> cbProduto = new JComboBox<String>();
		cbProduto.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecione (opcional)", "Camisa"}));
		
		JComboBox<String> cbVendedor = new JComboBox<String>();
		cbVendedor.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecione (Opcional)"}));
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setFont(new Font("Dialog", Font.BOLD, 12));
		
		txtR = new JTextField();
		txtR.setHorizontalAlignment(SwingConstants.RIGHT);
		txtR.setText("R$ 247.40");
		txtR.setToolTipText("");
		txtR.setColumns(10);
		
		JLabel lblTotalReceber = new JLabel("Total à Receber:");
		lblTotalReceber.setFont(new Font("Dialog", Font.BOLD, 12));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtDataInicial)
									.addComponent(txtFiltro)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(1)
										.addComponent(lblBusque, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(66)
									.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDataFinal, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtDataFinal, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblProduto, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
											.addGap(75))
										.addComponent(cbProduto, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblVendedor, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
											.addGap(75))
										.addComponent(cbVendedor, 0, 171, Short.MAX_VALUE))))
							.addGap(22))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTotalReceber, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtR, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProduto)
							.addGap(31))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDataInicial, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDataFinal)
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDataFinal, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblVendedor)
							.addGap(6)
							.addComponent(cbVendedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBusque, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTotalReceber, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtR, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = txtFiltro.getText();
				if (!filtro.isEmpty()) {
					sorter.setRowFilter(RowFilter.regexFilter(filtro));
				} else {
					sorter.setRowFilter(null);
				}
			}
		});
	}

	protected JTextField getTxtFiltro() {
		return txtFiltro;
	}
}
