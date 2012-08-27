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
package de.extra.client.core.observer;

import javax.inject.Named;

import org.springframework.util.Assert;

import de.drv.dsrv.extrastandard.namespace.components.ApplicationType;
import de.drv.dsrv.extrastandard.namespace.components.ClassifiableIDType;
import de.drv.dsrv.extrastandard.namespace.components.RequestDetailsType;
import de.drv.dsrv.extrastandard.namespace.response.TransportHeader;
import de.extrastandard.api.observer.impl.TransportInfo;

/**
 * @author Leonid Potap
 * @version $Id$
 */
@Named("transportInfoBuilder")
public class TransportInfoBuilder {

	/**
	 * 
	 * @param headerId
	 * @param time
	 * @param procedure
	 * @param application
	 */
	public TransportInfo createTransportInfo(TransportHeader requestHeader) {
		Assert.notNull(requestHeader, "TransportHeader is null");

		TransportInfo transportInfo = new TransportInfo();

		RequestDetailsType requestDetails = requestHeader.getRequestDetails();
		Assert.notNull(requestDetails, "RequestDetails is null");

		ClassifiableIDType requestID = requestDetails.getRequestID();
		Assert.notNull(requestID, "RequestID is null");
		transportInfo.setHeaderId(requestID.getValue());
		// TODO convert into Calender
		// this.time = requestHeader.getRequestDetails().getTimeStamp();
		transportInfo.setProcedure(requestDetails.getProcedure());

		ApplicationType applicationType = requestDetails.getApplication();
		if (applicationType != null) {
			transportInfo.setApplication(applicationType.getManufacturer());
		}

		return transportInfo;

	}

	/**
	 * Default Full Constructor
	 * 
	 * @param headerId
	 * @param time
	 * @param procedure
	 * @param application
	 */
	public TransportInfo createTransportInfo(
			de.drv.dsrv.extrastandard.namespace.request.TransportHeader requestHeader) {
		Assert.notNull(requestHeader, "TransportHeader is null");

		TransportInfo transportInfo = new TransportInfo();

		RequestDetailsType requestDetails = requestHeader.getRequestDetails();
		Assert.notNull(requestDetails, "RequestDetails is null");

		ClassifiableIDType requestID = requestDetails.getRequestID();
		Assert.notNull(requestID, "RequestID is null");
		transportInfo.setHeaderId(requestID.getValue());
		// TODO convert into Calender
		// this.time = requestHeader.getRequestDetails().getTimeStamp();
		transportInfo.setProcedure(requestDetails.getProcedure());

		ApplicationType applicationType = requestDetails.getApplication();
		if (applicationType != null) {
			transportInfo.setApplication(applicationType.getManufacturer());
		}
		return transportInfo;
	}

}
