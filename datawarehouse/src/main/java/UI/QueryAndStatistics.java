package UI;

import Query.runFunction;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Global.Results;

public class QueryAndStatistics extends Application {
    private XYChart.Series series1 = new XYChart.Series();
    private XYChart.Series series2 = new XYChart.Series();
    private XYChart.Series series3 = new XYChart.Series();
    private XYChart.Series series4 = new XYChart.Series();
    private final NumberAxis yAxis = new NumberAxis();
    private final CategoryAxis xAxis = new CategoryAxis();
    private final StackedBarChart<String,Number> bc =
            new StackedBarChart<String, Number>(xAxis,yAxis);

    private ObservableList instructions = FXCollections.observableArrayList(
            "列出所有的电影","列出所有的导演","列出所有的演员","列出所有的电影类型",
            "xxxx年xx月有多少部电影", "xxxx电影有多少版本",
            "xxx导演共有多少部电影", "xxx演员主演了多少部电影",
            "xxx演员参演了多少部电影", "xxx类型的电影共有多少部",
            "xxx演员经常和哪些演员合作","xxx演员经常和哪些导演合作",
            "xxx类型电影常见的黄金上映期是什么","xxx类型电影上映多久后xxx类型电影销售最好"
    );

    private void loadChartData(){
        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        series3 = new XYChart.Series();
        series4 = new XYChart.Series();
        series1.setName("MySQL");
        series1.getData().add(new XYChart.Data("MySQL", Results.MySQLTime));
        series2.setName("Neo4j");
        series2.getData().add(new XYChart.Data("Neo4j", Results.Neo4jTime));
        series3.setName("Influx");
        series3.getData().add(new XYChart.Data("Influx", Results.InfluxTime));
        series4.setName("Hive");
        series4.getData().add(new XYChart.Data("Hive", Results.HiveTime));
        bc.getData().clear();
        bc.getData().addAll(series1, series2, series3, series4);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            //选择框
            ChoiceBox choiceBox = new ChoiceBox();
            choiceBox.setItems(instructions);
            choiceBox.setMinSize(300, 50);
            //参数输入
            TextField arg = new TextField();
            arg.setPrefSize(200, 50);
            arg.setFont(new Font(20));
            //运行按钮
            Button runButton = new Button("RUN");
            runButton.setMinSize(100, 50);
            runButton.setFont(new Font(20));
            //帮助按钮
            Button help = new Button();
            help.setGraphic(new ImageView("question-mark.png"));
            help.setAlignment(Pos.CENTER);
            //结果显示
            TextArea resultText = new TextArea("Waiting for Instructions");
            resultText.setEditable(false);
            resultText.setFont(new Font(20));
            resultText.setMinSize(400, 400);
            resultText.setMaxSize(450,500);
            //条形图
            bc.setTitle("Time Comparision");
            bc.setCategoryGap(40);
            xAxis.setLabel("Method");
            yAxis.setLabel("Time/ms");
            loadChartData();
            //运行按键监听
            runButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        String res = runFunction.run(choiceBox.getValue().toString(), arg.getText());
                        resultText.setText(res);
                        loadChartData();
                    }
                    catch (NullPointerException e){
                        resultText.setText("至少选择一个功能！");
                    }
                }
            });
            //帮助按钮监听
            help.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    new helpBox().display("帮助", "" +
                            "在第一行的下拉菜单中选择一个功能\n" +
                            "在第一行的输入框中输入功能所需参数，如有多个，用#分开\n" +
                            "按RUN按钮即可令多种存储方式依次运行指令\n" +
                            "运行结果显示在第二行的文本框内\n" +
                            "每种存储方式运行的时间显示在第二行的条形图中");
                }
            });

            //排版
            HBox line1 = new HBox();
            line1.setPadding(new Insets(50, 50, 50, 50));
            line1.setSpacing(100);
            line1.getChildren().addAll(choiceBox, arg, runButton, help);
            HBox line2 = new HBox();
            line2.setPadding(new Insets(50, 50, 50, 50));
            line2.setSpacing(50);
            line2.getChildren().addAll(resultText, bc);
            VBox vBox = new VBox();
            vBox.setPadding(new Insets(50, 50, 50, 50));
            vBox.setSpacing(10);
            vBox.getChildren().addAll(line1, line2);

            Scene scene = new Scene(vBox,1200,800);
            scene.setFill(Color.ALICEBLUE);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Query & Statistics");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
