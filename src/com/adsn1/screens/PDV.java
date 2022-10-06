package com.adsn1.screens;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PDV extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDesktopPane desktopPane;

	/**
	 * Create the frame.
	 */
	public PDV() {
		setBackground(Color.GRAY);
		setTitle("PDV");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);
		
		JMenu mnCadastro = new JMenu("Produtos");
		mnCadastro.setForeground(Color.WHITE);
		menuBar.add(mnCadastro);
		
		JMenuItem mntmProdutosCadastrar = new JMenuItem("Cadastrar");
		mnCadastro.add(mntmProdutosCadastrar);
		
		JMenuItem mntmProdutosEditar = new JMenuItem("Editar");
		mnCadastro.add(mntmProdutosEditar);
		
		JMenuItem mntmProdutosExcluir = new JMenuItem("Excluir");
		mnCadastro.add(mntmProdutosExcluir);
		
		JMenuItem mntmProdutosListar = new JMenuItem("Buscar");
		mnCadastro.add(mntmProdutosListar);
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setForeground(Color.WHITE);
		menuBar.add(mnClientes);
		
		JMenuItem mntmClientesCadastrar = new JMenuItem("Cadastrar");
		mnClientes.add(mntmClientesCadastrar);
		
		JMenuItem mntmClientesEditar = new JMenuItem("Editar");
		mnClientes.add(mntmClientesEditar);
		
		JMenuItem mntmClientesExcluir = new JMenuItem("Excluir");
		mnClientes.add(mntmClientesExcluir);
		
		JMenuItem mntmClientesListar = new JMenuItem("Buscar");
		mnClientes.add(mntmClientesListar);
		
		JMenu mnTiposDePagamento = new JMenu("Tipos de Pagamento");
		mnTiposDePagamento.setForeground(Color.WHITE);
		menuBar.add(mnTiposDePagamento);
		
		JMenuItem mntmTiposDePagamentoCadastrar = new JMenuItem("Cadastrar");
		mnTiposDePagamento.add(mntmTiposDePagamentoCadastrar);
		
		JMenuItem mntmTiposDePagamentoEditar = new JMenuItem("Editar");
		mnTiposDePagamento.add(mntmTiposDePagamentoEditar);
		
		JMenuItem mntmTiposDePagamentoExcluir = new JMenuItem("Excluir");
		mnTiposDePagamento.add(mntmTiposDePagamentoExcluir);
		
		JMenuItem mntmTiposDePagamentoListar = new JMenuItem("Buscar");
		mnTiposDePagamento.add(mntmTiposDePagamentoListar);
		
		JMenu mnRelatrios = new JMenu("Relatórios");
		mnRelatrios.setForeground(Color.WHITE);
		menuBar.add(mnRelatrios);
		
		JMenuItem mntmRelatoriosVendas = new JMenuItem("Vendas");
		mnRelatrios.add(mntmRelatoriosVendas);
		
		JMenu mnExemplo = new JMenu("Exemplo");
		mnExemplo.setForeground(Color.WHITE);
		menuBar.add(mnExemplo);
		
		JMenuItem mntmTelaExemplo = new JMenuItem("Tela Exemplo");
		mntmTelaExemplo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaExemplo telaExemplo = TelaExemplo.getScreen();
				abrirJanela(telaExemplo);
			}
		});
		mnExemplo.add(mntmTelaExemplo);
		contentPane = new JPanel();

		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(12, 0, 422, 249);
		contentPane.add(desktopPane);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addComponent(desktopPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );
        desktopPane.setLayout(null);
        contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Função para abrir as telas
	 *
	 * @param tela
	 */
	private void abrirJanela(Component tela) {
		JInternalFrame[] frames = desktopPane.getAllFrames();
		for (int i = 0; i < frames.length; i++) {
			JInternalFrame frame = frames[i];
			if (frame.getClass() == tela.getClass()) {
				frame.setVisible(true);
				alignWindowInCenter(frame);
				return;
			}
		}
		desktopPane.add(tela);
		tela.setVisible(true);
		alignWindowInCenter(tela);
	}

	/**
	 * Função para alinhar a janela ao centro
	 *
	 * @param comp
	 */
	private void alignWindowInCenter(Component comp) {
		Dimension desktopSize = desktopPane.getSize();
        Dimension jInternalFrameSize = comp.getSize();
        comp.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
            (desktopSize.height- jInternalFrameSize.height)/2);
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}
}
