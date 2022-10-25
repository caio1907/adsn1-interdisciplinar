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
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Image;

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

		JMenu mnOperacao = new JMenu("Operação");
		ImageIcon mnOperacaoImageIcon = new ImageIcon(PDV.class.getResource("/com/adsn1/icons/systems.png"));
		Image mnOperacaoImage = mnOperacaoImageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		mnOperacao.setIcon(new ImageIcon(mnOperacaoImage));
		mnOperacao.setForeground(Color.WHITE);
		menuBar.add(mnOperacao);

		JMenuItem mntmVenda = new JMenuItem("Venda");
		mntmVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Venda venda = new Venda();
				abrirJanela(venda);
			}
		});
		ImageIcon mntmVendaImageIcon = new ImageIcon(PDV.class.getResource("/com/adsn1/icons/coin.png"));
		Image mntmVendaImage = mntmVendaImageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		mntmVenda.setIcon(new ImageIcon(mntmVendaImage));
		mnOperacao.add(mntmVenda);

		JMenu mnCadastro = new JMenu("Cadastro");
		ImageIcon mnCadastroImageIcon = new ImageIcon(PDV.class.getResource("/com/adsn1/icons/editing.png"));
		Image mnCadastroImage = mnCadastroImageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		mnCadastro.setIcon(new ImageIcon(mnCadastroImage));
		mnCadastro.setForeground(Color.WHITE);
		menuBar.add(mnCadastro);

		JMenuItem mntmCadastroProdutos = new JMenuItem("Produtos");
		mntmCadastroProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadProdutos cadProdutos = new CadProdutos();
				abrirJanela(cadProdutos);
			}
		});
		ImageIcon mntmCadastroProdutosImageIcon = new ImageIcon(PDV.class.getResource("/com/adsn1/icons/box.png"));
		Image mntmCadastroProdutosImage = mntmCadastroProdutosImageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		mntmCadastroProdutos.setIcon(new ImageIcon(mntmCadastroProdutosImage));
		mntmCadastroProdutos.setForeground(Color.BLACK);
		mnCadastro.add(mntmCadastroProdutos);

		JMenuItem mntmCadastroClientes = new JMenuItem("Clientes");
		mntmCadastroClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadClientes cadClientes = new CadClientes();
				abrirJanela(cadClientes);
			}
		});
		ImageIcon mntmCadastroClientesImageIcon = new ImageIcon(PDV.class.getResource("/com/adsn1/icons/customers.png"));
		Image mntmCadastroClientesImage = mntmCadastroClientesImageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		mntmCadastroClientes.setIcon(new ImageIcon(mntmCadastroClientesImage));
		mntmCadastroClientes.setForeground(new Color(0, 0, 0));
		mnCadastro.add(mntmCadastroClientes);

		JMenuItem mntmCadastroTipoPagamentos = new JMenuItem("Tipos de Pagamento");
		mntmCadastroTipoPagamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadTipoDePagamento cadTipoDePagamento = new CadTipoDePagamento();
				abrirJanela(cadTipoDePagamento);
			}
		});
		ImageIcon mntmCadastroTipoPagamentosImageIcon = new ImageIcon(PDV.class.getResource("/com/adsn1/icons/cards.png"));
		Image mntmCadastroTipoPagamentosImage = mntmCadastroTipoPagamentosImageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		mntmCadastroTipoPagamentos.setIcon(new ImageIcon(mntmCadastroTipoPagamentosImage));
		mntmCadastroTipoPagamentos.setForeground(Color.BLACK);
		mnCadastro.add(mntmCadastroTipoPagamentos);
		
		JMenu mnRelatorios = new JMenu("Relatorios");
		ImageIcon mnRelatoriosImageIcon = new ImageIcon(PDV.class.getResource("/com/adsn1/icons/document.png"));
		Image mnRelatoriosImage = mnRelatoriosImageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		mnRelatorios.setIcon(new ImageIcon(mnRelatoriosImage));
		mnRelatorios.setForeground(Color.WHITE);
		menuBar.add(mnRelatorios);
		
		JMenuItem mntmRelatorioVendas = new JMenuItem("Vendas");
		mntmRelatorioVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RelatorioVendas relatorioVendas = new RelatorioVendas();
				abrirJanela(relatorioVendas);
			}
		});
		ImageIcon mntmRelatorioVendasImageIcon = new ImageIcon(PDV.class.getResource("/com/adsn1/icons/document_sales.png"));
		Image mntmRelatorioVendasImage = mntmRelatorioVendasImageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		mntmRelatorioVendas.setIcon(new ImageIcon(mntmRelatorioVendasImage));
		mntmRelatorioVendas.setForeground(Color.BLACK);
		mnRelatorios.add(mntmRelatorioVendas);
		
		JMenu mnConfiguracoes = new JMenu("Configurações");
		ImageIcon mnConfiguracoesImageIcon = new ImageIcon(PDV.class.getResource("/com/adsn1/icons/settings.png"));
		Image mnConfiguracoesImage = mnConfiguracoesImageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		mnConfiguracoes.setIcon(new ImageIcon(mnConfiguracoesImage));
		mnConfiguracoes.setForeground(Color.WHITE);
		menuBar.add(mnConfiguracoes);
		
		JMenuItem mntmConfiguracoes = new JMenuItem("Usuarios/Vendedores");
		mntmConfiguracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadUsuarios cadUsuarios = new CadUsuarios();
				abrirJanela(cadUsuarios);
			}
		});
		ImageIcon mntmConfiguracoesImageIcon = new ImageIcon(PDV.class.getResource("/com/adsn1/icons/users.png"));
		Image mntmConfiguracoesImage = mntmConfiguracoesImageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		mntmConfiguracoes.setIcon(new ImageIcon(mntmConfiguracoesImage));
		mntmConfiguracoes.setForeground(Color.BLACK);
		mnConfiguracoes.add(mntmConfiguracoes);
		
		contentPane = new JPanel();

		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(12, 0, 422, 249);
		contentPane.add(desktopPane);
		setLocationRelativeTo(null);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 1908, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
        			.addContainerGap())
        );
        desktopPane.setLayout(null);

        contentPane.setLayout(gl_contentPane);
	}

	/**
	 * Função para abrir as telas
	 *
	 * @param tela
	 */
	private void abrirJanela(JInternalFrame tela) {
		JInternalFrame[] frames = desktopPane.getAllFrames();
		for (int i = 0; i < frames.length; i++) {
			JInternalFrame frame = frames[i];
			if (frame.getClass() == tela.getClass()) {
				frame.setVisible(true);
				maximizarEColocarNaFrente(frame);
				return;
			}
		}
		getDesktopPane().add(tela);
		tela.setVisible(true);
		maximizarEColocarNaFrente(tela);
	}

	/**
	 * Função para maximizar e colocar a janela na frente das outras
	 *
	 * @param comp
	 */
	private void maximizarEColocarNaFrente(JInternalFrame comp) {
		Dimension desktopSize = desktopPane.getSize();
        Dimension jInternalFrameSize = comp.getSize();
        comp.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
            (desktopSize.height- jInternalFrameSize.height)/2);
        try {
			comp.setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
        comp.toFront();
        comp.show();
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}
}
