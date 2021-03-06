/***********************************************************
 *      This software is part of the ProM package          *
 *             http://www.processmining.org/               *
 *                                                         *
 *            Copyright (c) 2003-2006 TU/e Eindhoven       *
 *                and is licensed under the                *
 *            Common Public License, Version 1.0           *
 *        by Eindhoven University of Technology            *
 *           Department of Information Systems             *
 *                 http://is.tm.tue.nl                     *
 *                                                         *
 **********************************************************/
package org.prom5.analysis.benchmark.metric;

import java.util.HashMap;

import org.prom5.analysis.causality.CausalFootprintSimilarityResult;
import org.prom5.framework.log.LogEvent;
import org.prom5.framework.log.LogReader;
import org.prom5.framework.models.LogEventProvider;
import org.prom5.framework.models.causality.CausalFootprint;
import org.prom5.framework.models.causality.CausalityFootprintFactory;
import org.prom5.framework.models.petrinet.PetriNet;
import org.prom5.framework.models.petrinet.Transition;
import org.prom5.framework.ui.Progress;

/**
 * TODO: brief description
 * <br>
 * Values range from 0.0 to 1.0, 1.0 is equivalence and 0.0 is completely different behaviour.
 * Note that no distinction is made between OR and XOR relations. If a split (using invisible transition)
 * is a real OR, then this results in the same metric as when it is an XOR.
 *
 * <br>
 * Assumptions: The Petri nets do not have duplicate tasks (or at least, for each transition in model, there
 * should be a corresponding transition in referenceModel, or it should be mapped to the constant
 * CausalFootprintSimilarityResult.DONOTMAP)
 *
 * @author Boudewijn van Dongen
 */
public class CausalFootprintMetric implements BenchmarkMetric {

	/*
	 * (non-Javadoc)
	 * @see org.processmining.analysis.benchmark.metric.BenchmarkMetric#measure(org.processmining.framework.models.petrinet.PetriNet, org.processmining.framework.log.LogReader, org.processmining.framework.models.petrinet.PetriNet, org.processmining.framework.ui.Progress)
	 */
	public double measure(PetriNet model, LogReader referenceLog, PetriNet referenceModel,
			Progress progress) {

		// check precondition: no duplicate tasks in the Petri net (both reference Model and model)
		if (model.getNumberOfDuplicateTasks() > 0 || referenceModel.getNumberOfDuplicateTasks() > 0) {
			return BenchmarkMetric.INVALID_MEASURE_VALUE;
		}

		// First, build mapping from transition to transition:
		HashMap<LogEventProvider,
				LogEventProvider> mapping = new HashMap(model.getTransitions().size());

		for (Transition t : model.getTransitions()) {
			if (t.getLogEvent() == null) {
				continue;
			}
			LogEvent le = t.getLogEvent();
			LogEventProvider mapTo = referenceModel.findRandomTransition(le);
			if (mapTo == null) {
				mapTo = CausalFootprintSimilarityResult.NOMAP;
			}
			mapping.put(t,mapTo);
		}

		CausalFootprint modelPrint = CausalityFootprintFactory.make(model,progress);
		if (modelPrint == null) {return -1;}
		CausalFootprint referenceModelPrint = CausalityFootprintFactory.make(referenceModel,progress);
		if (referenceModelPrint == null) {return -1;}

		// return result
		return (new CausalFootprintSimilarityResult(modelPrint,referenceModelPrint,mapping)).calculateSimilarity(progress, false).similarity;
	}

	/*
	 * (non-Javadoc)
	 * @see org.processmining.analysis.benchmark.metric.BenchmarkMetric#name()
	 */
	public String name() {
		return "Causal Footprint";
	}

	/*
	 * (non-Javadoc)
	 * @see org.processmining.analysis.benchmark.metric.BenchmarkMetric#description()
	 */
	public String description() {
		return 	"<p>This metric calculates the similarity between two causal footprints. "+
				"For more information about causal footprints, see "+
				org.prom5.framework.util.Constants.get_BVD_URLString("conditions_soundness","this page")+
				".";   //+
//				"<p>More information about similarity of footprints can be found "+
//				org.processmining.framework.util.Constants.get_BVD_URLString("footprint_similarity","here");
	}

	/*
	 * (non-Javadoc)
	 * @see org.processmining.analysis.benchmark.metric.BenchmarkMetric#needsReferenceLog()
	 */
	public boolean needsReferenceLog() {
		// needed to map the tasks of the two models onto each other
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.processmining.analysis.benchmark.metric.BenchmarkMetric#needsReferenceModel()
	 */
	public boolean needsReferenceModel() {
		return true;
	}
}
