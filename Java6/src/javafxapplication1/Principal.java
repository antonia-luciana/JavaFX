/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
/**
 *
 * @author lucia_000
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class Principal extends Application {

    Canvas canvas = new Canvas(600, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    BorderPane root = new BorderPane();   
    

    
    

    public void openFile() {

        MakeFile mkFile = new MakeFile();

        File file = new File("file.svg");
        mkFile.make("null.txt", "file.svg");
        try {
            x = new Formatter(file);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(TestSVGGen.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Eraore la open");
        }
    }

    public Label setText(String text, Pane root, Integer x, Integer y) {
        Label btn = new Label();
        btn.setText(text);
        btn.setTranslateX(x);
        btn.setTranslateY(y);
        btn.setStyle("border-color: rgb(49, 89, 23);");
        root.getChildren().add(btn);
        return btn;
    }

    public ChoiceBox adaugaChoiceBox(String[] text, Pane root) {
        //  pozitieY += 50;
        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(
                text)
        );

        root.getChildren().add(cb);
        return cb;
    }

    public TextArea adaugaTextArea(Pane root) {
        TextArea textArea = new TextArea();
        textArea.setMaxWidth(200.0);
        textArea.setMaxHeight(30.0);
        root.getChildren().add(textArea);
        return textArea;
    }

    public void deseneaza(Coordonate Coordonate, Pane roott, String functie, String culoare, double grosime) {
        Grafic plot = new Grafic(
                functie,
                -18, 18, 0.1,
                Coordonate, culoare, grosime
        );

        roott.getChildren().add(plot);

        scriePerechi(plot, culoare, grosime);

    }

    public void deseneazaAxe(Coordonate Coordonate, Pane root) {

        root.getChildren().clear();
        initializare();

    }

    @Override
    public void start(final Stage stage) {

        initializare();
        stage.setScene(new Scene(root, Color.WHITE));
        stage.setHeight(700);
        stage.setWidth(1000);

        stage.setMinHeight(600);
        stage.show();

    }

    private void PunePunct(double x, double y) {
        String color = culoare.getValue().toString();
        String grosim = grosime.getValue().toString();

        //         path.setStroke(Color.valueOf(culoare));
        // path.setStrokeWidth(grosime);
        gc.setFill(Color.valueOf(color));
        gc.setStroke(Color.valueOf(color));
        double grosime = Double.parseDouble(grosim);
        gc.setLineWidth(grosime);
        gc.fillOval(x, y, 5, 5);
    }

    private void Desenare(Pane background) {

        background.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double x = mouseEvent.getX(), y = mouseEvent.getY();
                PunePunct(x, y);

            }
        });

    }

    public void initializare() {

        openFile();
        initializareFile();
        grafic.getChildren().clear();
        Coordonate Coordonate = new Coordonate(
                600, 600,
                -10, 10, 1,
                -12, 12, 1
        );

        root.setMaxWidth(700);
        root.setMinWidth(700);

        Grafic plot = new Grafic(Coordonate);

        grafic.getChildren().add(plot);

        // Desenare(grafic);
        grafic.getChildren().add(canvas);

        root.setCenter(grafic);

        Pane butoane = new TilePane();
        root.setLeft(butoane);

        Button btnDesen = creazaButon("Traseaza grafic", butoane);
        Button btnPunct = creazaButon("Pune puncte    ", butoane);
        // TilePane.setAlignment(btnDesen, Pos.BOTTOM_LEFT);
        Button btnReset = creazaButon("Reseteaza       ", butoane);
        //TilePane.setAlignment(btnReset, Pos.BOTTOM_LEFT);

        Label origineX = setText("X", grafic, 615, 15);
        Label origineY = setText("Y", grafic, 310, -270);
        Label origineO = setText("O", grafic, 310, 15);

        TextArea txt = adaugaTextArea(butoane);
        String[] culori = {"BLACK", "BLUE", "GREEN", "RED", "PURPLE", "ORANGE", "YELLOW", "PINK"};
        culoare = adaugaChoiceBox(culori, butoane);
        culoare.getSelectionModel().selectFirst();

        String[] grosimi = {"1.0", "1.5", "2.0", "2.5", "3.5", "4.0", "4.5", "5.0"};
        grosime = adaugaChoiceBox(grosimi, butoane);
        grosime.getSelectionModel().selectFirst();

        TextArea fileName = adaugaTextArea(butoane);
        String[] extensii = {"jpg", "png", "gif", "svg"};
        ChoiceBox extensie = adaugaChoiceBox(extensii, butoane);
        extensie.getSelectionModel().selectFirst();
        Button btnSave = creazaButon(" Save to..             ", butoane);
        //TilePane.setAlignment(btnSave, Pos.BOTTOM_LEFT);

        Button btnLoad = creazaButon("Load from jpg     ", butoane);

        btnDesen.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String str = txt.getText();
                String culoaree = culoare.getValue().toString();
                String grosim = grosime.getValue().toString();
                double grosimee = Double.parseDouble(grosim);
                deseneaza(Coordonate, grafic, str, culoaree, grosimee);

            }
        });

        btnReset.setOnAction((ActionEvent event) -> {
            deseneazaAxe(Coordonate, root);
            gc.clearRect(0, 0, 600, 600);
        });
        btnSave.setOnAction((ActionEvent event) -> {
            String nume = fileName.getText();
            if (nume.equals("")) {
                
                TrateazaEroare tr=new TrateazaEroare();
                tr.eroare("Nu ati introdus un nume pentru imagine!");
                
            } else if (extensie.getValue().toString().equals("svg")) {
                System.out.println("Hei svg");
                incheiereFile();
                closeFile();
                File file = new File(nume + "." + extensie.getValue().toString());
                MakeFile mkFile = new MakeFile();
                mkFile.make("file.svg", nume + "." + extensie.getValue().toString());

            } else {
                saveAsImageFile(grafic, nume, extensie.getValue().toString());
            }

        });

        btnLoad.setOnAction((ActionEvent event) -> {
            loadImage();
        });

        btnPunct.setOnAction((ActionEvent event) -> {
            Desenare(grafic);
        });

    }

    public void saveAsImageFile(Node plot, String numeFile, String extensie) {
        WritableImage image = plot.snapshot(new SnapshotParameters(), null);

        File file = new File(numeFile + "." + extensie);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), extensie, file);
        } catch (IOException e) {
            System.out.println("Eroare in saveAs");
        }
    }

    public void loadImage() {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        ImageView myImageView = new ImageView();
        //  myImageView.setFitHeight(668);
        //  myImageView.setFitWidth(792);

        myImageView.setFitHeight(600);
        myImageView.setFitWidth(720);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            myImageView.setImage(image);

            grafic.getChildren().clear();

            grafic.getChildren().add(myImageView);
            /*   Grafic plot = new Grafic(new Coordonate(
                    600, 600,
                    -10, 10, 1,
                    -12, 12, 1
            ));*/

            // grafic.getChildren().add(plot);
            // Desenare(grafic);
            grafic.getChildren().add(canvas);

        } catch (IOException ex) {
            // Logger.getLogger(JavaFXPixel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Eroare in");
        }

    }

    public void initializareFile() {
        openFile();
        x.format("%s", "<svg height=\"600\" width=\"600\">\n"
                + " <line x1=\"-300\" y1=\"300\" x2=\"900\" y2=\"300\" style=\"stroke:gray;stroke-width:1\" /> "
                + "<line x1=\"300\" y1=\"-300\" x2=\"300\" y2=\"900\" style=\"stroke:gray;stroke-width:1\" />"
                + "<text x=\"310\" y=\"320\" style=\" fill: black; stroke: #000000 ;font-size:32\">O</text>"
                + "\"<text x=\"310\" y=\"-190\" style=\" fill: black; stroke: #000000 ;font-size:32\">Y</text>"
                + "\"<text x=\"880\" y=\"320\" style=\" fill: black; stroke: #000000 ;font-size:32\">X</text>" + "\n"
        //+ "<polyline points=\""
        );
    }

    public void addCeva(double unu, double doi) {
        x.format(" %s,%s", String.valueOf(unu), String.valueOf(doi));
    }

    public void incheiereFile() {
        x.format("%s", "</svg>");
    }

    public void scriePerechi(Grafic plot, String color, double grosime) {

        x.format("<polyline points=\"");
        for (int i = 0; i < plot.valori.size(); i++) {
            addCeva(plot.valori.get(i).x, plot.valori.get(i).y);
        }
        x.format("\"\n" + "  style=\"fill:none;stroke:"
                + color
                + ";stroke-width:"
                + String.valueOf(grosime)
                + "\" />\n");
    }

    public void closeFile() {
        x.close();
    }

    public static void main(String[] args) {
        launch();
    }

    public Button creazaButon(String nume, Pane root) {
        Button btn = new Button();
        btn.setText(nume);
        root.getChildren().add(btn);

        return btn;

    }
    
    GridPane grafic = new GridPane();
    ChoiceBox culoare = null;
    ChoiceBox grosime = null;
    private Formatter x;
}
