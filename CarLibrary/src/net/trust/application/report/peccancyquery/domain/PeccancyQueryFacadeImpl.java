package net.trust.application.report.peccancyquery.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.utils.PubFunction;
/**
 * 综合报表 -》 报警查询
 * @author chenqf
 *
 */
public class PeccancyQueryFacadeImpl implements PeccancyQueryFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	/**
	 * 综合报表 -》 报警查询
	 * @param map
	 * @return
	 */
	public String findPeccancyQuery(HashMap map) {
		String type = PubFunction.getNulltoStr(map.get("type"));
		String sum_field = "";
		String case_field = "";
		
		String table = "<table id='peccancyQueryTab'"+("export".equals(type)?" border='1' cellspacing='0' cellpadding='0'":"")+">"+
						"	<thead>"+
						"		<tr>"+
						"			<td width='100'>址市</td>";
		
		List list = baseSqlMapDAO.queryForList("dynamicSql", "select PARA_VALUE, PARA_VALUE_DESC from TD_SYSTEM_PARA tt where tt.para_type='PECCANCY_TYPE' and tt.para_name='PECCANCY_TYPE'  order by para_value");
		
		Iterator it = list.iterator();
		HashMap tmp = null;
		List tmpList = new ArrayList();
		int i = 0;
		while (it.hasNext()){
			tmp = (HashMap)it.next();
			String id = tmp.get("PARA_VALUE").toString();
			
			sum_field += "sum(peccancy_type_"+id+") peccancy_type_"+id+",";
			
			case_field += " case when (case when c.peccancy_type = "+id+" then num end) is null then 0 "+
		                  " else (case when c.peccancy_type = "+id+" then num end)  "+
		                  "  END peccancy_type_"+id+",";
			table += "<td width='110'>"+tmp.get("PARA_VALUE_DESC")+"</td>";
			tmpList.add(id);
		}
		
		table += "		<tr>" +
				"	</thead>" +
				"	<tbody>";
		
		map.put("sum_field", sum_field);
		map.put("case_field", case_field);
		
		list = baseSqlMapDAO.queryForList("PeccancyQuery.findPeccancyQuery", map);
		
		it = list.iterator();
		while (it.hasNext()){
			tmp = (HashMap)it.next();
			table += "	<tr>" +
					"		<td width='100'>"+tmp.get("city_name")+"</td>";
			for (int j=0; j<tmpList.size(); j++){
				if ("export".equals(type)){
					table += "	<td width='110'>"+tmp.get("peccancy_type_"+tmpList.get(j))+"</td>";
				}else{
					table += "	<td width='110'><a href=\"javascript:parent.addTab('报警明细', 'bjmx_"+tmpList.get(j)+"', '/carmanage/peccancymanage/peccancymanage.shtml?action=report&search.cityId="+tmp.get("city_id")+"&search.peccancyType="+tmpList.get(j)+"')\">"+tmp.get("peccancy_type_"+tmpList.get(j))+"</a></td>";
				}
			}
			table += "	</tr>";
		}
		table += "	</tbody>" +
				"</table>";
		
		
		return table;
	}

}
