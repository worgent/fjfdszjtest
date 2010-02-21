


public class Returndata
{

    public Returndata()
    {
        iReturnCode = 0;
        strMsg = null;
        iReturnCode = 0;
        strMsg = new String("\u64CD\u4F5C\u6210\u529F!");
    }

    public void setReturnData(int i, String s)
    {
        iReturnCode = i;
        strMsg = s;
    }

    public void initData()
    {
        iReturnCode = 0;
        strMsg = "\u64CD\u4F5C\u6210\u529F!";
    }

    public int iReturnCode;
    public String strMsg;
}
