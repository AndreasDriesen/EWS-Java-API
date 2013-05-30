/**************************************************************************
 * copyright file="AsyncCallback.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AsyncCallback.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.concurrent.Future;

public abstract class AsyncCallback extends AbstractAsyncCallback {
	
	AsyncCallback(){
		
	}
	
	void setTask(Future task){
		
		this.task = task;
	}
	Future getTask(){
		return this.task;
	}
	
	

	

}
