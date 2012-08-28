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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import de.extra.client.core.ClientProcessResult;

public class ExtraClientTest {

	private static final Logger logger = Logger.getLogger(ExtraClientTest.class);

	private ExtraClient extraClient;

	@Before
	public void setUp() throws Exception {
		extraClient = new ExtraClient();
	}

	@Test
	public void testExecute() throws Exception {
		final ClientProcessResult clientProcessResult = extraClient.execute();
		if (clientProcessResult.isSuccessful()) {
			assertTrue(true);
		} else {
			fail("Fehler bei der Verarbeitung!");
			if (clientProcessResult.hasExceptions()) {
				// logger.info(clientProcessResult.
			}
		}
	}
}
