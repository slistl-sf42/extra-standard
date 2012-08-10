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
package de.extra.client.plugins.dataplugin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import de.extra.client.core.model.SenderDataBean;
import de.extra.client.core.plugin.IDataPlugin;
import de.extra.client.plugins.dataplugin.interfaces.IDataPluginController;

@Named("fileDataPlugin")
public class FileDataPlugin implements IDataPlugin {

	private static Logger logger = Logger.getLogger(FileDataPlugin.class);

	@Inject
	@Named("dataPluginController")
	private IDataPluginController dataPluginController;

	@Override
	public List<SenderDataBean> getSenderData() {
		List<SenderDataBean> versanddatenListe = new ArrayList<SenderDataBean>();
		versanddatenListe = dataPluginController.processData();

		logger.info("Verarbeitung der Versanddaten abgeschlossen");
		return versanddatenListe;
	}
}
