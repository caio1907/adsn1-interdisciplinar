package com.adsn1.screens;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.adsn1.controllers.ClienteController;
import com.adsn1.types.Cliente;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.text.Format;
import javax.swing.JScrollPane;


public class CadClientes extends JInternalFrame {
	private static CadClientes screen = null;
	private ClienteController clienteController;
	private ArrayList<Cliente> clientes;
	private Cliente cliente;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JTextField txtFiltro;
	private JFormattedTextField txtTelefone;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JFormattedTextField txtDataNascimento;
	private JTextField txtLogradouro;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtUF;
	private JScrollPane scrollPane;

	public static CadClientes getScreen() {
		if (screen == null) {
			screen = new CadClientes();
		}
		return screen;
	}

	/**
	 * Create the frame.
	 */
	public CadClientes() {
		setTitle("Cadastro de Clientes");
		setClosable(true);
		setBounds(100, 100, 841, 600);
		this.clienteController = new ClienteController();
		this.clientes = this.clienteController.getAll();

		btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
				habilitarEdicao(true);
			}
		});
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Id", "Nome", "E-mail", "Telefone"
				}
				);
		for (Cliente cliente : this.clientes) {
			model.addRow(new Object[] {
					cliente.getId(),
					cliente.getNome(),
					cliente.getEmail(),
					cliente.getTelefone()
			});
		}

		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTxtNome().setText(cliente.getNome());
				getTxtEmail().setText(cliente.getEmail());
				getTxtDataNascimento().setText(Utils.formatDateToString(cliente.getDatanascimento()));
				getTxtTelefone().setText(cliente.getTelefone());
				getTxtLogradouro().setText(cliente.getEnd_logradouro());
				getTxtComplemento().setText(cliente.getEnd_complemento());
				getTxtBairro().setText(cliente.getEnd_bairro());
				getTxtCidade().setText(cliente.getEnd_cidade());
				getTxtUF().setText(cliente.getEnd_uf());
				habilitarEdicao(true);
			}
		});

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 12));

		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				if (cliente == null) {
					cliente = new Cliente();
				}
				if (!validarCampos()) {
					JOptionPane.showMessageDialog(rootPane, "Todos os campos são obrigatórios");
					return;
				}
				Date dtNascimento = Utils.formatStringToDate(getTxtDataNascimento().getText());
				if (dtNascimento == null) {
					JOptionPane.showMessageDialog(rootPane, "Data de nascimento inválida.\nPreencha de acordo com o exemplo 30/12/2000");
					getTxtDataNascimento().requestFocus();
					return;
				}
				cliente.setNome(getTxtNome().getText());
				cliente.setEmail(getTxtEmail().getText());
				cliente.setDatanascimento(dtNascimento);
				cliente.setTelefone(getTxtTelefone().getText());
				cliente.setEnd_logradouro(getTxtLogradouro().getText());
				cliente.setEnd_complemento(getTxtComplemento().getText());
				cliente.setEnd_bairro(getTxtBairro().getText());
				cliente.setEnd_cidade(getTxtCidade().getText());
				cliente.setEnd_uf(getTxtUF().getText());
				Cliente clienteSalvo = clienteController.save(cliente);
				if (clienteSalvo == null) {
					JOptionPane.showMessageDialog(rootPane, "Erro ao salvar os dados do cliente.\nEntre em contato com o administrador do sitema.");
					return;
				}
				Long id = cliente.getId();
				if (id != null && id > 0) {
					int selectedRow = table.getSelectedRow();
					model.setValueAt(clienteSalvo.getNome(), selectedRow, 1);
					model.setValueAt(clienteSalvo.getEmail(), selectedRow, 2);
					model.setValueAt(clienteSalvo.getTelefone(), selectedRow, 3);
				} else {
					model.addRow(new Object[] {
							clienteSalvo.getId(),
							clienteSalvo.getNome(),
							clienteSalvo.getEmail(),
							clienteSalvo.getTelefone()
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

		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 12));

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		txtDataNascimento = new JFormattedTextField(df);
		txtDataNascimento.setEnabled(false);
		txtDataNascimento.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
				{
					JOptionPane.showMessageDialog(null, "Insira uma data válida (01/01/1990)");
					e.consume();
				}
			}
		});

		JLabel lblDataDeNascimento = new JLabel("Data de nascimento");
		lblDataDeNascimento.setFont(new Font("Dialog", Font.BOLD, 12));

		txtTelefone = new JFormattedTextField((Format) null);
		txtTelefone.setEnabled(false);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Dialog", Font.BOLD, 12));

		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setFont(new Font("Dialog", Font.BOLD, 12));

		txtLogradouro = new JTextField();
		txtLogradouro.setEnabled(false);
		txtLogradouro.setColumns(10);

		txtComplemento = new JTextField();
		txtComplemento.setEnabled(false);
		txtComplemento.setColumns(10);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setFont(new Font("Dialog", Font.BOLD, 12));

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Dialog", Font.BOLD, 12));

		txtBairro = new JTextField();
		txtBairro.setEnabled(false);
		txtBairro.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setEnabled(false);
		txtCidade.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Dialog", Font.BOLD, 12));

		txtUF = new JTextField();
		txtUF.setEnabled(false);
		txtUF.setColumns(10);

		JLabel lblUF = new JLabel("UF");
		lblUF.setFont(new Font("Dialog", Font.BOLD, 12));

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
												.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblDataDeNascimento, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtDataNascimento, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblLogradouro, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtLogradouro, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtComplemento)
												.addComponent(lblComplemento, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblBairro, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtBairro, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtCidade)
												.addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblUF, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtUF, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))))
						.addGap(11))
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBusque, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(681, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
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
						.addGap(21)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail)
								.addComponent(lblDataDeNascimento)
								.addComponent(lblTelefone))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDataNascimento, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblComplemento)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtComplemento, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblLogradouro)
												.addGap(6)
												.addComponent(txtLogradouro, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblBairro)
												.addGap(6)
												.addComponent(txtBairro, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblCidade)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txtCidade, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblUF)
										.addGap(6)
										.addComponent(txtUF, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addComponent(lblBusque, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
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
					cliente = clientes.get(selectedRow);
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

	private void habilitarEdicao(boolean habilitar) {
		getTable().setEnabled(!habilitar);
		getBtnNovo().setEnabled(!habilitar);
		getBtnSalvar().setEnabled(habilitar);
		getBtnCancelar().setEnabled(habilitar);
		getTxtFiltro().setEnabled(!habilitar);
		getTxtNome().setEnabled(habilitar);
		getTxtEmail().setEnabled(habilitar);
		getTxtDataNascimento().setEnabled(habilitar);
		getTxtTelefone().setEnabled(habilitar);
		getTxtLogradouro().setEnabled(habilitar);
		getTxtComplemento().setEnabled(habilitar);
		getTxtBairro().setEnabled(habilitar);
		getTxtCidade().setEnabled(habilitar);
		getTxtUF().setEnabled(habilitar);
		if (habilitar) {
			getTxtNome().requestFocus();
		} else {
			getBtnNovo().requestFocus();
		}
	}

	private void limparCampos() {
		getTable().clearSelection();
		getBtnEditar().setEnabled(false);
		getTxtNome().setText("");
		getTxtEmail().setText("");
		getTxtDataNascimento().setText("");
		getTxtTelefone().setText("");
		getTxtLogradouro().setText("");
		getTxtComplemento().setText("");
		getTxtBairro().setText("");
		getTxtCidade().setText("");
		getTxtUF().setText("");
	}

	private boolean validarCampos() {
		JTextField[] fields = {
				getTxtNome(),
				getTxtEmail(),
				getTxtDataNascimento(),
				getTxtTelefone(),
				getTxtLogradouro(),
				getTxtBairro(),
				getTxtCidade(),
				getTxtUF()
		};
		for (JTextField field : fields) {
			if (field.getText().isEmpty()) {
				field.requestFocus();
				return false;
			}
		}
		return true;
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
	protected JTextField getTxtNome() {
		return txtNome;
	}
	protected JTextField getTxtEmail() {
		return txtEmail;
	}
	protected JFormattedTextField getTxtDataNascimento() {
		return txtDataNascimento;
	}
	protected JFormattedTextField getTxtTelefone() {
		return txtTelefone;
	}
	protected JTextField getTxtLogradouro() {
		return txtLogradouro;
	}
	protected JTextField getTxtComplemento() {
		return txtComplemento;
	}
	protected JTextField getTxtBairro() {
		return txtBairro;
	}
	protected JTextField getTxtCidade() {
		return txtCidade;
	}
	protected JTextField getTxtUF() {
		return txtUF;
	}
	protected JTable getTable() {
		return table;
	}
}
