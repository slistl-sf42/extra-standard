**Inhaltsverzeichnis**


# Betriebsanleitung #

In der Betriebsanleitung wird beschrieben, wie der eXTra-Client zur Konfiguration und Betrieb von Fachverfahren verwendet werden kann. Die Auslieferungsversion des eXTra-Clients besteht aus einem zip-Archiv (z.B. extra-cli-1.0.0.zip). In diesem befindet sich der Basisteil. Die einzelnen Fachverfahren können getrennt vom Basisteil entwickelt werden und werden jeweils in gesonderten zip-Archiven ausgeliefert.

Der eXTra-Client unterstützt mehrere Kommunikationsabläufe, z.B.:
  * Senden von Eingabedateien an den eXTra-Server über einen Webservice
  * Ergebnisse vom eXTra-Server anfordern.
  * Bestätigungen an den eXTra-Server senden

Die Kommunikationsabläufe werden von dem eXTra-Client in einer [Datenbank](eXTraClientBetriebDatenbank.md) protokolliert (die Skripte zum Aufbau der Datenbank befinden sich in der Auslieferungsversion). Dies ist notwendig, da spätere Phasen eventuell Ergebnisse von Vorgängerphasen benötigen (z.B. Empfangsbestätigung von Dateien).

Der eXTra-Client kann so konfiguriert werden, das mehrstufige Fachverfahren (z.B. Sterbedatenabgleich) ohne Programmänderungen realisiert werden können. Falls noch Funktionalität fehlt, kann der eXTra-Client durch das Entwickeln neuer Plugins erweitert werden.

Auf der Seite [eXTraClientBetriebSzenarioSendFetch](eXTraClientBetriebSzenarioSendFetch.md) wird exemplarisch die Installation des eXTra-Clients mit einem technischen Referenzverfahren beschrieben.

# Anforderungen an den Betriebsrechner #
Der eXTra-Client wird als Java Anwendung ausgeliefert und benötigt eine Java 6 Laufzeitumgebung. Er ist nicht an ein spezielles Betriebssystem gebunden. Für die Protokollierung des Kommuninkationsablaufes wird eine Datenbank benötigt (siehe [Datenbank](eXTraClientBetriebDatenbank.md)). Für den Kommunikationsablauf müssen evtl. bestimmte Externe eXTra-Server erreichbar sein.

# Bestandteile der Basisversion (extraClient) #

Die Auslieferungsversion besteht aus dem Programm-Bereich, einem exemplarischen Ausführungs-Bereich und einem SQL-Skript-Bereich. Jeder Bereich befindet sich in einem Verzeichnis:

#### Ausführungsbereich (Verzeichnis: /bin) ####
Hier befinden sich beispielhaft Batch-Dateien für Windows (eXTra-cli.bat) und Linux.

#### Programmbereich (Verzeichnis: /lib) ####
Hier stehen sämtliche benötigte Jar-Dateien - sowohl die eXTra-Jar-Dateien als auch die externen Bibliotheken zur Verfügung. Für eine Datenbindung Anbindung werden spezielle JDBC-Treiber benötigt (z.B. Oracle JDBC). Diese werden nicht mitgeliefert und müssen hier abgelegt werden.

#### SQL-Skript-Bereich (Verzeichnis: /sql) ####
Hier sind SQL-Skripte für den Aufbau der eXTra-Client Datenbank abgelegt.

Aufgrund des modularen Aufbaus und der konfigurativen Steuerung des eXTra-Clients können zur Basisversion weitere Fachverfahren hinzugefügt werden. Pro Fachverfahren sollte die Konfiguration in einem Konfigurationsverzeichnisse abgelegt werden. Jedem Fachverfahren ist ein DB-Skript für die Initialisierung zugewiesen.

# Installation/Bereitstellung einer Version #
Die Bereitstellung einer Version erfolgt in diesen Schritten:
  * Auspacken der Auslieferungsversion in einem Verzeichnis auf dem Betriebsrechner
  * DB-Treiber in den lib-Ordner Kopieren
  * Fachverfahren in einem eigenen Ordner ablegen.
  * Konfigurationsdateien des Fachverfahrens an die Umgebung des Betriebsrechners anpassen (z.B. Verzeichnisse für Input und Output).
  * eXTra-Client Datenbank einrichten, initialisieren oder updaten (z.B. über mitgeliefertes SQL-Skript).
  * Batch-Dateien für den Aufruf der Fachverfahren im Ordner _bin_ erstellen bzw. anpassen

