package com.adsn1.screens;

import com.adsn1.types.Cliente;
import com.adsn1.utils.Database;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
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

public class TelaExemplo extends JInternalFrame {
	private Database database;
	
	private static TelaExemplo screen = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JTextField txtFiltro;
	
	public static TelaExemplo getScreen() {
		if (screen == null) {
			screen = new TelaExemplo();
		}
		return screen;
	}

	/**
	 * Create the frame.
	 */
	public TelaExemplo() {
		setTitle("Tela Exemplo");
		setClosable(true);
		setBounds(100, 100, 700, 600);
		getContentPane().setLayout(null);
		
		this.database = new Database();
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTxtNome().setEnabled(true);
				getTxtEmail().setEnabled(true);
				getTxtTelefone().setEnabled(true);
				getBtnCancelar().setEnabled(true);
				getBtnSalvar().setEnabled(true);
				getBtnNovo().setEnabled(false);
				getTxtFiltro().setEnabled(false);
				getTxtNome().requestFocus();
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
				"Id", "Nome", "E-mail", "Telefone"
			}
		);
		ResultSet resultSet = this.database.executeSelect("SELECT * FROM cliente");
		if (resultSet != null) {
			Cliente cliente = new Cliente();
			try {
				while (resultSet.next()) {
					cliente.setId(resultSet.getInt("id"));
					cliente.setNome(resultSet.getString("nome"));
					cliente.setEmail(resultSet.getString("email"));
					cliente.setTelefone(resultSet.getString("telefone"));
					model.addRow(new Object[] {
							cliente.getId(),
							cliente.getNome(),
							cliente.getEmail(),
							cliente.getTelefone()
							});
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		table.setModel(model);
		table.setBounds(12, 146, 666, 410);
		getContentPane().add(table);
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBounds(106, 12, 88, 25);
		getContentPane().add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(206, 12, 88, 25);
		getContentPane().add(btnExcluir);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setBounds(12, 63, 114, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 47, 70, 15);
		getContentPane().add(lblNome);
		
		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(138, 63, 114, 19);
		getContentPane().add(txtEmail);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(138, 49, 70, 15);
		getContentPane().add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(264, 49, 70, 15);
		getContentPane().add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setEnabled(false);
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(264, 63, 114, 19);
		getContentPane().add(txtTelefone);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(309, 12, 82, 25);
		getContentPane().add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
				getBtnNovo().setEnabled(true);
				getBtnCancelar().setEnabled(false);
				getBtnSalvar().setEnabled(false);
				getTxtNome().setEnabled(false);
				getTxtEmail().setEnabled(false);
				getTxtTelefone().setEnabled(false);
				getTxtFiltro().setEnabled(true);
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(403, 12, 96, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblFiltro = new JLabel("Filtro");
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
		txtFiltro.setBounds(12, 115, 114, 19);
		getContentPane().add(txtFiltro);
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
	protected JTextField getTxtNome() {
		return txtNome;
	}
	protected JTextField getTxtEmail() {
		return txtEmail;
	}
	protected JTextField getTxtTelefone() {
		return txtTelefone;
	}
}
