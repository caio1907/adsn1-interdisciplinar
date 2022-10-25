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

import com.adsn1.controllers.UsuarioController;
import com.adsn1.types.Usuario;
import com.adsn1.utils.Auth;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;

public class CadUsuarios extends JInternalFrame {
	private static CadUsuarios screen = null;
	private UsuarioController usuarioController;
	private ArrayList<Usuario> usuarios;
	private Usuario usuario;
	private Auth auth;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JTextField txtFiltro;
	private JPasswordField txtSenha;
	private JPasswordField txtSenhaConfirm;

	public static CadUsuarios getScreen() {
		if (screen == null) {
			screen = new CadUsuarios();
		}
		return screen;
	}

	/**
	 * Create the frame.
	 */
	public CadUsuarios() {
		setClosable(true);
		setTitle("Cadastro de Usuários/Vendedores");
		setBounds(100, 100, 600, 500);
		this.usuarioController = new UsuarioController();
		this.auth = new Auth();
		loadData();

		txtNome = new JTextField();
		txtNome.setEnabled(false);

		txtEmail = new JTextField();
		txtEmail.setEnabled(false);

		JLabel lblNome = new JLabel("Nome");

		JLabel lblEmail = new JLabel("E-mail");

		JLabel lblSenha = new JLabel("Senha");

		txtFiltro = new JTextField();
		txtFiltro.setColumns(10);

		JLabel lblFiltro = new JLabel("Filtrar");
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "Nome", "E-mail"
				}
				);
		for (Usuario produto : this.usuarios) {
			model.addRow(new Object [] {
					produto.getId(),
					produto.getNome(),
					produto.getEmail()
			});
		}

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarEdicao(true);
				limparCampos();
				usuario = null;
			}
		});
		btnNovo.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTxtNome().setText(usuario.getNome());;
				getTxtEmail().setText(usuario.getEmail());
				getTxtSenha().setText("");
				getTxtSenhaConfirm().setText("");
				habilitarEdicao(true);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validarCampos()) {
					JOptionPane.showMessageDialog(rootPane, "Preencha todos os campos");
					return;
				}
				String nome = getTxtNome().getText();
				String email = getTxtEmail().getText();
				String senha = new String(getTxtSenha().getPassword());
				String senhaConfirm = new String(getTxtSenhaConfirm().getPassword());

				if (!senha.equals(senhaConfirm)) {
					JOptionPane.showMessageDialog(rootPane, "As senhas não coincidem");
					getTxtSenhaConfirm().requestFocus();
					return;
				}
				if (!auth.validateEmail(email)) {
					JOptionPane.showMessageDialog(rootPane, "E-mail inválido");
					getTxtEmail().requestFocus();
					return;
				}

				if (usuario == null) {
					usuario = new Usuario();
				}

				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setSenha(senhaConfirm);

				Usuario usuarioSalvo = usuarioController.save(usuario);

				Long id = usuario.getId();
				if (id != null && id > 0) {
					int selectedRow = getTable().getSelectedRow();
					model.setValueAt(usuario.getId(), selectedRow, 0);
					model.setValueAt(usuarioSalvo.getNome(), selectedRow, 1);
					model.setValueAt(usuarioSalvo.getEmail(), selectedRow, 2);
				} else {
					model.addRow(new Object [] {
							usuarioSalvo.getId(),
							usuarioSalvo.getNome(),
							usuarioSalvo.getEmail()
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

		txtSenha = new JPasswordField();
		txtSenha.setEnabled(false);

		JLabel lblConfirmarSenha = new JLabel("Confirmar senha");

		txtSenhaConfirm = new JPasswordField();
		txtSenhaConfirm.setEnabled(false);
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
												.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
										.addGap(5)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblSenha))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtSenhaConfirm, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblConfirmarSenha, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnNovo)
										.addGap(18)
										.addComponent(btnEditar)
										.addGap(18)
										.addComponent(btnSalvar)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnCancelar))
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)))
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
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
																.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(1)
														.addComponent(lblConfirmarSenha, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(txtSenhaConfirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGap(11)
										.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
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
					usuario = usuarios.get(selectedRow);
					getBtnEditar().setEnabled(true);
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		getContentPane().setLayout(groupLayout);
	}

	private void loadData() {
		ArrayList<Usuario> produtos = this.usuarioController.getAll();
		this.usuarios = produtos;
	}

	private void limparCampos() {
		getTable().clearSelection();
		getBtnEditar().setEnabled(false);
		getTxtNome().setText("");
		getTxtEmail().setText("");
		getTxtSenha().setText("");
		getTxtSenhaConfirm().setText("");
	}

	private void habilitarEdicao(boolean habilitar) {
		getTable().setEnabled(!habilitar);
		getBtnNovo().setEnabled(!habilitar);
		getBtnSalvar().setEnabled(habilitar);
		getBtnCancelar().setEnabled(habilitar);
		getTxtFiltro().setEnabled(!habilitar);
		getTxtNome().setEnabled(habilitar);
		getTxtEmail().setEnabled(habilitar);
		getTxtSenha().setEnabled(habilitar);
		getTxtSenhaConfirm().setEnabled(habilitar);
		if (habilitar) {
			getTxtNome().requestFocus();
		} else {
			getBtnNovo().requestFocus();
		}
	}

	private boolean validarCampos() {
		JTextField[] fields = {
				getTxtNome(),
				getTxtEmail(),
				getTxtSenha(),
				getTxtSenhaConfirm()
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
	protected JTextField getTxtNome() {
		return txtNome;
	}
	protected JTextField getTxtEmail() {
		return txtEmail;
	}
	protected JPasswordField getTxtSenha() {
		return txtSenha;
	}
	protected JPasswordField getTxtSenhaConfirm() {
		return txtSenhaConfirm;
	}
	protected JTextField getTxtFiltro() {
		return txtFiltro;
	}
}