Genauere Informationen zur Einrichtung eines Fachverfahrens befinden sich in der Dokumentation des jeweiligen Fachverfahrens. Beispiel siehe [eXTraClientBetriebSzenarioSendFetch](eXTraClientBetriebSzenarioSendFetch.md).

## Konfiguration ##
Alle Konfigurationsdateien für einen Anwendungsfall befinden sich üblicherweise in einem Verzeichnis (z.B. conf). Dieses Verzeichnis wird beim Aufruf des eXTra-Clients als Parameter übergeben.
Folgende Dateien bilden die Konfigurationsdaten für ein Fachverfahren:

| **Datei** | **Beschreibung** |
|:----------|:-----------------|
| [extra-global.properties](eXTraClientGlobalConfig.md) | Globale Konfigurationsdatei. In dieser Datei werden werden z.B. die Verbindungsparameter zur Datenbank konfiguriert. |
| [extra-properties-basic.properties](eXTraClientBetriebConfigBasis.md) | Zentrale Konfigurationsdatei. Besteht aus Attribut-Werte Paaren (Java-properties Format). In dieser Datei werden z.B. das Fachverfahren, die Plugins und die XML-Builder konfiguriert. |
| [extra-properties-user.properties](eXTraClientBetriebConfigUser.md) | Ergänzende/überschreibende Einstellungen. Üblicherweise sind hier die Verbindungsparameter zur Webservice-Zieladresse abgelegt. |
| [extra-mandant-xxx.properties](eXTraClientMandatorConfig.md) | Globale Mandantenkonfigurationsdatei. Optional, wobei _xxx_ der Name des Mandanten darstellt. Das Verzeichnis muss den Namen des angegebenen Mandanten tragen unter unterhalb der über -g angegebenen globalen Konfigurationsverzeichnisses liegen. In dieser Datei werden z.B. Parameter zum eXTra-Server (Endpoint Webservice) konfiguriert. |
| [logging-config.xml](eXTraClientBetriebLogging.md) | Logging Einstellungen (z.B. Name der Logfiles und Loglevel) |
| [profile.xml](eXTraClientBetriebConfigProfile.md) | eXTra-Profil Datei (siehe eXTra Standard). In der Profil-Date sind die Elemente des Extra-Standards aufgeführt, die zur Kommunikation zwischen Client und Server verwendet werden sollen (Der Name ist frei wählbar.)|

### Passwortverschlüsselung in Konfigurationsdateien ###

Damit Passwörter, z.B. für eine Datenbank, nicht als Klartext in
Konfigurationsdateien abgelegt werden müssen, stellt der eXTra-Client eine
Möglichkeit bereit, diese Passwörter zu verschlüsseln und lediglich den
Schlüssel anzugeben.

Vorraussetzung:

Die Umgebungsvariable `EXTRA_ENCRYPTION_PASSWORD` ist auf ein nur dem
Administrator bekanntes Kennwort zu setzen, welches dazu genutzt wird, das
eigentliche Passwort zu verschlüsseln und zu entschlüsseln.

Die Umgebungsvariable `EXTRA_ENCRYPTION_PASSWORD` kann nach dem Einsatz des
Verschlüsselungstool direkt wieder entfernt werden.

Vor und während eines Laufs des Extra-Clients muss die Umgebungsvariable gesetzt
sein. Diese kann nach dem Lauf wieder entfernt werden.

Anwendung:

In dem Installationsverzeichnis des Extra-Clients befindet sich das Skript "bin/encrypt-password.bat".

Dieses wird mit dem zu verschlüsselndem Passwort als Parameter aufgerufen:
```
extra-client\bin\encrypt-password.bat 12345678
```
Das Programm erzeugt als Ausgabe den in die Konfigurationsdatei einzutregenden Wert, z.B.
```
ENC(rGyUjlb1oi3WWeRUTDNQHLlV+4PefVTc)
```
Dieser Wert muss kopiert und in die entsprechende Konfigurationsdatei eingetragen werden, z.B.
```
plugins.execution.executionPersistenceJpa.database.password=ENC(rGyUjlb1oi3WWeRUTDNQHLlV+4PefVTc)
```
Sobald ein verschlüsselter Wert in einer Konfigurationsdatei vorhanden ist, wird
zur Laufzeit des eXTra-Clients versucht, diesen Wert zu entschlüsseln. Hierzu
muss, wie bereits unter "Vorraussetzung" beschrieben, die Umgebungsvariable
`EXTRA_ENCRYPTION_PASSWORD` auf das korrekte, zur Verschlüsselung genutzte
Passwort gesetzt werden, da ansonsten die Entschlüsselung nicht funktionieren
kann.



