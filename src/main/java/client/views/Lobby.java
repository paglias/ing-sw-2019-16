package client.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import messages.GameSettingsMessage;
import messages.GameStateMessage;
import messages.client_data.PlayerOtherData;
import utils.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Lobby extends AbstractView {
    private GenericWindows errorWindow = new GenericWindows();

    @FXML Button startButton;
    @FXML Label connectedPlayer1;
    @FXML Label connectedPlayer2;
    @FXML Label connectedPlayer3;
    @FXML Label connectedPlayer4;
    @FXML Label connectedPlayer5;

    @FXML RadioButton fiveSkulls;
    @FXML RadioButton sixSkulls;
    @FXML RadioButton sevenSkulls;
    @FXML RadioButton eightSkulls;

    @FXML RadioButton mapChosen1;
    @FXML RadioButton mapChosen2;
    @FXML RadioButton mapChosen3;
    @FXML RadioButton mapChosen4;

    public int Map = 0;

    /**
     * Sets map.
     *
     * @param map the map
     */
    public void setMap(int map) {
        this.Map = map;
        switch (map) {
            case 1:
                mapChosen1.setSelected(true);
                break;
            case 2:
                mapChosen2.setSelected(true);
                break;
            case 3:
                mapChosen3.setSelected(true);
                break;
            case 4:
                mapChosen4.setSelected(true);
                break;

        }
    }

    public int Skulls = 0;

    /**
     * Sets skulls.
     *
     * @param skulls the skulls
     */
    public void setSkulls(int skulls) {
        this.Skulls = skulls;
        switch (Skulls) {
            case 5:
                fiveSkulls.setSelected(true);
                break;
            case 6:
                sixSkulls.setSelected(true);
                break;
            case 7:
                sevenSkulls.setSelected(true);
                break;
            case 8:
                eightSkulls.setSelected(true);
                break;
        }
    }

    /**
     * Choose map 1.
     */
    public void chooseMap1() {
        setMap(1);
    }

    /**
     * Choose map 2.
     */
    public void chooseMap2() {
        setMap(2);
    }

    /**
     * Choose map 3.
     */
    public void chooseMap3() {
        setMap(3);
    }

    /**
     * Choose map 4.
     */
    public void chooseMap4() {
        setMap(4);
    }

    /**
     * Choose skulls 5.
     */
    public void chooseSkulls5() {
        setSkulls(5);
    }

    /**
     * Choose skulls 6.
     */
    public void chooseSkulls6() {
        setSkulls(6);
    }

    /**
     * Choose skulls 7.
     */
    public void chooseSkulls7() {
        setSkulls(7);
    }

    /**
     * Choose skulls 8.
     */
    public void chooseSkulls8() {
        setSkulls(8);
    }

    /**
     * Preview map 1.
     */
    public void previewMap1() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map one");
        Scene scene;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/PreviewMap1.fxml"));
            scene = new Scene(root, 600, 430);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            errorWindow.loadingFailure();
        }
    }

    /**
     * Preview map 2.
     */
    public void previewMap2() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map two");
        Scene scene;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/PreviewMap2.fxml"));
            scene = new Scene(root, 600, 430);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            errorWindow.loadingFailure();
        }
    }

    /**
     * Preview map 3.
     */
    public void previewMap3() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map three");
        Scene scene;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/PreviewMap3.fxml"));
            scene = new Scene(root, 600, 430);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            errorWindow.loadingFailure();
        }
    }

    /**
     * Preview map 4.
     */
    public void previewMap4() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map four");
        Scene scene;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/PreviewMap4.fxml"));
            scene = new Scene(root, 600, 430);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            errorWindow.loadingFailure();
        }
    }

    /**
     * Sets game with skulls and map choice set.
     */
    public void setupGame() {
        boolean isSetup = Game.controller.getLastGameStateMessage().gameBoardData.gameSetup;

        if (!isSetup) {
            GameSettingsMessage gameSettingsMessage = new GameSettingsMessage();
            gameSettingsMessage.setMapNumber(Map);
            gameSettingsMessage.setSkullsNumber(Skulls);
            Game.controller.sendMsg(gameSettingsMessage);
        }
    }

    /**
     * Start game.
     */
    public void startGame() {
        Stage stage;
        Parent root;
        stage = (Stage) startButton.getScene().getWindow();
        stage.close();

        List<Window> openWindows = Stage.getWindows().filtered(window -> window.isShowing());
        stage = (Stage) openWindows.get(0);

        try {
            switch (Map) {
                case 1:
                    root = FXMLLoader.load(getClass().getResource("/FXMLs/Map1.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    break;
                case 2:
                    root = FXMLLoader.load(getClass().getResource("/FXMLs/Map2.fxml"));
                    Scene scene2 = new Scene(root, 1366, 768);
                    stage.setScene(scene2);
                    stage.centerOnScreen();
                    break;
                case 3:
                    root = FXMLLoader.load(getClass().getResource("/FXMLs/Map3.fxml"));
                    Scene scene3 = new Scene(root, 1366, 768);
                    stage.setScene(scene3);
                    stage.centerOnScreen();
                    break;
                case 4:
                    root = FXMLLoader.load(getClass().getResource("/FXMLs/Map4.fxml"));
                    Scene scene4 = new Scene(root, 1366, 768);
                    stage.setScene(scene4);
                    stage.centerOnScreen();
                    break;
                default:
                    errorWindow.loadingFailure();
            }
        } catch (Throwable e) {
            Logger.err(e, null);
            errorWindow.loadingFailure();
        }
    }
    /**
     * Update on map choice, number of skulls, active players with nicknames.
     */
    public void updateWithData(GameStateMessage gameStateMessage) {
        if (gameStateMessage == null || gameStateMessage.gameBoardData == null) return;

        if (gameStateMessage.gameBoardData.gameSetup) {
            setMap(gameStateMessage.gameBoardData.nMap);
            setSkulls(gameStateMessage.gameBoardData.skullsN);
        }

        if (gameStateMessage.gameBoardData.gameStarted) {
            startGame();
            return;
        }

        ArrayList<PlayerOtherData> players = gameStateMessage.gameBoardData.players;
        int index = 0;
        for (PlayerOtherData p : players) {
            if (index == 0) {
                connectedPlayer1.setText(p.nickname);
            }

            if (index == 1) {
                connectedPlayer2.setText(p.nickname);
            }

            if (index == 2) {
                connectedPlayer3.setText(p.nickname);
            }

            if (index == 3) {
                connectedPlayer4.setText(p.nickname);
            }

            if (index == 4) {
                connectedPlayer5.setText(p.nickname);
            }
            index++;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Game.controller.registerCurrentView(this);
    }
}