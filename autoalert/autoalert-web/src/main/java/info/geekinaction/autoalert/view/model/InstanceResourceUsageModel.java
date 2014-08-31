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
import static info.geekinaction.autoalert.view.FormatUtil.formatDate;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;
import info.geekinaction.autoalert.model.domain.AbstractInstanceResourceUsage;
import info.geekinaction.autoalert.view.AutoAlertPanelUtil;
import info.geekinaction.autoalert.view.dt.AbstractDataTableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Table model for instance resource usage screens. 
 * 
 * @author lcsontos
 *
 */
public class InstanceResourceUsageModel<T extends AbstractInstanceResourceUsage> extends AbstractDataTableModel<T> {
	
	public InstanceResourceUsageModel() { }
	
	public InstanceResourceUsageModel(List<T> data) {
		super(data);
	}

	@Override
	protected void processTitles() {
		addTitle(MESSAGES.beginTime()); // 1
		addTitle(MESSAGES.endTime()); // 2
		// TODO
		addTitle("Resource usage"); // 3
		addTitle(MESSAGES.alert()); // 4
	}

	@Override
	protected void processData() {
		for (AbstractInstanceResourceUsage rec : data) {
			List<Object> record = new ArrayList<Object>();
			record.add(formatDate(rec.getBeginTime()));
			record.add(formatDate(rec.getEndTime()));
			record.add(rec.getValue());
			record.add(AutoAlertPanelUtil.createAlertImage(rec.getAlert()));
			cells.add(record);
		}
	}
}

