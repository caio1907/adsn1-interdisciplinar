package com.adsn1.screens;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class CadTipoDePagamento extends JInternalFrame {
	private static CadTipoDePagamento screen = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JFormattedTextField doubleTaxa;
	private JTextField txtDescricao;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JTextField txtFiltro;
	
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
		setTitle("Cadastro");
		setClosable(true);
		setBounds(100, 100, 700, 600);
		
		btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getDoubleTaxa().setEnabled(true);
				getTxtDescricao().setEnabled(true);
				getBtnCancelar().setEnabled(true);
				getBtnSalvar().setEnabled(true);
				getBtnNovo().setEnabled(false);
				getTxtFiltro().setEnabled(false);
				
			}
		});
		
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
				{"Pix", "0.00"},
				{"Dinheiro", "0.00"},
				{"Credito - Mastercard", "1.35"},
				{"Debito - Mastercard", "0.80"},
				{"Credito - Visa", "1.24"},
				{"Debito - Visa", "0.77"},
			},
			new String[] {
				"Descri\u00E7\u00E3o", "Taxa"
			}
		);
		table.setModel(model);
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setFont(new Font("Dialog", Font.BOLD, 12));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExcluir.setEnabled(false);
		
		doubleTaxa = new JFormattedTextField();
		doubleTaxa.setToolTipText("0,00%");
		doubleTaxa.setEnabled(false);
		doubleTaxa.setColumns(10);
		
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
		
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getBtnNovo().setEnabled(true);
				getBtnCancelar().setEnabled(false);
				getBtnSalvar().setEnabled(false);
				getTxtDescricao().setEnabled(false);
				getDoubleTaxa().setEnabled(false);
				getTxtFiltro().setEnabled(true);
			}
		});
		btnCancelar.setEnabled(false);
		
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
							.addGap(12)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtFiltro)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(lblBusque, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
							.addGap(66)
							.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(doubleTaxa, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTaxa, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTaxa, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(doubleTaxa, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(3))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBusque, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
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
	

	protected JComponent getTxtDescricao() {
		// TODO Auto-generated method stub
		return txtDescricao;
	}

	protected JComponent getDoubleTaxa() {
		// TODO Auto-generated method stub
		return doubleTaxa;
	}

	protected JButton getBtnNovo() {
		return btnNovo;
	}
	protected JButton getBtnEditar() {
		return btnEditar;
	}
	protected JButton getBtnExcluir() {
		return btnExcluir;
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
}
