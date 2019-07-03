#!/bin/bash
java --module-path ./jars/javafx-sdk-11.0.2/lib --add-modules javafx.fxml --add-modules javafx.controls  -jar ./jars/adrenaline.jar TIMEOUT=30 TURN_TIMEOUT=120 DEBUG=true