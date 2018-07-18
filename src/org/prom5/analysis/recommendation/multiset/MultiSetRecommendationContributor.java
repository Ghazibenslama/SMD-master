package org.prom5.analysis.recommendation.multiset;

import org.prom5.analysis.recommendation.contrib.LogBasedContributor;
import org.prom5.framework.models.logabstraction.MultiSetAbstractionFactory;

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
public class MultiSetRecommendationContributor extends LogBasedContributor {

	public MultiSetRecommendationContributor(){
		super();
		logAbstractionFactory = new MultiSetAbstractionFactory(true);
	};

	public String getName() {
		return "Multi Set Abstraction";
	}

	public String getHtmlDescription() {
		return "Calculate the multi set abstraction of a process instance";
	}

}
