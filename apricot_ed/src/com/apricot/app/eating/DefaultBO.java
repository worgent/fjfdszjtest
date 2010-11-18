/**
 * 
 */
package com.apricot.app.eating;

import java.util.Iterator;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.Column;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.Table;
/**
 * @author Administrator
 */
public class DefaultBO extends BO {
	/**
	 * 
	 */
	public DefaultBO() {
	}

	/**
	 * Query all data
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object all(DyncParameterMap map) throws NestedException {
		String tabName = map.getString(Table.TABLE_KEY);
		Table tab = getTable(tabName);
		tab.setSqlType(Table.SELECT);
		tab.setParameterMap(map);
		clearRuntimeParameter();
		setRuntimeStaticData(tabName);
		return showPages(tab.getSQL().toString(), 0, -1);
	}

	/**
	 * Query datas by page
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object pages(DyncParameterMap map) throws NestedException {
		String tabName = map.getString(Table.TABLE_KEY);
		Table tab = getTable(tabName);
		tab.setSqlType(Table.SELECT);
		tab.setParameterMap(map);
		clearRuntimeParameter();
		setRuntimeStaticData(tabName);
		tab.setAnd("true".equalsIgnoreCase(map.getString("cAndAndAnd")));
		System.out.println(tab.getSQL());
		System.out.println(map);
		System.out.println(map.getStartRows()+":"+map.getPageSize());
		return showPages(tab.getSQL().toString(), map.getStartRows(), map.getPageSize());
	}

	/**
	 * Update single row data
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object modify(DyncParameterMap map) throws NestedException {
		String tabName = map.getString(Table.TABLE_KEY);
		Table tab = getTable(tabName);
		tab.setSqlType(Table.UPDATE);
		tab.setParameterMap(map);

		execute(tab);
		return null;
	}

	/**
	 * Insert single row data
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object insert(DyncParameterMap map) throws NestedException {
		String tabName = map.getString(Table.TABLE_KEY);
		Table tab = getTable(tabName);
		tab.setSqlType(Table.INSERT);
		tab.setParameterMap(map);
		// Process data of primary key
		for (Iterator cols = tab.getColumns(); cols.hasNext();) {
			Column c = (Column) cols.next();
			if (c.isPrimaryKey()) {
				map.set(c.getName(), String.valueOf(getMax(tabName, c.getName())));
			}
		}
		System.out.println(tab.getSQL());
		execute(tab);
		return null;
	}

	/**
	 * Delete single row data
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object delete(DyncParameterMap map) throws NestedException {
		String tabName = map.getString(Table.TABLE_KEY);
		Table tab = getTable(tabName);
		tab.setSqlType(Table.DELETE);
		tab.setParameterMap(map);
		execute(tab);
		return null;
	}
}
