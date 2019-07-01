Funzionalità Implementate:

Regole Complete + GUI + Socket + Funzionalità Aggiuntiva partite multiple

NOTE:

Ancora non abbiamo il jar separato, mancano alcuni commenti e test e alcuni elementi della GUI.
Li aggiungeremo appena possibile nei prossimi giorni prima della consegna del 5.

Lancio JAR:

- Linux, Mac o Windows da Git Bash: da terminale eseguire ./adrenaline.sh
- Windows: da prompt eseguire
    java --module-path ./out/artifacts/javafx-sdk-11.0.2/lib --add-modules javafx.fxml --add-modules javafx.controls  -jar ./out/artifacts/ing_sw_2019_16_jar/ing-sw-2019-16.jar

Generazione JAR multiple
    - mvn clean:package
    - si troveranno in target