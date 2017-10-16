package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import classes_de_conexão.conexao;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ViewPrimeiratela extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtId;
	private JTextField txtCpf;
	private JTextField txtStatus;
	private JTextField txtTotal;
	private JLabel lblTotal;
	private JTextField txtCnpj;
	private JLabel lblCpf;
	private JLabel lblCnpj;
	private JLabel lblOu;
	private JLabel lblNome;
	private JLabel lblStatus;
	private JButton btnCadastrar;
	private JPanel panel_1;
	private JTextField txtId02;
	private JTextField txtMaior;
	private JTextField txtId01;
	private JLabel lblIdsEntre;
	private JLabel lblE;
	private JLabel lblValoresMaioresQue;
	private JButton btnCalcular;
	private JLabel lblReultado;
	private JTextField txtResultado;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPrimeiratela frame = new ViewPrimeiratela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewPrimeiratela() {
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 983);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(28, 58, 24, 14);
		contentPane.add(lblId);
		
		txtNome = new JTextField();
		txtNome.setBounds(69, 116, 151, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(69, 55, 151, 20);
		contentPane.add(txtId);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(69, 147, 151, 20);
		contentPane.add(txtCpf);
		
		txtStatus = new JTextField();
		txtStatus.setColumns(10);
		txtStatus.setBounds(69, 83, 151, 20);
		contentPane.add(txtStatus);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(71, 178, 149, 23);
		contentPane.add(txtTotal);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(28, 182, 62, 14);
		contentPane.add(lblTotal);
		
		txtCnpj = new JTextField();
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(300, 147, 151, 20);
		contentPane.add(txtCnpj);
		
		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(28, 150, 24, 14);
		contentPane.add(lblCpf);
		
		lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(251, 150, 46, 14);
		contentPane.add(lblCnpj);
		
		lblOu = new JLabel("OU");
		lblOu.setBounds(230, 150, 24, 14);
		contentPane.add(lblOu);
		
		lblNome = new JLabel("Nome ");
		lblNome.setBounds(28, 119, 46, 14);
		contentPane.add(lblNome);
		
		lblStatus = new JLabel("Status");
		lblStatus.setBounds(28, 86, 121, 14);
		contentPane.add(lblStatus);
		
		
		//BOTÃO CADASTRAR
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					
					//FAZ A CONEXÃO COM O BANCO
					Connection con = conexao.faz_conxao();
					
					//STRING COM COMANDO PARA INSERIR OS VALORES NO BANCO
					String cinsert = "INSERT INTO TB_CUSTOMER_ACCOUNT (NM_CUSTOMER, CPF, CNPJ, IS_ACTIVE, VL_TOTAL)"
							+ "VALUES"
							+ "(?,?,?,?,?)";
					
					
					//FAZ A PREPARAÇÃO
					PreparedStatement stmt = (PreparedStatement) con.prepareStatement(cinsert);
					
				
					
					//BUSCA OS VALORES NA STRING DE SELECT
					
					stmt.setString(1, txtNome.getText());
					stmt.setString(2, txtCpf.getText());
					stmt.setString(3, txtCnpj.getText());
					stmt.setString(4, txtStatus.getText());
					stmt.setString(5, txtTotal.getText());
					
					
					
					// EXECUTA AUQERY
					stmt.execute();
					
					//FECHA O BANCO
					
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Cadastro Feito Com Sucesso!");
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "O Cadastro não foi feito!");
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnCadastrar.setBounds(95, 252, 99, 43);
		contentPane.add(btnCadastrar);
		
		
		//BOTÃO EXCLUIR
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					//FAZ A CONEXÃO COM O BANCO
					Connection con = conexao.faz_conxao();
					
					// STRING COM O COMANDO PARA DELETAR OS ITENS SELECIONADOS DO BANCO
					String cdelet = "DELETE FROM TB_CUSTOMER_ACCOUNT WHERE ID_CUSTOMER LIKE ? OR CPF LIKE ? ";
					PreparedStatement stmt = (PreparedStatement) con.prepareStatement(cdelet);
					stmt.setString(1, "%" + txtId.getText());
					stmt.setString(2, "%" + txtCpf.getText());
					
					//EXECUTA A QUERY
					stmt.execute();
					
					//FECHA O BANCO
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Não foi possível realizar esta operação!");
					e1.printStackTrace();
				}
				
				
			}
			
		});
		btnExcluir.setBounds(204, 252, 103, 43);
		contentPane.add(btnExcluir);
		
		
		//BOTÃO LIMPAR
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				// DEIXA EM BRANCO AS CAIXAS DE TEXTO
				txtNome.setText("");
				txtStatus.setText("");
				txtId.setText("");
				txtCpf.setText("");
				txtCnpj.setText("");
				txtTotal.setText("");
				txtId01.setText("");
				txtId02.setText("");
				txtMaior.setText("");
				txtResultado.setText("");
				
				
			}
		});
	
		btnLimpar.setBounds(317, 252, 99, 43);
		contentPane.add(btnLimpar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "A\u00E7\u00F5es de edi\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setForeground(Color.WHITE);
		panel.setToolTipText("A\u00E7\u00F5es");
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 223, 523, 101);
		contentPane.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setToolTipText("A\u00E7\u00F5es");
		panel_1.setForeground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pesquisa de M\u00E9dia Salarial", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 335, 523, 588);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtMaior = new JTextField();
		txtMaior.setBounds(138, 81, 86, 20);
		txtMaior.setColumns(10);
		panel_1.add(txtMaior);
		
		txtId01 = new JTextField();
		txtId01.setBounds(138, 50, 86, 20);
		txtId01.setColumns(10);
		panel_1.add(txtId01);
		
		txtId02 = new JTextField();
		txtId02.setBounds(250, 50, 86, 20);
		txtId02.setColumns(10);
		panel_1.add(txtId02);
		
		lblIdsEntre = new JLabel("IDs entre:");
		lblIdsEntre.setBounds(10, 53, 74, 14);
		panel_1.add(lblIdsEntre);
		
		lblE = new JLabel("E");
		lblE.setBounds(234, 53, 13, 14);
		panel_1.add(lblE);
		
		lblValoresMaioresQue = new JLabel("Valores maiores que:");
		lblValoresMaioresQue.setBounds(10, 84, 128, 14);
		panel_1.add(lblValoresMaioresQue);
		
		
		// BOTÃO DE CALCULAR A MÉDIA
		btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					//FAZ A CONEXÃO COM O BACO
					Connection con = conexao.faz_conxao();
					//STRING COM SELECT PARA CALCULAR A MÉDIA
					String ccalc = "SELECT AVG (VL_TOTAL) FROM tb_customer_account WHERE VL_TOTAL > ? AND ID_CUSTOMER  BETWEEN ? AND ?"; 
					//FAZ A PREPARAÇÃO 		
				    PreparedStatement stmt = (PreparedStatement) con.prepareStatement(ccalc);
					//BUSCA OS VALORES SELECUINADOS NO SELECT
				    stmt.setString(1,  txtMaior.getText());
					stmt.setString(2,  txtId01.getText());
					stmt.setString(3,  txtId02.getText());
					
				
					//EXECUTA A QUERY
					ResultSet rs = stmt.executeQuery();
					
					
					
					while (rs.next()) {
						
						//INSERE O VALOR DO SELECT O9
						txtResultado.setText(rs.getString("AVG (VL_TOTAL)"));
						
						
						
				
						
					}
					
					rs.close();
					con.close();
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Falha na pesquisa!");
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnCalcular.setBounds(250, 80, 89, 23);
		panel_1.add(btnCalcular);
		
		lblReultado = new JLabel("Reultado:");
		lblReultado.setBounds(10, 130, 74, 14);
		panel_1.add(lblReultado);
		
		txtResultado = new JTextField();
		txtResultado.setColumns(10);
		txtResultado.setBounds(138, 127, 86, 20);
		panel_1.add(txtResultado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 207, 503, 370);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		//BOTÃO DE LISTAR - LISTA TODOS OS USUÁRIOS INSERIDOS NOS CAMPOS ID E ID 2 COM COMDIÇÃO DE SALÁRIO
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
				
					//ABRE A CONEXÃO COM O BANCO
					Connection con = conexao.faz_conxao();
					
					//STRING COM O COMANDO SELECT PARA LISTAR
					String clist = "SELECT ID_CUSTOMER AS ID, NM_CUSTOMER AS NOME, VL_TOTAL AS SALÁRIO FROM tb_customer_account WHERE VL_TOTAL > ? AND ID_CUSTOMER  BETWEEN ? AND ? ORDER BY VL_TOTAL DESC "; 
					//FAZ A PREPARAÇÃO 
					PreparedStatement stmt = (PreparedStatement) con.prepareStatement(clist);
				    
				    //BUSCA OS VALORES MENSIONADOS NA STRING DO SELECT
					stmt.setString(1,  txtMaior.getText());
					stmt.setString(2,  txtId01.getText());
					stmt.setString(3,  txtId02.getText());
					
					//EXECUTA A QUERY
				    ResultSet rs = stmt.executeQuery();
				    //INSERE OS VALORES NO JTABLE
				    table.setModel(DbUtils.resultSetToTableModel(rs));
				    
				    //FECHA O BANCO
				    rs.close();
					con.close();
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
		});
		btnListar.setBounds(247, 126, 89, 23);
		panel_1.add(btnListar);
		
		
		//BOTÃO PESQUISAR: FAZ A PESQUISA DOS USUÁRIOS CADASTRADOS NO BANCO DE DADOS, PELO CPF OU ID.
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					
					//FAZ A CONEXÃO COM O BANCO
					Connection con = conexao.faz_conxao();
					// STRING COM SELECT DE PESQUISA
					String cpesquisa = "SELECT * FROM TB_CUSTOMER_ACCOUNT WHERE ID_CUSTOMER LIKE ? OR CPF LIKE ?";
					//FAZ A PREPARAÇÃO E O TRATAMENTO COM BANCO
					PreparedStatement stmt = (PreparedStatement) con.prepareStatement(cpesquisa);
					//CHAMA OS VALORES ECRITOS NAS CAIXAS DE TEXTO
					stmt.setString(1,  txtId.getText());
					stmt.setString(2,  txtCpf.getText());
					
					//EXECUTA A QUERY
					ResultSet rs = stmt.executeQuery();
					
					
					while (rs.next()) {
					
						//INSERE OS VALORES DO BACO NAS CAIXAS DE TEXTO
						txtId.setText(rs.getString("ID_CUSTOMER"));
						txtNome.setText(rs.getString("NM_CUSTOMER"));
						txtStatus.setText(rs.getString("IS_ACTIVE"));
						txtCpf.setText(rs.getString("CPF"));
						txtCnpj.setText(rs.getString("CNPJ"));
						txtTotal.setText(rs.getString("VL_TOTAL"));
						
						
						
						
					}
					
					//FECHA O BANCO
					
					rs.close();
					con.close();
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Falha na pesquisa!");
					e.printStackTrace();
				}
			}
		});
		btnPesquisar.setBounds(300, 54, 151, 23);
		contentPane.add(btnPesquisar);
	}
}
