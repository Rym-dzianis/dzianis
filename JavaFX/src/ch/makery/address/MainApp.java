package ch.makery.address;


import java.io.IOException;

import ch.makery.address.model.Person;
import ch.makery.address.repository.PersonRepository;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private PersonRepository personRepository;

    /**
     * Constructor
     */
    public MainApp() {
    	personRepository = PersonRepository.getInstance();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("�������� �����");

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     * �������������� �������� �����.
     *
     */
    public void initRootLayout() {
        try {
            // ��������� �������� ����� �� fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // �������� ��������, ���������� �������� �����.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     * ���������� �������� ������� ������ ��������� ������.
     */
    public void showPersonOverview() {
        try {
            // �������� �������� �������
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // ��������� �������� ������� � �������� �����.
            rootLayout.setCenter(personOverview);

            // ���� ������ ����������� � ����� ������ (MainApp).
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<Person> getPersonList() {
        return personRepository.getPersonList();
    }

    public static void main(String[] args) {
    	System.out.println("Started !!!");
        launch(args);
    }
}
