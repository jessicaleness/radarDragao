package logica;

import java.awt.Point;
import paineis.PainelMapa;
import paineis.PainelPrincipal;
import paineis.PainelTipoBusca;
import java.awt.event.ActionListener;
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

    /* VARIAVEIS GLOBAIS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
    static final int TAMANHO_MAPA = 42;
    public static char[][] Mapa = new char[TAMANHO_MAPA][TAMANHO_MAPA];
    static Esfera[] esferas = new Esfera[7];
    public JFrame mapa = new JFrame("Mapa");
    JFrame pp = new JFrame("Simulador do Radar das Esferas do Dragao");
    PainelMapa painelMapa = new PainelMapa();
    PainelPrincipal painelPrincipal = new PainelPrincipal();
    public static RadarDoDragao radar;

    List<Ponto> locaisVisitados = new ArrayList<>();
    List<Ponto> locaisParaVisitar = new ArrayList<>();
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fim variaveis    

    /* METODOS DA CLASSE >> */

 /* CARREGA O MAPA DE UM ARQUIVO .txt  >>>>>>>>>>>>>>>>>>>>>> */
    public static void LoadMapa(String filename) throws IOException {

        /* Variaveis --------------------------------- */
        int count_line = 0;
        String linhaArquivo;
        BufferedReader leArquivo;
        boolean fimArquivo = false;
        StringTokenizer leLinhaArquivo;
        /* ------------------------------------------- */

        leArquivo = new BufferedReader(new FileReader(filename));

        FileInputStream entrada = new FileInputStream(filename);
        InputStreamReader entradaFormatada = new InputStreamReader(entrada);

        /* Le as linhas da matriz mapa  */
        for (int i = 0; i < TAMANHO_MAPA; i++) {
            linhaArquivo = leArquivo.readLine();

            if (linhaArquivo == null) {
                fimArquivo = true;
                break;
            }

            leLinhaArquivo = new StringTokenizer(linhaArquivo);

            // le os M elementos de linhaArquivo
            for (int j = 0; j < TAMANHO_MAPA; j++) {
                Mapa[i][j] = linhaArquivo.charAt(j);
            }
        }
    }
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* CONTRUTOR DA CLASSE >>>>>>>>>> */
    public static void RadarDoDragao() {
    }
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* -- >>>>>>>>>> */
    public static void ReviveKuririn() {
    }
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* DESENHA O MAPA NA TELA EM UMA MATRIZ 42x42 >> */
    public void DesenhaMap() {
        mapa.add(painelMapa);
        mapa.setSize(645, 690);
        mapa.setLocationRelativeTo(null);
        mapa.setVisible(true);
        BuscaEsferas(); // O usuario seleciona o tipo de busca
    } // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* RETORNA A MATRIZ "Mapa" A CLASSE SOLICITANTE >> */
    public static char[][] getMapa() {
        return Mapa;
    }
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* SORTEIA AS ESFERAS NO MAPA >> */
    public static void SorteiaEsferas() {
        /* O metodo cria 7 esferas e seta coordenadas
        X e Y aleatorias para cada esfera */

        for (int i = 0; i < 7; i++) {
            esferas[i] = new Esfera();
        }
        Random gerador = new Random();
        for (int i = 0; i < 7; i++) {
            esferas[i].setXY(gerador.nextInt(42), gerador.nextInt(42));

            // System.out.println("Esfera " + i + ": [" + esferas[i].x + ", " + esferas[i].y + "]");
        }
    }
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* RETORNA O VETOR DE ESFERAS A CLASSE SOLICITANTE >> */
    public static Esfera[] getEsferas() {
        return esferas;
    }
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /* O USUARIO SELECIONA O TIPO DE BUSCA >> */
    public static void BuscaEsferas() {
        /* Exibe um radioButton para selecao do tipo de busca */
        JFrame tipoBusca = new JFrame("Selecione o tipo de busca cega"); // Cria o frame
        PainelTipoBusca painelTipoBusca = new PainelTipoBusca(); // Cria o painel

        // Opcoes de busca:
        JRadioButton b1 = new JRadioButton("Profundidade");
        JRadioButton b2 = new JRadioButton("Bidirecional");
        JRadioButton b3 = new JRadioButton("A*");

        // Adiciona os botoes
        painelTipoBusca.add(b1);
        painelTipoBusca.add(b2);
        painelTipoBusca.add(b3);

        // Seleciona o tipo de busca na tela
        JOptionPane.showMessageDialog(null, painelTipoBusca, "Selecione o tipo de busca", 3);

        Busca busca = new Busca();

        if (b1.isSelected()) {
            // Busca em profundidade
            // Varre a matriz do primeiro elemento da esquerda ate o ultimo da direita
            // Para cada posicao, verifica se tem esfera                  
            for (int a = 0; a < 42; a++) {
                for (int b = 0; b < 42; b++) {
                    System.out.println(a + " " + b);
                    if (busca.hasEsfera(a, b)) {
                        JOptionPane.showMessageDialog(null, "Esfera encontrada!\n"
                                + "Posicao\n"
                                + "X = " + a + "\n"
                                + "Y = " + b + "\n Clique em OK para continuar");
                    }
                }
            }
        }
        if (b2.isSelected()) {
            // Busca bidirecional
            // Mexe as duas posicoes
            for (int a = 0; a <= 20; a++) {
                for (int b = 0; b <= 41; b++) {
                    if (busca.hasEsfera(a, b)) {
                        JOptionPane.showMessageDialog(null, "Esfera encontrada!\n"
                                + "Posicao\n"
                                + "X = " + a + "\n"
                                + "Y = " + b + "\n Clique em OK para continuar");
                    }
                    if (busca.hasEsfera(41 - a, 41 - b)) {
                        JOptionPane.showMessageDialog(null, "Esfera encontrada!\n"
                                + "Posicao\n"
                                + "X = " + (41 - a) + "\n"
                                + "Y = " + (41 - b) + "\n Clique em OK para continuar");
                    }
                }
            }
        }

        if (b3.isSelected()) {
            Integer qtdEsferasEncontradas = 0;
            // O agente inicia na casa do Mestre Kame
            // Ponto vermelho na regiao central do mapa

            Ponto kameHouse = new Ponto(19, 19);

            radar.locaisParaVisitar.add(kameHouse);         // adiciona ponto de partida

            while (radar.locaisParaVisitar.size() > 0 || qtdEsferasEncontradas < 7) {
                Ponto local = radar.locaisParaVisitar.remove(0);
                qtdEsferasEncontradas = radar.aEstrela(local, busca, qtdEsferasEncontradas);
            }

            // limpa as listas auxiliares
            radar.locaisParaVisitar = new ArrayList<>();
            radar.locaisVisitados = new ArrayList<>();
        }
    }

    private static Integer aEstrela(Ponto ponto, Busca busca, Integer qtdEsferasEncontradas) {
        radar.locaisVisitados.add(ponto);

        if (busca.hasEsfera(ponto.getX(), ponto.getY())) {
            JOptionPane.showMessageDialog(null, "Esfera encontrada!\n"
                    + "Posicao\n"
                    + "Peso = " + ponto.getPeso() + "\n"
                    + "X = " + ponto.getX() + "\n"
                    + "Y = " + ponto.getY() + "\n Clique em OK para continuar");
            qtdEsferasEncontradas++;
//            System.out.println("Esferas: " + qtdEsferasEncontradas);
        }
        adicionaVizinhos(ponto);
        return qtdEsferasEncontradas;
    }

    private static void adicionaVizinhos(Ponto ponto) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                Integer coordXvizinho = ponto.getX() + x;
                Integer coordYvizinho = ponto.getY() + y;
                Ponto vizinho = new Ponto(coordXvizinho, coordYvizinho, ponto.getPeso() + findPeso(coordXvizinho, coordYvizinho));
                if (isPontoValido(vizinho) && !radar.locaisVisitados.contains(vizinho) && !radar.locaisParaVisitar.contains(vizinho)) {
                    radar.locaisParaVisitar.add(vizinho);
                }
            }
        }
    }

    private static Boolean isPontoValido(Ponto ponto) {
        return ponto.getX() >= 0 && ponto.getX() < radar.TAMANHO_MAPA
                && ponto.getY() >= 0 && ponto.getY() < radar.TAMANHO_MAPA;
    }

    private static Integer findPeso(Integer x, Integer y) {

        if (isPontoValido(new Ponto(x, y))) {
            char c = Mapa[x][y];
            return getPeso(c);
        } else {
            return 0;
        }
    }

    private static Integer getPeso(char c) {
        switch (c) {
            case 'A':
                return 10;
            case 'G':
                return 1;
            case 'M':
                return 60;
            default:
                return 0;
        }
    }

    private static void exibeLista(List<Ponto> list) {
        for (Ponto p : list) {
            System.out.print(" [" + p.getX() + "," + p.getY() + "]");
        }
        System.out.println();
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
    // << FIM METODOS *********************************************************/

    /* MAIN >> */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        LoadMapa("resource/Mapa.txt"); // especifica aqui o diretorio do mapa
        radar = new RadarDoDragao();
        radar.PainelPrincipal(); // este metodo leva aos proximos metodos da execucao. 
        ReviveKuririn();
    }
    // << FIM *************************************************************************************************
}
