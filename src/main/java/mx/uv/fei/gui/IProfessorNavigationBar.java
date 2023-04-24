package mx.uv.fei.gui;

import javafx.fxml.FXML;

import java.io.IOException;

public interface IProfessorNavigationBar {
    @FXML
    void redirectToAdvancementManagement() throws IOException;
    @FXML
    void redirectToProjectManagement();
    @FXML
    void redirectToEvidences();
    @FXML
    void redirectToRequests() throws IOException;
    @FXML
    void actionLogOut() throws IOException;
}
