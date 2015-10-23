# Logging #

Zur Analyse und Fehlersuche kann konfigurativ ein Logging aktiviert werden. In den Logausgaben stehen z.B. Informationen über die verwendeten Plugins.

| **Logger** | **Beschreibung** |
|:-----------|:-----------------|
| de.extra.client.operation | Zentraler Logger. Protokolliert den Kommunikationsablauf |
| de.extra.client.core.annotation | Informationen über Konfigurationseinstellungen |
| de.extra.client.core.builder | Erstellung von XML-Dokumenten |
| de.extra.client.logging | Ausführung des eXTra-Clients |
| de.extra.client.message.request | Ausgabe des XML-Request (auf Level DEBUG). Achtung! XML-Request sollte nicht zu groß sein (< 1MB) |
| de.extra.client.message.response | Ausgabe des XML-Response (auf Level DEBUG). Achtung! XML-Response sollte nicht zu groß sein (< 1MB) |
| org.springframework | springframework-Logger |
| org.hibernate | hibernate-Logger |
| org.apache | apache-Logger    |

Für jeden Logger kann ein Log-Level angegeben werden (_ERROR, WARN, INFO, DEBUG_). Das Logging erfolgt unter Verwendung des Logback Frameworks (http://logback.qos.ch/)

Die Logdatei hat den Namen _logging-config.xml_. Sie kann für jede Phase angepasst werden und befindet sich im Konfigurationsverzeichnis der Phase. Das Ausgabeverzeichnis heißt üblicherweise _logs_.

**Beispiel einer Logdatei:**

```

<?xml version="1.0" encoding="ISO-8859-1" ?>
<configuration>

	<timestamp key="now" datePattern="yyyyMMdd_HHmmss" />

	<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
		<encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
			<Pattern>%date{ISO8601,Europe/Berlin}: [%level{6}]: %logger{10} %msg%n</Pattern>
		</encoder>
	</appender>

	<appender name="OPERATION_FILE" class="ch.qos.logback.core.FileAppender">
		<file>${logfilepath}/extra-execution_${now}.log</file>
		<encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
			<pattern>%date{ISO8601,Europe/Berlin}: %msg%n
			</pattern>
		</encoder>
	</appender>
	
	<appender name="FILE" class="ch.qos.logback.core.FileAppender">
		<file>${logfilepath}/extra-execution_developer_${now}.log</file>
		<encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
			<pattern>%date{ISO8601,Europe/Berlin}: [%thread] [%level{6}]: %logger{10} %msg%n
			</pattern>
		</encoder>
	</appender>

	<appender name="MESSAGE_FILE" class="ch.qos.logback.core.FileAppender">
		<file>${logfilepath}/extra-message_${now}.log</file>
		<encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
			<pattern>%date{ISO8601,Europe/Berlin}: %msg%n
			</pattern>
		</encoder>
	</appender>


	<logger name="org.springframework" level="ERROR" />
	<logger name="org.hibernate" level="ERROR" />
	<logger name="org.apache" level="ERROR" />
	
	<logger name="de.extra.client.core.annotation" level="INFO" />
	<logger name="de.extra.client.core.builder" level="INFO" />
	
	<logger name="de.extra.client.operation" level="INFO">
		<appender-ref ref="OPERATION_FILE" />
	</logger>
	
	<logger name="de.extra.client.logging" level="DEBUG" />

	<!-- extra Nachrichten -->
	<logger name="de.extra.client.message.request" level="INFO">
		<appender-ref ref="MESSAGE_FILE" />
	</logger>
	<logger name="de.extra.client.message.response" level="INFO">
		<appender-ref ref="MESSAGE_FILE" />
	</logger>

	<root level="DEBUG">
		<appender-ref ref="STDOUT" />
		<appender-ref ref="FILE" />
	</root>

</configuration>
```