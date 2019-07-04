# ing-sw-2019-16

- Matteo Pagliazzi - 847228 - [paglias](https://github.com/paglias)
- Bruno Sciarrone - 891589 - [brunosciarrone](https://github.com/brunosciarrone)
- Andrei - - [andreipricope](https://github.com/andreipricope)

## Funzionalità Implementate:

- Regole Complete 
- GUI 
- Socket 
- Funzionalità Aggiuntiva: Partite Multiple

## Tecnologie utilizzate

- Java 11
- JavaFX 11
- Libreria `gson` per il parsing deli file JSON di armi, dei powerup e dei settings di default.
- Libreria `junit` per i test
- Plugin `maven` per la generazione di JAR, test, ...
- SonarQube

## Sonar

TODO Sonar immagine qua (con test coverage per cartelle e spiegazione)

## UML

TODO link

## JavaDoc

TODO link/istruzioni

## Lancio JAR

### Setup JavaFX

Essendo JavaFX platform specific non è inclusa direttamente nei file jar ma abbiamo
messo a disposizione dei file zip per ogni piattaforma che possono essere trovati
nella cartella `jars`. Prima di eseguire i jar è necessario estrarre lo zip per
il proprio sistema operativo e (nel caso non sia già stato fatto automaticamente) spostare
i file estratti in una cartella chiamata `javafx-sdk-11.0.2` dentro `jars`.

NOTA: In base al sistema operativo l'estrazione dello zip potrebbe già
generare una cartella `javafx-sdk-11.0.2`, in quel caso non è necessario
spostare alcun file ma è sufficiente avere
 la cartella `javafx-sdk-11.0.2` all'interno di `jars`.

### Linux e macOs

Esistono 3 jar che possono essere trovati nella cartella `jars` e 3 script sh
per il lancio che si trovano nella root del progetto:

- jar completo lanciabile tramite lo script `adrenaline.sh` 
che permette di far partire sia client che server
- jar per il client lanciabile tramite lo script `adrenaline-client.sh`
- jar per il server lanciabile tramite lo script `adrenaline-server.sh`

Il jar completo include tutti i file del progetto mentre quelli per server e client solo
le classi e le resources necessarie.

### Windows

Ci sono due modi per far partire i jar: 

- Nel caso di utilizzo di Git Bash come terminale si possono utilizzare gli script `.sh`
- Per utilizzare il Prompt di Windows invece è necessario eseguire i commandi che si trovano
all'interno degli script `.sh` direttamente che riportiamo qua sotto per semplicità.

#### adrenaline.sh

`java --module-path ./jars/javafx-sdk-11.0.2/lib --add-modules javafx.fxml --add-modules javafx.controls  -jar ./jars/adrenaline.jar TIMEOUT=30 TURN_TIMEOUT=120 DEBUG=true`

#### adrenaline-client.sh

`java --module-path ./jars/javafx-sdk-11.0.2/lib --add-modules javafx.fxml --add-modules javafx.controls  -jar ./jars/adrenaline-client.jar TIMEOUT=30 TURN_TIMEOUT=120 DEBUG=true`

#### adrenaline-server.sh

`java --module-path ./jars/javafx-sdk-11.0.2/lib --add-modules javafx.fxml --add-modules javafx.controls  -jar ./jars/adrenaline-server.jar TIMEOUT=30 TURN_TIMEOUT=120 DEBUG=true`

## Settaggio impostazioni da linea di comando

Come mostrato poco sopra è possibile passare 3 settaggi per il gioco da linea di comando:

- `DEBUG=true/false` indica se stampare sul terminale info aggiuntive su errori e messaggi di rete utili per il debug (default `true`).
- `TIMEOUT=X` indica il tempo in secondi dopo il quale una partita parte in automatico dopo il raggiungimento di 3 o più giocatori (default `30`).
- `TURN_TIMEOUT=X` indica il tempo in secondi che ogni giocatore ha per eseguire un azione prima che venga disconnesso (default `120`).

## Generazione JAR

### JAR completo

Il jar completo è generabile tramite la funzionalità `Artifacts` di IntelliJ IDEA. 
Il jar viene generato nella cartella `out/artifacts/ing_sw_2019_16_jar` 
e prima di poter essere utilizzato deve essere rinominato in `adrenaline.jar` e spostato in `jars`.


### JAR separati per Client e Server

I jar separati per client e server invece sono generabili tramite maven con `mvn clean package`. 
Vengono creati nella cartella `target` con i nomi `client.jar-jar-with-dependencies.jar` e `server.jar-jar-with-dependencies.jar`.

Devono essere spostati nella cartella `jars` e rinominati in `adrenaline-client.jar` e `adrenaline-server.jar`.