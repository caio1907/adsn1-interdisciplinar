package com.adsn1.screens;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.adsn1.controllers.TiposDePagamentoController;
import com.adsn1.types.TiposDePagamento;
import com.adsn1.utils.Utils;

import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;


public class CadTipoDePagamento extends JInternalFrame {
	private static CadTipoDePagamento screen = null;
	private TiposDePagamentoController tiposDePagamentoController;
	private ArrayList<TiposDePagamento> tiposDePagamento;
	private TiposDePagamento tipoDePagamento;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JFormattedTextField txtTaxa;
	private JTextField txtDescricao;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JTextField txtFiltro;
	private JScrollPane scrollPane;

	public static CadTipoDePagamento getScreen() {
		if (screen == null) {
			screen = new CadTipoDePagamento();
		}
		return screen;
	}

	/**
	 * Create the frame.
	 */
	public CadTipoDePagamento() {
		setTitle("Cadastro Tipos de Pagemento");
		setClosable(true);
		setBounds(100, 100, 700, 600);
		this.tiposDePagamentoController = new TiposDePagamentoController();
		loadData();

		btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarEdicao(true);
				limparCampos();
			}
		});
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"C\u00F3digo", "Descri\u00E7\u00E3o", "Taxa"
				}
				);
		for (TiposDePagamento tipoDePagamento : this.tiposDePagamento) {
			model.addRow(new Object [] {
					tipoDePagamento.getId(),
					tipoDePagamento.getDescricao(),
					Utils.formatToDecimal(tipoDePagamento.getTaxa()),
			});
		}
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTxtDescricao().setText(tipoDePagamento.getDescricao());
				getTxtTaxa().setText(tipoDePagamento.getTaxa()+"");
				habilitarEdicao(true);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setFont(new Font("Dialog", Font.BOLD, 12));

		txtTaxa = new JFormattedTextField();
		txtTaxa.setToolTipText("0.00");
		txtTaxa.setEnabled(false);
		txtTaxa.setColumns(10);

		JLabel lblTaxa = new JLabel("Taxa");
		lblTaxa.setFont(new Font("Dialog", Font.BOLD, 12));

		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setFont(new Font("Dialog", Font.BOLD, 12));

		txtDescricao = new JTextField();
		txtDescricao.setEnabled(false);
		txtDescricao.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				double taxa;
				try {
					taxa = Double.parseDouble(getTxtTaxa().getText());
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(rootPane, "Campo taxa inválido.");
					getTxtTaxa().requestFocus();
					return;
				}
				String descricao = getTxtDescricao().getText();
				if (descricao.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Preencha o campo descrição.");
					getTxtDescricao().requestFocus();
					return;
				}
				if (tipoDePagamento == null) {
					tipoDePagamento = new TiposDePagamento();
				}
				tipoDePagamento.setDescricao(descricao);
				tipoDePagamento.setTaxa(taxa);

				TiposDePagamento tipoDePagamentoSalvo = tiposDePagamentoController.save(tipoDePagamento);
				if (tipoDePagamentoSalvo == null) {
					JOptionPane.showMessageDialog(rootPane, "Erro ao salvar tipo de pagamento.\nEntre em contato com o administrador do sitema.");
					return;
				}
				Long id = tipoDePagamento.getId();
				if (id != null && id > 0) {
					model.setValueAt(
							tipoDePagamentoSalvo.getDescricao(),
							table.getSelectedRow(),
							1);
					model.setValueAt(
							Utils.formatToDecimal(tipoDePagamentoSalvo.getTaxa()),
							table.getSelectedRow(),
							2);
				} else {
					model.addRow(new Object [] {
							tipoDePagamentoSalvo.getId(),
							tipoDePagamentoSalvo.getDescricao(),
							Utils.formatToDecimal(tipoDePagamentoSalvo.getTaxa()),
					});
				}
				getTable().setModel(model);
				habilitarEdicao(false);
				limparCampos();
			}
		});


		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				habilitarEdicao(false);
			}
		});
		btnCancelar.setEnabled(false);

		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);

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

		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(12)
										.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
										.addGap(12)
										.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
										.addGap(12)
										.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtTaxa, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblTaxa, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtFiltro)
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(1)
														.addComponent(lblBusque, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))))
						.addGap(0))
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(12)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblTaxa, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtTaxa, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
										.addGap(3))
								.addComponent(lblDescricao, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(lblBusque, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addGap(3)
						.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
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
					tipoDePagamento = tiposDePagamento.get(selectedRow);
					getBtnEditar().setEnabled(true);
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		table.setRowSorter(sorter);
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

	private void loadData() {
		ArrayList<TiposDePagamento> tiposDePagamento = this.tiposDePagamentoController.getAll();
		this.tiposDePagamento = tiposDePagamento;
	}

	private void limparCampos() {
		getTable().clearSelection();
		getBtnEditar().setEnabled(false);
		getTxtDescricao().setText("");
		getTxtTaxa().setText("");
	}

	private void habilitarEdicao(boolean habilitar) {
		getTable().setEnabled(!habilitar);
		getBtnNovo().setEnabled(!habilitar);
		getBtnSalvar().setEnabled(habilitar);
		getBtnCancelar().setEnabled(habilitar);
		getTxtFiltro().setEnabled(!habilitar);
		getTxtDescricao().setEnabled(habilitar);
		getTxtTaxa().setEnabled(habilitar);
		if (habilitar) {
			getTxtDescricao().requestFocus();
		} else {
			getBtnNovo().requestFocus();
		}
	}


	protected JTextField getTxtDescricao() {
		return txtDescricao;
	}
	protected JTextField getTxtTaxa() {
		return txtTaxa;
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
	protected JTextField getTxtFiltro() {
		return txtFiltro;
	}
	protected JTable getTable() {
		return table;
	}
}
