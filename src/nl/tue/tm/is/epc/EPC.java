package nl.tue.tm.is.epc;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class EPC {

	private Map<String, Function> functions;
	private Map<String, Event> events;
	private Map<String, Connector> connectors;
	private Set<Arc> arcs;
	private Map<Node, Set<Arc>> arcsBySource;
	private Map<Node, Set<Arc>> arcsByTarget;
	private int nextid=1;
	
	public EPC(){
		functions = new HashMap<String,Function>();
		events = new HashMap<String,Event>();;
		connectors = new HashMap<String,Connector>();
		arcs = new HashSet<Arc>();
		arcsBySource = new HashMap<Node,Set<Arc>>();
		arcsByTarget = new HashMap<Node,Set<Arc>>();		
	}
	
	private String getNextid() {
		String idToString = ""+nextid;
//		System.out.println("getNextid "+ idToString);
		while (functions.containsKey(idToString) 
				|| events.containsKey(idToString) 
				|| connectors.containsKey(idToString)
				|| hasArc(idToString)) {
			
//			System.out.println("\t containsKey "+ idToString);
			nextid++;
			idToString = ""+nextid;
		}
		nextid++;
//		System.out.println("return  "+ idToString);
		return idToString;
	}
	
	private boolean hasArc(String id) {
		for (Arc a : arcs) {
			if (a.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	private void removeSelfCycles() {
		LinkedList<Arc> toRemove = new LinkedList<Arc>();
		for (Arc a : arcs) {
			if (a.getSource().getId().equals(a.getTarget().getId())) {
				toRemove.add(a);
			}
		}
		
		for (Arc rm : toRemove) {
			arcs.remove(rm);
			arcsBySource.get(rm.getSource()).remove(rm);
			arcsByTarget.get(rm.getTarget()).remove(rm);
		}
	}
	
	private void removeSingleNodes(Map<String, ? extends Node> nodemap) {
		
		LinkedList<String> toRemove = new LinkedList<String>();
		for (Node n : nodemap.values()) {
			if (!arcsBySource.containsKey(n) && !arcsByTarget.containsKey(n)) {
				toRemove.add(n.getId());
			}
		}
		
		for (String rm : toRemove) {
			nodemap.remove(rm);
		}
	}
	
	private void printparentchild() {
		System.out.println("printparentchild()");
		for (Entry<Node, Set<Arc>> e : arcsBySource.entrySet()) {
			System.out.println("arcsBySource " + e);
			for (Arc a : e.getValue()) {
				System.out.println("\t"+ a);
			}
		}
		for (Entry<Node, Set<Arc>> e : arcsByTarget.entrySet()) {
			System.out.println("arcsByTarget " + e);
			for (Arc a : e.getValue()) {
				System.out.println("\t"+ a);
			}
		}
	}
	
	private void removeNodesWithoutLabel(Map<String, ? extends Node> nodemap) {
		
//		printparentchild();
		
		LinkedList<Node> toRemove = new LinkedList<Node>();
		
		for (Node n : nodemap.values()) {
//			System.out.println("removeNodesWithoutLabel ? " + n.getName());
			if (n.getName() == null || n.getName().equals("") || n.getName().trim().equals("\\n")) {
//				System.out.println("\tremoveNodesWithoutLabel REMOVE "+ n.getId() );
				toRemove.add(n);
			}
		}
		
		for (Node rm : toRemove) {
			// we should use this method only in case of functions and events 
			// the connectors should have a name ..
//			System.out.println("Removing node "+rm.getId());s
			// parent
			Set<Arc> fromParent = arcsByTarget.get(rm);
//			System.out.println("\tparents "+ (fromParent == null ? "null" : fromParent.size()));
			
			// childs
			Set<Arc> toChild = arcsBySource.get(rm);
//			System.out.println("\tchildren "+ (toChild == null ? 
//					"null" : toChild.size()));
			
			// we have a source node 
			if (fromParent == null || fromParent.size() == 0) {
				// remove node and edge
				nodemap.remove(rm.getId());
				arcsBySource.remove(rm);
//				System.out.println("arcsBySource.remove(rm) "+rm.getId());
				// remove arc toChild
				Node child = toChild.iterator().next().getTarget();
				Set<Arc> childParents = arcsByTarget.get(child);
				
				if (childParents != null) {
					// this is the only child
					if (childParents.size() == 1) {
						arcsByTarget.remove(child);
						Arc a = childParents.iterator().next();
						arcs.remove(a);
					}
					// there are more parents of its child
					else {
						Arc toRemoveA = null;
						for (Arc a : childParents) {
							if (a.getSource().getId().equals(rm.getId())) {
								toRemoveA = a;
								break;
							}
						}
						if (toRemoveA != null) {
							childParents.remove(toRemoveA);
							arcs.remove(toRemoveA);
						}
					}
				}
			}
			//  we have fall node
			else if (toChild == null || toChild.size() == 0) {
				// remove node and edge
				nodemap.remove(rm.getId());
				arcsByTarget.remove(rm);
				
				// remove arc from parent
				Node parent = fromParent.iterator().next().getSource();
				Set<Arc> parentChildren = arcsBySource.get(parent);
				
				// this is the only child
				if (parentChildren != null && parentChildren.size() == 1) {
					arcsBySource.remove(parent);
					arcs.remove(parentChildren.iterator().next());
				}
				// there are more children
				else {
					Arc toRemoveA = null;
					for (Arc a : parentChildren) {
						if (a.getTarget().getId().equals(rm.getId())) {
							toRemoveA = a;
							break;
						}
					}
					if (toRemoveA != null) {
						parentChildren.remove(toRemoveA);
						arcs.remove(toRemoveA);
					}
				}
			}
			else {
				nodemap.remove(rm.getId());
				// use the first arc 
				Arc toUse = fromParent.iterator().next();
//				System.out.println(toUse);
				
				Node parent = toUse.getSource();
				Node child = toChild.iterator().next().getTarget();
				
//				System.out.println("\t parent  "+parent.getId());
//				System.out.println("\t child  "+child.getId());
				
				arcsByTarget.remove(rm);
				arcsBySource.remove(rm);
				
				toUse.setTarget(child);
				
				Set<Arc> parentChildren = arcsBySource.get(parent);
				parentChildren.add(toUse);
//				// this is the only child
//				if (parentChildren != null && parentChildren.size() == 1) {
//					arcsBySource.remove(parent);
//				}
//				// there are more children
//				else {
					Arc toRemoveA = null;
					for (Arc a : parentChildren) {
						if (a.getTarget().getId().equals(rm.getId())) {
							toRemoveA = a;
							break;
						}
					}
					if (toRemoveA != null) {
						parentChildren.remove(toRemoveA);
						arcs.remove(toRemoveA);
					}
//				}

				Set<Arc> childParents = arcsByTarget.get(child);
				childParents.add(toUse);
//				if (childParents != null) {
//					// this is the only child
//					if (childParents.size() == 1) {
//						arcsByTarget.remove(child);
//					}
//					// there are more children
//					else {
						toRemoveA = null;
						for (Arc a : childParents) {
							if (a.getSource().getId().equals(rm.getId())) {
								toRemoveA = a;
								break;
							}
						}
						if (toRemoveA != null) {
							childParents.remove(toRemoveA);
							arcs.remove(toRemoveA);
//							System.out.println("arcsBySource.remove(rm) "+rm.getId());
						}
//					}
//				}
				arcs.add(toUse);
//				Set<Arc> t = new HashSet<Arc>();
//				t.add(toUse);
//				for (Arc a : getArcs()) {
//					System.out.println(a);
//				}
//				arcsBySource.put(parent, t);
//				arcsByTarget.put(child, t);
			}
//			nodemap.remove(rm);
		}
	}
	
	private void removeSingleEntryExitGateways(Map<String, Connector> nodemap) {
		
		LinkedList<Node> toRemove = new LinkedList<Node>();
		
		for (Node n : nodemap.values()) {
			Set<Arc> fromParent = arcsByTarget.get(n);
			Set<Arc> toChild = arcsBySource.get(n);
//			System.out.println("fromParent "+ fromParent.size() + " toChild "+ toChild.size() );

			if ((fromParent == null || fromParent.size() <= 1) && (toChild == null || toChild.size() <=1)) {
//				System.out.println("removeSingleEntryExitGateways REMOVE " + n );
				toRemove.add(n);
			}
		}
		
		for (Node rm : toRemove) {
			// we should use this method only in case of functions and events 
			// the connectors should have a name ..
//			System.out.println("<>Removing node "+rm);
			// parent
			Set<Arc> fromParent = arcsByTarget.get(rm);
//			System.out.println("\tparents "+ (fromParent == null ? "null" : fromParent.size()));
			
			// childs
			Set<Arc> toChild = arcsBySource.get(rm);
//			System.out.println("\tchildren "+ (toChild == null ? 
//					"null" : toChild.size()));
			
			// we have a source node 
			if (fromParent == null || fromParent.size() == 0) {
				// remove node and edge
				nodemap.remove(rm.getId());
				arcsBySource.remove(rm);
				
				// remove arc toChild
				Node child = toChild.iterator().next().getSource();
				Set<Arc> childParents = arcsByTarget.get(child);
				
				if (childParents != null) {
					// this is the only child
					if (childParents.size() == 1) {
						arcsByTarget.remove(child);
					}
					// there are more children
					else {
						Arc toRemoveA = null;
						for (Arc a : childParents) {
							if (a.getSource().getId().equals(rm.getId())) {
								toRemoveA = a;
								break;
							}
						}
						if (toRemoveA != null) {
							childParents.remove(toRemoveA);
						}
					}
				}
			}
			//  we have fall node
			else if (toChild == null || toChild.size() == 0) {
				// remove node and edge
				nodemap.remove(rm.getId());
				arcsByTarget.remove(rm);
				
				// remove arc from parent
				Node parent = fromParent.iterator().next().getSource();
				Set<Arc> parentChildren = arcsBySource.get(parent);
				
				// this is the only child
				if (parentChildren != null && parentChildren.size() == 1) {
					arcsBySource.remove(parent);
				}
				// there are more children
				else {
					Arc toRemoveA = null;
					for (Arc a : parentChildren) {
						if (a.getTarget().getId().equals(rm.getId())) {
							toRemoveA = a;
							break;
						}
					}
					if (toRemoveA != null) {
						parentChildren.remove(toRemoveA);
					}
				}
			}
			else {
				nodemap.remove(rm.getId());
				// use the first arc 
				Arc toUse = fromParent.iterator().next();
				
				Node parent = toUse.getSource();
				Node child = toChild.iterator().next().getTarget();
				
//				System.out.println("\t parent  "+parent.id);
//				System.out.println("\t child  "+child.id);
				
				arcsByTarget.remove(rm);
				arcsBySource.remove(rm);
				
				arcsBySource.remove(parent);
				arcsByTarget.remove(child);
				
				toUse.setTarget(child);
				arcsBySource.put(parent, fromParent);
				arcsByTarget.put(child, fromParent);
			}
			nodemap.remove(rm.getId());
		}
	}
	public void addSplitJoinGatewayInfo() {
		addSplitJoinGatewayInfo(connectors);
	}
	
	private void addSplitJoinGatewayInfo(Map<String, Connector> nodemap) {
		
		for (Node n : nodemap.values()) {
			Set<Arc> fromParent = arcsByTarget.get(n);
//			System.out.println("addSplitJoinGatewayInfo " + n);
			if (fromParent != null && fromParent.size() > 1) {
//				System.out.println("\tjoin");
				n.setName(n.getName() + "join");
			} else {
//				System.out.println("\tsplit");
				n.setName(n.getName() + "split");
			}
		}
		
	}

	public void checkEPC(String modelname) {
		// remove self cycles - where the start point and end point
		// are the same
		int i = 0;
		for (Arc a : arcs) {
			if (a.getSource().getId().equals(a.getTarget().getId())) {
				i++;
			}
		}
		if (i > 0)
			System.out.println(modelname + " contained " + i + " self cycles");
		
		i = 0;
		for (Node n : functions.values()) {
			if (!arcsBySource.containsKey(n) && !arcsByTarget.containsKey(n)) {
				i++;
			}
		}
		if (i > 0)
			System.out.println(modelname + " contained " + i + " single functions");
		
		i = 0;
		for (Node n : events.values()) {
			if (!arcsBySource.containsKey(n) && !arcsByTarget.containsKey(n)) {
				i++;
			}
		}
		if (i > 0)
			System.out.println(modelname + " contained " + i + " single events");
		
		i = 0;
		for (Node n : connectors.values()) {
			if (!arcsBySource.containsKey(n) && !arcsByTarget.containsKey(n)) {
				i++;
			}
		}
		if (i > 0)
			System.out.println(modelname + " contained " + i + " single connectors");

		
		i = 0;
		for (Node n : functions.values()) {
			if (n.getName() == null || n.getName().equals("") || n.getName().trim().equals("\\n")) {
				i++;
			}
		}
		if (i > 0)
			System.out.println(modelname + " contained " + i + " functions without name");

		
		i = 0;
		for (Node n : events.values()) {
			if (n.getName() == null || n.getName().equals("") || n.getName().trim().equals("\\n")) {
				i++;
			}

		}
		if (i > 0)
			System.out.println(modelname + " contained " + i + " events without name");

		
		i = 0;
		int j = 0;
		for (Node n : connectors.values()) {
			Set<Arc> fromParent = arcsByTarget.get(n);
			Set<Arc> toChild = arcsBySource.get(n);

			if ((fromParent == null || fromParent.size() <= 1) && (toChild == null || toChild.size() <=1)) {
				i++;
			}
			
			if (fromParent != null && fromParent.size() > 1 && toChild != null && toChild.size() > 1) {
				j++;
			}
		}
		if (i > 0)
			System.out.println(modelname + " contained " + i + " primitive connectors");
		
		if (j > 0)
			System.out.println(modelname + " contained " + j + " splitjoin connectors");


	}

	
	public void cleanEPC() {
//		System.out.println("Cleaning... ");
		// remove self cycles - where the start point and end point
		// are the same
		removeSelfCycles();
		
		// remove nodes that have no parents and children
		removeSingleNodes(functions);
		removeSingleNodes(events);
		removeSingleNodes(connectors);
		
		removeNodesWithoutLabel(functions);
		removeNodesWithoutLabel(events);
		addGatewaysToFunctionsEvents(functions);
		addGatewaysToFunctionsEvents(events);
		addGatewayToSplitJoinGw();
		removeSingleEntryExitGateways(connectors);
		addSplitJoinGatewayInfo(connectors);
	}

	private void addGatewaysToFunctionsEvents(Map<String, ? extends Node> nodemap) {
		
//		printparentchild();
		LinkedList<Node> toRemove = new LinkedList<Node>();
//		System.out.println("addGatewaysToFunctionsEvents ");
		for (Node n : nodemap.values()) {
			Set<Arc> fromParent = arcsByTarget.get(n);
			Set<Arc> toChild = arcsBySource.get(n);

			if ((fromParent != null && fromParent.size() > 1) || (toChild != null && toChild.size() > 1)) {
//				System.out.println("addGatewaysToFunctionsEvents " + n );
				toRemove.add(n);
			}
		}
		
		for (Node rm : toRemove) {
			// we should use this method only in case of functions and events 
			// the connectors should have a name ..
//			System.out.println("Removing node "+rm.id);
			// parent
			Set<Arc> fromParent = arcsByTarget.get(rm);
//			System.out.println("\tparents "+ (fromParent == null ? "null" : fromParent.size()));
			
			// childs
			Set<Arc> toChild = arcsBySource.get(rm);
//			System.out.println("\tchildren "+ (toChild == null ? 
//					"null" : toChild.size()));
			
			// function/event has more that one child
			if (toChild != null && toChild.size() > 1) {
				Connector rp = new Connector(getNextid(), "XOR");
				addConnector(rp);
				// remove old and add new node
				arcsBySource.remove(rm);
				arcsBySource.put(rp, toChild);
				
//				System.out.println("REMOVE "+rm);
//				System.out.println("arcsBySource.get RP "+arcsBySource.get(rp).size());
				for (Arc a : toChild) {
					if (a.getSource().getId().equals(rm.getId())) {
						a.setSource(rp);
					}
				}
				addArc(new Arc(getNextid(), rm, rp));

//				for (Arc a : arcs) {
//					System.out.println("\tARC "+a);
//				}
//				System.out.println("...........................");
//				for (Connector a : connectors.values()) {
//					System.out.println("\t "+a);
//				}
			}
			// function/event has more that one parent
			if (fromParent != null && fromParent.size() > 1) {
				
				Connector rp = new Connector(getNextid(), "XOR");
				addConnector(rp);
				// remove old and add new node
				arcsByTarget.remove(rm);
				arcsByTarget.put(rp, fromParent);
				
//				System.out.println("REMOVE "+rm);
				for (Arc a : fromParent) {
					if (a.getTarget().getId().equals(rm.getId())) {
						a.setTarget(rp);
					}
				}
//				for (Arc a : arcs) {
//					System.out.println("\tARC "+a);
//				}
				addArc(new Arc(getNextid(), rp, rm));
//				System.out.println("...........................");
//				for (Connector a : connectors.values()) {
//					System.out.println("\t "+a);
//				}
			}
		}
//		printparentchild();
	}

	private void addGatewayToSplitJoinGw() {
		LinkedList<Node> toRemove = new LinkedList<Node>();
		
		for (Connector n : connectors.values()) {
			Set<Arc> fromParent = arcsByTarget.get(n);
			Set<Arc> toChild = arcsBySource.get(n);

			if ((fromParent != null && fromParent.size() > 1) && (toChild != null && toChild.size() > 1)) {
//				System.out.println("switchToGateways REMOVE " + n );
				toRemove.add(n);
			}
		}
		
		for (Node rm : toRemove) {
			// we should use this method only in case of functions and events 
			// the connectors should have a name ..
//			System.out.println("Removing node "+rm.id);
			
			// childs
			Set<Arc> toChild = arcsBySource.get(rm);
//			System.out.println("\tchildren "+ (toChild == null ? 
//					"null" : toChild.size()));
		
			Connector rp = new Connector(getNextid(), rm.getName());
			addConnector(rp);
			// remove old and add new node
			arcsBySource.remove(rm); 
			arcsBySource.put(rp, toChild);
			
//				System.out.println("REMOVE "+rm);
//				System.out.println("arcsBySource.get RP "+arcsBySource.get(rp).size());
			for (Arc a : toChild) {
				if (a.getSource().getId().equals(rm.getId())) {
					a.setSource(rp);
				}
			}
			addArc(new Arc(getNextid(), rm, rp));
//				for (Arc a : arcs) {
//					System.out.println("\tARC "+a);
//				}
//				System.out.println("...........................");
//				for (Connector a : connectors.values()) {
//					System.out.println("\t "+a);
//				}
		}
	}

	public static EPC loadEPML(String fileLocation){
		EPC result = new EPC();
		
        EPCParser handler = new EPCParser(result);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(fileLocation), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return result;
	}
	
	public void addFunction(Function f){
//		System.out.println("ADDED Function " + f);
		functions.put(f.getId(), f);
	}
	
	public void addEvent(Event e){
//		System.out.println("ADDED Event " + e + " " + e.getId());
		events.put(e.getId(), e);
	}
	
	public void addConnector(Connector c){
//		System.out.println("ADDED Connector " + c);
		connectors.put(c.getId(), c);
	}	
	
	public void addArc(Arc a){
//		System.out.println("ADDED Arc>> " + a.getId());
//		System.out.println("ADDED Arc " + a);
		arcs.add(a);
		Set<Arc> arcsFromSource = arcsBySource.get(a.getSource());
		if (arcsFromSource == null){
			arcsFromSource = new HashSet<Arc>();
			arcsFromSource.add(a);
			arcsBySource.put(a.getSource(),arcsFromSource);
		}else{
			arcsFromSource.add(a);
		}
		Set<Arc> arcsToTarget = arcsByTarget.get(a.getTarget());
		if (arcsToTarget == null){
			arcsToTarget = new HashSet<Arc>();
			arcsToTarget.add(a);
			arcsByTarget.put(a.getTarget(),arcsToTarget);
		}else{
			arcsToTarget.add(a);
		}		
	}
	
	public Function findFunction(String id){
		return functions.get(id);
	}
	public Event findEvent(String id){
		return events.get(id);
	}
	public Connector findConnector(String id){
		return connectors.get(id);
	}
	public Node findNode(String id){
		Node result = events.get(id);
		if (result != null){
			return result;
		}
		result = functions.get(id);
		if (result != null){
			return result;
		}
		result = connectors.get(id);
		return result;
	}

	public Set<Node> getPre(Node n){
		Set<Node> result = new HashSet<Node>();
		Set<Arc> incoming = arcsByTarget.get(n);
		if (incoming != null){
			for (Iterator<Arc> i = incoming.iterator(); i.hasNext();){
				Arc a = i.next();
				result.add(a.getSource());
			}
		}
		return result;
	}
	
	public Set<Node> getPost(Node n){
		Set<Node> result = new HashSet<Node>();
		Set<Arc> outgoing = arcsBySource.get(n);
		if (outgoing != null){
			for (Iterator<Arc> i = outgoing.iterator(); i.hasNext();){
				Arc a = i.next();
				result.add(a.getTarget());
			}
		}
		return result;
	}
	
	public Set<Function> getFunctions(){
		return new HashSet<Function>(functions.values());
	}
	public Set<Event> getEvents(){
		return new HashSet<Event>(events.values());		
	}
	public Set<Connector> getConnectors(){
		return new HashSet<Connector>(connectors.values());
	}
	public Set<Arc> getArcs(){
		return new HashSet<Arc>(arcs);
	}
	public Set<Node> getNodes(){
		Set<Node> result = new HashSet<Node>();
		result.addAll(functions.values());
		result.addAll(events.values());
//		for (Connector c : connectors.values()) {
//			System.out.println(".... " + c);
//		}
		result.addAll(connectors.values());
		return result;
	}
	
	public String toString(){
		String result = "";
		
		for (Function f: functions.values()){
			result += f.getName() + "\n";
		}
		for (Event e: events.values()){
			result += e.getName() + "\n";
		}
		for (Connector c: connectors.values()){
			result += c.getName() + "\n";
		}
		for (Arc a: arcs){
			result += "(" + a.getSource().getName() + ", " + a.getTarget().getName() + ")\n";
		}
		
		return result;
	}
	
    public static void writeModel(String outputfile, EPC g) {
    	try {
    		PrintWriter output = null;
    		
    		output = new PrintWriter(new FileWriter(outputfile));
    		
    		// print header
    		output.println("<?xml version=\'1.0\' encoding=\'UTF-8\'?>");
    		output.println("<epml:epml xmlns:epml=\'http://www.epml.de\' " +
    				"xmlns:xsi=\'http://www.w3.org/2001/XMLSchema-instance\' xsi:schemaLocation=\'http://www.epml.de EPML_2.0.xsd\'>");
    		
    		output.println("\t<epc epcId=\'1\' name=\'test\'>");
    		
    		addNodes(g.getFunctions(), output);
    		addNodes(g.getEvents(), output);
    		addNodes(g.getConnectors(), output);

    		for (Arc e : g.getArcs()) {
//    			System.out.println(e.getSource().getId()+" -> "+e.getTarget().getId());
				output.println("\t\t<arc id=\'"+e.getId()+"\'>");
				output.println("\t\t\t<flow source=\'"+e.getSource().getId()+"\' target=\'"+e.getTarget().getId()+"\'/>");
				output.println("\t\t</arc>");
    		}
    		output.println("\t</epc>");	
    		output.println("</epml:epml>");	
    	
    		output.close();
    	} catch (Exception e) {
			e.printStackTrace();
        }   	
    }

	private static void addNodes(Set<? extends Node> nodemap, PrintWriter output) {
		// vertices
		for (Node v : nodemap) {
//			System.out.println(v.getId()+" - "+v.getName());
			// functions
			if (v instanceof Function) {
				output.println("\t\t<function defRef=\'"+v.getId()+"\' id=\'"+v.getId()+"\'>");
				output.println("\t\t\t<name>"+wrapLabels(v.getName())+"</name>");
				output.println("\t\t</function>");
			}
			else if (v instanceof Event) {
				output.println("\t\t<event defRef=\'"+v.getId()+"\' id=\'"+v.getId()+"\'>");
				output.println("\t\t\t<name>"+wrapLabels(v.getName())+"</name>");
				output.println("\t\t</event>");
			}
			else if (v instanceof Connector) {
				if (v.getName().startsWith("AND")) {
					output.println("\t\t<and id=\'"+v.getId()+"\'>");
					output.println("\t\t\t<name/>");
					output.println("\t\t</and>");
				}
				else if (v.getName().startsWith("OR")) {
					output.println("\t\t<or id=\'"+v.getId()+"\'>");
					output.println("\t\t\t<name/>");
					output.println("\t\t</or>");
				}
				else if (v.getName().startsWith("XOR")) {
					output.println("\t\t<xor id=\'"+v.getId()+"\'>");
					output.println("\t\t\t<name/>");
					output.println("\t\t</xor>");
				}
			}
		}
	}
    private static String wrapLabels(String s) {
    	StringTokenizer st = new StringTokenizer(s, " ");
    	String resultString = "";
    	String currentToken = "";
    	if (s.length() > 14) {
	    	while (st.hasMoreTokens()) {
	    		String next = st.nextToken();
	    		if (next.length() + 1 + currentToken.length() <= 14) {
	    			currentToken += " "+ next;
	    		}
	    		else {
	    			resultString += currentToken + "\n";
	    			currentToken = next;
	    		}
	    	}
	    	if (currentToken.length() > 0 ) {
	    		resultString += currentToken + "\n";
	    	}
	    	
	    	// remove last newline
	    	resultString = resultString.substring(0, resultString.length()-1);
    	}
    	else {
    		return s;
    	}
     	return resultString;
    }

}
