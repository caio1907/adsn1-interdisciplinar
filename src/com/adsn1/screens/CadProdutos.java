package com.adsn1.screens;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.adsn1.controllers.ProdutoController;
import com.adsn1.types.Produto;
import com.adsn1.utils.Utils;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CadProdutos extends JInternalFrame {
	private static CadProdutos screen = null;
	private ProdutoController produtoController;
	private ArrayList<Produto> produtos;
	private Produto produto;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtCodDeBarras;
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JTextField txtQtdEstoque;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JTextField txtFiltro;

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
		this.produtoController = new ProdutoController();
		loadData();

		txtCodDeBarras = new JTextField();
		txtCodDeBarras.setEnabled(false);

		txtDescricao = new JTextField();
		txtDescricao.setEnabled(false);

		txtValor = new JTextField();
		txtValor.setEnabled(false);

		txtQtdEstoque = new JTextField();
		txtQtdEstoque.setEnabled(false);

		JLabel lblCodigoDeBarras = new JLabel("Código de barras");

		JLabel lblDescricao = new JLabel("Descrição");

		JLabel lblValor = new JLabel("Valor");

		JLabel lblQtdEstoque = new JLabel("Em estoque");

		txtFiltro = new JTextField();
		txtFiltro.setColumns(10);

		JLabel lblFiltro = new JLabel("Filtrar");
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "C\u00F3d. de Barras", "Descri\u00E7\u00E3o", "Valor", "Qtd. Estoque"
				}
				);
		for (Produto produto : this.produtos) {
			model.addRow(new Object [] {
					produto.getId(),
					produto.getCodigo_de_barras(),
					produto.getDescricao(),
					Utils.formatMoney(produto.getValor()),
					produto.getQtd_estoque()
			});
		}

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarEdicao(true);
				limparCampos();
			}
		});
		btnNovo.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTxtCodDeBarras().setText(produto.getCodigo_de_barras());;
				getTxtDescricao().setText(produto.getDescricao());
				getTxtValor().setText(produto.getValor()+"");;
				getTxtQtdEstoque().setText(produto.getQtd_estoque()+"");;
				habilitarEdicao(true);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				if (!validarCampos()) {
					JOptionPane.showMessageDialog(rootPane, "Preencha todos os campos");
					return;
				}
				double valor;
				int qtdEstoque;
				try {
					valor = Double.parseDouble(getTxtValor().getText());
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(rootPane, "Campo valor inválido.");
					getTxtValor().requestFocus();
					return;
				}
				try {
					qtdEstoque = Integer.parseInt(getTxtQtdEstoque().getText());
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(rootPane, "Quantidade no estoque inválida");
					getTxtQtdEstoque().requestFocus();
					return;
				}
				if (produto == null) {
					produto = new Produto();
				}
				produto.setCodigo_de_barras(getTxtCodDeBarras().getText());
				produto.setDescricao(getTxtDescricao().getText());
				produto.setValor(valor);
				produto.setQtd_estoque(qtdEstoque);
				
				Produto produtoSalvo = produtoController.save(produto);
				if (produto == null) {
					JOptionPane.showMessageDialog(rootPane, "Erro ao salvar produto.\nEntre em contato com o administrador do sitema.");
					return;
				}
				Long id = produto.getId();
				if (id != null && id > 0) {
					int selectedRow = getTable().getSelectedRow();
					model.setValueAt(produto.getId(), selectedRow, 0);
					model.setValueAt(produtoSalvo.getCodigo_de_barras(), selectedRow, 1);
					model.setValueAt(produtoSalvo.getDescricao(), selectedRow, 2);
					model.setValueAt(Utils.formatMoney(produtoSalvo.getValor()), selectedRow, 3);
					model.setValueAt(produtoSalvo.getQtd_estoque(), selectedRow, 4);
				} else {
					model.addRow(new Object [] {
							produtoSalvo.getId(),
							produtoSalvo.getCodigo_de_barras(),
							produtoSalvo.getDescricao(),
							Utils.formatMoney(produtoSalvo.getValor()),
							produtoSalvo.getQtd_estoque()
					});
				}
				getTable().setModel(model);
				habilitarEdicao(false);
				limparCampos();
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				habilitarEdicao(false);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblCodigoDeBarras, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCodDeBarras, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblValor)
								.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtQtdEstoque, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQtdEstoque, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNovo)
							.addGap(18)
							.addComponent(btnEditar)
							.addGap(18)
							.addComponent(btnSalvar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar)))
					.addGap(0))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCodigoDeBarras, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCodDeBarras, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
							.addGap(11)
							.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblQtdEstoque, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addGap(26))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblValor, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtQtdEstoque, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
					.addContainerGap())
		);
		
				// Disabilita edição da célula
				table = new JTable() {
					private static final long serialVersionUID = 1L;
		
					public boolean isCellEditable(int row, int column) {                
						return false;
					};
				};
				scrollPane.setViewportView(table);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int selectedRow = getTable().getSelectedRow();
						if (selectedRow != -1) {
							produto = produtos.get(selectedRow);
							getBtnEditar().setEnabled(true);
						}
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setModel(model);
		getContentPane().setLayout(groupLayout);
	}

	private void loadData() {
		ArrayList<Produto> produtos = this.produtoController.getAll();
		this.produtos = produtos;
	}

	private void limparCampos() {
		getTable().clearSelection();
		getBtnEditar().setEnabled(false);
		getTxtCodDeBarras().setText("");
		getTxtDescricao().setText("");
		getTxtValor().setText("");
		getTxtQtdEstoque().setText("");
	}

	private void habilitarEdicao(boolean habilitar) {
		getTable().setEnabled(!habilitar);
		getBtnNovo().setEnabled(!habilitar);
		getBtnSalvar().setEnabled(habilitar);
		getBtnCancelar().setEnabled(habilitar);
		getTxtFiltro().setEnabled(!habilitar);
		getTxtCodDeBarras().setEnabled(habilitar);
		getTxtDescricao().setEnabled(habilitar);
		getTxtValor().setEnabled(habilitar);
		getTxtQtdEstoque().setEnabled(habilitar);
		if (habilitar) {
			getTxtCodDeBarras().requestFocus();
		} else {
			getBtnNovo().requestFocus();
		}
	}
	
	private boolean validarCampos() {
		JTextField[] fields = {
				getTxtCodDeBarras(),
				getTxtDescricao(),
				getTxtValor(),
				getTxtQtdEstoque()
		};
		for (JTextField field : fields) {
			if (field.getText().isEmpty()) {
				field.requestFocus();
				return false;
			}
		}
		return true;
	}

	protected JTable getTable() {
		return table;
	}
	protected JButton getBtnNovo() {
		return btnNovo;
	}
	protected JButton getBtnEditar() {
		return btnEditar;
	}
	protected JButton getBtnSalvar() {
		return btnSalvar;
	}
	protected JButton getBtnCancelar() {
		return btnCancelar;
	}
	protected JTextField getTxtCodDeBarras() {
		return txtCodDeBarras;
	}
	protected JTextField getTxtDescricao() {
		return txtDescricao;
	}
	protected JTextField getTxtValor() {
		return txtValor;
	}
	protected JTextField getTxtQtdEstoque() {
		return txtQtdEstoque;
	}
	protected JTextField getTxtFiltro() {
		return txtFiltro;
	}
}
