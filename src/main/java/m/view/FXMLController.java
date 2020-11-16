/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m.view;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import m.model.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import net.sf.jclec.RunExperiment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import net.sf.jclec.listener.PopulationReporter;

/**
 * FXML Controller class
 *
 * @author Fabrice
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    @FXML
    private TextArea outputResult;
    private PrintStream ps;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ps = new PrintStream(new Console(outputResult));

    }

    static void callMain() {
        String[] args = {"examples/ClassModel_1.cfg"};
        ClassModelEvaluator.FITNESS_MEASURE = 1;
        RunExperiment.main(args);

    }

    static void callMain2() {
        String[] args = {"examples/ClassModel_1.cfg"};
        ClassModelEvaluator.FITNESS_MEASURE = 2;
        RunExperiment.main(args);
    }

    /**
     ***********************************
     *                                 *
     * Objective search FXML controls * *
     **********************************
     */
    public void runOScoupling(ActionEvent event) {
        System.setOut(ps);
        callMain();
        OScouplingCm.setDisable(false);
    }

    public void runOSelegance(ActionEvent event) {
        callMain2();
        OSeleganceCm.setDisable(false);
    }

    public void displayOScouplingCm() {

        ListView<String> listview1 = new ListView<>();
        ListView<String> listview2 = new ListView<>();
        ListView<String> listview3 = new ListView<>();
        ListView<String> listview4 = new ListView<>();
        ListView<String> listview5 = new ListView<>();

        ObservableList class1 = FXCollections.observableArrayList();
        ObservableList class2 = FXCollections.observableArrayList();
        ObservableList class3 = FXCollections.observableArrayList();
        ObservableList class4 = FXCollections.observableArrayList();
        ObservableList class5 = FXCollections.observableArrayList();

        VBox vboxc = new VBox();
        VBox vboxc2 = new VBox();
        VBox vboxc3 = new VBox();
        VBox vboxc4 = new VBox();
        VBox vboxc5 = new VBox();
        AnchorPane Cpane = new AnchorPane(vboxc, vboxc2, vboxc3, vboxc4, vboxc5);

        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        Label label5 = new Label();

        label1.setText("Class 1");
        label1.setFont(Font.font("System", FontWeight.BOLD, 12));
        label2.setText("Class 2");
        label2.setFont(Font.font("System", FontWeight.BOLD, 12));
        label3.setText("Class 3");
        label3.setFont(Font.font("System", FontWeight.BOLD, 12));
        label4.setText("Class 4");
        label4.setFont(Font.font("System", FontWeight.BOLD, 12));
        label5.setText("Class 5");
        label5.setFont(Font.font("System", FontWeight.BOLD, 12));

        ArrayList<String> classOne = new ArrayList<>();
        classOne = ClassModelController.Class_1;
        class1.addAll(classOne);
        listview1.setItems(class1);
        vboxc.setLayoutX(10.0);
        vboxc.setLayoutY(4.0);
        vboxc.setPrefSize(140, 350);
        vboxc.getChildren().addAll(label1, listview1);

        ArrayList<String> classTwo = new ArrayList<>();
        classTwo = ClassModelController.Class_2;
        class2.addAll(classTwo);
        listview2.setItems(class2);
        vboxc2.setLayoutX(155.0);
        vboxc2.setLayoutY(4.0);
        vboxc2.setPrefSize(140, 350);
        vboxc2.getChildren().addAll(label2, listview2);

        ArrayList<String> classThree = new ArrayList<>();
        classThree = ClassModelController.Class_3;
        class3.addAll(classThree);
        listview3.setItems(class3);
        vboxc3.setLayoutX(300.0);
        vboxc3.setLayoutY(4.0);
        vboxc3.setPrefSize(140, 350);
        vboxc3.getChildren().addAll(label3, listview3);

        ArrayList<String> classFour = new ArrayList<>();
        classFour = ClassModelController.Class_4;
        class4.addAll(classFour);
        listview4.setItems(class4);
        vboxc4.setLayoutX(445.0);
        vboxc4.setLayoutY(4.0);
        vboxc4.setPrefSize(140, 350);
        vboxc4.getChildren().addAll(label4, listview4);

        ArrayList<String> classFive = new ArrayList<>();
        classFive = ClassModelController.Class_5;
        class5.addAll(classFive);
        listview5.setItems(class5);
        vboxc5.setLayoutX(590.0);
        vboxc5.setLayoutY(4.0);
        vboxc5.setPrefSize(140, 350);
        vboxc5.getChildren().addAll(label5, listview5);

        Scene scene = new Scene(Cpane, 745, 370);

        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle(" OS coupling Class model");
        window.show();

        OSCouplingGraph.setDisable(false);

    }

    public void displayOSeleganceCm() {
        ListView<String> listview1 = new ListView<>();
        ListView<String> listview2 = new ListView<>();
        ListView<String> listview3 = new ListView<>();
        ListView<String> listview4 = new ListView<>();
        ListView<String> listview5 = new ListView<>();

        ObservableList class1 = FXCollections.observableArrayList();
        ObservableList class2 = FXCollections.observableArrayList();
        ObservableList class3 = FXCollections.observableArrayList();
        ObservableList class4 = FXCollections.observableArrayList();
        ObservableList class5 = FXCollections.observableArrayList();

        VBox vboxe = new VBox();
        VBox vboxe2 = new VBox();
        VBox vboxe3 = new VBox();
        VBox vboxe4 = new VBox();
        VBox vboxe5 = new VBox();
        AnchorPane Celpane = new AnchorPane(vboxe, vboxe2, vboxe3, vboxe4, vboxe5);

        Label labele = new Label();
        Label labele2 = new Label();
        Label labele3 = new Label();
        Label labele4 = new Label();
        Label labele5 = new Label();

        labele.setText("Class 1");
        labele.setFont(Font.font("System", FontWeight.BOLD, 12));
        labele2.setText("Class 2");
        labele2.setFont(Font.font("System", FontWeight.BOLD, 12));
        labele3.setText("Class 3");
        labele3.setFont(Font.font("System", FontWeight.BOLD, 12));
        labele4.setText("Class 4");
        labele4.setFont(Font.font("System", FontWeight.BOLD, 12));
        labele5.setText("Class 5");
        labele5.setFont(Font.font("System", FontWeight.BOLD, 12));

        ArrayList<String> classOne = new ArrayList<>();
        classOne = ClassModelController.Class_1;
        class1.addAll(classOne);
        listview1.setItems(class1);
        vboxe.setLayoutX(10.0);
        vboxe.setLayoutY(4.0);
        vboxe.setPrefSize(140, 350);
        vboxe.getChildren().addAll(labele, listview1);

        ArrayList<String> classTwo = new ArrayList<>();
        classTwo = ClassModelController.Class_2;
        class2.addAll(classTwo);
        listview2.setItems(class2);
        vboxe2.setLayoutX(155.0);
        vboxe2.setLayoutY(4.0);
        vboxe2.setPrefSize(140, 350);
        vboxe2.getChildren().addAll(labele2, listview2);

        ArrayList<String> classThree = new ArrayList<>();
        classThree = ClassModelController.Class_3;
        class3.addAll(classThree);
        listview3.setItems(class3);
        vboxe3.setLayoutX(300.0);
        vboxe3.setLayoutY(4.0);
        vboxe3.setPrefSize(140, 350);
        vboxe3.getChildren().addAll(labele3, listview3);

        ArrayList<String> classFour = new ArrayList<>();
        classFour = ClassModelController.Class_4;
        class4.addAll(classFour);
        listview4.setItems(class4);
        vboxe4.setLayoutX(445.0);
        vboxe4.setLayoutY(4.0);
        vboxe4.setPrefSize(140, 350);
        vboxe4.getChildren().addAll(labele4, listview4);

        ArrayList<String> classFive = new ArrayList<>();
        classFive = ClassModelController.Class_5;
        class5.addAll(classFive);
        listview5.setItems(class5);
        vboxe5.setLayoutX(590.0);
        vboxe5.setLayoutY(4.0);
        vboxe5.setPrefSize(140, 350);
        vboxe5.getChildren().addAll(labele5, listview5);

        Scene scene = new Scene(Celpane, 745, 370);

        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle(" OS elegance Class model");
        window.show();

        OSeleganceGraph.setDisable(false);

    }

    public void displayOSCouplingGraph() {

        final NumberAxis genAxis = new NumberAxis();
        final NumberAxis fitAxis = new NumberAxis();

        genAxis.setLabel("Generations");
        fitAxis.setLabel("Coupling fitness");

        final LineChart<Number, Number> couplingChart = new LineChart<Number, Number>(genAxis, fitAxis);
        couplingChart.setTitle("OS coupling measure graph");

        XYChart.Series Cseries = new XYChart.Series();
        Cseries.setName("OS coupling");

        Cseries = PopulationReporter.Cseries;

        Scene scene = new Scene(couplingChart, 800, 600);
        couplingChart.getData().add(Cseries);

        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle(" OS coupling fitness graph");
        window.show();

    }

    public void displayOSeleganceGraph() {

        final NumberAxis genAxis = new NumberAxis();
        final NumberAxis fitAxis = new NumberAxis();

        genAxis.setLabel("Generations");
        fitAxis.setLabel("Elegance measure characterisation");

        final LineChart<Number, Number> eleganceChart = new LineChart<>(genAxis, fitAxis);
        eleganceChart.setTitle("OS elegance characterisation graph");

        XYChart.Series Eseries = new XYChart.Series();
        Eseries.setName("OS elegance");

        Eseries = PopulationReporter.Eseries;

        Scene scene = new Scene(eleganceChart, 800, 600);
        eleganceChart.getData().add(Eseries);

        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("OS elegance characterisation graph");
        window.show();

    }

    /**
     ***********************************
     *                                 *
     * Novelty search FXML controls * * **********************************
     */
    public void runNScoupling(ActionEvent event) {
        System.setOut(ps);
        Evolver evolve = new Evolver();
        String mValue = NSCouplingMut.getValue().toString();
        String cValue = NSCouplingCross.getValue().toString();
        String gValue = NSCouplingCount.getValue().toString();

        double mutRate = Double.parseDouble(mValue);
        double crossRate = Double.parseDouble(cValue);
        int generation = Integer.parseInt(gValue);

        evolve.MUTATION_RATE = mutRate;
        evolve.CROSSOVER_RATE = crossRate;
        evolve.FITNESS_MEASURE = 1;
        AlgorithmController.MAX_GEN = generation;

        AlgorithmController.NSController();
        NScouplingCm.setDisable(false);
    }

    public void runNSelegance(ActionEvent event) {
        Evolver evolve = new Evolver();
        String mValue = NSeleganceMut.getValue().toString();
        String cValue = NSeleganceCross.getValue().toString();
        String gValue = NSeleganceCount.getValue().toString();

        double mutRate = Double.parseDouble(mValue);
        double crossRate = Double.parseDouble(cValue);
        int generation = Integer.parseInt(gValue);

        evolve.MUTATION_RATE = mutRate;
        evolve.CROSSOVER_RATE = crossRate;
        evolve.FITNESS_MEASURE = 2;
        AlgorithmController.MAX_GEN = generation;

        AlgorithmController.NSController();

        NSeleganceCm.setDisable(false);

    }

    public void displayNScouplingCm(ActionEvent event) {

        ListView<String> listview1 = new ListView<>();
        ListView<String> listview2 = new ListView<>();
        ListView<String> listview3 = new ListView<>();
        ListView<String> listview4 = new ListView<>();
        ListView<String> listview5 = new ListView<>();

        ObservableList class1 = FXCollections.observableArrayList();
        ObservableList class2 = FXCollections.observableArrayList();
        ObservableList class3 = FXCollections.observableArrayList();
        ObservableList class4 = FXCollections.observableArrayList();
        ObservableList class5 = FXCollections.observableArrayList();

        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();
        VBox vbox3 = new VBox();
        VBox vbox4 = new VBox();
        VBox vbox5 = new VBox();
        AnchorPane NSCpane = new AnchorPane(vbox1, vbox2, vbox3, vbox4, vbox5);

        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        Label label5 = new Label();

        label1.setText("Class 1");
        label1.setFont(Font.font("System", FontWeight.BOLD, 12));
        label2.setText("Class 2");
        label2.setFont(Font.font("System", FontWeight.BOLD, 12));
        label3.setText("Class 3");
        label3.setFont(Font.font("System", FontWeight.BOLD, 12));
        label4.setText("Class 4");
        label4.setFont(Font.font("System", FontWeight.BOLD, 12));
        label5.setText("Class 5");
        label5.setFont(Font.font("System", FontWeight.BOLD, 12));

        ArrayList<String> classOne = new ArrayList<>();
        classOne = ClassModelController.Class_1;
        class1.addAll(classOne);
        listview1.setItems(class1);
        vbox1.setLayoutX(10.0);
        vbox1.setLayoutY(4.0);
        vbox1.setPrefSize(140, 350);
        vbox1.getChildren().addAll(label1, listview1);

        ArrayList<String> classTwo = new ArrayList<>();
        classTwo = ClassModelController.Class_2;
        class2.addAll(classTwo);
        listview2.setItems(class2);
        vbox2.setLayoutX(155.0);
        vbox2.setLayoutY(4.0);
        vbox2.setPrefSize(140, 350);
        vbox2.getChildren().addAll(label2, listview2);

        ArrayList<String> classThree = new ArrayList<>();
        classThree = ClassModelController.Class_3;
        class3.addAll(classThree);
        listview3.setItems(class3);
        vbox3.setLayoutX(300.0);
        vbox3.setLayoutY(4.0);
        vbox3.setPrefSize(140, 350);
        vbox3.getChildren().addAll(label3, listview3);

        ArrayList<String> classFour = new ArrayList<>();
        classFour = ClassModelController.Class_4;
        class4.addAll(classFour);
        listview4.setItems(class4);
        vbox4.setLayoutX(445.0);
        vbox4.setLayoutY(4.0);
        vbox4.setPrefSize(140, 350);
        vbox4.getChildren().addAll(label4, listview4);

        ArrayList<String> classFive = new ArrayList<>();
        classFive = ClassModelController.Class_5;
        class5.addAll(classFive);
        listview5.setItems(class5);
        vbox5.setLayoutX(590.0);
        vbox5.setLayoutY(4.0);
        vbox5.setPrefSize(140, 350);
        vbox5.getChildren().addAll(label5, listview5);

        Scene scene = new Scene(NSCpane, 745, 370);

        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("NS coupling Class model");
        window.show();

        NSCouplingGraph.setDisable(false);
    }

    public void displayNSeleganceCm(ActionEvent event) throws IOException {

        ListView<String> listview1 = new ListView<>();
        ListView<String> listview2 = new ListView<>();
        ListView<String> listview3 = new ListView<>();
        ListView<String> listview4 = new ListView<>();
        ListView<String> listview5 = new ListView<>();

        ObservableList class1 = FXCollections.observableArrayList();
        ObservableList class2 = FXCollections.observableArrayList();
        ObservableList class3 = FXCollections.observableArrayList();
        ObservableList class4 = FXCollections.observableArrayList();
        ObservableList class5 = FXCollections.observableArrayList();

        VBox vbox11 = new VBox();
        VBox vbox12 = new VBox();
        VBox vbox13 = new VBox();
        VBox vbox14 = new VBox();
        VBox vbox15 = new VBox();
        AnchorPane NSEpane = new AnchorPane(vbox11, vbox12, vbox13, vbox14, vbox15);

        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        Label label5 = new Label();

        label1.setText("Class 1");
        label1.setFont(Font.font("System", FontWeight.BOLD, 12));
        label2.setText("Class 2");
        label2.setFont(Font.font("System", FontWeight.BOLD, 12));
        label3.setText("Class 3");
        label3.setFont(Font.font("System", FontWeight.BOLD, 12));
        label4.setText("Class 4");
        label4.setFont(Font.font("System", FontWeight.BOLD, 12));
        label5.setText("Class 5");
        label5.setFont(Font.font("System", FontWeight.BOLD, 12));

        ArrayList<String> classOne = new ArrayList<>();
        classOne = ClassModelController.Class_1;
        class1.addAll(classOne);
        listview1.setItems(class1);
        vbox11.setLayoutX(10.0);
        vbox11.setLayoutY(4.0);
        vbox11.setPrefSize(140, 350);
        vbox11.getChildren().addAll(label1, listview1);

        ArrayList<String> classTwo = new ArrayList<>();
        classTwo = ClassModelController.Class_2;
        class2.addAll(classTwo);
        listview2.setItems(class2);
        vbox12.setLayoutX(155.0);
        vbox12.setLayoutY(4.0);
        vbox12.setPrefSize(140, 350);
        vbox12.getChildren().addAll(label2, listview2);

        ArrayList<String> classThree = new ArrayList<>();
        classThree = ClassModelController.Class_3;
        class3.addAll(classThree);
        listview3.setItems(class3);
        vbox13.setLayoutX(300.0);
        vbox13.setLayoutY(4.0);
        vbox13.setPrefSize(140, 350);
        vbox13.getChildren().addAll(label3, listview3);

        ArrayList<String> classFour = new ArrayList<>();
        classFour = ClassModelController.Class_4;
        class4.addAll(classFour);
        listview4.setItems(class4);
        vbox14.setLayoutX(445.0);
        vbox14.setLayoutY(4.0);
        vbox14.setPrefSize(140, 350);
        vbox14.getChildren().addAll(label4, listview4);

        ArrayList<String> classFive = new ArrayList<>();
        classFive = ClassModelController.Class_5;
        class5.addAll(classFive);
        listview5.setItems(class5);
        vbox15.setLayoutX(590.0);
        vbox15.setLayoutY(4.0);
        vbox15.setPrefSize(140, 350);
        vbox15.getChildren().addAll(label5, listview5);

        Scene scene = new Scene(NSEpane, 745, 370);

        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("NS elegance Class model");
        window.show();

        NSeleganceGraph.setDisable(false);

    }

    public void displayNSCouplingGraph() {
        final NumberAxis genAxis = new NumberAxis();
        final NumberAxis fitAxis = new NumberAxis();

        genAxis.setLabel("Generations");
        fitAxis.setLabel("NS Coupling fitness");

        final LineChart<Number, Number> NScouplingChart = new LineChart<Number, Number>(genAxis, fitAxis);
        NScouplingChart.setTitle("NS coupling measure graph (current run)");

        XYChart.Series NSCseries = new XYChart.Series();
        NSCseries.setName("NS coupling");

        NSCseries = AlgorithmController.NSCseries;

        Scene scene = new Scene(NScouplingChart, 800, 600);
        NScouplingChart.getData().add(NSCseries);

        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("NS coupling fitness graph");
        window.show();
    }

    public void displayNSeleganceGraph() {
        final NumberAxis genAxis = new NumberAxis();
        final NumberAxis fitAxis = new NumberAxis();

        genAxis.setLabel("Generations");
        fitAxis.setLabel("NS elegance measure characterisation");

        final LineChart<Number, Number> NSeleganceChart = new LineChart<Number, Number>(genAxis, fitAxis);
        NSeleganceChart.setTitle("NS elegance characterisation graph (current run)");

        XYChart.Series NSEseries = new XYChart.Series();
        NSEseries.setName("NS elegance");

        NSEseries = AlgorithmController.NSEseries;

        Scene scene = new Scene(NSeleganceChart, 800, 600);
        NSeleganceChart.getData().add(NSEseries);

        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("NS elegance characterisation graph");
        window.show();

    }
    @FXML
    Button NScouplingCm;
    @FXML
    Button NSCouplingGraph;
    @FXML
    Button NSeleganceGraph;
    @FXML
    Button NSeleganceCm;
    @FXML
    Button runNScoupling;
    @FXML
    Button runNSelegance;

    @FXML
    Button OScouplingCm;
    @FXML
    Button OSCouplingGraph;
    @FXML
    Button OSeleganceGraph;
    @FXML
    Button OSeleganceCm;
    @FXML
    Button runOScoupling;
    @FXML
    Button runOSelegance;

    @FXML
    ChoiceBox NSCouplingMut;
    @FXML
    ChoiceBox NSCouplingCross;
    @FXML
    ChoiceBox NSCouplingCount;
    @FXML
    ChoiceBox NSeleganceMut;
    @FXML
    ChoiceBox NSeleganceCross;
    @FXML
    ChoiceBox NSeleganceCount;
//   @FXML 
//public static TextArea outputResult;
//   @FXML
//public static PrintStream ps;
//   

}
