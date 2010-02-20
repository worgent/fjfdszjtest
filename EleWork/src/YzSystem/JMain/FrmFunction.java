package YzSystem.JMain;

import java.util.ArrayList;

public class FrmFunction {
	
    public FrmFunction() {
	}

	protected void finalize()

	throws Throwable {
		super.finalize();

		// other finalization code...

	}

	public FrmFunctionBase getFunctionBase() throws wlglException {
		String tseqn = UtilWebTools.getRequestParameter("tseqn");
		FrmFunctionBase frmSysFunctionBase = null;
		UtilDB utildb = new UtilDB();
		ArrayList param = new ArrayList();
		param.add(tseqn);
		// 取得类名即是写缩写
		String tmp = " select TheShortCode from tsSystemFunction where theCode=?";
		ArrayList r = utildb.exeQueryOneRow(tmp, param);
		utildb.closeCon();
		String classname = "";
		if (r.size() > 0) {
			classname = UtilCommon.NVL((String) r.get(0));
		}
		if (classname.equals("")) {
			classname = "";
		}

		try {
			// 根据类名建立Class类型的对象。
			Class cc = null;
			cc = Class.forName(classname);

			// 建立被载入类类的实例并强制类型转换，
			// 值赋给share类型的变量。

			//frmSysFunctionBase = (FrmFunctionBase) cc.newInstance();
			frmSysFunctionBase=(FrmFunctionBase) UtilWebTools.getValueBinding(classname);
			// 调用该类的方法进行工作。
		} catch (Exception ex) {
			// 如果发生例外，则进行相应处理。
			wlglException.ProcessMainWebExceptionMessage("203",
					"getSysFunctionBase失败", ex);

		}

		return frmSysFunctionBase;
	}

}
