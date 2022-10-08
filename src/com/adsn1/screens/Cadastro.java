package com.adsn1.screens;

import com.adsn1.types.Cliente;
import com.adsn1.utils.Database;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;


public class Cadastro extends JInternalFrame {
	private Database database;
	
	private static Cadastro screen = null;

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
	
	public static Cadastro getScreen() {
		if (screen == null) {
			screen = new Cadastro();
		}
		return screen;
	}

	/**
	 * Create the frame.
	 */
	public Cadastro() {
		setTitle("Cadastro");
		setClosable(true);
		setBounds(100, 100, 700, 600);
		getContentPane().setLayout(null);
		
		this.database = new Database();
		
		btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
		btnNovo.setBounds(12, 12, 82, 25);
		getContentPane().add(btnNovo);
		
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
			},
			new String[] {
				"Taxa","Descrição"
			}
		);
		
		table.setModel(model);
		table.setBounds(12, 215, 666, 341);
		getContentPane().add(table);
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditar.setBounds(106, 12, 88, 25);
		getContentPane().add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(206, 12, 88, 25);
		getContentPane().add(btnExcluir);
		
		doubleTaxa = new JFormattedTextField();
		doubleTaxa.setToolTipText("0,00%");
		doubleTaxa.setEnabled(false);
		doubleTaxa.setColumns(10);
		doubleTaxa.setBounds(341, 90, 70, 25);
		getContentPane().add(doubleTaxa);
		
		JLabel lblTaxa = new JLabel("TAXA:");
		lblTaxa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTaxa.setBounds(341, 66, 70, 15);
		getContentPane().add(lblTaxa);
		
		JLabel lblDescricao = new JLabel("DESCRIÇÃO:");
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescricao.setBounds(53, 66, 96, 15);
		getContentPane().add(lblDescricao);
		
		txtDescricao = new JTextField();
		txtDescricao.setEnabled(false);
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(53, 90, 193, 25);
		getContentPane().add(txtDescricao);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvar.setEnabled(false);
		//JOptionPane.showMessageDialog(contentPane, "SALVO!");
		btnSalvar.setBounds(309, 12, 82, 25);
		getContentPane().add(btnSalvar);
		
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
		btnCancelar.setBounds(403, 12, 96, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblFiltro = new JLabel("");
		lblFiltro.setBounds(12, 99, 70, 15);
		getContentPane().add(lblFiltro);
		
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
		txtFiltro.setBounds(144, 159, 337, 25);
		getContentPane().add(txtFiltro);
		
		JLabel lblBusque = new JLabel("BUSQUE AQUI!");
		lblBusque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBusque.setBounds(145, 142, 138, 14);
		getContentPane().add(lblBusque);
		
		JFormattedTextField frmtdtxtfldDescrio = new JFormattedTextField();
		frmtdtxtfldDescrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmtdtxtfldDescrio.setText("DESCRIÇÃO");
		frmtdtxtfldDescrio.setEditable(false);
		frmtdtxtfldDescrio.setBounds(12, 195, 137, 20);
		getContentPane().add(frmtdtxtfldDescrio);
		
		JFormattedTextField frmtdtxtfldTaxa = new JFormattedTextField();
		frmtdtxtfldTaxa.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtfldTaxa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmtdtxtfldTaxa.setEditable(false);
		frmtdtxtfldTaxa.setText("TAXA");
		frmtdtxtfldTaxa.setBounds(144, 195, 111, 20);
		getContentPane().add(frmtdtxtfldTaxa);
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
