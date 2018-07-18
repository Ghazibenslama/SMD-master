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

package org.prom5.framework.log;

/**
 * Exception generated by the <code>LogReader</code>.
 * Note that this is an UNCHECKED exception.
 *
 * @author Peter van den Brand
 * @version 1.0
 */

public class LogReaderException extends RuntimeException {
	/**
	 * serial version UID - implement properly if you intend to use it!
	 */
	private static final long serialVersionUID = 1702480118464180459L;

	public LogReaderException(Throwable t) {
		super(t);
	}

	public LogReaderException(String s) {
		super(s);
	}
}
