package beautysalonapp1;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BeautySalonApp1 extends Application implements Payable {

    private Stage primaryStage;
    private List<Service> selectedServices = new ArrayList<>();
    private String userName = "";

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showWelcomeScreen();
    }

    private void showWelcomeScreen() {
        Label title = new Label("Welcome to Elegance Beauty Salon !!");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
        title.setTextFill(Color.HOTPINK);

        Image m = new Image ("file:ph-1.jpg");
        ImageView view = new ImageView(m);
        view.setFitHeight(300);
        view.setFitWidth(700);
        VBox.setMargin(view, new Insets(-110, 0, 0, 0));

        Button startBtn = new Button("Start");
        startBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        startBtn.setTextFill(Color.GOLDENROD);
        startBtn.setPrefSize(200, 50);
        startBtn.setOnAction(e -> showServiceSelection());

        VBox layout = new VBox(30, view, title, startBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #ffe4e1;");

        Scene scene = new Scene(layout, 700, 550);
        primaryStage.setTitle("Beauty Salon");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showServiceSelection() {
        selectedServices.clear();

        Label lbl = new Label("Choose your services:");
        lbl.setFont(Font.font("Tahoma", FontWeight.BOLD, 26));
        lbl.setTextFill(Color.HOTPINK);

        Label nameLabel = new Label("Enter your name:");
        nameLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        nameLabel.setTextFill(Color.HOTPINK);

        TextField nameField = new TextField();
        nameField.setPromptText("Your name");
        nameField.setMaxWidth(200);
        CheckBox hair = new CheckBox("Hair Styling");
        hair.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        hair.setTextFill(Color.HOTPINK);
        CheckBox nails = new CheckBox("Nails");
        nails.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        nails.setTextFill(Color.HOTPINK);
        CheckBox skin = new CheckBox("Skin Care");
        skin.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        skin.setTextFill(Color.HOTPINK);
        CheckBox makeup = new CheckBox("Makeup");
        makeup.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        makeup.setTextFill(Color.HOTPINK);

        Button next = new Button("Confirm Selection");
        next.setTextFill(Color.GOLDENROD);
        next.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        next.setOnAction(e -> {
            if (hair.isSelected()) selectedServices.add(new HairService());
            if (nails.isSelected()) selectedServices.add(new NailService());
            if (skin.isSelected()) selectedServices.add(new SkinCareService());
            if (makeup.isSelected()) selectedServices.add(new MakeupService());
            userName = nameField.getText();
            showConfirmationScreen();
        });

        Image h = new Image ("file:ph-2.jpg");
        ImageView v = new ImageView(h);
        v.setFitHeight(170);
        v.setFitWidth(600);

        VBox layout = new VBox(20, lbl, nameLabel, nameField, v, hair, nails, skin, makeup, next);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #ffe4e1;");
        for (Node node : layout.getChildren()) {
            if (node instanceof Labeled) {
                ((Labeled) node).setTextFill(Color.HOTPINK);
            }
        }

        Scene scene = new Scene(layout, 700, 550);
        primaryStage.setScene(scene);
    }

    private void showConfirmationScreen() {
        Label lbl = new Label("You selected:");
        lbl.setFont(Font.font("Tahoma", FontWeight.BOLD, 22));
        lbl.setTextFill(Color.HOTPINK);

        TextArea area = new TextArea();
        area.setFont(Font.font("Tahoma", 14));
        area.setEditable(false);
        area.setStyle("-fx-text-fill: hotpink;");
        for (Service s : selectedServices) {
            area.appendText(s.getDetails() + "\n");
        }

        Button pay = new Button("Proceed to Payment");
        Button back = new Button("Back");
        pay.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        pay.setTextFill(Color.GOLDENROD);
        back.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        back.setTextFill(Color.GOLDENROD);
        pay.setOnAction(e -> showPaymentOption());
        back.setOnAction(e -> showServiceSelection());

        VBox layout = new VBox(20, lbl, area, pay, back);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #ffe4e1;");
        lbl.setTextFill(Color.HOTPINK);

        Scene scene = new Scene(layout, 700, 550);
        primaryStage.setScene(scene);
    }

    private void showPaymentOption() {
        Label lbl = new Label("Select Payment Method:");
        lbl.setFont(Font.font("Tahoma", FontWeight.BOLD, 22));
        lbl.setTextFill(Color.HOTPINK);

        Button cash = new Button("Cash");
        Button card = new Button("Card");
        cash.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        cash.setTextFill(Color.GOLDENROD);
        card.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        card.setTextFill(Color.GOLDENROD);
        cash.setOnAction(e -> showThankYouScreen("Cash"));
        card.setOnAction(e -> showThankYouScreen("Card"));

        VBox layout = new VBox(20, lbl, cash, card);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #ffe4e1;");
        lbl.setTextFill(Color.HOTPINK);

        Scene scene = new Scene(layout, 700, 550);
        primaryStage.setScene(scene);
    }

    private void showThankYouScreen(String method) {
        Label lbl = new Label("Thank you, " + userName + ", for choosing Elegance Salon!");
        lbl.setFont(Font.font("Tahoma", FontWeight.BOLD, 22));
        lbl.setTextFill(Color.HOTPINK);

        TextArea area = new TextArea();
        area.setFont(Font.font("Tahoma", 14));
        area.setEditable(false);
        for (Service s : selectedServices) {
            area.appendText(s.getDetails() + "\n");
        }
        area.appendText("Payment: " + processPayment(method));

        VBox layout = new VBox(20, lbl, area);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #ffe4e1;");

        Scene scene = new Scene(layout, 700, 550);
        primaryStage.setScene(scene);
    }

    @Override
    public String processPayment(String method) {
        return method.equals("Cash") ? "Cash (to be paid at counter)" : "Paid by Card";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
