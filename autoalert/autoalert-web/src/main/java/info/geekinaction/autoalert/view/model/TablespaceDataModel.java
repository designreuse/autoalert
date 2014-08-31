/*
 * Copyright (C) 2010 - present, Laszlo Csontos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

/**
 * 
 */
package info.geekinaction.autoalert.view.model;

import static info.geekinaction.autoalert.view.FormatUtil.formatNumber;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;
import info.geekinaction.autoalert.model.domain.Tablespace;
import info.geekinaction.autoalert.view.AutoAlertPanelUtil;
import info.geekinaction.autoalert.view.dt.AbstractDataTableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Table model for tablespaces.
 * 
 * @author lcsontos
 *
 */
public class TablespaceDataModel extends AbstractDataTableModel<Tablespace> {

	/**
	 * 
	 */
	public TablespaceDataModel() { }

	/**
	 * 
	 * @param tablespaces
	 */
	public TablespaceDataModel(List<Tablespace> tablespaces) {
		super(tablespaces);
	}

	/**
	 * 
	 */
	@Override
	protected void processTitles() {
		// Column titles
		addTitle(MESSAGES.tablespaceName()); // 1
		addTitle(MESSAGES.sizeMb()); // 2
		addTitle(MESSAGES.usedMb()); // 3
		addTitle(MESSAGES.usedPer()); // 4
		addTitle(MESSAGES.freeMb()); // 5
		addTitle(MESSAGES.sizeRemainMb()); // 6
		addTitle(MESSAGES.sizeRemainPer()); // 7
		addTitle(MESSAGES.alert()); // 8
	}
	
	/**
	 * 
	 */
	@Override
	protected void processData() {
		for (Tablespace rec : data) {
			List<Object> record = new ArrayList<Object>();

			record.add(rec.getTablespaceName());
			record.add(formatNumber(rec.getSizeMb()));
			record.add(formatNumber(rec.getUsedMb()));
			record.add(formatNumber(rec.getUsedPer()));
			record.add(formatNumber(rec.getFreeMb()));
			record.add(formatNumber(rec.getSizeRemainMb()));
			record.add(formatNumber(rec.getSizeRemainPer()));
			record.add(AutoAlertPanelUtil.createAlertImage(rec.getAlert()));
			cells.add(record);
		}
	}

}
