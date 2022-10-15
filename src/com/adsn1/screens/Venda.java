package com.adsn1.screens;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.adsn1.controllers.ClienteController;
import com.adsn1.controllers.ProdutoController;
import com.adsn1.controllers.TiposDePagamentoController;
import com.adsn1.controllers.VendaController;
import com.adsn1.types.Cliente;
import com.adsn1.types.Produto;
import com.adsn1.types.TiposDePagamento;
import com.adsn1.types.VendaItem;
import com.adsn1.utils.Utils;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Venda extends JInternalFrame {
	private static Venda screen = null;
	private static final long serialVersionUID = 1L;

	private ClienteController clienteController;
	private ProdutoController produtoController;
	private TiposDePagamentoController tiposDePagamentoController;
	private VendaController vendaController;
	private JTable table;
	private JTextField txtBuscarProduto;
	private JTextField txtQuantidade;
	private JTextField txtTotalAReceber;
	private JComboBox<String> cbTipoDePagamento;
	private JComboBox<String> cbClientes;
	private DefaultTableModel modelTable;

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
		this.clienteController = new ClienteController();
		this.produtoController = new ProdutoController();
		this.tiposDePagamentoController = new TiposDePagamentoController();
		this.vendaController = new VendaController();

		JButton btnNewButton = new JButton("Finalizar pedido");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
					com.adsn1.types.Venda venda = new com.adsn1.types.Venda();
					ArrayList<VendaItem> vendaItens = new ArrayList<VendaItem>();
					int cliente = Integer.parseInt(getCbClientes().getSelectedItem().toString().split("-")[0]);
					int tipoDePagamento = Integer.parseInt(getCbTipoDePagamento().getSelectedItem().toString().split("-")[0]);
					double total = 0;
					VendaItem item;
					Produto produto;
					for(int i = 0; i < getTable().getRowCount(); i++) {
						String codBarras = getTable().getValueAt(i, 0).toString();
						int quantidade = Integer.parseInt(getTable().getValueAt(i, 2).toString());
						item = new VendaItem();
						produto = produtoController.getByCodigoDeBarras(codBarras);
						item.setProduto(produto.getId());
						item.setQuantidade(quantidade);
						item.setValor_unitario(produto.getValor());
						total += item.getValor_unitario() * item.getQuantidade();
						vendaItens.add(item);
					}
					venda.setCliente(cliente);
					venda.setTipo_pagamento(tipoDePagamento);
					venda.setTotal(total);
					venda.setVendaItem(vendaItens);
					vendaController.save(venda);
					limpaCampos();
					JOptionPane.showMessageDialog(rootPane, "Venda finalizada com sucesso");
					getCbClientes().requestFocus();
				}
			}
		});

		ArrayList<TiposDePagamento> tiposDePagamento = this.tiposDePagamentoController.getAll();
		ArrayList<String> tipos = new ArrayList<String>();
		for (int i = 0; i < tiposDePagamento.size(); i++) {
			TiposDePagamento tipoDePagamento = tiposDePagamento.get(i);
			tipos.add(tipoDePagamento.getId()+"-"+tipoDePagamento.getDescricao());
		}
		DefaultComboBoxModel<String> modelCbTipoDePagamento = new DefaultComboBoxModel<String>(tipos.toArray(new String[0]));
		cbTipoDePagamento = new JComboBox<String>();
		cbTipoDePagamento.setModel(modelCbTipoDePagamento);

		JLabel lblTotalAReceber = new JLabel("Total a receber:");

		JLabel lblProdutos = new JLabel("Produtos:");

		JLabel lblTipoDePagamento = new JLabel("Tipo de Pagamento:");

		txtTotalAReceber = new JTextField();
		txtTotalAReceber.setEditable(false);

		ArrayList<Cliente> clientes = this.clienteController.getAll();
		ArrayList<String> cli = new ArrayList<String>();
		cli.add("Selecione um cliente");
		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = clientes.get(i);
			cli.add(cliente.getId()+"-"+cliente.getNome()+"-"+cliente.getTelefone());
		}
		DefaultComboBoxModel<String> modelCbCliente = new DefaultComboBoxModel<String>(cli.toArray(new String[0]));
		cbClientes = new JComboBox<String>();
		cbClientes.setModel(modelCbCliente);

		JLabel lblFiltroClientes = new JLabel("Clientes");
		lblFiltroClientes.setAlignmentX(1.0f);

		JLabel lblBuscarProduto = new JLabel("Buscar Produto (Cód. de barras)");

		txtQuantidade = new JTextField();
		getTxtQuantidade().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && !txtBuscarProduto.getText().isEmpty() && !getTxtQuantidade().getText().isEmpty()) {
					addProduto();
				}
			}
		});
		getTxtQuantidade().setText("1");

		modelTable = new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"C\u00D3DIGO DE BARRAS", "DESCRI\u00C7\u00C3O", "QUANT.", "VALOR", "TOTAL"
				}
				);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					getModelTable().removeRow(table.getSelectedRow());
					atualizaTotalAReceber();
				}
			}
		});
		table.setModel(getModelTable());
		table.setToolTipText("produtos");
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		txtBuscarProduto = new JTextField();
		txtBuscarProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && !txtBuscarProduto.getText().isEmpty() && !getTxtQuantidade().getText().isEmpty()) {
					addProduto();
				}
			}
		});

		JLabel lblQuantidadeProduto = new JLabel("Quantidade");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cbClientes, 0, 566, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
						.addComponent(lblBuscarProduto)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(cbTipoDePagamento, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblTipoDePagamento, Alignment.LEADING))
							.addPreferredGap(ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtTotalAReceber)
								.addComponent(lblTotalAReceber, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(txtBuscarProduto, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblQuantidadeProduto)
								.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblFiltroClientes)
						.addComponent(lblProdutos))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFiltroClientes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbClientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBuscarProduto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtBuscarProduto, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblQuantidadeProduto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(lblProdutos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTipoDePagamento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbTipoDePagamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTotalAReceber)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTotalAReceber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private void addProduto() {
		String codBarras = getTxtBuscarProduto().getText();
		String quantidade = getTxtQuantidade().getText();
		try {
			Integer.parseInt(quantidade);
		} catch (NumberFormatException exception) {
			JOptionPane.showMessageDialog(rootPane, "Quantidade inválida");
			getTxtQuantidade().requestFocus();
			return;
		}
		Produto produto = produtoController.getByCodigoDeBarras(codBarras);
		if (produto == null) {
			JOptionPane.showMessageDialog(rootPane, "Produto não encontrado");
			return;
		}
		int rowExists = produtoJaExisteNaTabela(produto.getCodigo_de_barras());
		if (rowExists != -1) {
			getModelTable().setValueAt(getTxtQuantidade().getText(), rowExists, 2);
			getModelTable().setValueAt(Utils.formatMoney(produto.getValor()), rowExists, 3);
			getModelTable().setValueAt(Utils.formatMoney(Integer.parseInt(quantidade) * produto.getValor()), rowExists, 4);
		} else {
			getModelTable().addRow(new Object[] {
					produto.getCodigo_de_barras(),
					produto.getDescricao(),
					getTxtQuantidade().getText(),
					Utils.formatMoney(produto.getValor()),
					Utils.formatMoney(Integer.parseInt(quantidade) * produto.getValor())
			});
		}
		table.setModel(getModelTable());
		getTxtQuantidade().setText("1");
		getTxtBuscarProduto().setText("");
		getTxtBuscarProduto().requestFocus();
		atualizaTotalAReceber();
	}
	
	private void atualizaTotalAReceber() {
		double total = 0;
		int linhas = getTable().getRowCount();
		for (int i = 0; i < linhas; i++) {
			String valor = getTable().getValueAt(i, 4).toString();
			valor = valor.replaceAll("\\D", "");
			valor = valor.substring(0, valor.length()-2) + "." + valor.substring(valor.length()-2, valor.length());
			if (valor.equalsIgnoreCase(valor)) {
				total += Double.parseDouble(valor);
			}
		}
		getTxtTotalAReceber().setText(Utils.formatMoney(total));
	}
	
	private int produtoJaExisteNaTabela(String codBarras) {
		int linhas = getTable().getRowCount();
		for (int i = 0; i < linhas; i++) {
			String valor = getTable().getValueAt(i, 0).toString();
			if (valor.equalsIgnoreCase(valor)) {
				return i;
			}
		}
		return -1;
	}
	
	private boolean validarCampos() {
		if (getTable().getRowCount() <= 0) {
			JOptionPane.showMessageDialog(rootPane, "Insira um produto");
			getTxtBuscarProduto().requestFocus();
			return false;
		}
		if (getCbClientes().getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(rootPane, "Selecione um cliente");
			getCbClientes().requestFocus();
			return false;
		}
		return true;
	}
	
	private void limpaCampos() {
		getCbClientes().setSelectedIndex(0);
		modelTable = new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"C\u00D3DIGO DE BARRAS", "DESCRI\u00C7\u00C3O", "QUANT.", "VALOR", "TOTAL"
				}
				);
		getTable().setModel(getModelTable());
		getCbTipoDePagamento().setSelectedIndex(0);
		atualizaTotalAReceber();
	}

	protected JTextField getTxtBuscarProduto() {
		return txtBuscarProduto;
	}
	protected JTextField getTxtQuantidade() {
		return txtQuantidade;
	}
	protected JTable getTable() {
		return table;
	}
	protected JTextField getTxtTotalAReceber() {
		return txtTotalAReceber;
	}
	protected JComboBox<String> getCbTipoDePagamento() {
		return cbTipoDePagamento;
	}
	protected JComboBox<String> getCbClientes() {
		return cbClientes;
	}
	protected DefaultTableModel getModelTable() {
		return modelTable;
	}
}
