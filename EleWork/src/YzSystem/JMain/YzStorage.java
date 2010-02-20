package YzSystem.JMain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.ArrayList;


import YzSystem.JMain.wlglException;
/*
 * 仓库管理模块
 * 2008-07-11
 * Author:fjfdszj
 */
public class YzStorage {
	//商品流转记录
	//ProductCode, ProductTypeCode, BillNo, RowNo, BillSort,    RepaireCode, RepaireDate,Clientcode,CopartnerType, StorageCode
	private void UpdateStorageProductFlow(UtilDB utildbexe,boolean isCheck, boolean isStorageIn,
			ArrayList RowDate, String ModuleSort, String ModuleName) throws wlglException{
		if (isCheck) {
			String productflowsql="insert into tbstorageproduct (ProductCode, ProductTypeCode, BillNo, RowNo, BillSort, RepaireCode, RepaireDate," +
					"\n"+" Clientcode,CopartnerType, StorageCode)values(?,?,?,?,?,?,?,?,?,?)";
			ArrayList rs = utildbexe.exeQueryOneRow(productflowsql,RowDate);
		}
		else
		{
			String productflowsql="delete from tbstorageproduct where ProductCode=? and ProductTypeCode=? and BillNo=? and RowNo=? and BillSort=?";
			ArrayList rs = utildbexe.exeQueryOneRow(productflowsql,RowDate);
		}
	}
	//库存串号表
    private void UpdateStorageList(UtilDB utildbexe,boolean isStorageIn, ArrayList RowDate)  throws wlglException
    {
		if (isStorageIn) {
			String Storageitemsql="insert into tbstorageitem (ListNo, ProductCode, ProductTypecode, StorageCode, PlaceCode, AssentsCode, SupplyerCode," +
					"\n"+"SupplyerType, InDate) values(?,?,?,?,?,?,?,?,?) ";
			ArrayList rs = utildbexe.exeQueryOneRow(Storageitemsql,RowDate);
		}
		else
		{
			String Storageitemsql="delete from tbstorageitem where ListNo=? and ProductCode=? and ProductTypeCode=? and StorageCode=? and PlaceCode=?";
			ArrayList rs = utildbexe.exeQueryOneRow(Storageitemsql,RowDate);
		}
    }
    //库存主表
    public int StorageIsEnough(UtilDB utildbexe,ArrayList RowDate, int Number)throws wlglException
    {
    	int result=0;
        String Sql ="select sum(Number) from tbstorage where StorageCode=? and PlaceCode=? and ProductCode=? and ProductTypeCode=?";
        ArrayList rs = utildbexe.exeQueryOneRow(Sql,RowDate);
        if ( Integer.parseInt((String) rs.get(0))>Number) result=1;
        else if( Integer.parseInt((String) rs.get(0))==Number) result=0;
        else  result=-1;
        return result;
    }
    /*
     * 仓库库存处理
     * Number,StorageCode, PlaceCode, ProductCode, ProductTypeCode
     * 
     */
    private void UpdateStorage(boolean isStorageIn, ArrayList RowDate)  throws wlglException
    {
    	  UtilDB utildbexe = new UtilDB();
    	  utildbexe.beginTransaction();
          try {

			if (isStorageIn) {
				String Storageinsertsql = "insert into tbstorage (StorageCode, PlaceCode, ProductCode, ProductTypeCode, Number)"
						+ "\n" + "values(?,?,?,?,?) ";

				String Storageupdatesql = "update tbstorage Number=Number+? where StorageCode=?, PlaceCode=?, ProductCode=?, ProductTypeCode=?";

				if (StorageIsEnough(utildbexe,RowDate,0) == 0) {
					ArrayList rs = utildbexe.exeQueryOneRow(Storageinsertsql,
							RowDate);
				} else {
					ArrayList rs = utildbexe.exeQueryOneRow(Storageupdatesql,
							RowDate);
				}
			} else {
				String Storagedeletsql = "delete from tbstorage where StorageCode=?, PlaceCode=?, ProductCode=?, ProductTypeCode=?";

				String Storageupdatesql = "update tbstorage Number=Number-? where StorageCode=?, PlaceCode=?, ProductCode=?, ProductTypeCode=?";

				
				int NowNumber=Integer.parseInt((String)RowDate.get(0));
				if (StorageIsEnough(utildbexe,RowDate,NowNumber) == 0) {
					ArrayList rs = utildbexe.exeQueryOneRow(Storagedeletsql,
							RowDate);
				} 
				else if(StorageIsEnough(utildbexe,RowDate,NowNumber)==-1)
				{
					
				}
				else {
					ArrayList rs = utildbexe.exeQueryOneRow(Storageupdatesql,
							RowDate);
				}
				utildbexe.commit();
			}
		} catch (wlglException ex) {
			utildbexe.rollback();
			throw ex;
		} finally {
			utildbexe.closeCon();
		}
    }
}
