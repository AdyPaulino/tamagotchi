import java.awt.*;
import java.awt.image.*;
import java.applet.*;

public class Ovo extends Applet implements Runnable {
    public void init() {
        //getContentPane()setLayout(null);
        setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        setIgnoreRepaint(true);
        add(new Button("Sorvete"));
        add(new Button("Cenoura"));
        add(new Button("Sanduiche"));
        add(new Button("Leite"));
        add(new Button("Carinho"));
        add(new Button("Bola"));
        add(new Button("Livro"));
        add(new Button("Musica"));
        add(new Button("Medicamento"));
        add(new Button("Apagar Luz"));
        
        backBuffer = new BufferedImage(DISPLAY_WIDTH, DISPLAY_HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        bbGraphics = (Graphics2D) backBuffer.getGraphics();
        
        // load in animation image sheet
        imagemfundo = getImage(getCodeBase(), "sheetcenario.gif");
        sheetovo = getImage(getCodeBase(), "sheetovo.gif");//"numbersheet.gif");
        sheetcriancafeliz = getImage(getCodeBase(), "sheetcriancafeliz.gif");//"numbersheet.gif");
        sheetcriancafome = getImage(getCodeBase(), "sheetcriancafome.gif");//"numbersheet.gif");
        sheetcriancasono = getImage(getCodeBase(), "sheetcriancasono.gif");
        sheetcriancadoente = getImage(getCodeBase(), "sheetcriancadoente.gif");
        sheetcriancatriste = getImage(getCodeBase(), "sheetcriancatriste.gif");
        sheetadultofeliz = getImage(getCodeBase(), "sheetadultofeliz.gif");
        sheetadultofome = getImage(getCodeBase(), "sheetadultofome.gif");
        sheetadultosono = getImage(getCodeBase(), "sheetadultosono.gif");
        sheetadultodoente = getImage(getCodeBase(), "sheetadultodoente.gif");
        sheetadultotriste = getImage(getCodeBase(), "sheetadultotriste.gif");
        sheetsorvete = getImage(getCodeBase(), "sheetsorvete.gif");
        sheetcenoura = getImage(getCodeBase(), "sheetcenoura.gif");
        sheetleite = getImage(getCodeBase(), "sheetleite.gif");
        sheetsanduiche = getImage(getCodeBase(), "sheetsanduiche.gif");
        sheetseringa = getImage(getCodeBase(), "sheetseringa.gif");
        sheetbola = getImage(getCodeBase(), "sheetbola.gif");
        sheetcarinho = getImage(getCodeBase(), "sheetcarinho.gif");  
        sheetnotasmusicais = getImage(getCodeBase(), "sheetmusicais.gif");        
        sheetlivro = getImage(getCodeBase(), "sheetlivro.gif");                      
        sheetcenarionoite = getImage(getCodeBase(), "sheetcenarionoite.gif");
 
        MediaTracker m = new MediaTracker(this);
        m.addImage(imagemfundo, 0);
        m.addImage(sheetovo, 0);
        m.addImage(sheetcriancafeliz, 0);
        m.addImage(sheetcriancafome, 0);
        m.addImage(sheetcriancasono, 0);
        m.addImage(sheetcriancadoente, 0);
        m.addImage(sheetcriancatriste, 0);
        m.addImage(sheetcenoura, 0);
        m.addImage(sheetadultofeliz, 0);
        m.addImage(sheetadultofome, 0);
        m.addImage(sheetadultosono, 0);
        m.addImage(sheetadultodoente, 0);
        m.addImage(sheetadultotriste, 0);
        m.addImage(sheetsorvete, 0);
        m.addImage(sheetleite, 0);
        m.addImage(sheetsanduiche, 0);
        m.addImage(sheetseringa, 0);
        m.addImage(sheetbola, 0);
        m.addImage(sheetcarinho, 0);
        m.addImage(sheetnotasmusicais, 0);
        m.addImage(sheetlivro, 0);
        
        
        //logica = new Logica();
        //botao = new Botao();
        
        
        try {
            m.waitForID(0);
        } catch(InterruptedException e) {
            System.out.println(e);
        }
    }
    
    public void start() {
        loop = new Thread(this);
        loop.start();
    }
    
    public void stop() {
        loop = null;
    }
    
    public void run() {
        long startTime, waitTime, elapsedTime;
        int delayTime = 500;
        //int time = (delayTime*2);
        
        
        Thread thisThread = Thread.currentThread();
        
        while(loop==thisThread) {
            i++;
            
            //contadorfome = contadorfome - 10; //
            //contfome = contfome - 30;
            cont = Math.random() * 5; //logica.geraNumero();
            contador = (int)cont;
            System.out.println("Numero: "+contador);
            System.out.println("Satisfacao: "+satisfacao);
            satisfacao = satisfacao - contador;
            contadorfome = contadorfome - contador;
            System.out.println("contadorfome: "+contadorfome);
            contadorsono = contadorsono -(1);
            System.out.println("contadorsono: "+contadorsono);
            contadorsaude = contadorsaude - contador;
            System.out.println("contadorsaude: "+contadorsaude);
            contadorestado = contadorestado - contador;
            System.out.println("contadorestado: "+contadorestado);
            System.out.println(idade);
            System.out.println(i);
            
            {
                startTime = System.currentTimeMillis();
                
                changeState();
                
                // render to back buffer now
                render(bbGraphics);
                
                // render back buffer image to screen
                Graphics g = getGraphics();
                g.drawImage(backBuffer, 0, 0, null);
                g.dispose();
                
                //  handle frame rate
                elapsedTime = System.currentTimeMillis() - startTime;
                waitTime = Math.max(delayTime - elapsedTime, 5);
                
                try {
                    Thread.sleep(waitTime);
                } catch(InterruptedException e) {}
            }
        }
        
        //CriancaFeliz();
    }
    
    public void changeState() {
        
        AudioClip ac = getAudioClip(getDocumentBase(), "bipe.au");
        
        if (counter<1) counter++;
        else counter = 0;
        
        //sorvete
        if (comida.equals("sorvete")) {
            if (contsorvete<2) contsorvete++;
            else contsorvete = 0;
        }
        
        if (i == 10) {
            idade = "criancafeliz";
        }
        
        if((i > 3) && (i < 100)) {
            if(satisfacao <= 0) {
                if (contadorsono <= 0) {
                    idade = "criancasono";
                    ac.play();
                }
                
                if(contadorsono > 0) {
                    if (contadorfome <= 0) {
                        idade = "criancafome";
                        ac.play();
                    } else
                        if(contadorfome > 0) {
                        if(contadorsaude <= 0) {
                            idade = "criancadoente";
                            ac.play();
                        } else
                            if(contadorsaude > 0) {
                            if(contadorestado <= 0) {
                                idade = "criancatriste";
                                ac.play();
                            }
                            }
                        }
                }
            }
            
        }
        
        if(i > 100) {
            idade = "adultofeliz";
            if(satisfacao <= 0) {
                if (contadorsono <= 0) {
                    idade = "adultosono";
                }
                if(contadorsono > 0) {
                    if (contadorfome <= 0) {
                        idade = "adultofome";
                    } else
                        if(contadorfome > 0) {
                        if(contadorsaude <= 0) {
                            idade = "adultodoente";
                        } else
                            if(contadorsaude > 0) {
                            if(contadorestado <= 0) {
                                idade = "adultotriste";
                            }
                            }
                        }
                }
                
            }
        }
    }
    
    
    public void render(Graphics g) {
        g.clearRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
        g.drawImage(imagemfundo, 0, 0, null);
        
        int srcX0 = counter*FRAME_WIDTH;
        int srcY0 = 0;
        int srcX1 = srcX0+FRAME_WIDTH;
        int srcY1 = FRAME_HEIGHT;
        
        // start pos to center in applet
        int dstX0 = (DISPLAY_WIDTH-FRAME_WIDTH)/2;
        int dstY0 = (DISPLAY_HEIGHT-FRAME_HEIGHT)-100;
        int dstX1 = dstX0+FRAME_WIDTH;
        int dstY1 = dstY0+FRAME_HEIGHT;
        
        //crianca
        int srccX0 = counter*FRAME_WIDTHc;
        int srccY0 = 0;
        int srccX1 = srccX0+FRAME_WIDTHc;
        int srccY1 = FRAME_HEIGHTc;
        
        int dstcX0 = (DISPLAY_WIDTH-FRAME_WIDTHc)-100;
        int dstcY0 = (DISPLAY_HEIGHT-FRAME_HEIGHTc)-50;
        int dstcX1 = dstcX0+FRAME_WIDTHc;
        int dstcY1 = dstcY0+FRAME_HEIGHTc;
        
        //adulto
        int srcaX0 = counter*FRAME_WIDTHa;
        int srcaY0 = 0;
        int srcaX1 = srcaX0+FRAME_WIDTHa;
        int srcaY1 = FRAME_HEIGHTa;
        
        int dstaX0 = (DISPLAY_WIDTH-FRAME_WIDTHa)-40;
        int dstaY0 = (DISPLAY_HEIGHT-FRAME_HEIGHTa)-10;
        int dstaX1 = dstaX0+FRAME_WIDTHa;
        int dstaY1 = dstaY0+FRAME_HEIGHTa;
        
        //sorvete
        int srcsX0 = counter*FRAME_WIDTHs;
        int srcsY0 = 0;
        int srcsX1 = srcsX0+FRAME_WIDTHs;
        int srcsY1 = FRAME_HEIGHTs;
        
        int dstsX0 = (DISPLAY_WIDTH-FRAME_WIDTHs)/2;
        int dstsY0 = (DISPLAY_HEIGHT-FRAME_HEIGHTs)/2;
        int dstsX1 = dstsX0+FRAME_WIDTHs;
        int dstsY1 = dstsY0+FRAME_HEIGHTs;
        
        //cenoura
        int srcceX0 = counter*FRAME_WIDTHce; //primeiro ponto do retangulo invisivel
        int srcceY0 = 0;
        int srcceX1 = srcceX0+FRAME_WIDTHce; //segundo ponto do retangulo invisivel
        int srcceY1 = FRAME_HEIGHTce;
        
        int dstceX0 = (DISPLAY_WIDTH-FRAME_WIDTHce)/2;
        int dstceY0 = (DISPLAY_HEIGHT-FRAME_HEIGHTce)/2;
        int dstceX1 = dstceX0+FRAME_WIDTHce;
        int dstceY1 = dstceY0+FRAME_HEIGHTce;
        
        //leite
        int srcleX0 = counter*FRAME_WIDTHle;
        int srcleY0 = 0;
        int srcleX1 = srcleX0+FRAME_WIDTHle;
        int srcleY1 = FRAME_HEIGHTle;
        
        int dstleX0 = (DISPLAY_WIDTH-FRAME_WIDTHle)/2;
        int dstleY0 = (DISPLAY_HEIGHT-FRAME_HEIGHTle)/2;
        int dstleX1 = dstleX0+FRAME_WIDTHle;
        int dstleY1 = dstleY0+FRAME_HEIGHTle;
        
        //sanduiche
        int srcsaX0 = counter*FRAME_WIDTHsa;
        int srcsaY0 = 0;
        int srcsaX1 = srcsaX0+FRAME_WIDTHsa;
        int srcsaY1 = FRAME_HEIGHTsa;
        
        int dstsaX0 = (DISPLAY_WIDTH-FRAME_WIDTHsa)/2;
        int dstsaY0 = (DISPLAY_HEIGHT-FRAME_HEIGHTsa)/2;
        int dstsaX1 = dstsaX0+FRAME_WIDTHsa;
        int dstsaY1 = dstsaY0+FRAME_HEIGHTsa;
        
        //seringa
        int srcseX0 = counter*FRAME_WIDTHse; //primeiro ponto do retangulo invisivel
        int srcseY0 = 0;
        int srcseX1 = srcseX0+FRAME_WIDTHse; //segundo ponto do retangulo invisivel
        int srcseY1 = FRAME_HEIGHTse;
        
        int dstseX0 = (DISPLAY_WIDTH-FRAME_WIDTHse)/2;
        int dstseY0 = (DISPLAY_HEIGHT-FRAME_HEIGHTse)/2;
        int dstseX1 = dstseX0+FRAME_WIDTHse;
        int dstseY1 = dstseY0+FRAME_HEIGHTse;
        
          //bola
        int srcboX0 = counter*FRAME_WIDTHbo; //primeiro ponto do retangulo invisivel
        int srcboY0 = 0;
        int srcboX1 = srcboX0+FRAME_WIDTHbo; //segundo ponto do retangulo invisivel
        int srcboY1 = FRAME_HEIGHTbo;
 
        int dstboX0 = (DISPLAY_WIDTH-FRAME_WIDTHbo)/2;
        int dstboY0 = (DISPLAY_HEIGHT-FRAME_HEIGHTbo)/2;
        int dstboX1 = dstboX0+FRAME_WIDTHbo;
        int dstboY1 = dstboY0+FRAME_HEIGHTbo;
        
        
        //carinho
        int srccaX0 = counter*FRAME_WIDTHca; 
        int srccaY0 = 0;
        int srccaX1 = srccaX0+FRAME_WIDTHca; 
        int srccaY1 = FRAME_HEIGHTca;
 
        int dstcaX0 = (DISPLAY_WIDTH-FRAME_WIDTHca)/2;
        int dstcaY0 = (DISPLAY_HEIGHT-FRAME_HEIGHTca)/2;
        int dstcaX1 = dstcaX0+FRAME_WIDTHca;
        int dstcaY1 = dstcaY0+FRAME_HEIGHTca;
        
        
        //musica
        int srcnmX0 = counter*FRAME_WIDTHnm; 
        int srcnmY0 = 0;
        int srcnmX1 = srcX0+FRAME_WIDTHnm; 
        int srcnmY1 = FRAME_HEIGHTnm;
 
        int dstnmX0 = (DISPLAY_WIDTH-FRAME_WIDTHnm)/2;
        int dstnmY0 = (DISPLAY_HEIGHT-FRAME_HEIGHTnm)/2;
        int dstnmX1 = dstnmX0+FRAME_WIDTHnm;
        int dstnmY1 = dstnmY0+FRAME_HEIGHTnm;
        
        //livro
        int srclvX0 = counter*FRAME_WIDTHlv; //primeiro ponto do retangulo invisivel
        int srclvY0 = 0;
        int srclvX1 = srcX0+FRAME_WIDTHlv; //segundo ponto do retangulo invisivel
        int srclvY1 = FRAME_HEIGHTlv;

        int dstlvX0 = (DISPLAY_WIDTH-FRAME_WIDTHlv)/2;
        int dstlvY0 = (DISPLAY_HEIGHT-FRAME_HEIGHTlv)/2;
        int dstlvX1 = dstlvX0+FRAME_WIDTHlv;
        int dstlvY1 = dstlvY0+FRAME_HEIGHTlv;
        
        
        
        
        //musica
        if (comida.equals("musica")) {
            counter = 0;
            g.clearRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
            g.drawImage(sheetnotasmusicais,
                    dstnmX0, dstnmY0, dstnmX1, dstnmY1,
                    srcnmX0, srcnmY0, srcnmX1, srcnmY1, null);
            //     idade = "criancafeliz";
            comida = "";
        }
        
        
        //livro
        if (comida.equals("livro")) {
            counter = 0;
            g.clearRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
            g.drawImage(sheetlivro,
                    dstlvX0, dstlvY0, dstlvX1, dstlvY1,
                    srclvX0, srclvY0, srclvX1, srclvY1, null);
            //     idade = "criancafeliz";
            comida = "";
        }
        
        
        //carinho
        if (comida.equals("carinho")) {
            counter = 0;
            g.clearRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
            g.drawImage(sheetcarinho,
                    dstcaX0, dstcaY0, dstcaX1, dstcaY1,
                    srccaX0, srccaY0, srccaX1, srccaY1, null);
            //     idade = "criancafeliz";
            comida = "";
        }
        
        //bola
        if (comida.equals("bola")) {
            counter = 0;
            g.clearRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
            g.drawImage(sheetbola,
                    dstboX0, dstboY0, dstboX1, dstboY1,
                    srcboX0, srcboY0, srcboX1, srcboY1, null);
            //     idade = "criancafeliz";
            comida = "";
        }
        
        if (comida.equals("seringa")) {
            counter = 0;
            g.clearRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
            g.drawImage(sheetseringa,
                    dstseX0, dstseY0, dstseX1, dstseY1,
                    srcseX0, srcseY0, srcseX1, srcseY1, null);
            //     idade = "criancafeliz";
            comida = "";
        }
        
        if (comida.equals("sanduiche")) {
            counter = 0;
            g.clearRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
            g.drawImage(sheetsanduiche,
                    dstsaX0, dstsaY0, dstsaX1, dstsaY1,
                    srcsaX0, srcsaY0, srcsaX1, srcsaY1, null);
            idade = "criancafeliz";
            comida = "";
        }
        
        if (comida.equals("leite")) {
            counter = 0;
            g.clearRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
            g.drawImage(sheetleite,
                    dstleX0, dstleY0, dstleX1, dstleY1,
                    srcleX0, srcleY0, srcleX1, srcleY1, null);
            idade = "criancafeliz";
            comida = "";
            
        }
        
        
        if (comida.equals("sorvete")) {
            counter = 0;
            g.clearRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
            g.drawImage(sheetsorvete,
                    dstsX0, dstsY0, dstsX1, dstsY1,
                    srcsX0, srcsY0, srcsX1, srcsY1, null);
            idade = "criancafeliz";
            comida = "";
            
        }
        
        if (comida.equals("cenoura")) {
            counter = 0;
            g.clearRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
            g.drawImage(sheetcenoura,
                    dstceX0, dstceY0, dstceX1, dstceY1,
                    srcceX0, srcceY0, srcceX1, srcceY1,
                    null);
            idade = "criancafeliz";
            comida = "";
            
        }
        
        
        if (idade.equals("ovo")) {
            g.drawImage(sheetovo,
                    dstX0, dstY0, dstX1, dstY1,
                    srcX0, srcY0, srcX1, srcY1,
                    null);
        }
        
        if (idade.equals("criancafeliz")) {
            //counter = 0;
            g.drawImage(sheetcriancafeliz,
                    dstcX0, dstcY0, dstcX1, dstcY1,
                    srccX0, srccY0, srccX1, srccY1,
                    null);
        }
        
        if ((idade.equals("criancadoente"))) {
            g.drawImage(sheetcriancadoente,
                    dstcX0, dstcY0, dstcX1, dstcY1,
                    srccX0, srccY0, srccX1, srccY1,
                    null);
            trava = "doente";
            satisfacao = 20;
            contadorsaude = 30;
        }
        
        if  (idade.equals("criancafome")){
            g.drawImage(sheetcriancafome,
                    dstcX0, dstcY0, dstcX1, dstcY1,
                    srccX0, srccY0, srccX1, srccY1,
                    null);
            trava = "fome";
            satisfacao = 20;
            contadorfome = 25;
        }
        
        if ((idade.equals("criancasono"))) {
            g.drawImage(sheetcriancasono,
                    dstcX0, dstcY0, dstcX1, dstcY1,
                    srccX0, srccY0, srccX1, srccY1,
                    null);
            trava = "sono";
            satisfacao = 20;
            contadorsono = 40;
        }
        
        if ((idade.equals("criancatriste"))) {
            g.drawImage(sheetcriancatriste,
                    dstcX0, dstcY0, dstcX1, dstcY1,
                    srccX0, srccY0, srccX1, srccY1,
                    null);
            trava = "triste";
            satisfacao = 20;
            contadorestado = 10;
        }
        
        
        //adulto
        
        if ((idade.equals("adultofeliz"))) {
            g.drawImage(sheetadultofeliz,
                    dstaX0, dstaY0, dstaX1, dstaY1,
                    srcaX0, srcaY0, srcaX1, srcaY1,
                    null);
        }
        
        if ((idade.equals("adultofome"))) {
            g.drawImage(sheetadultofome,
                    dstaX0, dstaY0, dstaX1, dstaY1,
                    srcaX0, srcaY0, srcaX1, srcaY1,
                    null);
            
            satisfacao = 20;
            contadorfome = 25;
            idade = "adultofeliz";
        }
        
        if ((idade.equals("adultosono"))) {
            g.drawImage(sheetadultosono,
                    dstaX0, dstaY0, dstaX1, dstaY1,
                    srcaX0, srcaY0, srcaX1, srcaY1,
                    null);
            
            satisfacao = 20;
            contadorsono = 40;
            idade = "adultofeliz";
        }
        
        if ((idade.equals("adultodoente"))) {
            g.drawImage(sheetadultodoente,
                    dstaX0, dstaY0, dstaX1, dstaY1,
                    srcaX0, srcaY0, srcaX1, srcaY1,
                    null);
            
            satisfacao = 20;
            contadorsaude = 30;
            idade = "adultofeliz";
        }
        
        if ((idade.equals("adultotriste"))) {
            g.drawImage(sheetadultotriste,
                    dstaX0, dstaY0, dstaX1, dstaY1,
                    srcaX0, srcaY0, srcaX1, srcaY1,
                    null);
            
            satisfacao = 20;
            contadorestado = 40;
            idade = "adultofeliz";
        }
    }
    
    public boolean action(Event evento, Object quem) {
        
        if(evento.target instanceof Button) {
            String label = (String)quem;
            

            if((label == "Sorvete") && (trava.equals("fome"))) {
                comida = "sorvete";
                idade = "criancafeliz";
            }
            
            if ((label == "Cenoura") && (trava.equals("fome"))) {
                comida = "cenoura";
                idade = "criancafeliz";
            }
            
            if ((label == "Leite") && (trava.equals("fome"))) {
                comida = "leite";
                idade = "criancafeliz";
            }
            
            if ((label == "Sanduiche") && (trava.equals("fome"))) {
                comida = "sanduiche";
                idade = "criancafeliz";
            }
            
            if ((label == "Carinho") && (trava.equals("triste"))){
                comida = "carinho";
                idade = "criancafeliz";
            }   
                 
             if ((label == "Bola") && (trava.equals("triste"))){
                comida = "bola";
                idade = "criancafeliz";
            }
                        
            if ((label == "Livro") && (trava.equals("triste"))){
                comida = "livro";
                idade = "criancafeliz";
            }       
            
            if ((label == "Musica") && (trava.equals("triste"))){
                comida = "musica";
                idade = "criancafeliz";
            }        
            
            if ((label == "Medicamento") && (trava.equals("doente"))){
                comida = "seringa";
                idade = "criancafeliz";
            }           
            
            if ((label == "Apagar Luz") && (trava.equals("sono"))){
                comida = "seringa";
                idade = "criancafeliz";
            }  
        }
        
        return (true);
    }
    
    private Image sheetovo, sheetcriancafeliz, imagemfundo, sheetcriancafome, sheetcriancasono,
            sheetcriancadoente, sheetcriancatriste, sheetsorvete, sheetleite, sheetcenoura, 
            sheetsanduiche, sheetseringa, sheetbola, sheetcarinho, sheetadultofeliz, sheetadultofome, 
            sheetadultosono, sheetadultodoente, sheetadultotriste,sheetnotasmusicais,sheetlivro,
            sheetcenarionoite;
    
    private int counter = 0, contadorfome = 25, contadorsono = 40, contador, contadorsaude = 30, contadorestado = 10, contsorvete = 0;
    
    private int n, satisfacao = 20;
    private double cont;
    
    private static final int FRAME_WIDTH = 104;
    private static final int FRAME_HEIGHT = 153;
    
    //crianca
    private static final int FRAME_WIDTHc = 274;
    private static final int FRAME_HEIGHTc = 214;
    
    //adulto
    private static final int FRAME_WIDTHa = 409; //largura em pixels da figura
    private static final int FRAME_HEIGHTa = 300;//altura em pixels da figura
    
    //sorvete
    private static final int FRAME_WIDTHs = 276;
    private static final int FRAME_HEIGHTs = 523;
    
    //cenoura
    private static final int FRAME_WIDTHce = 150;
    private static final int FRAME_HEIGHTce = 400;
    
    //leite
    private static final int FRAME_WIDTHle = 400;
    private static final int FRAME_HEIGHTle = 400;
    
    //sanduiche
    private static final int FRAME_WIDTHsa = 430;
    private static final int FRAME_HEIGHTsa = 332;
    
    //seringa
    private static final int FRAME_WIDTHse = 91; //largura em pixels da figura
    private static final int FRAME_HEIGHTse = 500;
    
    //bola
    private static final int FRAME_WIDTHbo = 186; //largura em pixels da figura
    private static final int FRAME_HEIGHTbo = 468;//altura em pixels da figura
    
    //carinho
    private static final int FRAME_WIDTHca = 504; 
    private static final int FRAME_HEIGHTca = 246;
    
    //notas musicais
    private static final int FRAME_WIDTHnm = 475; 
    private static final int FRAME_HEIGHTnm = 430;
    
    //livro
    private static final int FRAME_WIDTHlv = 517; 
    private static final int FRAME_HEIGHTlv = 343;
    
    
    
    private Thread loop;
    private BufferedImage backBuffer;
    private Graphics2D bbGraphics;
    
    private static final int DISPLAY_WIDTH = 800;
    private static final int DISPLAY_HEIGHT = 600;
    private int i = 0;
    private String idade = "ovo";
    private String comida = "";
    private String trava;
    // private Logica logica;
    //private Botao botao;
}
