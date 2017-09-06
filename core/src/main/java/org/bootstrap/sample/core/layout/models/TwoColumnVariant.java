package org.bootstrap.sample.core.layout.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.bootstrap.sample.core.layout.dto.Columns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model(adaptables = Resource.class)
public class TwoColumnVariant {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Inject @Optional
	public String desktopColumns;

	@Inject @Optional
	public String tabletColumns;

	private List<Columns> col;

	@PostConstruct
	protected void init() {
		LOG.info("Column Control  **** INIT ***");
		col = new ArrayList<Columns>();
		
		if(desktopColumns != null || tabletColumns != null){
			String[] desktopColVals = desktopColumns.split(",");
			String[] tabletColVals = tabletColumns.split(",");
			int[] mdVal = setVals(desktopColVals);
			int[] smVal = setVals(tabletColVals);
			
				Columns item = new Columns();
				Map<String, String> clssAttr1 = new HashMap<String, String>();
				Map<String, String> clssAttr2 = new HashMap<String, String>();
				if (desktopColumns.equals(tabletColumns)) {
					clssAttr1.put("class", "contentdiv col-sm-" + smVal[0]);
					clssAttr2.put("class", "asidediv col-sm-" + smVal[1]);
				} else {
					clssAttr1.put("class", "contentdiv col-sm-" + smVal[0] + " col-md-" + mdVal[0]);
					clssAttr2.put("class", "asidediv col-sm-" + smVal[1] + " col-md-" + mdVal[1]);
				}
				item.setClssAttr1(clssAttr1);
				item.setClssAttr2(clssAttr2);
				col.add(item);
			
		}
		

		setCol(col);
	}

	private int[] setVals(String[] colVals) {
		int[] desktopCols = new int[2];
		int i = 0;
		for(String s : colVals){
		desktopCols[i] = Integer.parseInt(s);
		i++;
		}
		return desktopCols;
	}

	public List<Columns> getCol() {
		return col;
	}

	public void setCol(List<Columns> col) {
		this.col = col;
	}

}
