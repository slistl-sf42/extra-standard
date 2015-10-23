# eXTra-Konfiguration für CLI #

**Inhaltsverzeichnis**


# Einführung #

Durch die Profiling wird vereinbart welche Nachrichtenteile für die Datenaustausch zwischen Client und Server zugelassen sind. Dabei wird eine sogenannte Profildatei erstellt.

Diese Profiledatei wird in dem Client eingelesen und für die Steuerung der Nachrichtenaufbau verwendet.

# Konfiguration der Nachrichtaufbau über Profilling #

Eine Profildatei sieht wie folgt aus:

```
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<profil-configuration
	xmlns:xcpt="http://www.extra-standard.de/namespace/components/1"
	xmlns:xplg="http://www.extra-standard.de/namespace/plugins/1"
	xmlns:xreq="http://www.extra-standard.de/namespace/request/1">
	<element>
		<Name>xcpt:XMLTransport</Name>
	</element>
	<element>
		<Name>req:TransportHeader</Name>
		<Elternelement>XMLTransport</Elternelement>
	</element>
	<element>
		<Name>req:TransportBody</Name>
		<Elternelement>XMLTransport</Elternelement>
	</element>
	<element>
		<Name>req:TransportPlugins</Name>
		<Elternelement>XMLTransport</Elternelement>
	</element>
	<element>
		<Name>xplg:DataSource</Name>
		<Elternelement>TransportPlugins</Elternelement>
	</element>
	<element>
		<Name>xplg:!DataTransforms</Name>
		<Elternelement>TransportPlugins</Elternelement>
	</element>
	<element>
		<Name>xcpt:Sender</Name>
		<Elternelement>TransportHeader</Elternelement>
	</element>
	<element>
		<Name>xcpt:Receiver</Name>
		<Elternelement>TransportHeader</Elternelement>
	</element>
	<element>
		<Name>xcpt:TestIndicator</Name>
		<Elternelement>TransportHeader</Elternelement>
	</element>
	<element>
		<Name>xcpt:RequestDetails</Name>
		<Elternelement>TransportHeader</Elternelement>
	</element>
	<element>
		<Name>xcpt:Data</Name>
		<Elternelement>TransportBody</Elternelement>
	</element>
	<element>
		<Name>xcpt:Base64CharSequence</Name>
		<Elternelement>Data</Elternelement>
	</element>
</profil-configuration>
```

Hier ist sowohl die Reihenfolge als auch die erlaubte Nachrichtenfragmente definiert, was ausreichen ist um die Erstellung der eXTra Datei zu steuern.

# Konfiguration der Builder-Implementierungen über Properties #

Über eXTra Property Datei kann gesteuert werden, wie die Einzelnen Nachricht XML Fragmente erstellt werden sollen.

Hier ist wichtig zu wissen, dass die Implementierungen über die Namen angesprochen werden.

Beispiel: Für das Erstellen des Elements xplg:DataTransforms sind 2 Implementierungen vorhenden: dataTransformPluginsBuilder und dataTransformConfigurablePluginsBuilder.


Dabei kann zwischen 3 Konfigurationsmöglichkeiten unterschieden werden:

  * Default Konfiguration
  * Konfiguration für die mehrfachimplementierten Fragement-Builder
  * Konfiguration für die Plugins, die mehrfach angewendet werden.

## Default Konfiguration ##

Wenn für die Erstellung eines XML Fragmentes nur eine Implementierung vorhanden ist, wird diese Implementierung automatisch ausgewählt, ohne diese impliziert konfiguriert werden muss.


## Konfiguration für die mehrfachimplementierten Fragement-Builder ##

Wenn für die Erstellung eines XML Fragmentes mehrere Implementierung vorhanden sind,  muss die gewünschte Implementierung in properties über den entsprechenen Schlüssel und der Namen der Implementierung konfiguriert sein:

```
builder.xplg.DataTransforms=dataTransformConfigurablePluginsBuilder
```
Der Schlüssel wird erstellt durch den Prefix **_builder_**, darauffolgenden Element Prefix **_xplg_** und Elementnamen **_DataTransforms_** durch den komma getrennt.

Value ist der Name der Implementierung.

## Konfiguration für die Plugins, die mehrfach angewendet werden ##


Wenn ein XML Fragment Builder mehrfach bei dem Nachrichtenaufbau angewendet werden sollte sind die folgende Konfigurationseinträge zu verwenden:

