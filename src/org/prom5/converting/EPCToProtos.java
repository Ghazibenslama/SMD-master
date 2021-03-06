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

package org.prom5.converting;

import java.util.HashMap;
import java.util.HashSet;

import org.prom5.framework.log.LogReader;
import org.prom5.framework.models.epcpack.ConfigurableEPC;
import org.prom5.framework.models.epcpack.EPC;
import org.prom5.framework.models.petrinet.PetriNet;
import org.prom5.framework.models.petrinet.WFNet;
import org.prom5.framework.models.protos.ProtosFlowElement;
import org.prom5.framework.models.protos.ProtosModel;
import org.prom5.framework.models.protos.ProtosSubprocess;
import org.prom5.framework.models.yawl.YAWLCondition;
import org.prom5.framework.models.yawl.YAWLDecomposition;
import org.prom5.framework.models.yawl.YAWLModel;
import org.prom5.framework.models.yawl.YAWLNode;
import org.prom5.framework.models.yawl.YAWLTask;
import org.prom5.framework.plugin.ProvidedObject;
import org.prom5.framework.ui.Message;
import org.prom5.mining.petrinetmining.PetriNetResult;
import org.prom5.mining.protosmining.ProtosResult;

/**
 * <p>Title: EPCToProtos</p>
 *
 * <p>Description: Converts an EPC into a Protos model</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: TU/e</p>
 *
 * @author Eric Verbeek
 * @version 1.0
 */
public class EPCToProtos implements ConvertingPlugin {
	public EPCToProtos() {
	}

	public String getName() {
		return "EPC to Protos";
    }

	public String getHtmlDescription() {
		return "http://www.win.tue.nl/~hverbeek/doku.php?id=projects:prom:plug-ins:conversion:epc2protos";
	}

	public ProtosResult convert(ProvidedObject object) {
		ConfigurableEPC provided = null;
		LogReader log = null;

		for (int i = 0; provided == null && i < object.getObjects().length; i++) {
			if (object.getObjects()[i] instanceof ConfigurableEPC) {
				provided = (ConfigurableEPC) object.getObjects()[i];
			}
			if (object.getObjects()[i] instanceof EPC) {
				provided = (ConfigurableEPC) object.getObjects()[i];
			}
			if (object.getObjects()[i] instanceof LogReader) {
				log = (LogReader) object.getObjects()[i];
			}
		}

		if (provided == null) {
			return null;
		}

		ProtosModel protos = result(provided);
		return new ProtosResult(log, protos);
	}

	public boolean accepts (ProvidedObject object) {
		for (int i = 0; i < object.getObjects().length; i++) {
			if (object.getObjects()[i] instanceof EPC) {
				return true;
			}
			if (object.getObjects()[i] instanceof ConfigurableEPC) {
				return true;
			}
		}
		return false;
	}

	public ProtosModel result(ConfigurableEPC epc) {
		ProtosModel model = new ProtosModel("Model");
		EPCToPetriNetConverterPlugin epc2pn = new EPCToPetriNetConverterPlugin();
		PetriNetToWFNet pn2wfn = new PetriNetToWFNet();
		WFNetToYAWL wfn2yawl = new WFNetToYAWL();
		ProvidedObject epcObject = new ProvidedObject("EPC", new Object[]{epc});
		PetriNetResult pnr = (PetriNetResult) epc2pn.convert(epcObject);
		PetriNet pn = pnr.getPetriNet();
		WFNet wfn = pn2wfn.convert(pn);
		YAWLModel yawl = wfn2yawl.convert(wfn);
		HashMap<YAWLNode,ProtosFlowElement> map = new HashMap<YAWLNode,ProtosFlowElement>();
		YAWLCondition inputCondition = null, outputCondition = null;

		// Now, we need to convert the YAWL model into a Protos model.
		HashSet<YAWLDecomposition> decompositions = new HashSet<YAWLDecomposition>(yawl.getDecompositions());
		for (YAWLDecomposition decomposition: decompositions) {
			if (decomposition.isRoot()) {
				int nofStatuses = 0, nofActivities = 0, nofArcs = 0, nofStartActivities = 0, nofEndActivities = 0;
				ProtosSubprocess subprocess = model.addSubprocess("Subprocess");
				for (YAWLNode node : decomposition.getNodes()) {
					if (node instanceof YAWLCondition) {
						YAWLCondition condition = (YAWLCondition) node;
						if (condition.getPredecessors().isEmpty()) {
							// Input condition.
							inputCondition = condition;
						} else if (condition.getSuccessors().isEmpty()) {
							// Output condition
							outputCondition = condition;
						} else {
							int i = condition.getIdentifier().indexOf("\\n");
							subprocess.addStatus(condition.getName(), i == -1 ? condition.getIdentifier() : condition.getIdentifier().substring(0, i));
							nofStatuses++;
						}
					} else if (node instanceof YAWLTask) {
						YAWLTask task = (YAWLTask) node;
						int i = task.getIdentifier().indexOf("\\n");
						ProtosFlowElement activity = subprocess.addActivity(task.getName(), i == -1 ? task.getIdentifier() : task.getIdentifier().substring(0, i));
						activity.setJoinType(task.getJoinType());
						activity.setSplitType(task.getSplitType());
						nofActivities++;
					}
				}
				for (YAWLNode node : decomposition.getNodes()) {
					if (node instanceof YAWLTask) {
						HashSet<YAWLNode> nodes = new HashSet<YAWLNode>(node.getPredecessors());
						for (YAWLNode predNode : nodes) {
							if (predNode == inputCondition) {
								subprocess.setStartEndActivity(node.getName(), true);
								nofStartActivities++;
							} else {
								subprocess.addArc(predNode.getName(), node.getName());
								nofArcs++;
							}
						}
					} else if (node instanceof YAWLCondition) {
						HashSet<YAWLNode> nodes = new HashSet<YAWLNode>(node.getPredecessors());
						for (YAWLNode predNode : nodes) {
							if (node == outputCondition) {
								subprocess.setStartEndActivity(predNode.getName(), false);
								nofEndActivities++;
							} else {
								subprocess.addArc(predNode.getName(), node.getName());
								nofArcs++;
							}
						}
					}
				}
				Message.add("<EPCToProtos nofStatuses=\"" + nofStatuses +
						"\" nofActivities=\"" + nofActivities +
						"\" nofArcs=\"" + nofArcs +
						"\" nofStartActivities=\"" + nofStartActivities +
						"\" nofEndActivities=\"" + nofEndActivities +
						"\"/>", Message.TEST);
			}
		}
		return model;
	}
}
