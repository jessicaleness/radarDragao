package lógica;

import java.awt.Point;
import painéis.PainelMapa;
import painéis.PainelPrincipal;
import painéis.PainelTipoBusca;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;


/**
 * @author Jhonatan Nabhan
 */
public class RadarDoDragao {

    /* VARIÁVEIS GLOBAIS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
    static final int TAMANHO_MAPA = 42;
    public static char[][] Mapa = new char[TAMANHO_MAPA][TAMANHO_MAPA];
    static Esfera[] esferas = new Esfera[7];
    public JFrame mapa = new JFrame("Mapa");
    JFrame pp = new JFrame("Simulador do Radar das Esferas do Dragão");
    PainelMapa painelMapa = new PainelMapa();
    PainelPrincipal painelPrincipal = new PainelPrincipal();
    public static RadarDoDragao radar;

    List<Point2D> locaisVisitados = new ArrayList<>();
    List<Point2D> locaisParaVisitar = new ArrayList<>();
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fim variáveis    

    /* MÉTODOS DA CLASSE >> */

    /* CARREGA O MAPA DE UM ARQUIVO .txt  >>>>>>>>>>>>>>>>>>>>>> */
    public static void LoadMapa(String filename) throws IOException {
        
    /* Variáveis --------------------------------- */
    int count_line = 0;
    String linhaArquivo;
    BufferedReader leArquivo;
    boolean fimArquivo = false;
    StringTokenizer leLinhaArquivo;
    /* ------------------------------------------- */

        leArquivo = new BufferedReader( new FileReader( filename ) );

        FileInputStream entrada = new FileInputStream(filename);        
        InputStreamReader entradaFormatada = new InputStreamReader(entrada);

        /* Lê as linhas da matriz mapa  */
        for(int i = 0; i < TAMANHO_MAPA; i++) {	  
            linhaArquivo = leArquivo.readLine();

            if( linhaArquivo == null ){
                fimArquivo = true;
                break;}	

            leLinhaArquivo = new StringTokenizer(linhaArquivo);

            // le os M elementos de linhaArquivo
            for(int j = 0; j < TAMANHO_MAPA; j++)
                    Mapa[i][j] = linhaArquivo.charAt(j);}}
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* CONTRUTOR DA CLASSE >>>>>>>>>> */
    public static void RadarDoDragao() {} 
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* -- >>>>>>>>>> */
    public static void ReviveKuririn() {}
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    /* DESENHA O MAPA NA TELA EM UMA MATRIZ 42x42 >> */
    public void DesenhaMap() {    
        mapa.add(painelMapa);
        mapa.setSize(645, 690);
        mapa.setLocationRelativeTo(null);
        mapa.setVisible(true);
        BuscaEsferas(); // O usuário seleciona o tipo de busca
    } // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* RETORNA A MATRIZ "Mapa" À CLASSE SOLICITANTE >> */
    public static char[][] getMapa() {
        return Mapa;}
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* SORTEIA AS ESFERAS NO MAPA >> */
    public static void SorteiaEsferas() {
        /* O método cria 7 esferas e seta coordenadas
        X e Y aleatórias para cada esfera */
        
        for(int i = 0; i < 7; i++)
                esferas[i] = new Esfera();
        Random gerador = new Random();
        for(int i = 0; i < 7; i++) {
            esferas[i].setXY(gerador.nextInt(42), gerador.nextInt(42));

            // System.out.println("Esfera " + i + ": [" + esferas[i].x + ", " + esferas[i].y + "]");
        }
    }
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* RETORNA O VETOR DE ESFERAS À CLASSE SOLICITANTE >> */
    public static Esfera[] getEsferas() {
        return esferas;}
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* O USUÁRIO SELECIONA O TIPO DE BUSCA >> */
    public static void BuscaEsferas() {
        /* Exibe um radioButton para seleção do tipo de busca */
        JFrame tipoBusca = new JFrame("Selecione o tipo de busca cega"); // Cria o frame
        PainelTipoBusca painelTipoBusca = new PainelTipoBusca(); // Cria o painel
        
        // Opções de busca:
        JRadioButton b1 = new JRadioButton("Profundidade");
        JRadioButton b2 = new JRadioButton("Bidirecional");
        JRadioButton b3 = new JRadioButton("A*");
        
        // Adiciona os botões
        painelTipoBusca.add(b1);
        painelTipoBusca.add(b2);
        painelTipoBusca.add(b3);
        
        // Seleciona o tipo de busca na tela
        JOptionPane.showMessageDialog(null, painelTipoBusca, "Selecione o tipo de busca", 3);
        
        Busca busca = new Busca(); 
        
        if(b1.isSelected()) {
            // Busca em profundidade
               // Varre a matriz do primeiro elemento da esquerda até o último da direita
                  // Para cada posição, verifica se tem esfera                  
                  for(int a = 0; a < 42; a++) {
                    for(int b = 0; b < 42; b++) {
                        System.out.println(a + " " + b);
                        if(busca.hasEsfera(a, b)) {
                            JOptionPane.showMessageDialog(null, "Esfera encontrada!\n"
                                    + "Posição\n"
                                    + "X = " + a + "\n"
                                    + "Y = " + b + "\n Clique em OK para continuar");
                        }
                    }                 
                  }
        }
        if(b2.isSelected()) {
            // Busca bidirecional
                // Mexe as duas posições
            for(int a = 0; a <= 20; a++) {
                for(int b = 0; b <= 41; b++) {
                    if(busca.hasEsfera(a, b)) {
                        JOptionPane.showMessageDialog(null, "Esfera encontrada!\n"
                                        + "Posição\n"
                                        + "X = " + a + "\n"
                                        + "Y = " + b + "\n Clique em OK para continuar");
                    }
                    if(busca.hasEsfera(41-a, 41-b)) {
                        JOptionPane.showMessageDialog(null, "Esfera encontrada!\n"
                                        + "Posição\n"
                                        + "X = " + (41-a) + "\n"
                                        + "Y = " + (41-b) + "\n Clique em OK para continuar");
                    }
                }
            }
        }
        
        if(b3.isSelected()) {
            Integer qtdEsferasEncontradas = 0;
            // O agente inicia na casa do Mestre Kame
            // Ponto vermelho na região central do mapa
            Point2D kameHouse = new Point(19, 19);

            radar.locaisParaVisitar.add(kameHouse);         // adiciona ponto de partida

            while(radar.locaisParaVisitar.size() > 0 || qtdEsferasEncontradas < 7) {
                Point2D local = radar.locaisParaVisitar.remove(0);
                qtdEsferasEncontradas = radar.aEstrela(local, busca, qtdEsferasEncontradas);
            }

            // limpa as listas auxiliares
            radar.locaisParaVisitar = new ArrayList<>();
            radar.locaisVisitados = new ArrayList<>();
        }
    }

