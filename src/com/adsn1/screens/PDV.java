package com.adsn1.screens;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.adsn1.utils.Database;

import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

public class PDV extends JFrame {

	private static final long serialVersionUID = 1L;
	private Database database;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JTable table;
	private JTextField campoValor;

	/**
	 * Create the frame.
	 */
	public PDV() {
		database = new Database();
		setBackground(Color.GRAY);
		setTitle("PDV");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);
		
		JMenu mnCadastro = new JMenu("Produtos");
		mnCadastro.setForeground(new Color(0, 0, 0));
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
		mnClientes.setForeground(new Color(0, 0, 0));
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
		mnTiposDePagamento.setForeground(new Color(0, 0, 0));
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
		mnRelatrios.setForeground(new Color(0, 0, 0));
		menuBar.add(mnRelatrios);
		
		JMenuItem mntmRelatoriosVendas = new JMenuItem("Vendas");
		mnRelatrios.add(mntmRelatoriosVendas);
		
		JMenu mnExemplo = new JMenu("Pedidos ");
		mnExemplo.setForeground(new Color(0, 0, 0));
		menuBar.add(mnExemplo);
		
		JMenuItem mntmTelaExemplo = new JMenuItem("Cadastro de Pedidos");
		mntmTelaExemplo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaExemplo telaExemplo = new TelaExemplo();
				desktopPane.add(telaExemplo);
				telaExemplo.setVisible(true);
			}
		});
		mnExemplo.add(mntmTelaExemplo);
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
        			.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        			.addGap(0))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
        			.addGap(0))
        );
        desktopPane.setLayout(null);
        
        JTextArea txtrCodCliente = new JTextArea();
        txtrCodCliente.setText("Filtro");
        txtrCodCliente.setForeground(new Color(0, 0, 0));
        txtrCodCliente.setToolTipText("Cod. Cliente");
        txtrCodCliente.setBounds(30, 10, 110, 22);
        desktopPane.add(txtrCodCliente);
        
        JTextArea txtrNome = new JTextArea();
        txtrNome.setToolTipText("Cod. Cliente");
        txtrNome.setText("Valor");
        txtrNome.setForeground(Color.BLACK);
        txtrNome.setBounds(150, 10, 52, 22);
        desktopPane.add(txtrNome);
        
        JTextArea txtrDadosDoCliente = new JTextArea();
        txtrDadosDoCliente.setToolTipText("Cod. Cliente");
        txtrDadosDoCliente.setText("Dados do Cliente ");
        txtrDadosDoCliente.setForeground(Color.BLACK);
        txtrDadosDoCliente.setBounds(30, 68, 206, 22);
        desktopPane.add(txtrDadosDoCliente);
        
        JTextArea txtrIncluirProdutos = new JTextArea();
        txtrIncluirProdutos.setToolTipText("Cod. Cliente");
        txtrIncluirProdutos.setText("Incluir produtos");
        txtrIncluirProdutos.setForeground(Color.BLACK);
        txtrIncluirProdutos.setBounds(30, 159, 205, 22);
        desktopPane.add(txtrIncluirProdutos);
        
        JTextArea txtrTipoDePagamento = new JTextArea();
        txtrTipoDePagamento.setToolTipText("Cod. Cliente");
        txtrTipoDePagamento.setText("Tipo de pagamento");
        txtrTipoDePagamento.setForeground(Color.BLACK);
        txtrTipoDePagamento.setBounds(35, 327, 153, 22);
        desktopPane.add(txtrTipoDePagamento);
        
        JTextArea txtrJuros = new JTextArea();
        txtrJuros.setToolTipText("Cod. Cliente");
        txtrJuros.setText("Juros");
        txtrJuros.setForeground(Color.BLACK);
        txtrJuros.setBounds(219, 327, 52, 22);
        desktopPane.add(txtrJuros);
        
        JTextArea txtrTotalAReceber = new JTextArea();
        txtrTotalAReceber.setToolTipText("Cod. Cliente");
        txtrTotalAReceber.setText("Total a receber:");
        txtrTotalAReceber.setForeground(Color.BLACK);
        txtrTotalAReceber.setBounds(314, 327, 196, 22);
        desktopPane.add(txtrTotalAReceber);
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("Pix");
        rdbtnNewRadioButton.setBounds(45, 355, 103, 21);
        desktopPane.add(rdbtnNewRadioButton);
        
        JRadioButton rdbtnDinheiro = new JRadioButton("Dinheiro");
        rdbtnDinheiro.setBounds(45, 380, 103, 21);
        desktopPane.add(rdbtnDinheiro);
        
        JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Cartão de Crédito");
        rdbtnNewRadioButton_2.setBounds(45, 403, 103, 21);
        desktopPane.add(rdbtnNewRadioButton_2);
        
        JRadioButton rdbtnNewRadioButton_2_1 = new JRadioButton("Cartão de Débito");
        rdbtnNewRadioButton_2_1.setBounds(45, 426, 103, 21);
        desktopPane.add(rdbtnNewRadioButton_2_1);
        
        JButton btnNewButton = new JButton("Finalizar pedido");
        btnNewButton.setBounds(424, 414, 159, 21);
        desktopPane.add(btnNewButton);
        
        table = new JTable();
        table.setCellSelectionEnabled(true);
        table.setColumnSelectionAllowed(true);
        table.setSurrendersFocusOnKeystroke(true);
        table.setToolTipText("produtos");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        	},
        	new String[] {
        		"New column", "New column", "New column", "New column", "New column", "New column"
        	}
        ));
        table.setBounds(40, 178, 531, 129);
        desktopPane.add(table);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(229, 354, 45, 33);
        desktopPane.add(panel_2);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBounds(347, 359, 126, 28);
        desktopPane.add(panel_3);
        
        JComboBox campoFiltro = new JComboBox();
        campoFiltro.setModel(new DefaultComboBoxModel(new String[] {"nome", "email", "telefone"}));
        campoFiltro.setBounds(40, 37, 82, 21);
        desktopPane.add(campoFiltro);
        
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        JComboBox cbClientes = new JComboBox();
        cbClientes.setModel(model);
        cbClientes.setBounds(406, 37, 267, 21);
        desktopPane.add(cbClientes);
        
        campoValor = new JTextField();
        campoValor.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER && !campoValor.getText().isEmpty()) {
        			String filtro = campoFiltro.getSelectedItem().toString();
        			String valor = campoValor.getText();
        			ResultSet resultSet = database.executeSelect("SELECT * FROM cliente WHERE "+filtro+" LIKE '%"+valor+"%'");
        			if(resultSet == null) {
        				return;
        			}
        			try {
        				cbClientes.removeAllItems();
						while (resultSet.next()) {
							cbClientes.addItem(resultSet.getInt("id")+"-"+resultSet.getString("nome")+"-"+resultSet.getString("telefone"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        	}
        });
        campoValor.setBounds(160, 38, 206, 19);
        desktopPane.add(campoValor);
        campoValor.setColumns(10);
        
        JTextArea txtrCliente = new JTextArea();
        txtrCliente.setToolTipText("Cod. Cliente");
        txtrCliente.setText("Cliente");
        txtrCliente.setForeground(Color.BLACK);
        txtrCliente.setBounds(381, 10, 92, 22);
        desktopPane.add(txtrCliente);      
        
        JTextArea txtDadosCliente = new JTextArea();
        txtDadosCliente.setEditable(false);
        txtDadosCliente.setBounds(40, 100, 536, 43);
        desktopPane.add(txtDadosCliente);
        
        contentPane.setLayout(gl_contentPane);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}
}
