package org.prom5.analysis.hmm.metrics;

import org.prom5.analysis.hmm.HmmAnalyzer;
import org.prom5.framework.log.LogReader;
import org.prom5.framework.models.petrinet.PetriNet;

/**
 * New Hmm-based model-level fitness metric for HMM experiment. 
 * 
 * @author Anne Rozinat (a.rozinat at tue.nl)
 */
public class FitnessModelLevel extends HmmExpMetric {
	
	public FitnessModelLevel(String aFolder) {
		super(aFolder, "Model Level", MetricType.Fitness, ExpType.Noise);
	}

	public double calculateValue(HmmAnalyzer analyzer, PetriNet pnet, 
			LogReader log) {
		return analyzer.getMetrics().getModelLevelFitness();
	}

}