    private static Integer aEstrela(Point2D ponto, Busca busca, Integer qtdEsferasEncontradas) {
        radar.locaisVisitados.add(ponto);
        if (busca.hasEsfera((int)ponto.getX(), (int)ponto.getY())) {
            JOptionPane.showMessageDialog(null, "Esfera encontrada!\n"
                                + "Posição\n"
                                + "X = " + (int)ponto.getX() + "\n"
                                + "Y = " + (int)ponto.getY() + "\n Clique em OK para continuar");
            qtdEsferasEncontradas++;
            // System.out.println("Esferas: " + qtdEsferasEncontradas);
        }
        adicionaVizinhos(ponto);
        return qtdEsferasEncontradas;
    }

    private static void adicionaVizinhos(Point2D ponto) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                Point2D vizinho = new Point((int)ponto.getX()+x, (int)ponto.getY()+y);
                if (isPontoValido(vizinho) && !radar.locaisVisitados.contains(vizinho) && !radar.locaisParaVisitar.contains(vizinho)) {
                    radar.locaisParaVisitar.add(vizinho);
                }
            }
        }
    }

    private static Boolean isPontoValido(Point2D ponto) {
        return ponto.getX() >= 0 && ponto.getX() < radar.TAMANHO_MAPA &&
               ponto.getY() >= 0 && ponto.getY() < radar.TAMANHO_MAPA;
    }

    
    
    /* PAINEL PRINCIPAL (PLAY) >> */
    private void PainelPrincipal() {
        ImageIcon imgPlay;
        imgPlay = new ImageIcon("resource/play_icon.png");
        JButton btnPlay = new JButton(imgPlay);
        EventoBotao btEvnt = new EventoBotao();
        btnPlay.addActionListener((ActionListener) btEvnt);
        pp.add(painelPrincipal);
        pp.setSize(350, 250);
        pp.setLocationRelativeTo(null);
        pp.add(btnPlay);
        pp.setVisible(true);
        pp.setDefaultCloseOperation(EXIT_ON_CLOSE); 
    }
    // << FIM MÉTODOS *********************************************************/
    
    /* MAIN >> */

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        LoadMapa("resource/Mapa.txt"); // especifica aqui o diretório do mapa
        radar = new RadarDoDragao();
        radar.PainelPrincipal(); // este método leva aos próximos métodos da execução. 
        ReviveKuririn(); 
    }
    // << FIM *************************************************************************************************
}
