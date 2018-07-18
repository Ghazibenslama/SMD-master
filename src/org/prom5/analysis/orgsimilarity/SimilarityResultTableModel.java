package org.prom5.analysis.orgsimilarity;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.prom5.framework.models.orgmodel.OrgEntity;
import org.prom5.framework.models.orgmodel.OrgModel;

import cern.colt.matrix.DoubleMatrix2D;

public class SimilarityResultTableModel extends AbstractTableModel {
	
	private OrgModel orgModel1;
	private OrgModel orgModel2;
	private DoubleMatrix2D matrix;
	private Map<OrgEntity,OrgEntity> relations = null;

	public SimilarityResultTableModel(OrgModel om1, OrgModel om2, DoubleMatrix2D matrix, Map<OrgEntity,OrgEntity> relations) {
		orgModel1 = om1;
		orgModel2 = om2;
		this.matrix = matrix;
		this.relations = relations;
	}

	public String getColumnName(int col) {
		return col == 0 ? " " : orgModel2.getOrgEntityList().get(col-1).getName();
	}
	
	public double getMaximumInRow(int row)
	{
		double result = Double.MIN_VALUE;
		for(int i=0;i<matrix.columns();i++) {
			if(result<matrix.get(row, i)) result = matrix.get(row, i);
		}
		return result;
	}

	public String getRowIndex(int i) {
		return orgModel1.getOrgEntityList().get(i).getID();
	}

	public String getColumnIndex(int i) {
		return orgModel2.getOrgEntityList().get(i).getID();
	}

	public int getRowCount() {
		return orgModel1.getOrgEntityList().size();
	}

	public int getColumnCount() {
		return orgModel2.getOrgEntityList().size() + 1;
	}

	public Object getValueAt(int row, int col) {
		return col == 0 ? orgModel1.getOrgEntityList().get(row).getName() : String.valueOf(matrix.get(row, col - 1));
	}
	
	public Map<OrgEntity,OrgEntity> getMapping()
	{
		return relations;
	}
}
