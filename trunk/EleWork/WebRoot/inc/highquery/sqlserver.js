
	 /********************************************
	 * 文件名称：sqlserver.js
	 * 功能描述：sqlserver的sql语句方言
	 * 创建日期：2008-07-21
	 * @author：codeslave
	 * @version 0.4
	 *********************************************/

// 生成like查询条件内容。
function likeCondition(objTemp, strType, strCondition)
{
	var strTemp = normalCondition(objTemp, strType);
	return " " + strCondition + strTemp;
}

// 生成in或not in查询条件内容。
function inCondition(objTemp, strType, strCondition)
{
	var strTemp = normalCondition(objTemp, strType);
	var strReturn = "";
	if(strTemp != " ''")
	{
		strTemp = strTemp.substring(2, strTemp.length - 1); // 除去两边的单引号
		var arrTemp = strTemp.split(',');
		for(var i = 0; i < arrTemp.length; i++)
		{
			if(arrTemp[i] != null)
			{
				strReturn += "'" + arrTemp[i] + "',"
			}
		}
		strReturn = strReturn.substring(0, strReturn.length - 1); // 除去最后一个豆号
		return " " + strCondition.replace("_", " ") + " ( " + strReturn + " )";
	} 
	else
	{
		strReturn = strTemp;
		return " " + strCondition.replace("_", " ") + " ( " + strReturn + " )";
	}
}

// 生成between的查询条件内容。
function betweenCondition(objTemp1, objTemp2, strType, strCondition)
{
	strTemp1 = normalCondition(objTemp1, strType);
	strTemp2 = normalCondition(objTemp2, strType);
	return " " + strCondition + strTemp1 + " and" + strTemp2;
}

// 生成is查询条件内容。
function isCondition(objTemp, strType, strCondition)
{
	var strTemp = objTemp.value;
	strTemp = strTemp.trim();
	return strTemp==""?" " + strCondition + " null":" " + strCondition + " " + strTemp;
}

// 生成一般查询条件内容。
function normalCondition(objTemp, strType)
{
	var strTemp = objTemp.value;
	strTemp = strTemp.trim();
	return strTemp==""?" ''":" '" + strTemp + "'";
}
