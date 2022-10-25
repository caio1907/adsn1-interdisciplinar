package com.adsn1.screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.adsn1.types.Usuario;
import com.adsn1.utils.Auth;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private Auth auth;
	private JPanel contentPane;
	private JTextField inputEmail;
	private JLabel lblSenha;
	private JPasswordField inputPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 259, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		inputEmail = new JTextField();
		inputEmail.setText("");
		inputEmail.setBounds(40, 50, 165, 19);
		contentPane.add(inputEmail);
		inputEmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(44, 23, 70, 15);
		contentPane.add(lblEmail);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(48, 99, 70, 15);
		contentPane.add(lblSenha);
		
		inputPassword = new JPasswordField();
		inputPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					entrar();
				}
			}
		});
		inputPassword.setBounds(40, 126, 165, 19);
		contentPane.add(inputPassword);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrar();
			}
		});
		btnEntrar.setBounds(58, 200, 117, 25);
		contentPane.add(btnEntrar);
	}
	
	private void entrar() {
		if (auth == null) {
			auth = new Auth();
		}
		Usuario usuario = new Usuario();
		usuario.setEmail(inputEmail.getText());
		usuario.setSenha(new String(inputPassword.getPassword()));
		if (inputEmail.getText().isEmpty() || inputEmail.getText().isBlank()) {
			JOptionPane.showMessageDialog(contentPane, "Preencha o e-mail!");
			inputEmail.requestFocus();
			return;
		}
		if (inputPassword.getPassword().length == 0) {
			JOptionPane.showMessageDialog(contentPane, "Preencha a senha");
			inputPassword.requestFocus();
			return;
		}
		if (!auth.validateEmail(usuario.getEmail())) {
			JOptionPane.showMessageDialog(contentPane, "E-mail inválido!");
			return;
		}
		try {
			if(!auth.login(usuario)) {
				JOptionPane.showMessageDialog(contentPane, "Erro ao realizar login.\nVerifique os logs");
				return;
			}
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(contentPane, exception.getMessage());
			return;
		}
		PDV pdv = new PDV(usuario.getEmail());
		pdv.setVisible(true);
		dispose();
	}
}
