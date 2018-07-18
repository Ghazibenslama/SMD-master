package org.prom5.mining.semanticorganizationmining.ui;

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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.prom5.framework.models.orgmodel.OrgModelConcept;
import org.prom5.framework.util.GUIPropertyBoolean;
import org.prom5.framework.util.GuiNotificationTarget;

public class OrgModelGrahp extends JPanel implements GuiNotificationTarget {

	private	JPanel graphPanel = new JPanel();
	private	JPanel buttonPanel = new JPanel();
	private OrgModelConcept orgModel;

	private GUIPropertyBoolean myOrgEntitySelected = new GUIPropertyBoolean("Show OrgEntity Concepts", true, this);
	private GUIPropertyBoolean myResourceSelected = new GUIPropertyBoolean("Show Resource Concepts", true, this);
	private GUIPropertyBoolean myTaskSelected = new GUIPropertyBoolean("Show Task Concepts", true, this);
	private GUIPropertyBoolean myInstanceSelected = new GUIPropertyBoolean("Show Instances", true, this);

	public OrgModelGrahp() {
	}

	public OrgModelGrahp(OrgModelConcept orgmodel) {
		orgModel = orgmodel;

		graphPanel.setLayout(new BorderLayout());
		graphPanel.add(orgModel.getGraphPanel(myOrgEntitySelected.getValue(),myResourceSelected.getValue(),myTaskSelected.getValue(),true), BorderLayout.CENTER);

		buttonPanel.add(myResourceSelected.getPropertyPanel());
		buttonPanel.add(myOrgEntitySelected.getPropertyPanel());
		buttonPanel.add(myTaskSelected.getPropertyPanel());
		buttonPanel.add(myInstanceSelected.getPropertyPanel());

		JButton jRedrawButton = new JButton("Redraw Graph");
		buttonPanel.add(jRedrawButton);

		this.setLayout(new BorderLayout());
		this.add(graphPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);

		jRedrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redraw();
			}
		});
	}

	public void redraw(){
		graphPanel.removeAll();
		graphPanel.add(orgModel.getGraphPanel(myOrgEntitySelected.getValue(),myResourceSelected.getValue(),myTaskSelected.getValue(),myInstanceSelected.getValue()), BorderLayout.CENTER);
		graphPanel.validate();
		graphPanel.repaint();
	}

	public void updateGUI(){
		myOrgEntitySelected.setSelected(myOrgEntitySelected.getValue());
		myResourceSelected.setSelected(myResourceSelected.getValue());
		myTaskSelected.setSelected(myTaskSelected.getValue());
		myInstanceSelected.setSelected(myInstanceSelected.getValue());
	}
}

