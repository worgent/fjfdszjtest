

import java.io.PrintStream;
import java.util.StringTokenizer;

public class CutStrings
{
    StringTokenizer strToken;
    int iTokens;
    int iMembers;
    public CutStrings()
    {
    }

    public void initDataEx(String s, String s1, int i)
    {
        strToken = new StringTokenizer(s, s1);
        iTokens = strToken.countTokens() / i;
        iMembers = i;
    }

    public void initData(String s, String s1)
    {
        if(s != null)
        {
            strToken = new StringTokenizer(s, s1);
            iTokens = strToken.countTokens();
        } else
        {
            iTokens = 0;
        }
    }

    public int getCountToken()
    {
        return iTokens;
    }

    public String[] getMembers()
    {
        String as[] = null;
        if(iTokens > 0)
        {
            as = new String[iTokens];
            for(int i = 0; i < iTokens; i++)
                as[i] = strToken.nextToken();

        }
        return as;
    }

    public String[] getMembersEx(int i)
    {
        String as[] = new String[iTokens];
        for(int j = 0; j < iTokens; j++)
        {
            as[j] = strToken.nextToken();
            for(int k = 0; k < iMembers; k++)
                if(k == 0)
                    as[j] = fillSpaces(i, as[j]);
                else
                    as[j] = as[j] + fillSpaces(i, strToken.nextToken());

        }

        return as;
    }

    public String[] getMembersExs(int ai[])
    {
        String as[] = new String[iTokens];
        for(int i = 0; i < iTokens; i++)
        {
            as[i] = strToken.nextToken();
            for(int j = 0; j < iMembers; j++)
                if(j == 0)
                    as[i] = fillSpaces(ai[j], as[i]);
                else
                    as[i] = as[i] + fillSpaces(ai[j], strToken.nextToken());

        }

        return as;
    }

    public String[] getMembersExsTd()
    {
        String as[] = new String[iTokens];
        for(int i = 0; i < iTokens; i++)
        {
            as[i] = strToken.nextToken();
            for(int j = 0; j < iMembers; j++)
                if(j == 0)
                    as[i] = "<td><font size=2 color=blue>" + as[i] + "</font></td>";
                else
                    as[i] = as[i] + "<td><font size=2 color=blue>" + strToken.nextToken() + "</font></td>";

        }

        return as;
    }

    public static String fillSpaces(int i, String s)
    {
        String s1 = null;
        int i1 = 0;
        try
        {
            s1 = new String(s.getBytes("8859_1"));
            int l = s1.length();
            char ac[] = s1.toCharArray();
            s1 = s;
            for(int j = 0; j < l; j++)
                if(ac[j] == '?')
                    i1++;

            l = (l - i1) + i1 * 2;
            for(int k = l; k < i; k++)
                s1 = s1 + "&nbsp";

        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
        return s1;
    }

    public static String fillUserChar(int i, String s, String s1)
    {
        String s2 = null;
        int i1 = 0;
        try
        {
            s2 = new String(s.getBytes("8859_1"));
            int l = s2.length();
            char ac[] = s2.toCharArray();
            s2 = s;
            for(int j = 0; j < l; j++)
                if(ac[j] == '?')
                    i1++;

            l = (l - i1) + i1 * 2;
            for(int k = l; k < i; k++)
                s2 = s2 + s1;

        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
        return s2;
    }


}
