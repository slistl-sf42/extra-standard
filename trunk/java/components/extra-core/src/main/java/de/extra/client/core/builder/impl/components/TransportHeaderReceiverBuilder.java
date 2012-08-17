/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package de.extra.client.core.builder.impl.components;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import de.drv.dsrv.extrastandard.namespace.components.ClassifiableIDType;
import de.drv.dsrv.extrastandard.namespace.components.ReceiverType;
import de.drv.dsrv.extrastandard.namespace.components.TextType;
import de.extra.client.core.builder.IXmlComplexTypeBuilder;
import de.extra.client.core.model.ConfigFileBean;
import de.extra.client.core.model.SenderDataBean;

/**
 * @author Leonid Potap
 * 
 */
@Named("transportHeaderReceiverBuilder")
public class TransportHeaderReceiverBuilder implements IXmlComplexTypeBuilder {

	private static Logger logger = Logger
			.getLogger(TransportHeaderReceiverBuilder.class);

	private static final String BUILDER_XML_MESSAGE_TYPE = "xcpt:Receiver";

	@Value("${message.builder.header.receiverId.class}")
	private String receiverIdClass;
	@Value("${message.builder.header.receiverId.value}")
	private String receiverIdValue;
	@Value("${message.builder.header.receiverNameValue}")
	private String receiverNameValue;

	/**
	 * Erstellt die SenderInformationen im Kontext von Header (non-Javadoc)
	 * 
	 * @see de.extra.client.core.builder.IXmlComplexTypeBuilder#buildXmlFragment(de.extra.client.core.model.SenderDataBean,
	 *      de.extra.client.core.model.ConfigFileBean)
	 */
	@Override
	public Object buildXmlFragment(SenderDataBean senderData,
			ConfigFileBean config) {
		// Objects für Receiverinformation
		ReceiverType receiver = new ReceiverType();
		ClassifiableIDType receiverId = new ClassifiableIDType();
		TextType receiverName = new TextType();
		// Setting Receiverinformation
		receiverId.setClazz(receiverIdClass);
		receiverId.setValue(receiverIdValue);
		receiverName.setValue(receiverNameValue);
		receiver.setReceiverID(receiverId);
		receiver.setName(receiverName);
		logger.debug("TransportHeaderReceiver created.");
		return receiver;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("XmlComplexTypeBuilder : ")
				.append(this.getClass());
		stringBuilder.append(" for  XMLMessage: ").append(getXmlType());
		return stringBuilder.toString();
	}

	@Override
	public String getXmlType() {
		return BUILDER_XML_MESSAGE_TYPE;
	}

}