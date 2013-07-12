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
package de.extra.client.starter;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.extra.client.core.ClientCore;
import de.extra.client.core.ClientProcessResult;
import de.extrastandard.api.exception.ExceptionCode;
import de.extrastandard.api.exception.ExtraConfigRuntimeException;

/**
 * 
 * @author Leonid Potap
 * @author Thorsten Vogel
 * @version $Id: ExtraClient.java 538 2012-09-05 09:48:23Z
 *          thorstenvogel@gmail.com $
 */
public class ExtraClient {

	private static final Logger logger = LoggerFactory
			.getLogger(ExtraClient.class);

	private static final Logger opperation_logger = LoggerFactory
			.getLogger("de.extra.client.operation");

	/**
	 * Name der grundlegenden Konfiguration
	 */
	private static final String PROPERTIES_BASIC_FILENAME = "extra-properties-basic.properties";

	/**
	 * Dateiname der Benutzerkonfiguration
	 */
	private static final String PROPERTIES_USER_FILENAME = "extra-properties-user.properties";

	/**
	 * Pfad und Dateiname der Spring Konfiguration
	 */
	private static final String SPRING_XML_FILE_PATH = "spring-cli.xml";

	private final File configurationDirectory;

	/**
	 * Erzeugt einen ExtraClient. Die Konfiguration wird aus den Dateien
	 * {@link #PROPERTIES_BASIC_FILENAME} und {@link #PROPERTIES_USER_FILENAME}
	 * ausgelesen.
	 * 
	 * @param configurationDirectory
	 *            Konfigurationsverzeichnis
	 */
	public ExtraClient(final File configurationDirectory) {
		this.configurationDirectory = configurationDirectory;
	}

	/**
	 * Startmethode zum Aufruf aus dem startenden Programm.
	 * 
	 * @return Statuscode
	 */
	public ClientProcessResult execute() {
		opperation_logger.info("Start Of Processing.");
		logger.debug("Load ApplicationContext");
		try {
			final ApplicationContext applicationContext = createApplicationContext();

			final ClientCore clientCore = applicationContext.getBean(
					"clientCore", ClientCore.class);

			final ClientProcessResult processResult = clientCore
					.process(configurationDirectory.getAbsolutePath());

			opperation_logger.info("ExecutionsResults: {}",
					processResult.printResults());

			return processResult;

		} catch (final Exception e) {
			logger.error("Fehler beim Start", e);
			throw new ExtraConfigRuntimeException(e);
		}
	}

	/**
	 * Erzeugt den Spring-ApplicationContext der Anwendung.
	 * 
	 * @return
	 * @throws Exception
	 */
	ApplicationContext createApplicationContext() throws Exception {
		ApplicationContext applicationContext = null;
		final File basicPropsFile = new File(configurationDirectory,
				PROPERTIES_BASIC_FILENAME);
		if (!basicPropsFile.exists() || !basicPropsFile.canRead()) {
			throw new ExtraConfigRuntimeException(
					ExceptionCode.EXTRA_CONFIGURATION_EXCEPTION, String.format(
							"Konfiguration nicht gefunden: %s",
							PROPERTIES_BASIC_FILENAME));
		}
		final File userPropsFile = new File(configurationDirectory,
				PROPERTIES_USER_FILENAME);
		if (!userPropsFile.exists() || !userPropsFile.canRead()) {
			throw new ExtraConfigRuntimeException(
					ExceptionCode.EXTRA_CONFIGURATION_EXCEPTION, String.format(
							"Konfiguration nicht gefunden: %s",
							PROPERTIES_USER_FILENAME));
		}
		final Properties basicProperties = new Properties();
		final FileInputStream basicPropsStream = new FileInputStream(
				basicPropsFile);
		basicProperties.load(basicPropsStream);
		IOUtils.closeQuietly(basicPropsStream);

		final Properties userProperties = new Properties();
		final FileInputStream userPropsStream = new FileInputStream(
				userPropsFile);
		basicProperties.load(userPropsStream);
		IOUtils.closeQuietly(userPropsStream);

		final Map<String, Object> env = new HashMap<String, Object>();
		env.put("_extern_extra-properties-basic", basicProperties);
		env.put("_extern_extra-properties-user", userProperties);
		env.put("_configurationDirectory", configurationDirectory);
		applicationContext = new ApplicationContextStarter<AbstractApplicationContext>() {
			@Override
			protected AbstractApplicationContext createUninitializedContext() {
				return new ClassPathXmlApplicationContext(
						new String[] { SPRING_XML_FILE_PATH }, false);
			}
		}.createApplicationContext(env);

		return applicationContext;
	}

}