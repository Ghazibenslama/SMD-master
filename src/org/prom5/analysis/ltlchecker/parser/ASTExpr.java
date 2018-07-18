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

package org.prom5.analysis.ltlchecker.parser;

/* Generated By:JJTree: Do not edit this line. ASTExpr.java */



public class ASTExpr extends SimpleNode {

	Attribute attr;

	public void setAttribute(Attribute attr) {
		this.attr = attr;
	}

	public Attribute getAttribute() {
		return this.attr;
	}

	public ASTExpr(int id) {
		super(id);
	}

	public ASTExpr(LTLParser p, int id) {
		super(p, id);
	}

    public String toString(boolean verbose) {
        return super.toString() + ": attr=" + (attr == null ? "null" : attr.toString(verbose));
    }
    
	public String asParseableString() {
		if (getType() == UNMINUS) {
			// unary operator
			assert(children.length == 1);
			return " - " + ((SimpleNode) children[0]).asParseableString(); 
		} else if (getType() == PLUS || getType() == MINUS ||
				getType() == MULT || getType() == DIV) {
			// binary operator
			String op = "";
			assert(children.length == 2);
			switch (getType()) {
			case PLUS: op = " + "; break;
			case MINUS: op = " - "; break;
			case MULT: op = " * "; break;
			case DIV: op = " / "; break;
			default: assert(false); break;
			};
			return " ( " + 
				((SimpleNode) children[0]).asParseableString() + 
				op +  
				((SimpleNode) children[1]).asParseableString() + 
				" ) ";
		} else if (getType() == STRING || getType() == DATESTRING || getType() == SETSTRING) {
			// string-like value, we need to add quotes around it
			return " \"" + getName() + "\" ";
		} else if (getType() == CONCEPTSET) {
			String result = " [ ";
			ConceptSetAttribute conceptSet = (ConceptSetAttribute) attr;
			for (String uri : conceptSet.getModelReferences()) {
				result += "@" + uri + " ";
			}
			return result + "] ";
		} else {
			assert(getType() == INT || getType() == REAL || getType() == ATTRIBUTE);
			return " " + getName() + " ";
		}
	}
}
