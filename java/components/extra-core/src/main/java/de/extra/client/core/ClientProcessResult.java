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
package de.extra.client.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import de.extra.client.core.util.IExtraReturnCodeAnalyser;
import de.extrastandard.api.model.content.IInputDataContainer;
import de.extrastandard.api.model.content.IResponseData;

/**
 * Beinhaltet Ergebnisse eines Laufs.
 * 
 * @author Leonid Potap
 * @version $Id$
 */
@Scope("prototype")
@Named("clientProcessResult")
public class ClientProcessResult {

	@Inject
	@Named("extraReturnCodeAnalyser")
	private IExtraReturnCodeAnalyser returnCodeAnalyser;

	private final Map<String, ProcessResult> responseMap = new HashMap<String, ProcessResult>();

	public void addResult(final IInputDataContainer dataContainer, final List<IResponseData> responses) {
		final String requestId = dataContainer.getInputIdentification();
		responseMap.put(requestId, new ProcessResult(responses));
	}

	public void addException(final IInputDataContainer dataContainer, final Exception exception) {
		final String requestId = dataContainer.getInputIdentification();
		responseMap.put(requestId, new ProcessResult(exception));
	}

	/**
	 * Liefert true, wenn die Verarbeitung aller Dateien ohne Fehler
	 * abgeschlossen wurde.
	 * 
	 * @return
	 */
	public boolean isSuccessful() {
		final Set<String> keySet = responseMap.keySet();
		boolean hasErrors = false;
		for (final String key : keySet) {
			final ProcessResult result = responseMap.get(key);
			if (result.getResponseDatas() != null) {
				for (final IResponseData iResponseData : result.getResponseDatas()) {
					if (!returnCodeAnalyser.isReturnCodeSuccessful(iResponseData.getReturnCode())) {
						hasErrors = true;
						// TODO refactor
						break;
					}
				}
			} else if (result.getException() != null) {
				// TODO possibly recoverable exceptions?
				return false;
			}
		}
		return !hasErrors;
	}

	public boolean hasExceptions() {
		final Set<String> keySet = responseMap.keySet();
		for (final String key : keySet) {
			final ProcessResult result = responseMap.get(key);
			if (result.getException() != null) {
				return true;
			}
		}
		return false;
	}

}