package com.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import com.geradores.Alfabeto;
import com.geradores.Criptografar;
import com.geradores.Gerador;

import net.miginfocom.swing.MigLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Cursor;

public class InterfaceDesktop extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel, gerarSenha, verificarSenha, criptografarSenha, salvarSenha, visualizarSenhas, menuOpcoes;
	private Fontes fontes = new Fontes();

	public InterfaceDesktop() {
		this.setIconImage(new ImageIcon("src\\Files\\Logo.png").getImage());
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setLayout(new MigLayout(",fill,height 1000", ""));
		setContentPane(mainPanel);

		try {
			this.criaMenu();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.criaGerarSenha();
		this.criaVerificarSenha();
		this.criaSalvarSenha(null);
		this.criaCriptografarSenha();
		this.criaDescriptografarSenha();

	}

	/*
	 * Criação do Menu de seleção das opções e administrar os menus
	 * 
	 */
	private void criaMenu() throws FontFormatException, IOException {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Gerador de Senhas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1390, 598);

		menuOpcoes = new JPanel();
		menuOpcoes.setBorder(null);
		menuOpcoes.setBackground(new Color(0, 255, 128));
		menuOpcoes.setLayout(new MigLayout("width 20sp:45sp:45sp, fill", "[grow]", "[][][][][][]"));

		JLabel titulo = new JLabel("Gerador de Senhas");
		JButton chamaGerarSenha = new JButton("Gerar Senha Aleatoriamente");
		JButton chamaVerificarSenha = new JButton("Verificar Força da senha");
		JButton chamaSalvar = new JButton("Salvar Senha em arquivo");
		JButton chamaCriptografar = new JButton("Criptografar Arquivo com Senhas");
		JButton chamaVisuArqui = new JButton("Visualizar arquivo com Senhas");

		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(fontes.getFontGrande());

		this.criaBotoesMenu(chamaGerarSenha);
		this.criaBotoesMenu(chamaVerificarSenha);
		this.criaBotoesMenu(chamaSalvar);
		this.criaBotoesMenu(chamaCriptografar);
		this.criaBotoesMenu(chamaVisuArqui);

		chamaGerarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.removeAll();
				getContentPane().add(menuOpcoes, "west,alignx center");
				mainPanel.updateUI();
				if (!mainPanel.isAncestorOf(gerarSenha)) {
					mainPanel.add(gerarSenha, "east");
					mainPanel.updateUI();

				}
			}
		});

		chamaVerificarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.removeAll();
				getContentPane().add(menuOpcoes, "west,alignx center");
				mainPanel.updateUI();
				if (!mainPanel.isAncestorOf(verificarSenha)) {
					mainPanel.add(verificarSenha, "east");
					mainPanel.updateUI();

				}
			}
		});

		chamaSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.removeAll();
				getContentPane().add(menuOpcoes, "west,alignx center");
				mainPanel.updateUI();
				if (!mainPanel.isAncestorOf(salvarSenha)) {
					mainPanel.add(salvarSenha, "east");
					mainPanel.updateUI();

				}
			}
		});

		chamaCriptografar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.removeAll();
				getContentPane().add(menuOpcoes, "west,alignx center");
				mainPanel.updateUI();
				if (!mainPanel.isAncestorOf(criptografarSenha)) {
					mainPanel.add(criptografarSenha, "east");
					mainPanel.updateUI();

				}
			}
		});

		chamaVisuArqui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.removeAll();
				getContentPane().add(menuOpcoes, "west,alignx center");
				mainPanel.updateUI();
				if (!mainPanel.isAncestorOf(visualizarSenhas)) {
					mainPanel.add(visualizarSenhas, "east");
					mainPanel.updateUI();

				}
			}
		});

		getContentPane().add(menuOpcoes, "west,alignx center");

		menuOpcoes.add(titulo, "cell 0 0,alignx center,aligny center");
		menuOpcoes.add(chamaGerarSenha, "cell 0 1,alignx center,aligny center");
		menuOpcoes.add(chamaVerificarSenha, "flowx,cell 0 2,alignx center");
		menuOpcoes.add(chamaSalvar, "cell 0 3,alignx center");
		menuOpcoes.add(chamaCriptografar, "cell 0 4,alignx center");
		menuOpcoes.add(chamaVisuArqui, "cell 0 5,alignx center");
	}

	/*
	 * Criação do menu responsável pela geração aleatória de Senhas
	 */
	private void criaGerarSenha() {
		gerarSenha = new JPanel();
		gerarSenha.setBorder(null);
		gerarSenha.setBackground(new Color(255, 255, 255));
		gerarSenha.setLayout(new MigLayout("width 20sp:65sp:65sp, fill", "[grow]", "[][][][][]"));
		gerarSenha.setBackground(Color.white);
		mainPanel.add(gerarSenha, "east,alignx center");

		JLabel tituloSenhaAleatoria = new JLabel("Gerar Senha aleatoriamente");
		JCheckBox addLetraMai = new JCheckBox("Adicionar Letras Maiusculas");
		JCheckBox addLetramin = new JCheckBox("Adicionar Letras Minusculas");
		JCheckBox addnum = new JCheckBox("Adicionar Números");
		JCheckBox addCaracEsp = new JCheckBox("Adicionar Caracteres Especiais");
		JButton btnGerarSenha = new JButton("Gerar Senha");
		JTextField senhaGerada = new JTextField();
		JButton salvar = new JButton("Salvar Senha");
		JSlider defTamSenha = new JSlider();
		
		defTamSenha.setBackground(new Color(255, 255, 255));
		defTamSenha.setPaintLabels(true);
		defTamSenha.setPaintTicks(true);
		defTamSenha.setFont(fontes.getFontPequena());
		
		this.criaSelecaoSenhas(addCaracEsp);
		this.criaSelecaoSenhas(addnum);
		this.criaSelecaoSenhas(addLetraMai);
		this.criaSelecaoSenhas(addLetramin);
		
		this.criaBotoesMenu(btnGerarSenha);
		
		tituloSenhaAleatoria.setHorizontalAlignment(SwingConstants.CENTER);
		tituloSenhaAleatoria.setFont(fontes.getFontGrande());

		defTamSenha.setSize(new Dimension(100, 0));
		defTamSenha.setSnapToTicks(true);
		defTamSenha.setMajorTickSpacing(1);
		defTamSenha.setMinorTickSpacing(1);
		defTamSenha.setToolTipText("Define o Tamanho da Senha (min 8)");
		defTamSenha.setValue(17);
		defTamSenha.setMaximum(25);
		defTamSenha.setMinimum(8);

		senhaGerada.setHorizontalAlignment(SwingConstants.CENTER);
		senhaGerada.setEditable(false);
		senhaGerada.setColumns(30);
		

		btnGerarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String temp = Gerador.geradorSenha(addLetraMai.isSelected(), addLetramin.isSelected(),
							addnum.isSelected(), addCaracEsp.isSelected(), defTamSenha.getValue());
					senhaGerada.setText(temp);
					gerarSenha.add(salvar, "cell 0 4, alignx center");
					salvar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							criaSalvarSenha(senhaGerada.getText());
							mainPanel.removeAll();
							getContentPane().add(menuOpcoes, "west,alignx center");
							mainPanel.updateUI();
							if (!mainPanel.isAncestorOf(salvarSenha)) {
								mainPanel.add(salvarSenha, "east");
								mainPanel.updateUI();

							}

						}
					});
					gerarSenha.updateUI();
				} catch (Exception e1) {
					senhaGerada.setText("Selecione as opções corretamente. ");
				}

			}
		});
		this.criaBotoesMenu(salvar);
		this.criaCaixaSelecao(senhaGerada);
		
		
		gerarSenha.add(tituloSenhaAleatoria, "cell 0 0,alignx center,aligny center");
		gerarSenha.add(btnGerarSenha, "cell 0 3,alignx center");
		gerarSenha.add(defTamSenha, "cell 0 2,growx");
		gerarSenha.add(senhaGerada, "cell 0 4,alignx center");
		gerarSenha.add(addLetraMai, "flowx,cell 0 1,alignx center");
		gerarSenha.add(addLetramin, "cell 0 1,alignx center");
		gerarSenha.add(addnum, "cell 0 1,alignx center");
		gerarSenha.add(addCaracEsp, "cell 0 1,aligny center");
	}

	/*
	 * Criação do menu responsável pela verificação da força da senha
	 */
	private void criaVerificarSenha() {
//		Definições do panel - Início
		verificarSenha = new JPanel();
		verificarSenha.setBackground(new Color(255, 255, 255));
		verificarSenha.setLayout(new MigLayout("width 20sp:65sp:65sp, fill", "[grow]", "[][][]"));
		verificarSenha.setBackground(Color.white);
//		mainPanel.add(verificarSenha, "east");
//		Definições do panel - Fim

//		Inicializa os elementos utilizados - Início
		JTextField senAnalisada = new JTextField();
		JLabel tituloVerificaSenha = new JLabel("Verificar a Força da Senha");
		JTextField forcaSenha = new JTextField();
		JButton verificaForca = new JButton("Verificar Senha");
//		Inicializa os elementos utilizados - Fim

//		Definição de cada elemento - Início
		this.criaBotoesMenu(verificaForca);
		this.criaCaixaSelecao(forcaSenha);
		this.criaCaixaSelecao(senAnalisada);
		
		tituloVerificaSenha.setHorizontalAlignment(SwingConstants.CENTER);
		tituloVerificaSenha.setFont(fontes.getFontGrande());

		forcaSenha.setEditable(false);
		forcaSenha.setHorizontalAlignment(SwingConstants.CENTER);
		forcaSenha.setText("A força da senha aparecerá aqui");
		forcaSenha.setColumns(20);

		senAnalisada.setHorizontalAlignment(SwingConstants.CENTER);
		senAnalisada.setColumns(50);
//		Definição de cada elemento - Fim

//		Action Listeners - Início
		verificaForca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					forcaSenha.setText(Gerador.verificaSenha(senAnalisada.getText()));
				} catch (Exception e1) {
					forcaSenha.setText("Preencha corretamente");
				}
			}
		});
