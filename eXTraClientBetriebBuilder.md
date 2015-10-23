# XML-Builder #

Die in der Profil-Datei angegebenen Elemente werden mit Hilfe von _XML-Buildern_ in XML-Abschnitte überführt.

Beispiel:

Profil-Datei
```
<element>
  <Name>xcpt:Sender</Name>
  <Elternelement>TransportHeader</Elternelement>
</element>
```

Properties-Datei
```
message.builder.header.senderId.class=
message.builder.header.senderId.value=SENDER-ID
message.builder.header.senderNameValue=SENDER NAME
```

Erzeugter XML-Abschnitt
```
<xcpt:Sender>
  <xcpt:SenderID>SENDER-ID</xcpt:SenderID>
  <xcpt:Name>SENDER NAME</xcpt:Name>
</xcpt:Sender>
```

Die meisten XML-Builder sind genau einem Profil-Element zugeordnet. Deshalb müssen sie in der Konfiguration nicht ausgewählt werden.
## Übersicht über alle XML-Builder ##
| **Name** | **XML-Element** | **Klasse** |
|:---------|:----------------|:-----------|
| [configurableSMTPContactsPluginsBuilder](eXTraClientXMLBuilderconfigurableSMTPContactsPluginsBuilder.md) | xplg:Contacts   | de.extra.client.core.builder.impl.plugins.ConfigurableSMTPContactsPluginsBuilder |
| dataSourceConfigurablePluginsBuilder | xplg:DataSource | de.extra.client.core.builder.impl.plugins.DataSourceConfigurablePluginsBuilder |
| dataSourceInputDataPluginsBuilder | xplg:DataSource | de.extra.client.core.builder.impl.plugins.DataSourceInputDataPluginsBuilder |
| dataSourcePluginsBuilder | xplg:DataSource | de.extra.client.core.builder.impl.plugins.DataSourcePluginsBuilder |
| dataTransformConfigurablePluginsBuilder | xplg:DataTransforms | de.extra.client.core.builder.impl.plugins.DataTransformConfigurablePluginsBuilder |
| dataTransformPluginsBuilder | xplg:DataTransforms | de.extra.client.core.builder.impl.plugins.DataTransformPluginsBuilder |
| requestTransportBodyBuilder | req:TransportBody | de.extra.client.core.builder.impl.request.RequestTransportBodyBuilder |
| requestTransportBuilder | xcpt:Transport  | de.extra.client.core.builder.impl.request.RequestTransportBuilder |
| requestTransportHeaderBuilder | req:TransportHeader | de.extra.client.core.builder.impl.request.RequestTransportHeaderBuilder |
| transportBodyCharSequenceBuilder | xcpt:CharSequence | de.extra.client.core.builder.impl.components.TransportBodyCharSequenceBuilder |
| transportBodyDataBuilder | xcpt:Data       | de.extra.client.core.builder.impl.components.TransportBodyDataBuilder |
| !transportBodyFileInputBase64CharSequenceBuilder | xcpt:Base64CharSequence | de.extra.client.core.builder.impl.components.TransportBodyFileInputBase64CharSequenceBuilder |
| transportBodyRequestConfirmationOfReceiptSequenceBuilder | xcpt:ElementSequence | de.extra.client.core.builder.impl.components.TransportBodyRequestConfirmationOfReceiptSequenceBuilder |
| TransportBodyRequestListOfConfirmationOfReceiptSequenceBuilder | xcpt:ElementSequence | de.extra.client.core.builder.impl.components.TransportBodyRequestListOfConfirmationOfReceiptSequenceBuilder |
| [transportBodyRequestQueryElementSequenceBuilder](eXTraClientXMLBuilderTransportBodyRequestQueryElementSequenceBuilder.md) | xcpt:ElementSequence | de.extra.client.core.builder.impl.components.TransportBodyRequestQueryElementSequenceBuilder |
| transportHeaderReceiverBuilder | xcpt:Receiver   | de.extra.client.core.builder.impl.components.TransportHeaderReceiverBuilder |
| transportHeaderRequestDetailsBuilder | xcpt:RequestDetails | de.extra.client.core.builder.impl.components.TransportHeaderRequestDetailsBuilder |
| transportHeaderSenderBuilder | xcpt:Sender     | de.extra.client.core.builder.impl.components.TransportHeaderSenderBuilder |
| transportHeaderTestIndicatorBuilder | xcpt:TestIndicator | de.extra.client.core.builder.impl.components.TransportHeaderTestIndicatorBuilder |
| transportPluginsBuilder | req:TransportPlugins | de.extra.client.core.builder.impl.plugins.TransportPluginsBuilder |