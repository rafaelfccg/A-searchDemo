import java.awt.Frame;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.BorderLayout;
import java.awt.Button;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class projetoSI extends Frame {

	private static final long serialVersionUID = 1L;
	
	private final static int qNada = 0; //branco
	private final static int qInicio = 1; //amarelo
	private final static int qFim = 2; //vermelho
	private final static int qBarreira = 3; //preto
	private final static int qFronteira = 4; //azul
	private final static int qExpandido = 5; //ciano
	private final static int qCaminho = 6; //verde
	
	private int botaoIndex = qNada;
	
	public double w = 1;
	public int tamanho = 15;
	
	private int[][] espacos = new int[tamanho][tamanho];
	public Button[][] b = new Button[tamanho][tamanho];
	
	private static projetoSI f0;

	public static void main(String[] args) {

		f0 = new projetoSI();
		f0.setTitle("A* e wA*");
		f0.setLocation(0, 0);
		f0.setVisible(true);

	}

	projetoSI() {
		
		for (int i = 0; i < tamanho; i++) {
			for (int j = 0; j < tamanho; j++) {
				espacos[i][j] = 0;
			}
		}
		
		setSize(657, 713);

		Panel p = new Panel();
		Panel quadrados = new Panel();
		quadrados.setLayout(new GridLayout(tamanho, tamanho));
		
		for (int i = 0; i < tamanho; i++) {
			for (int j = 0; j < tamanho; j++) {
				b[i][j] = new Button();
				b[i][j].setBackground(Color.white);
				quadrados.add(b[i][j]);
				b[i][j].addActionListener(new ActionListener() {
					public void actionPerformed (ActionEvent e) {
						Button sourceBtn = (Button) e.getSource();
						
						switch (botaoIndex) {
						case 0:
							sourceBtn.setBackground(Color.white);
							break;
						case 1: 
							sourceBtn.setBackground(Color.yellow);
							for (int i = 0; i < tamanho; i++) {
								for (int j = 0; j < tamanho; j++) {
									if (b[i][j].getBackground() == Color.yellow) {
										if (b[i][j] != sourceBtn) {
											b[i][j].setBackground(Color.white);
										}
									}
								}
							}
							break;
						case 2: 
							sourceBtn.setBackground(Color.red); 
							for (int i = 0; i < tamanho; i++) {
								for (int j = 0; j < tamanho; j++) {
									if (b[i][j].getBackground() == Color.red) {
										if (b[i][j] != sourceBtn) {
											b[i][j].setBackground(Color.white);
										}
									}
								}
							}
							break;
						case 3: 
							sourceBtn.setBackground(Color.black); 
							break;
						case 4: 
							sourceBtn.setBackground(Color.blue); 
							break;
						case 5: 
							sourceBtn.setBackground(Color.cyan); 
							break;
						case 6: 
							sourceBtn.setBackground(Color.green); 
							break;
						}
						
					}
				});
			}
			
			add(quadrados);
		}

		/*
		final TextField tamanhoTF = new TextField ("10");
		p.add(tamanhoTF);
		
		Button atualizarTamanho = new Button("Atualizar tamanho");
		p.add(atualizarTamanho);
		*/
		Label wL = new Label ("w:");
		p.add(wL);

		final TextField wTF = new TextField ("1");
		p.add(wTF);
		
		Button atualizarW = new Button("Atualizar w");
		p.add(atualizarW);
		
		Button cancelar = new Button("Cancelar");
		p.add(cancelar);
		
		Button inicio = new Button("Início");
		p.add(inicio);
		
		Button fim = new Button("Fim");
		p.add(fim);
		
		Button barreira = new Button("Barreira");
		p.add(barreira);
		
		Button executar = new Button("Executar");
		p.add(executar);
		/*
		atualizarTamanho.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
				int novoTamanho = Integer.parseInt(tamanhoTF.getText());
				tamanho = novoTamanho;
				System.out.println(tamanho);
				
			}	
		});
		*/
		atualizarW.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				double novoW = Double.parseDouble(wTF.getText());
				w = novoW;
				System.out.println(w);
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				for (int i = 0; i < tamanho; i++) {
					for (int j = 0; j < tamanho; j++) {
						b[i][j].setBackground(Color.white);
					}
				}
				botaoIndex = qNada;
			}
		});
		
		inicio.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				botaoIndex = qInicio;
			}
		});
		
		fim.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				botaoIndex = qFim;
			}
		});
		
		barreira.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				botaoIndex = qBarreira;
			}
		});
		
//		private final static int qNada = 0; //branco
//		private final static int qInicio = 1; //amarelo
//		private final static int qFim = 2; //vermelho
//		private final static int qBarreira = 3; //preto
//		private final static int qFronteira = 4; //azul
//		private final static int qExpandido = 5; //ciano
//		private final static int qCaminho = 6; //verde
		executar.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				int[][] espaco = new int[tamanho][tamanho];
				Node start = new Node();
				Node destiny = new Node();
				for (int i = 0; i < tamanho; i++) {
					for (int j = 0; j < tamanho; j++) {
						if(b[i][j].getBackground() == Color.black){
							espaco[i][j] = 3;
						}else if(b[i][j].getBackground() == Color.yellow){
							espaco[i][j] = 1;
							start.x = i;
							start.y = j;
							start.cost = 0;
						}else if(b[i][j].getBackground() == Color.red){
							espaco[i][j] = 2;
							destiny.x = i;
							destiny.y = j;
							destiny.cost = 0;
						}
					}
				}
				BuscaClasse bc = new BuscaClasse(espaco);
				bc.paintDelegate = f0;
				bc.start = start;
				bc.destiny = destiny;
				bc.h = new EuclidianDistance(destiny.x, destiny.y);
				bc.aEstrela(w);
				
				
			}
		});
		
		p.setBackground(Color.orange);
		add(p, BorderLayout.NORTH);
		add(quadrados);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

}