//		Action Listeners - Fim

//		Adiciona os componentes ao Panel - Início
		verificarSenha.add(forcaSenha, "cell 0 2,alignx center");
		verificarSenha.add(senAnalisada, "flowx,cell 0 1,alignx center");
		verificarSenha.add(verificaForca, "cell 0 1");
		verificarSenha.add(tituloVerificaSenha, "cell 0 0,alignx center,aligny center");
//		Adiciona os componentes ao Panel - Fim
	}

	/*
	 * Criação do menu responsável pelo salvamento das senhas em um TXT
	 * 
	 * @param tmpSenha Inicializa função com uma senha já iniciada
	 */
	private void criaSalvarSenha(String tmpSenha) {
		salvarSenha = new JPanel();
		salvarSenha.setBackground(new Color(255, 255, 255));
		salvarSenha.setLayout(new MigLayout("width 20sp:65sp:65sp, fill", "[grow]", "[][][][]"));
		salvarSenha.setBackground(Color.white);
//		mainPanel.add(salvarSenha, "east");

		JTextField recebeIdentificacao;
		JLabel tituloSalvarSenha = new JLabel("Salvar Senhas em um Arquivo");
		JTable table;
		JTextField recebeSenha = new JTextField();
		JButton addSenha = new JButton("Adicionar senhas Senha");
		DefaultTableModel model = new DefaultTableModel();
		JButton salvaSenha = new JButton("Salvar Senhas");
		Component horizontalStrut = Box.createHorizontalStrut(20);
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		JLabel infID = new JLabel("Identificação Senha");
		JLabel infSenha = new JLabel("Senha");

		if (tmpSenha != null) {
			recebeSenha.setText(tmpSenha);
			recebeSenha.setEnabled(false);
		}
		tituloSalvarSenha.setHorizontalAlignment(SwingConstants.CENTER);
		tituloSalvarSenha.setFont(fontes.getFontGrande());

		model.addColumn("Identificação Senhas");
		model.addColumn("Senhas");
		model.setColumnIdentifiers(new Object[] { "Identificação Senhas", "Senhas" });

		recebeIdentificacao = new JTextField();
		recebeIdentificacao.setHorizontalAlignment(SwingConstants.CENTER);
		recebeIdentificacao.setColumns(10);
		recebeSenha.setHorizontalAlignment(SwingConstants.CENTER);
		recebeSenha.setColumns(10);

		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		this.criaBotoesMenu(salvaSenha);
		this.criaBotoesMenu(addSenha);
		
		this.criaCaixaSelecao(recebeSenha);
		this.criaCaixaSelecao(recebeIdentificacao);
		
		infID.setFont(fontes.getFontMedia());
		infSenha.setFont(fontes.getFontMedia());
		
		addSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recebeSenha.setEnabled(true);
				try {
					if (!recebeIdentificacao.getText().isBlank() && !recebeSenha.getText().isBlank()) {
						model.insertRow(model.getRowCount(),
								new Object[] { recebeIdentificacao.getText(), recebeSenha.getText() });
					}
				} catch (Exception f) {
					System.out.println("Erro");
				}
				recebeIdentificacao.setText("");
				recebeSenha.setText("");
			}
		});

		salvaSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser filechooser = new JFileChooser();
				filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				filechooser.setAcceptAllFileFilterUsed(false);
				filechooser.addChoosableFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "Arquivos Txt";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						} else {
							return f.getName().toLowerCase().endsWith(".txt");
						}
					}
				});
				filechooser.setCurrentDirectory(new File("C:\\Users\\marta\\OneDrive\\Área de Trabalho"));
				int result = filechooser.showSaveDialog(null); // Show the file dialog

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = filechooser.getSelectedFile();
					try {
						if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {

							selectedFile = new File(selectedFile.getParentFile(), selectedFile.getName() + ".txt");
							selectedFile.createNewFile();
						}
					} catch (Exception e1) {

						System.out.println("ERRO!");
					}

					for (int i = 0; i < model.getRowCount(); i++) {
						try {

							Gerador.salvarSenhas(table.getValueAt(i, 0).toString(), table.getValueAt(i, 1).toString(),
									selectedFile.toString());
						} catch (Exception f) {

							System.out.println("Deu Ruim! " + table.getValueAt(i, 0).toString());
						}
					}
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
						model.removeRow(i);
					}

				}
			}
		});

		salvarSenha.add(infID, "flowx,cell 0 1,alignx center");
		salvarSenha.add(tituloSalvarSenha, "cell 0 0,alignx center,aligny center");
		salvarSenha.add(recebeIdentificacao, "cell 0 1,alignx center");
		salvarSenha.add(infSenha, "cell 0 1,alignx center");
		salvarSenha.add(recebeSenha, "cell 0 1,alignx center");
		salvarSenha.add(horizontalStrut, "cell 0 1");
		salvarSenha.add(horizontalStrut_1, "cell 0 1");
		salvarSenha.add(addSenha, "cell 0 1");
		salvarSenha.add(table, "cell 0 2,growx,aligny center");
		salvarSenha.add(salvaSenha, "cell 0 3,alignx center");
	}

	/*
	 * Criação do menu responsável pela encripatação de um arquivo TXT(com senhas)
	 */
	private void criaCriptografarSenha() {
		criptografarSenha = new JPanel();
		criptografarSenha.setBackground(new Color(255, 255, 255));
		criptografarSenha.setLayout(new MigLayout("width 20sp:65sp:65sp, fill", "[grow]", "[][][]"));
		criptografarSenha.setBackground(Color.white);
//		mainPanel.add(criptografarSenha, "east");

		JTextField senhaCriptografia = new JTextField();
		JButton criptografarArquivo = new JButton("Abrir arquivo para criptografar");
		JTextField codigoSenha = new JTextField();
		JLabel tituloCriptografa = new JLabel("Criptografar Arquivo com senhas");
		JLabel infSenhaCriptografia = new JLabel("Senha para o arquivo");
		JButton addCriptografia = new JButton("Salvar senha");
		JLabel infCodigo = new JLabel("Codigo para a Senha");
		JLabel statusArquivoCrip = new JLabel("Arquivo ainda não aberto");

		tituloCriptografa.setHorizontalAlignment(SwingConstants.CENTER);
		tituloCriptografa.setFont(fontes.getFontGrande());

		senhaCriptografia.setColumns(10);

		criptografarArquivo.setEnabled(false);
		criptografarArquivo.setToolTipText("Defina uma senha e um codigo antes de criptografar o arquivo.");

		codigoSenha.setColumns(10);

		addCriptografia.setToolTipText("ATENÇÂO! Uma vez adicionada a senha, não será possível realizar uma troca.");

		this.criaBotoesMenu(addCriptografia);
		this.criaBotoesMenu(criptografarArquivo);
		this.criaCaixaSelecao(codigoSenha);
		this.criaCaixaSelecao(senhaCriptografia);
	
		infSenhaCriptografia.setFont(fontes.getFontMedia());
		infCodigo.setFont(fontes.getFontMedia());
		statusArquivoCrip.setFont(fontes.getFontPequena());
		
		criptografarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser arquivoaCriptografar = new JFileChooser();
				arquivoaCriptografar.setFileSelectionMode(JFileChooser.FILES_ONLY);
				arquivoaCriptografar.setAcceptAllFileFilterUsed(false);
				arquivoaCriptografar.setCurrentDirectory(new File("C:\\Users\\marta\\OneDrive\\Área de Trabalho"));
				arquivoaCriptografar.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {

						return "Arquivos txt";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						} else {
							return f.getName().toLowerCase().endsWith(".txt");
						}
					}
				});

				int fileStatus = arquivoaCriptografar.showOpenDialog(null);
				if (fileStatus == JFileChooser.APPROVE_OPTION) {
					File fileOpened = arquivoaCriptografar.getSelectedFile();
					Alfabeto novoAlfabeto = new Alfabeto();
					Criptografar arquivoCriptografado = new Criptografar();
					if (!senhaCriptografia.getText().isBlank() && !codigoSenha.getText().isBlank()) {
						try {
							novoAlfabeto.criaAlfabeto(senhaCriptografia.getText(),
									Integer.parseInt(codigoSenha.getText()));
						} catch (Exception e2) {
							senhaCriptografia.setText("PRREENCHA");
							codigoSenha.setText("CORRETAMENTE");
						}
					}
					try {
						arquivoCriptografado.criptografarSenhas(novoAlfabeto, fileOpened.getAbsolutePath().toString());
						statusArquivoCrip.setText("Arquivo criptografado com sucesso!");
					} catch (IOException e1) {
						statusArquivoCrip.setText("Erro ao criptografar o arquivo!");
					}
				} else {
					statusArquivoCrip.setText("Não foi possível abrir o arquivo!");
				}
				senhaCriptografia.setEnabled(true);
				codigoSenha.setEnabled(true);
				criptografarArquivo.setEnabled(false);
				addCriptografia.setEnabled(true);
			}
		});

		addCriptografia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				senhaCriptografia.setEnabled(false);
				codigoSenha.setEnabled(false);
				criptografarArquivo.setEnabled(true);
				addCriptografia.setEnabled(false);
			}
		});

		criptografarSenha.add(criptografarArquivo, "flowx,cell 0 2,alignx center");
		criptografarSenha.add(infSenhaCriptografia, "flowx,cell 0 1,alignx center");
		criptografarSenha.add(senhaCriptografia, "cell 0 1");
		criptografarSenha.add(infCodigo, "cell 0 1,alignx center");
		criptografarSenha.add(codigoSenha, "cell 0 1,alignx center");
		criptografarSenha.add(addCriptografia, "cell 0 1");
		criptografarSenha.add(statusArquivoCrip, "cell 0 2");
		criptografarSenha.add(tituloCriptografa, "cell 0 0,alignx center,aligny center");
	}

	/*
	 * Criação do menu responsável pela visualização de um arquivo encripatado
	 */
	private void criaDescriptografarSenha() {
		visualizarSenhas = new JPanel();
		visualizarSenhas.setBackground(new Color(255, 255, 255));
		visualizarSenhas.setLayout(new MigLayout("width 20sp:65sp:65sp, fill", "[grow]", "[][][][]"));
		visualizarSenhas.setBackground(Color.white);
//		mainPanel.add(visualizarSenhas, "east");

		JTextField codigoDescriptografar = new JTextField();
		JTextField infSenhaDescriptografar = new JTextField();
		JLabel tituloVisualizarSenhas = new JLabel("Visualizar Senhas Criptografadas");
		JLabel infSenhaDescripografar = new JLabel("Informe a Senha do arquivo:");
		JLabel infcodigoDescriptografar = new JLabel("Informe o código do arquivo");
		JLabel statusDescriptografado = new JLabel("Arquivo ainda não aberto");
		JButton abrirDescriptografado = new JButton("Abrir arquivo");
		JButton salvarsenhaDescriptografar = new JButton("Salvar Senha ");
		JTextPane senhasDescriptografadas = new JTextPane();

		tituloVisualizarSenhas.setHorizontalAlignment(SwingConstants.CENTER);
		tituloVisualizarSenhas.setFont(fontes.getFontGrande());

		codigoDescriptografar.setColumns(10);

		abrirDescriptografado.setEnabled(false);

		infSenhaDescriptografar.setColumns(10);

		senhasDescriptografadas.setText("Suas Senhas irão Aparecer aqui");
		senhasDescriptografadas.setSize(new Dimension(50, 100));
		senhasDescriptografadas.setEditable(false);
		senhasDescriptografadas.setAlignmentX(CENTER_ALIGNMENT);
		senhasDescriptografadas.setAlignmentY(CENTER_ALIGNMENT);

		this.criaBotoesMenu(salvarsenhaDescriptografar);
		this.criaBotoesMenu(abrirDescriptografado);
		
		this.criaCaixaSelecao(infSenhaDescriptografar);
		this.criaCaixaSelecao(codigoDescriptografar);
		
		infSenhaDescripografar.setFont(fontes.getFontMedia());
		infcodigoDescriptografar.setFont(fontes.getFontMedia());
		statusDescriptografado.setFont(fontes.getFontPequena());
		senhasDescriptografadas.setFont(fontes.getFontPequena());
	
		
		salvarsenhaDescriptografar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!infSenhaDescriptografar.getText().isBlank() && !codigoDescriptografar.getText().isBlank()) {
					infSenhaDescriptografar.setEnabled(false);
					codigoDescriptografar.setEnabled(false);
					salvarsenhaDescriptografar.setEnabled(false);
					abrirDescriptografado.setEnabled(true);
				}
			}
		});

		abrirDescriptografado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser arquivoaDescriptografar = new JFileChooser();
				arquivoaDescriptografar.setFileSelectionMode(JFileChooser.FILES_ONLY);
				arquivoaDescriptografar.setAcceptAllFileFilterUsed(false);
				arquivoaDescriptografar.setCurrentDirectory(new File("C:\\Users\\marta\\OneDrive\\Área de Trabalho"));
				arquivoaDescriptografar.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {

						return "Arquivos txt";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						} else {
							return f.getName().toLowerCase().endsWith(".txt");
						}
					}
				});
				int fileStatus = arquivoaDescriptografar.showOpenDialog(null);
				if (fileStatus == JFileChooser.APPROVE_OPTION) {
					File fileOpened = arquivoaDescriptografar.getSelectedFile();
					Alfabeto novoAlfabeto = new Alfabeto();
					Criptografar arquivoCriptografado = new Criptografar();

					try {
						novoAlfabeto.criaAlfabeto(infSenhaDescriptografar.getText(),
								Integer.parseInt(codigoDescriptografar.getText()));
					} catch (Exception e2) {
						infSenhaDescriptografar.setText("PRREENCHA");
						codigoDescriptografar.setText("CORRETAMENTE");
					}

					try {
						senhasDescriptografadas.setText(arquivoCriptografado.descriptografarSenhas(novoAlfabeto, fileOpened.toString(), false));
						statusDescriptografado.setText("Arquivo descriptografado com sucesso!");
					} catch (IOException e1) {
						statusDescriptografado.setText("Erro ao descriptografar o arquivo!");
						infSenhaDescriptografar.setEnabled(true);
						codigoDescriptografar.setEnabled(true);
						salvarsenhaDescriptografar.setEnabled(true);
					}
				} else {
					statusDescriptografado.setText("Não foi possível abrir o arquivo!");
					infSenhaDescriptografar.setEnabled(true);
					codigoDescriptografar.setEnabled(true);
					salvarsenhaDescriptografar.setEnabled(true);
				}
			}
		});

		visualizarSenhas.add(infcodigoDescriptografar, "flowx,cell 0 1,alignx center");
		visualizarSenhas.add(tituloVisualizarSenhas, "cell 0 0,alignx center,aligny center");
		visualizarSenhas.add(infSenhaDescriptografar, "cell 0 1,alignx center");
		visualizarSenhas.add(infSenhaDescripografar, "cell 0 1,alignx center");
		visualizarSenhas.add(codigoDescriptografar, "cell 0 1,alignx center");
		visualizarSenhas.add(salvarsenhaDescriptografar, "cell 0 1,alignx center,aligny center");
		visualizarSenhas.add(abrirDescriptografado, "flowx,cell 0 2,alignx center");
		visualizarSenhas.add(statusDescriptografado, "cell 0 2,aligny center");
		visualizarSenhas.add(senhasDescriptografadas, "cell 0 3,alignx center,aligny center");
	}

	private void criaBotoesMenu(JButton tmp) {
		Insets margin = new Insets(10, 30, 10, 30);
		boolean borderPainted = false;
		Color backgroundColor = new Color(255, 255, 255);
		boolean focusable = false;
		boolean focusPainted = false;
		boolean focusTraversalKeysEnabled = false;
		Font font = fontes.getFontMedia();

		tmp.setMargin(margin);
		tmp.setBorderPainted(borderPainted);
		tmp.setBackground(backgroundColor);
		tmp.setFocusable(focusable);
		tmp.setFocusPainted(focusPainted);
		tmp.setFocusTraversalKeysEnabled(focusTraversalKeysEnabled);
		tmp.setFont(font);
		tmp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		tmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tmp.setBackground(Color.black);
				tmp.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tmp.setBackground(Color.white);
				tmp.setForeground(Color.black);
			}
		});
	}

	private void criaSelecaoSenhas(JCheckBox tmp) {
		tmp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tmp.setFocusPainted(false);
		tmp.setBackground(new Color(255, 255, 255));
		tmp.setFont(fontes.getFontPequena());
		tmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tmp.isSelected()) {
					tmp.setBackground(Color.BLACK);
					tmp.setForeground(Color.white);
					
				}else {
					tmp.setBackground(Color.white);
					tmp.setForeground(Color.BLACK);
				}
			}
		});
		
	}
	
	private void criaCaixaSelecao(JTextField tmp) {
		tmp.setFont(fontes.getFontPequena());
		tmp.setBackground(Color.white);
		
		
		
	}

}
