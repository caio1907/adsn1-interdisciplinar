package com.adsn1.screens;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.adsn1.controllers.ProdutoController;
import com.adsn1.controllers.UsuarioController;
import com.adsn1.controllers.VendaController;
import com.adsn1.types.Produto;
import com.adsn1.types.Usuario;
import com.adsn1.types.VendaItem;
import com.adsn1.utils.Utils;

import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RelatorioVendas extends JInternalFrame {
	private static RelatorioVendas screen = null;
	private VendaController vendaController;
	private ProdutoController produtoController;
	private UsuarioController usuarioController;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JFormattedTextField txtDataInicial;
	private JTextField txtFiltro;
	private JLabel lblDataFinal;
	private JFormattedTextField txtDataFinal;
	private JLabel lblProduto;
	private JTextField txtReceber;
	private DefaultTableModel model;
	private JComboBox<String> cbProduto;
	private JComboBox<String> cbVendedor;

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
		setBounds(100, 100, 800, 600);

		this.vendaController = new VendaController();
		this.produtoController = new ProdutoController();
		this.usuarioController = new UsuarioController();

		JLabel lblDescricao = new JLabel("Data Inical");
		lblDescricao.setFont(new Font("Dialog", Font.BOLD, 12));

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		txtDataInicial = new JFormattedTextField(df);
		txtDataInicial.setText(df.format(new Date()));
		txtDataInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validarData(e);
			}
		});

		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setFont(new Font("Dialog", Font.BOLD, 12));

		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Data/Hora", "Produto", "Vendedor", "Tipo de Pagamento", "Valor", "Taxa", "Valor à Receber"
				}
				);

		txtFiltro = new JTextField();
		txtFiltro.setToolTipText("Pressione Enter para filtrar");

		lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setFont(new Font("Dialog", Font.BOLD, 12));

		txtDataFinal = new JFormattedTextField(df);
		txtDataFinal.setText(df.format(new Date()));
		txtDataFinal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validarData(e);
			}
		});

		lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Dialog", Font.BOLD, 12));

		ArrayList<Produto> produtos = this.produtoController.getAll();
		ArrayList<String> prods = new ArrayList<String>();
		prods.add("Selecione (Opcional)");
		for (int i = 0; i < produtos.size(); i++) {
			Produto produto = produtos.get(i);
			prods.add(produto.getId()+"-"+produto.getDescricao());
		}
		DefaultComboBoxModel<String> modelCbProduto = new DefaultComboBoxModel<String>(prods.toArray(new String[0]));
		cbProduto = new JComboBox<String>();
		cbProduto.setModel(modelCbProduto);

		ArrayList<Usuario> vendedores = this.usuarioController.getAll();
		ArrayList<String> vends = new ArrayList<String>();
		vends.add("Selecione (Opcional)");
		for(int i = 0; i < vendedores.size(); i++) {
			Usuario vendedor = vendedores.get(i);
			vends.add(vendedor.getId()+"-"+vendedor.getNome());
		}
		DefaultComboBoxModel<String> modelCbVendedor = new DefaultComboBoxModel<String>(vends.toArray(new String[0]));
		cbVendedor = new JComboBox<String>();
		cbVendedor.setModel(modelCbVendedor);

		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setFont(new Font("Dialog", Font.BOLD, 12));

		txtReceber = new JTextField();
		txtReceber.setHorizontalAlignment(SwingConstants.RIGHT);
		txtReceber.setText("R$ 247.40");

		JLabel lblTotalReceber = new JLabel("Total à Receber:");
		lblTotalReceber.setFont(new Font("Dialog", Font.BOLD, 12));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1155, Short.MAX_VALUE)
										.addGap(12))
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtDataInicial, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblDataFinal, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtDataFinal, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblProduto, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
														.addGap(75))
												.addComponent(cbProduto, 0, 198, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblVendedor, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
														.addGap(75))
												.addComponent(cbVendedor, 0, 199, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnBuscar)
										.addContainerGap(359, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblTotalReceber, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
														.addGap(1))
												.addComponent(txtReceber, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txtFiltro, Alignment.LEADING)
												.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
														.addGap(1)
														.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(639, Short.MAX_VALUE))))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtDataInicial, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblProduto)
												.addGap(31))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblDataFinal)
												.addGap(6)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(txtDataFinal, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
														.addComponent(cbProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblVendedor)
												.addGap(6)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(cbVendedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(btnBuscar)))))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(11)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTotalReceber, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtReceber, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(24))
				);

		// Disabilita edição da célula
		table = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;
			};
		};
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Data/Hora", "Cód. Venda", "Produto", "Vendedor", "Tipo de Pagamento", "Valor", "Taxa", "Valor à Receber"
				}
				);
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);

		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = txtFiltro.getText();
				if (!filtro.isEmpty()) {
					sorter.setRowFilter(RowFilter.regexFilter(filtro));
				} else {
					sorter.setRowFilter(null);
				}
				atualizaTotalAReceber();
			}
		});
		buscar();
		getContentPane().setLayout(groupLayout);
	}

	private void atualizaTotalAReceber() {
		double total = 0;
		int linhas = getTable().getRowCount();
		for (int i = 0; i < linhas; i++) {
			String valor = getTable().getValueAt(i, 6).toString();
			valor = valor.replaceAll("\\D", "");
			valor = valor.substring(0, valor.length()-2) + "." + valor.substring(valor.length()-2, valor.length());
			if (valor.equalsIgnoreCase(valor)) {
				total += Double.parseDouble(valor);
			}
		}
		getTxtReceber().setText(Utils.formatMoney(total));
	}

	private void validarData(KeyEvent e) {
		char c = e.getKeyChar();
		if (!((c >= '0') && (c <= '9') ||
				(c == KeyEvent.VK_BACK_SPACE) ||
				(c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
		{
			JOptionPane.showMessageDialog(null, "Preencha um data válida.\nEx: 31/12/2000");
			e.consume();
		}
	}

	private void buscar() {
		for(int i = getModel().getRowCount()-1; i >= 0; i--) {
			getModel().removeRow(i);
		}
		
		Date dataInicial = Utils.formatStringToDate(getTxtDataInicial().getText());
		Date dataFinal = Utils.formatStringToDate(getTxtDataFinal().getText());
		if (dataInicial == null) {
			JOptionPane.showMessageDialog(rootPane, "Data inicial inválida");
			getTxtDataInicial().requestFocus();
			return;
		}
		if (dataFinal == null) {
			JOptionPane.showMessageDialog(rootPane, "Data final inválida");
			getTxtDataFinal().requestFocus();
			return;
		}
		String produto = getCbProduto().getSelectedItem().toString();
		produto = produto.contains("-") ? produto.split("-")[0] : "0";
		String vendedor = getCbVendedor().getSelectedItem().toString();
		vendedor = vendedor.contains("-") ? vendedor.split("-")[0] : "0";
		ArrayList<com.adsn1.types.Venda> vendas = vendaController.getAll(
				dataInicial,
				dataFinal,
				Integer.parseInt(produto),
				Integer.parseInt(vendedor)
				);
		for(int i = 0; i < vendas.size(); i++) {
			com.adsn1.types.Venda venda = vendas.get(i);
			for (int j = 0; j < venda.getVendaItem().size(); j++) {
				VendaItem vendaItem = venda.getVendaItem().get(j);
				model.addRow(new Object[] {
						Utils.formatDateToString(venda.getData_criacao(), true),
						venda.getId(),
						vendaItem.getProduto_descricao(),
						venda.getVendedor_nome(),
						venda.getTipo_pagamento_descricao(),
						Utils.formatMoney(venda.getTotal()),
						Utils.formatToDecimal(venda.getTipo_pagamento_taxa()),
						Utils.formatMoney(venda.getTotal() - ((venda.getTotal() / 100) * venda.getTipo_pagamento_taxa()))
				});
			}
		}
		getTable().setModel(model);
	}

	protected JTextField getTxtFiltro() {
		return txtFiltro;
	}
	protected JTextField getTxtReceber() {
		return txtReceber;
	}
	protected JTable getTable() {
		return table;
	}
	protected DefaultTableModel getModel() {
		return model;
	}
	protected JComboBox<String> getCbProduto() {
		return cbProduto;
	}
	protected JComboBox<String> getCbVendedor() {
		return cbVendedor;
	}
	protected JFormattedTextField getTxtDataInicial() {
		return txtDataInicial;
	}
	protected JFormattedTextField getTxtDataFinal() {
		return txtDataFinal;
	}
}
