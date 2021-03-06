package org.prom5.analysis.logclustering.profiles;

import org.prom5.framework.log.AuditTrailEntry;
import org.prom5.framework.log.LogReader;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ActivityVectorProfile extends VectorProfile{
	
	public ActivityVectorProfile(LogReader log) {
				super("Activity profile", "Profile for activities",
				log.getLogSummary().getNumberOfProcessInstances(), 
				log.getLogSummary().getModelElements());
	}

	public void buildProfile(int traceIndex, AuditTrailEntry ate) {
		super.increaseItemBy(ate.getElement(), traceIndex, 1.0);
	}

}
