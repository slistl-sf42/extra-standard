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
package de.extrastandard.api.model.execution;

/**
 * Verfahren.
 * 
 * @author Thorsten Vogel
 * @version $Id: IProcedure.java 487 2012-09-03 13:07:52Z
 *          thorstenvogel@gmail.com $
 */
public interface IProcedure extends PersistentEntity {

	/**
	 * Mandant dieses Verfahrens.
	 * 
	 * @return Mandant
	 */
	IMandator getMandator();

	/**
	 * Name dieses Verfahrens.
	 * 
	 * @return Name
	 */
	String getName();

	/**
	 * @param phase
	 * @return liefert end Status dieser Phase
	 */
	public IStatus getPhaseEndStatus(final PhaseQualifier phase);

	/**
	 * @param status
	 * @return true, wenn {@link Status} der letzte in diesem Scenario ist
	 */
	public boolean isProcedureEndStatus(final IStatus status);
}
