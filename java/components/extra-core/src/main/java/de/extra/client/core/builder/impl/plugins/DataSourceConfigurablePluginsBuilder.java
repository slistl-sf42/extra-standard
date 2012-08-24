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

import de.drv.dsrv.extrastandard.namespace.plugins.DataContainerType;
import de.drv.dsrv.extrastandard.namespace.plugins.DataSource;
import de.extra.client.core.annotation.PluginConfigType;
import de.extra.client.core.annotation.PluginConfiguration;
import de.extra.client.core.annotation.PluginValue;
import de.extra.client.core.builder.impl.XmlComplexTypeBuilderAbstr;
import de.extra.client.core.model.IExtraProfileConfiguration;
import de.extra.client.core.model.IInputDataContainer;

/**
 * @author Leonid Potap
 * 
 */

@Named("dataSourceConfigurablePluginsBuilder")
@PluginConfiguration(pluginBeanName = "dataSourceConfigurablePluginsBuilder", pluginType = PluginConfigType.Builder)
public class DataSourceConfigurablePluginsBuilder extends
		XmlComplexTypeBuilderAbstr {

	private static Logger logger = Logger
			.getLogger(DataSourceConfigurablePluginsBuilder.class);

	private static final String BUILDER_XML_MESSAGE_TYPE = "xplg:DataSource";

	@PluginValue(key = "type")
	private String type;
	@PluginValue(key = "name")
	private String name;
	@PluginValue(key = "encoding")
	private String encoding;

	/**
	 * Erstellt die DataSource, aus der Konfigurationsdatei (non-Javadoc)
	 * 
	 * @see de.extra.client.core.builder.IXmlComplexTypeBuilder#buildXmlFragment(de.extra.client.core.model.SenderDataBean,
	 *      de.extra.client.core.model.ExtraProfileConfiguration)
	 */
	@Override
	public Object buildXmlFragment(IInputDataContainer senderData,
			IExtraProfileConfiguration config) {
		DataSource dataSource = new DataSource();
		DataContainerType dataContainerType = new DataContainerType();
		dataContainerType.setType(type);
		dataContainerType.setName(name);
		dataContainerType.setEncoding(encoding);
		dataSource.setDataContainer(dataContainerType);
		logger.debug("DataSourcePlugin created.");
		return dataSource;

	}

	@Override
	public String getXmlType() {
		return BUILDER_XML_MESSAGE_TYPE;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * @param encoding
	 *            the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
