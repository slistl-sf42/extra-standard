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
package de.extra.client.core.builder.impl.plugins;

import javax.inject.Named;

import org.apache.log4j.Logger;

import de.drv.dsrv.extrastandard.namespace.plugins.DataSource;
import de.extra.client.core.builder.impl.XmlComplexTypeBuilderAbstr;
import de.extra.client.core.model.ConfigFileBean;
import de.extra.client.core.model.SenderDataBean;

/**
 * @author Leonid Potap
 * 
 */
@Named("dataSourceInputDataPluginsBuilder")
public class DataSourceInputDataPluginsBuilder extends XmlComplexTypeBuilderAbstr {

	private static Logger logger = Logger.getLogger(DataSourceInputDataPluginsBuilder.class);

	private static final String BUILDER_XML_MESSAGE_TYPE = "xplg:DataSource";

	/**
	 * Erstellt die SenderInformationen im Kontext von Header (non-Javadoc)
	 * 
	 * @see de.extra.client.core.builder.IXmlComplexTypeBuilder#buildXmlFragment(de.extra.client.core.model.SenderDataBean,
	 *      de.extra.client.core.model.ConfigFileBean)
	 */
	@Override
	public Object buildXmlFragment(SenderDataBean senderData, ConfigFileBean config) {
		// Transportplugins erstellen
		// TODO Als erste Lösung Plugins über Konfiguration auswerden.
		// Es besteht die Möglichkeit Plugins über SenderDataBean auszuwerten
		DataSource dataSource = new DataSource();
		logger.debug("DataSourcePlugin created. ");
		return dataSource;

	}

	@Override
	public String getXmlType() {
		return BUILDER_XML_MESSAGE_TYPE;
	}

}