```
builder.xplg.DataTransforms=dataTransformConfigurablePluginsBuilder
builder.xplg.DataTransforms1=dataTransformPluginsBuilder
```

Die Schlüssel werden durch hinzufügen von einer Zahl ergänzt. Diese Zahl wird bei der Reihenfolge der Nachrichtenaufbau berücksichtigt.

# Felderkonfiguration über Properties #

Um Properties zu einer Implementierung einfacher voneinander zu trennen sind folgende Namenkonventionen zu berücksichtigen.

Propertie für den Feld name in der Implementierung **_dataTransformConfigurablePluginsBuilder_** für die Aufbau des Elementes xplg:DataTransforms ist mit dem folgenden Schlüssel zu definieren:

```
builder.xplg.DataTransforms.dataTransformConfigurablePluginsBuilder=Mein Name
```

## Annotation PluginConfiguration und PluginValue ##

Für die Unterstützung bei der Plugin Entwicklung sind 2 Annotation entwickelt worden. Die dienen der Erleichterung bei der Plugin und Builder Konfiguration.

Ein Beispiel für die Anwendung der Annotation sieht wie folgt aus:

```
@Named("configurableSMTPContactsPluginsBuilder")
@PluginConfiguration(pluginBeanName = "configurableSMTPContactsPluginsBuilder", pluginType = PluginConfigType.Builder)
public class ConfigurableSMTPContactsPluginsBuilder extends XmlComplexTypeBuilderAbstr {

	private static final String BUILDER_XML_MESSAGE_TYPE = "xplg:Contacts";

	@PluginValue(key = "emailaddress")
	@NotNull
	private String eMailAddress;
```

Der Konfigurationseintrag zu der Value eMailAddress wird von dem Programm automatisch berechnet:

```
#Konfigurationsvalue
builder.xplg.Contacts.configurableSMTPContactsPluginsBuilder.emailaddress=test@rentenservice.de
```

Der Key besteht aus folgenden Teilen:

  * Builderprefix = builder
    * BuilderPrefix wird aus dem Wert PluginConfiguration.pluginType abgeleitet. Die Lieste des Builder bzw. Pluginsprefixes ist [hier](eXTraBuilderKonfiguration#Plugins_Prefix.md) zu finden
  * Plugintype = xplg.Contacts
  * PluginBeanName = configurableSMTPContactsPluginsBuilder
    * Der PluginBeanName wird aus dem @PluginConfiguration.pluginBeanName übernommen
  * ValueName = emailaddress
    * Der Name der Value wird aus dem Wert @PluginValue.key übernommen

Aktivierung der @PluginConfiguration wird durch den Spring Eintrag vorgenommen:
```
	<bean
		class="de.extra.client.core.annotation.PropertyPlaceholderPluginConfigurer">
		<property name="propertiesArray">
			<array>
				<ref bean="meineProperty" />
			</array>
		</property>	
			<property name="ignoreNullValues" value="true" />
	</bean> 
```
Dabei stellt der Eintrag _**ignoreNullValues = true**_ sicher,
dass die fehlenden Konfigurationswerte für die nicht aktivierte Plugins bzw. Builder den Start der Applikation nicht hindern.

Mit Hilfe von javax.validation (z.B hier _**@NotNull**_) werden die Konfigurationswerte zur Laufzeit überprüft.

### Default Value ###

Eine konfigurierbare Property kann  mit dem DEFAULT-Wert vorbelegt werden Z.B.
```
@Named("dbQueryDataPlugin")
@PluginConfiguration(pluginBeanName = "dbQueryDataPlugin", pluginType = PluginConfigType.DataPlugins)
public class DBQueryDataPlugin implements IDataPlugin {

	@PluginValue(key = "resendFailed:false")
	// @Value("${plugins.dataplugin.dbQueryDataPlugin.resendFailed:false}")
	private boolean resendFailed;
```

## Plugins Präfix ##

| **PluginConfigType** | **Prefix** |
|:---------------------|:-----------|
| DataPlugins          | plugins.dataplugin |
| DataSource           | plugins.datasource |
| Certificates         | plugins.certificates |
| DataTransforms       | plugins.datatransform |
| Contacts             | plugins.contacts |
| OutputPlugins        | plugins.outputplugin |
| ResponseProcessPlugins | plugins.responseprocessplugin |
| Builder              | builder    |