## Aufruf der Fachverfahren ##
Die Fachverfahren können über Batch-Dateien aufgerufen werden. Beispielhaft befindet sich im _/bin_ Verzeichnis der Auslieferungsversion ein Aufruf für Windows (extra.bat). In diesen Skripten wird eine Umgebungsvariable EXTRA\_CLIENT\_HOME für den Zugriff auf die eXTra-Client Dateien verwendet.

In der Datei extra.bat kann auch der Heap-Speicher angepasst werden. Dies kann nötig werden, wenn sehr große Dateien versendet werden sollen (> 30MB). Auf einem 32 Bit Windows System sind folgende Testdaten ermittelt worden:

| Heap Space | Dateigröße | Ergebnis |
|:-----------|:-----------|:---------|
| 1610       | 69,6 MB    | Fehler   |
| 1610       | 53,6 MB    | OK       |

In einer Produktionsumgebung (64 Bit) kann der Heap-Speicher größer gewählt werden. In der aktuellen Version des eXTra-Clients muss aber im Vorfeld auf die Größe geachtet werden und der Heap-Space entsprechend groß gewählt werden.

**Beispiel für einen einfachen Aufruf:**

java -jar extra-cli-1.0.0-SNAPSHOT.jar -l logs -c conf

Das Logging erfolgt in das Verzeichnis logs. Die Konfiguration wird aus dem Verzeichnis conf gelesen.

### Aufrufparameter ###

| Parameter | Beispiel | Beschreibung |
|:----------|:---------|:-------------|
| -g "Globales Konfigurationsverzeichnis" | -g globalconf | Globale Konfiguration wird aus dem Verzeichnis _globalconf_ gelesen **(Pflicht)** |
| -c "Konfigurationsverzeichnis" | -c conf  | Konfiguration wird aus dem Verzeichnis _conf_ gelesen **(Pflicht)** |
| -m "Mandant" | -m xyz   | Mandanten-spezifische Konfiguration wird aus dem Konfigurationsverzeichnis des Mandanten gelesen und überschreibt ggfs. globale Konfigurationen. Das Verzeichnis muss den Namen des angegebenen Mandanten tragen unter unterhalb der über -g angegebenen globalen Konfigurationsverzeichnisses liegen. |
| -l "Logverzeichnis" | -l logs  | Logs werden in das Verzeichnis _logs_ geschrieben |
| -b "Backupverzeichnis" | -l backup | Erfolgreich gesendete Dateien werden in das angegebene Verzeichnis kopiert. |
| -d        | -d       | Erfolgreich gesendete Dateien werden aus dem Quellverzeichnis gelöscht. |
| -h        | -h       | Hilfe        |

Im Verarbeitungsverlauf können vom eXTra-Client Ergebnisdateien abgelegt worden sein, die noch von einer externen Anwendung geprüft werden müssen. Das Ergebnis dieser Prüfung kann dem eXTra-Client über folgende zwei Parameter mitgeteilt werden:

| Parameter | Beispiel | Beschreibung |
|:----------|:---------|:-------------|
| -oc "Dateiname"| extra.bat conf\luxemburg\phase2 -oc "daten1" | Meldet das die Datei "daten1" erfolgreich verarbeitet werden konnte (oc steht für "Output Confirm") |
| -of "Dateiname"| extra.bat conf\luxemburg\phase2 -of "daten2" | Meldet das die Datei "daten2" nicht erfolgreich verarbeitet werden konnte (of steht für "Output Failure") |

#### Beispiel für die Verwendung der Aufrufparameter ####

