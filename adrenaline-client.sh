#!/bin/bash
java --module-path ./out/artifacts/javafx-sdk-11.0.2/lib --add-modules javafx.fxml --add-modules javafx.controls  -jar ./target/client.jar-jar-with-dependencies.jar TIMEOUT=20 TURN_TIMEOUT=20 DEBUG=true