Folgendes Beispiel (Fachverfahren Sterbedaten Ausland) zeigt die Verwendung der Aufrufparameter:

  * Phase 1: Senden einer Datei an den eXTra-Server
  * Phase 2 A: Empfang von 3 Dateien vom eXTra-Server
  * Phase 2 B: Verarbeitung der Dateien und Ergebnismeldung:
    * Der eXTra Client ist so konfiguriert, das eine Datei erst dann bestätigt werden, wenn ihre erfolgreiche Verarbeitung gemeldet wurde.  (Eintrag in phase2/extra-properties-basic.properties: plugins.responseprocessplugin.fileSystemResultPackageDataResponseProcessPlugin.successStatus=WAIT)
    * Eine Datei wird erfolgreich verarbeitet (Meldung an den eXTra-Client)
    * Fehler beim Verarbeiten einer Datei (Meldung an den eXTra-Client)
    * Eine Datei wird gar nicht verarbeitet
  * Phase 3: Bestätigung der erfolgreich verarbeiteten Dateien an den eXTra-Server

Die Aufrufe werden über den Parametrisierten Aufruf der Datei 'extra.bat' durchgeführt. (siehe Verzeichnis extra-sterbedaten-ausland/bin)

##### Aufruf Phase 1: Senden einer Datei an den eXTra-Server #####
```
extra.bat -g ..\..\global -c conf\luxemburg\phase1 -m DRV -l ..\..\logs -b ..\..\backup\phase1```

##### Aufruf Phase 2: Empfang von 3 Dateien vom eXTra-Server #####
```
extra.bat -g ..\..\global -c conf\luxemburg\phase2 -m DRV -l ..\..\logs -b ..\..\backup\phase1```
Folgende drei Dateien hat der eXTra-Client empfangen und im Dateisystem abgelegt:

![http://extra-standard.googlecode.com/svn/wiki/images/betrieb_nach_phase2_dateien_empfangen.png](http://extra-standard.googlecode.com/svn/wiki/images/betrieb_nach_phase2_dateien_empfangen.png)

##### Verarbeitung der Dateien #####
Die drei Dateien werden von einer externen Anwendung verarbeitet. Dem eXTra-Client soll das Verarbeitungsergebnis der drei Dateien gemeldet werden. In der Kommunikations-Tabelle (COMMUNICATION\_PROTOCOL) vermerkt der eXTra-Client dieses Ergebnis. Nur erfolgreich verarbeitete Dateien meldet der eXTra-Client in Phase 3 an den Server. Nach Aufruf von Phase 2 hat die Tabelle COMMUNICATION\_PROTOCOL diesen Inhalt:

![http://extra-standard.googlecode.com/svn/wiki/images/betrieb_nach_phase2_status.png](http://extra-standard.googlecode.com/svn/wiki/images/betrieb_nach_phase2_status.png)

Die Verarbeitungsergebnisse werden dem eXTra-Client gemeldet:

  * Erfolgreiche Verarbeitung einer Datei dem eXTra-Client melden (Endung 676):
```
extra.bat -g ..\..\global -c conf\luxemburg\phase2 -m DRV -oc "FIKELA.PRD.DS.LU2013-08-28 10-01-34.676"```

  * Fehlerhafte Verarbeitung einer Datei dem eXTra-Client melden (Endung 693):
```
 extra.bat -g ..\..\global -c conf\luxemburg\phase2 -m DRV -of "FIKELA.PRD.DS.LU2013-08-28 10-01-34.693"```

In der Tabelle COMMUNICATION\_PROTOCOL sieht man die Verarbeitungsergebnisse:

![http://extra-standard.googlecode.com/svn/wiki/images/betrieb_nach_phase2_status_meldung.png](http://extra-standard.googlecode.com/svn/wiki/images/betrieb_nach_phase2_status_meldung.png)

##### Aufruf Phase 3: Bestätigung der erfolgreich verarbeiteten Dateien an den eXTra-Server #####
In Phase 3 werden erfolgreich verarbeitete Dateien dem eXTra-Server über diesen Aufruf bestätigt.

```
extra.bat -g ..\..\global -c conf\luxemburg\phase3 -m DRV -l ..\..\logs -b ..\..\backup\phase1```

Nach Aufruf der Phase 3 darf nur die Datei '...676' als erfolgreich verarbeitet gemeldet worden sein. In der Tabelle COMMUNICATION\_PROTOCOL ist ein entsprechender Eintrag vorhanden:

![http://extra-standard.googlecode.com/svn/wiki/images/betrieb_nach_phase3_bestaetigung.png](http://extra-standard.googlecode.com/svn/wiki/images/betrieb_nach_phase3_bestaetigung.png)




