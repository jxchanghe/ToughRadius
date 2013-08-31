package org.toughradius.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * �ַ���������
 */
public class StringUtil
{
    private static final String RMB_NUM[] = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��" };
    private static final String RMB_UNIT[] = { "Բ", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ", "��", "ʰ","��", "Ǫ" };
    private static final String RMB_DEC[] = { "��", "��" };
    
    /** ����chars */
    public static final String DIGITS = "0123456789";
    
    /**  Сд��ĸchars */
    public static final String LETTERS_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    
    /**  Сд��ĸchars + ���� */
    public static final String LETTERS_DIGITS_LOWERCASE = "0123456789abcdefghijklmnopqrstuvwxyz";

    /** ��д��ĸchars */
    public static final String LETTERS_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /** ȫ����ĸchars */
    public static final String LETTERS = LETTERS_LOWERCASE + LETTERS_UPPERCASE;

    /** ȫ����ĸ���� */
    public static final String LETTERS_DIGITS = LETTERS + DIGITS;
    
    /** �հ׵�chars (�����ո�,\t,\n,\r) */
    public static final String WHITE_SPACE = " \t\n\r";
  
    private static char[] LOWER_CASES = {
        '\000','\001','\002','\003','\004','\005','\006','\007',
        '\010','\011','\012','\013','\014','\015','\016','\017',
        '\020','\021','\022','\023','\024','\025','\026','\027',
        '\030','\031','\032','\033','\034','\035','\036','\037',
        '\040','\041','\042','\043','\044','\045','\046','\047',
        '\050','\051','\052','\053','\054','\055','\056','\057',
        '\060','\061','\062','\063','\064','\065','\066','\067',
        '\070','\071','\072','\073','\074','\075','\076','\077',
        '\100','\141','\142','\143','\144','\145','\146','\147',
        '\150','\151','\152','\153','\154','\155','\156','\157',
        '\160','\161','\162','\163','\164','\165','\166','\167',
        '\170','\171','\172','\133','\134','\135','\136','\137',
        '\140','\141','\142','\143','\144','\145','\146','\147',
        '\150','\151','\152','\153','\154','\155','\156','\157',
        '\160','\161','\162','\163','\164','\165','\166','\167',
        '\170','\171','\172','\173','\174','\175','\176','\177' };
    
    /** ����UTF-8���ַ��� */
    public static String newStringByUTF8(byte[] data)
    {
        return newString(data, "UTF-8");
    }
    
    /** ����ָ��������ַ��� */
    public static String newString(byte[] data, String encoding)
    {
        try
        {
            return new String(data, encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }
    }
    
    /** ת��Ϊ�ַ��� */
    public static String toString(Object o)
    {
        return String.valueOf(o);
    }
    
    /** ת��Ϊ�ַ���,���object==null,����"" */
    public static String getString(Object o)
    {
        if (o == null)
            return "";
        
        return toString(o);
    }
    
    /** ת��Ϊ�ַ���,���object==null,����d */
    public static String getString(Object o, String d)
    {
        if (o == null)
            return d;
        
        return toString(o);
    }
    
    /** �ַ���ת��Ϊint,�쳣������ ע:Ĭ��10���� */
    public static int toInt(String s)
    {
        return Integer.parseInt(s);
    }

    /** �ַ���ת��Ϊint ����쳣�򷵻��쳣ֵ */
    public static int toInt(String s,int exception)
    {
        try
        {
            return Integer.parseInt(s);
        }
        catch(NumberFormatException e)
        {
            return exception;
        }
    }

    /**
     * ���ַ�����split�ָ�ת������������
     * 
     * @param strs �ַ���
     * @param split �ָ�
     * @return int[] ת�������������
     */
    public static int[] toIntArray(String strs, String split)
    {
        if (ValidateUtil.isEmpty(strs))
            return new int[0];
        
        if (strs.endsWith(split))
            strs = strs.substring(0, strs.length()-split.length());
        
        String[] array = strs.split(split);
        int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++)
            intArray[i] = Integer.parseInt(array[i]);
        return intArray;
    }
    
    /**
     * ���ַ������鴫������������ �ʺ���web��form���ύʱ�õ������ַ�������,��ʵ��Ҫ��������
     * 
     * @param array
     * @return int[] ת�������������
     */
    public static int[] toIntArray(String[] array)
    {
        int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++)
            intArray[i] = Integer.parseInt(array[i]);
        return intArray;
    }
    
    /**
     * ���ַ�����split�ָ�ת������������
     * 
     * @param strs �ַ���
     * @param split �ָ�
     * @return int[] ת�������������
     */
    public static long[] toLongArray(String strs, String split)
    {
        if (ValidateUtil.isEmpty(strs))
            return new long[0];
        
        if (strs.endsWith(split))
            strs = strs.substring(0, strs.length()-split.length());
        
        String[] array = strs.split(split);
        long[] longArray = new long[array.length];
        for (int i = 0; i < array.length; i++)
            longArray[i] = Long.parseLong(array[i]);
        return longArray;
    }

    /**
     * ���ַ������鴫������������ �ʺ���web��form���ύʱ�õ������ַ�������,��ʵ��Ҫ��������
     * 
     * @param array
     * @return long[] ת�������������
     */
    public static long[] toLongArray(String[] array)
    {
        long[] longArray = new long[array.length];
        for (int i = 0; i < array.length; i++)
            longArray[i] = Long.parseLong(array[i]);
        return longArray;
    }
    
    /**
     * ����ͨ�ַ�����ʽ�������ݿ��Ͽɵ��ַ�����ʽ
     * 
     * @param str Ҫ��ʽ�����ַ���
     * @return �Ϸ������ݿ��ַ���
     */
    public static String toSql(String str)
    {
        return str.replaceAll("'", "''");
    }

    /**
     * ��id�������Ϊ�ַ��� �ʺ�����֯sql����ǻ�����
     * 
     * @param ids id����
     * @param delimeter �ָ���
     * @return
     */
    public static String toStringByArray(int[] ids, String separator)
    {
        if (ids == null || ids.length == 0)
            return "";
        
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < ids.length; i++)
        {
            buf.append(ids[i]);
            if (i < ids.length - 1)
                buf.append(separator);
        }
        return buf.toString();
    }
    
    /**
     * ��id�������Ϊ�ַ��� �ʺ�����֯sql����ǻ�����
     * 
     * @param ids id����
     * @param delimeter �ָ���
     * @return
     */
    public static String toStringByArrayLong(long[] ids, String separator)
    {
        if (ids == null || ids.length == 0)
            return "";
        
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < ids.length; i++)
        {
            buf.append(ids[i]);
            if (i < ids.length - 1)
                buf.append(separator);
        }
        return buf.toString();
    }
    
    /**
     * ��id�������Ϊ�ַ��� �ʺ�����֯sql����ǻ�����
     * 
     * @param ids id����
     * @param delimeter �ָ���
     * @return
     */
    public static String toStringSqlByArray(String[] ids, String separator)
    {
        if (ids == null || ids.length == 0)
            return "";
        
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < ids.length; i++)
        {
            buf.append("'").append(ids[i]).append("'");
            
            if (i < ids.length - 1)
                buf.append(separator);
        }

        return buf.toString();
    }

    
    /**
     * ���ַ�����ʽ���� HTML ������� ����ͨ�����ַ��⣬���Կո��Ʊ���ͻ��н���ת���� �Խ����ݸ�ʽ������� �ʺ��� HTML �е���ʾ���
     * 
     * @param str Ҫ��ʽ�����ַ���
     * @return ��ʽ������ַ���
     */
    public static String toHtml(String str)
    {
        if (str == null)
            return "";

        String html = new String(str);

        html = toHtmlInput(html);
        html = html.replaceAll("\r\n", "\n");
        html = html.replaceAll("\n", "<br>\n");
        html = html.replaceAll("\t", "    ");
        html = html.replaceAll("  ", " &nbsp;");

        return html;
    }

    /**
     * ���ַ�����ʽ���� HTML ������� ֻת�������ַ����ʺ��� HTML �еı�����
     * 
     * @param str Ҫ��ʽ�����ַ���
     * @return ��ʽ������ַ���
     */
    public static String toHtmlInput(String str)
    {
        if (str == null)
            return "";

        String html = new String(str);

//        html = html.replaceAll("&", "&amp;");
        html = html.replaceAll("<", "&lt;");
        html = html.replaceAll(">", "&gt;");
//        html = html.replaceAll("\"", "&quot;");

        return html;
    }
    
    /**
     * ���ַ�����ʽ���� HTML ������� ֻת�������ַ����ʺ��� HTML �еı�����
     * 
     * @param str Ҫ��ʽ�����ַ���
     * @return ��ʽ������ַ���
     */
    public static String toInputStr(String str)
    {
        if (str == null)
            return "";

        String html = new String(str);

        html = html.replaceAll("&", "&amp;");
        html = html.replaceAll("<", "&lt;");
        html = html.replaceAll(">", "&gt;");
        html = html.replaceAll("\"", "&quot;");

        return html;
    }

    /** JSת�� */
    public static String toJsStr(String str)
    {
        if (str == null)
            return "";
        
        String html = new String(str);
        html = html.replaceAll("\'", "\\\\\'");
        html = html.replaceAll("\"", "\\\\\"");
        
        return html;
    }
    
    public static void main(String[] args)
    {
        String str = "da\\fd'a\"";
        System.out.println(toJsStr(str));
    }
    
    public static String toFilter(String str)
    {
        if (str == null)
            return "";
        
        String filter = new String(str);
        filter = filter.trim();
        filter = filter.replaceAll(">", "");
        filter = filter.replaceAll("<", "");
        filter = filter.replaceAll(";", "");
        filter = filter.replaceAll("\'", "");
        filter = filter.replaceAll("\"", "");
        filter = filter.replaceAll("&", "");
        
        return filter;
    }
    
    /**
     * ���ķ�ת���ɱ༭���Ϸ��ĸ�ʽ
     * 
     * @param source Դ�ַ���
     * @return String
     */
    public static String toHtmlEditor(String source)
    {
        if (source == null)
            return null;

        String html = new String(source);

        html = html.replaceAll("\"", "\\\"");
        html = html.replaceAll("\r\n", "\n");
        html = html.replaceAll("\n", "\\n");
        html = html.replaceAll("\t", "    ");
        //html = html.replaceAll(" ", " &nbsp;"); //��һ����ʱ����

        html = html.replaceAll("<script", "\\<script");
        html = html.replaceAll("<SCRIPT", "\\<SCRIPT");
        html = html.replaceAll("/script>", "/script\\>");
        html = html.replaceAll("/SCRIPT>", "/SCRIPT\\>");

        return html;
    }

    /**
     * �ϲ��ַ���,���ڲ���ַ���Ϊ����,��ֱ�ӵ���String.split(String regex)����
     * 
     * ����:���ַ�������������Ԫ��hello��my��friend���û�����ָ���Ϊ|����ô�ַ����� �Ժϲ�Ϊhello|my|friend
     * @param array �ַ�������
     * @param delim �ָ���
     * @return �ϲ�����ַ���
     */
    public static String toStringByArray(String[] array, String separator)
    {
        if (array == null || array.length == 0)
            return null;
        
        if (ValidateUtil.isEmpty(separator))
            separator = ",";

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < array.length; i++)
        {
            buf.append(array[i]);
            if (i < array.length - 1)
                buf.append(separator);
        }

        return buf.toString();
    }

    /**
     * �滻һ������
     * 
     * @param src Դ�� ���� "���ѳɹ�ע�ᵽ%s���û�����%s�����룺%s�����¼..."
     * @param placeholder ռλ���� ���� "%s"
     * @param replaceList �滻�б����� {"�����ƶ�","����","111111"};
     * @return �滻������ݣ����ӵ�������Ϊ�� "���ѳɹ�ע�ᵽ�����ƶ����û��������������룺111111�����¼..."
     */
    public static String replaceSequence(String src, String placeholder,String[] replaceList)
    {
        StringBuffer buf = new StringBuffer();
        String[] segmentArray = src.split(placeholder);
        int replaceListLen = replaceList.length;
        if (segmentArray != null)
        {
            int len = segmentArray.length;
            int i = 0;
            for (; i < len - 1 && i < replaceListLen; i++)
            {
                String segment = segmentArray[i];
                buf.append(segment).append((String) replaceList[i]);
            }
            for (int j = i; j < len; j++)
                buf.append(segmentArray[j]);
        }
        return buf.toString();
    }


    /**
     * �������һ�����ȵ�����
     * 
     * @param length ����
     * @return �ַ���
     */
    public static String getRandomDigits(int length)
    {
        StringBuffer strb = new StringBuffer();
        Random random = new Random();
        for (int i=0;i<length;i++)
        {
            strb.append(random.nextInt(10));
        }
        
        return strb.toString();
    }
    
    /**
     * �������һ�����ȵĴ�д��ĸ
     * 
     * @param length ����
     * @return �ַ���
     */
    public static String getRandomUpperLetters(int length)
    {
        StringBuffer strb = new StringBuffer();
        Random random = new Random();
        for (int i=0;i<length;i++)
        {
            int index = random.nextInt(26);
            strb.append(LETTERS_UPPERCASE.charAt(index));
        }
        
        return strb.toString();
    }
    
    /**
     * �������һ�����ȵ�Сд��ĸ
     * 
     * @param length ����
     * @return �ַ���
     */
    public static String getRandomLowerLetters(int length)
    {
        StringBuffer strb = new StringBuffer();
        Random random = new Random();
        for (int i=0;i<length;i++)
        {
            int index = random.nextInt(26);
            strb.append(LETTERS_LOWERCASE.charAt(index));
        }
        
        return strb.toString();
    }
    
    /**
     * �������һ�����ȵ�Сд��ĸ
     * 
     * @param length ����
     * @return �ַ���
     */
    public static String getRandomLowerLettersDigits(int length)
    {
        StringBuffer strb = new StringBuffer();
        Random random = new Random();
        for (int i=0;i<length;i++)
        {
            int index = random.nextInt(36);
            strb.append(LETTERS_DIGITS_LOWERCASE.charAt(index));
        }
        
        return strb.toString();
    }
    
    /**
     * �������һ�����ȵ���ĸ
     * 
     * @param length ����
     * @return �ַ���
     */
    public static String getRandomLetters(int length)
    {
        StringBuffer strb = new StringBuffer();
        Random random = new Random();
        for (int i=0;i<length;i++)
        {
            int index = random.nextInt(52);
            strb.append(LETTERS.charAt(index));
        }
        
        return strb.toString();
    }
    
    /**
     * �������һ�����ȵ���ĸ������
     * 
     * @param length ����
     * @return �ַ���
     */
    public static String getRandomLettersDigits(int length)
    {
        StringBuffer strb = new StringBuffer();
        Random random = new Random();
        for (int i=0;i<length;i++)
        {
            int index = random.nextInt(62);
            strb.append(LETTERS_DIGITS.charAt(index));
        }
        
        return strb.toString();
    }
    
    /**
     * �������һ�����ȵ��ַ�������
     * 
     * @param radomLength ����
     * @param type ���ͱ������ɵ�����ַ�������ĸ����(0),����(1),��ĸ(2),��д��ĸ(3),Сд��ĸ(4),��д��ĸ������(5),Сд��ĸ������(6)
     * @return �ַ���
     */
    public static String getRandomValue(int randomLength, int type)
    {
        if (randomLength < 1)
            return "";
        
        int maxInt = 62;
        String sendString = LETTERS_DIGITS;
        switch (type)
        {
        case 1:
            maxInt = 10;
            sendString = DIGITS;
            break;
        case 2:
            maxInt = 52;
            sendString = LETTERS;
            break;
        case 3:
            maxInt = 26;
            sendString = LETTERS_UPPERCASE;
            break;
        case 4:
            maxInt = 26;
            sendString = LETTERS_LOWERCASE;
            break;
        case 5:
            maxInt = 36;
            sendString = LETTERS_UPPERCASE + DIGITS;
            break;
        case 6:
            maxInt = 36;
            sendString = LETTERS_LOWERCASE + DIGITS;
            break;
        }
        
        StringBuffer returnString = new StringBuffer();
        Random random = new Random();
        
        for (int i=0;i<randomLength;i++)
        {
            int rand = random.nextInt(maxInt);
            returnString.append(sendString.charAt(rand));
        }
        
        return returnString.toString();
    }

    /*********************************/
    //����Ϊ�ַ�����ȡ���
    /*********************************/
    
    /** ��ȡһ���ַ����ַ������ֵĴ��� */
    public static int getTimes(String src, char c)
    {
        int times = 0;
        for (int i=0;i<src.length();i++)
        {
            char curc = (char)src.charAt(i);
            if (curc == c)
                times++;
        }
        
        return times;
    }

    /** ��ȡһ���ַ��������ַ������ֵĴ��� */
    public static int getTimes(String src, String timeStr)
    {
        int times = 0;
        if (src == null || timeStr == null)
            return times;
        
        int len = src.length();
        int timelen = timeStr.length();
        if (len < timelen)
            return times;
        
        while(true)
        {
            int ind = src.indexOf(timeStr);
            if (ind == -1)
                return times;
            
            times++;
            src = src.substring(ind+timelen);
        }
    }

    
    /**
     * ����srcPage���õ���Ե�returnPage���·�� ��WEBActionServlet�õ��ˣ�����Ҫ 
     * 
     * @param returnPage ����·�������·��
     * @param srcPage ����·��
     * @return ��Ժ�����·��
     */
    public static String convertPath(String returnPage,String srcPage)
    {
        //�������/��ͷ������Ϊ�����·��������
        if (!returnPage.startsWith("/"))
            return returnPage;
        
        //����·��,ȥ��"/"
        returnPage = returnPage.substring(1);
        
        int count = StringUtil.getTimes(srcPage,'/');
        if (count > 1)
        {
            for (int i=1;i<count;i++)
                returnPage = "../"+returnPage;
        }
        
        return returnPage;
    }
    
    /** ɾ��s�����пհ� (�����ո�,\t,\r,\n) */
    public static String removeWhitespace(String s)
    {
        return removeCharsInBag(s, WHITE_SPACE);
    }

    /** ɾ���ַ���ǰ��Ŀհ�,ֱ���������� */
    public static String removeInitialWhitespace(String s)
    {
        int i = 0;
        while ((i < s.length()) && ValidateUtil.isCharInString(s.charAt(i), WHITE_SPACE))
            i++;
        return s.substring(i);
    }
    
    /** 
     * ɾ��s�г��ֵ�����bag������ַ�
     * 
     * ����: s = "adddsg"; bag = "ds"; �õ������:returnString = "ag";
     * @param s ԭ�ַ���
     * @param bag ���ַ���
     * @return ɾ��s�г��ֵ�����bag������ַ�����ַ���
     */
    public static String removeCharsInBag(String s, String bag)
    {
        String returnString = "";
        
        if (ValidateUtil.isEmpty(s))
            return returnString;

        // ����ַ����,������ַ�����bag��,��ӵ�returnString��
        for (int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);

            if (bag.indexOf(c) == -1)
                returnString += c;
        }
        return returnString;
    }

    /** 
     * ɾ��s������bagδ������ַ�
     * 
     * ����: s = "adddsg"; bag = "ds"; �õ������:returnString = "ddds";
     * @param s ԭ�ַ���
     * @param bag ���ַ���
     * @return ɾ��s�г��ֵ�����bagδ������ַ�����ַ���
     */
    public static String removeCharsNotInBag(String s, String bag)
    {
        String returnString = "";

        // ����ַ����,������ַ���bag��,��ӵ�returnString��
        for (int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);

            if (bag.indexOf(c) != -1)
                returnString += c;
        }
        return returnString;
    }
    
    /*********************************/
    //����Ϊ�������
    /*********************************/
    
    /**
     * ����(GBK,GB2312)���뵽UTF8 ������WAP &#x;����
     * 
     * @param sChinese
     * @return String UTF8����
     */
    public static String toWAPUTF8(String sChinese)
    {
        if(ValidateUtil.isEmpty(sChinese)) 
            return "";
        
        String retStr = "";
        String tempStr = "";
        for(int i=0;i<sChinese.length();i++)
        {
            tempStr = "&#x"+Integer.toHexString((int)sChinese.charAt(i)) + ";";
            retStr += tempStr;
        }
        
        return retStr;
    }
    
    /**
     * ����(GBK,GB2312˫�ֽ�)���뵽Unicode ������Application \\u����
     * 
     * @param sChinese
     * @return
     */
    public static String toUnicode(String sChinese)
    {
        if(ValidateUtil.isEmpty(sChinese)) 
            return "";
        
        String retStr = "";
        String tempStr = "";
        for (int i = 0; i < sChinese.length(); i++)
        {
            tempStr = "\\u" + Integer.toHexString((int) sChinese.charAt(i));
            retStr += tempStr;
        }
        return retStr;
    }
    
    /** 4���� to ip */
    public static String byteToIp(byte[] value)
    {
        StringBuffer strb = new StringBuffer();
        strb.append(value[0] & 0xFF).append(".");
        strb.append(value[1] & 0xFF).append(".");
        strb.append(value[2] & 0xFF).append(".");
        strb.append(value[3] & 0xFF);
        return strb.toString();
    }
    
    /** ip to long */
    public static long ipToLong(String ip)
    {
        String[] strs = ip.split("\\.");
        int[] ints = toIntArray(strs);
        int ipInt = (ints[0] << 24) + (ints[1] << 16) + (ints[2] << 8) + (ints[3]);   
        long ipLong = ipInt & 0x7FFFFFFFL;
        if (ipLong < 0)
            ipLong |= 0x80000000L;
        
        return ipLong;
    }
    
    /*********************************/
    //����Ϊ�ļ����
    /*********************************/

    /**
     * ȡ��ϵͳ���ļ��ָ���
     * 
     * @return ϵͳ�ָ�������windowϵͳ����"\"����unix/linuxϵͳ����"/"
     */
    public static String getSystemSeparator()
    {
        return System.getProperty("file.separator");
    }
    
    /**
     * ����·�� ��Ҫ����ȥ����ķָ��� ��WIN��
     * 
     * @param path Ҫ����·��
     * @return ������·��
     */
    public static String formatWinPath(String path)
    {
        path = path.replaceAll("//", "/");
        return path;
    }
    
    /**
     * ȡ��URL���ļ���׺ ע:����ֻ����"." 
     * 
     * @param path URL���ļ���
     * @return ��׺,Сд
     */
    public static String getPathSuffix(String path)
    {
        if (ValidateUtil.isEmpty(path))
            return "";

        int pos = path.lastIndexOf(".");
        String fileExt = path.substring(pos + 1, path.length());
        return fileExt.toLowerCase();
    }
    
    /**
     * ȡ���ļ�����,��ȡ���ļ���׺ ע:����ֻ����"."
     * 
     * @param fileName �ļ���
     * @return �ļ���׺,Сд
     */
    public static String getFileExt(String fileName)
    {
        if (ValidateUtil.isEmpty(fileName))
            return "";

        int pos = fileName.lastIndexOf(".");
        if (pos == -1)
            return null;
        
        String fileExt = fileName.substring(pos + 1, fileName.length());
        return fileExt.toLowerCase();
    }

    /**
     * ��ȡһ���ļ�·����Ŀ¼�ṹ�� ��c:\\temp\\article.jsp���򷵻�c:\\temp\\��������Ҫ ������ȡ·����Ŀ¼����
     * 
     * @param filePath �ļ�����·��
     * @return Ŀ¼�ṹ
     */
    public static String getFilePath(String filePath)
    {
        return getFilePath(filePath, getSystemSeparator());
    }
    
    /**
     * ��ȡһ���ļ�·����Ŀ¼�ṹ�� ��c:\\temp\\article.jsp���򷵻�c:\\temp\\��������Ҫ ������ȡ·����Ŀ¼����
     * 
     * @param filePath �ļ�����·��
     * @param sep �ָ���
     * @return Ŀ¼�ṹ
     */
    public static String getFilePath(String filePath, String sep)
    {
        int pos = filePath.lastIndexOf(sep);
        if (pos == -1)
            return "";
        
        return filePath.substring(0, pos);
    }

    /**
     * ��ȡһ���ļ�·����Ŀ¼�ṹ�� ��c:\\temp\\article.jsp���򷵻�c:\\temp\\��������Ҫ ������ȡ·����Ŀ¼����
     * 
     * @param filePath �ļ�����·��
     * @return Ŀ¼�ṹ
     */
    public static String getFileURL(String filePath)
    {
        int pos = filePath.lastIndexOf("/");
        if (pos == -1)
            return "";
        return filePath.substring(0, pos);
    }

    /**
     * ��ȡһ���ļ�·����Ŀ¼�ṹ�� ��c:\\temp\\article.jsp���򷵻�article.jsp��������Ҫ ������ȡ·�����ļ�����
     * 
     * @param filePath �ļ�����·��
     * @return �ļ�����
     */
    public static String getFileName(String filePath)
    {
        return getFileName(filePath, getSystemSeparator());
    }
    
    /**
     * ��ȡһ���ļ�·����Ŀ¼�ṹ�� ��c:\\temp\\article.jsp���򷵻�article.jsp��������Ҫ ������ȡ·�����ļ�����
     * 
     * @param filePath �ļ�����·��
     * @param sep �ָ���
     * @return �ļ�����
     */
    public static String getFileName(String filePath, String sep)
    {
        int pos = filePath.lastIndexOf(sep);
        if (pos == -1)
            return "";
        return filePath.substring(pos + 1);
    }
    
    /**
     * ��ȡĿ¼·�����Ե�ǰʱ�����һ��·��
     * 
     * @return String ����·��
     */
    public static String getPathByCurrentDate()
    {
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH) + 1;
        int day = date.get(Calendar.DATE);

        return year + getSystemSeparator() + month + getSystemSeparator() + day;
    }

    /**
     * ��ȡĿ¼·�����Բο�ʱ�����һ������·��
     * @return String ����·��
     */
    public static String getPathByCurrentDate(Date date)
    {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);

        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH) + 1;
        int day = calender.get(Calendar.DATE);

        return year + getSystemSeparator() + month + getSystemSeparator() + day;

    }
      
    /**
     * ͨ��������ʼstr,��ǰ��char,�õ��������ȵ�ֵ, ͨ������ǰ��0��
     * 
     * @param str ��ʼstr
     * @param len ��������
     * @param prefix ǰ��char
     * @return String
     */
    public static String getPrefixFixLenStr(String str, int len, char prefix)
    {
        String prefixStr = "";
        for (int i=0;i<len;i++)
        {
            prefixStr += prefix;
        }
        
        str = prefixStr + str;
        return str.substring(str.length() - len);
    }
    
    /**
     * ͨ��������ʼint,��ǰ��char,�õ��������ȵ�ֵ, ͨ������ǰ��0��
     * 
     * @param str ��ʼstr
     * @param len ��������
     * @param prefix ǰ��char
     * @return String
     */
    public static String getPrefixFixLenStr(int intValue, int len, char prefix)
    {
        String str = "";
        for (int i=0;i<len;i++)
        {
            str += prefix;
        }
        
        str = str + intValue;
        return str.substring(str.length() - len);
    }
    
    /**
     * �Ƚ������ַ����ı����С,Ĭ��GBK����
     * 
     * @param s1 ��һ���ַ���
     * @param s2 �ڶ����ַ���
     * @return �����һ���ȵڶ������ֵ�(����)ǰ,��<0,����>0
     */
    public static int compare(String s1, String s2)
    {
        String m_s1 = null, m_s2 = null;
        try
        { // �Ƚ����ַ��������GBK
            m_s1 = new String(s1.getBytes(), "GBK");
            m_s2 = new String(s2.getBytes(), "GBK");
        }
        catch (Exception ex)
        {
            return s1.compareTo(s2);
        }

        return chineseCompareTo(m_s1, m_s2);
    }

    /**
     * �Ƚ������ַ����ı����С, ȡ��������
     * 
     * @param s1 ��һ���ַ���
     * @param s2 �ڶ����ַ���
     * @return �����һ���ȵڶ������ֵ�(����)ǰ,��<0,����>0
     */
    public static int chineseCompareTo(String s1, String s2)
    {
        int len1 = s1.length();
        int len2 = s2.length();
        int n = Math.min(len1, len2);
        for (int i = 0; i < n; i++)
        {
            int s1_code = getCharCode(s1.charAt(i) + "");
            int s2_code = getCharCode(s2.charAt(i) + "");
            if (s1_code != s2_code)
                return s1_code - s2_code;
        }
        return len1 - len2;
    }
    
    /**
     * ͨ��һ���ַ���,��ȡ����CHAR,��֤��һ���ַ��Ǻ��ֻ�Ӣ��(ȡ��λ)
     * 
     * @param s �ַ���
     * @return ��Ӧint��
     */
    public static int getCharCode(String s)
    {
        if (s == null || s.length() == 0)
            return -1;

        byte[] b = s.getBytes();
        int value = 0; // ��֤ȡ��һ���ַ������ֻ���Ӣ�ģ�
        for (int i = 0; i < b.length && i <= 2; i++)
        {
            value = value * 100 + b[i];
        }

        return value;
    }
    
    /** ��ȡ�ַ������� */
    public static int length(String s)
    {
        if (s == null || s.length() ==0)
            return 0;
        
        return s.length();
    }
    
    /** ��ȡ��unicode�ĳ��� */
    public static int lengthUnicode(String s)
    {
        if (s == null || s.length() ==0)
            return 0;
        
        int len = 0;
        for (int i=0;i<s.length();i++)
        {
            if ((int) s.charAt(i) > 127)
               len += 2;
            else
                len += 1;
        }
        return len;
    }
    
    /** ���Ȼ�ʣ�����ֽ� */
    public static int lengthUnicodeRemain(String s, int maxLength)
    {
        return maxLength - lengthUnicode(s);
    }
    
    /** 
     * ��ȡ���ȣ�ָ����ʼ����ֹ����
     * 
     * @param Դ��
     * @param ��ʼ����
     * @param ��ֹ����
     */
    public static String substring(String src, int beginIndex, int endIndex)
    {
        if (src.length()<beginIndex)
            return "";
        
        return src.substring(beginIndex, endIndex);
    }
    
    /** 
     * ��ȡ���ȣ�ָ����ʼ����β
     * 
     * @param Դ��
     * @param ��ʼ����
     */
    public static String substring(String src, int beginIndex)
    {
        if (src.length()<beginIndex)
            return "";
        
        return src.substring(beginIndex);
    }
    
    /**
     * ��ȡ���ȣ�ָ�����ֳ���
     * 
     * @param str Դ��
     * @param len ���ֳ���
     * @return ��ȡ����ĸ����������...�������һ������
     */
    public static String substringLen(String str, int len)
    {
        if(ValidateUtil.isEmpty(str))
            return "";
        
        len = len * 5;//����λ*5���ټ���
        int length = 0;int ind = 0;
        for (int i=0;i<str.length();i++)
        {
            if ((int) str.charAt(i) > 127)
                length += 5;//���ֺ���ĸ������������5:3����3�����ֺ�5����ĸռλ�൱
            else
                length += 3;
            
            ind = i+1;
            if (length >= len)
                break;
        }
        
        if (ind < str.length())
            str = str.substring(0, ind-1) + "...";
        else
            str = str.substring(0, ind);
        
        return str;
    }
    
    /**
     * �ṩ��������������С��λ�����õ����
     * 
     * @param dividend ������
     * @param divisor ����
     * @param radixLen С��λ��
     * @return String ���
     */
    public static String getDivisionString(long dividend, long divisor, int radixLen)
    {
        double result = (double)dividend / divisor;
        StringBuffer radix = new StringBuffer("#");
        if (radixLen > 0)
        {
            radix.append(".");
            for (int i=0;i<radixLen;i++)
                radix.append("#");
        }
        
        DecimalFormat df = new DecimalFormat(radix.toString());
        String ret = df.format(result);
        
        if (radixLen > 0)
        {
            int ind = ret.indexOf('.');
            if (ind == -1)
            {//û��С����
                ret += ".";
                for (int i=0;i<radixLen;i++)
                    ret += "0";
            }
            else if (ind > ret.length() - radixLen -1)
            {//С��λ������,β����0
                int zeroNum = ind - (ret.length() - radixLen - 1);
                for (int i=0;i<zeroNum;i++)
                {
                    ret += "0";
                }
            }
        }
        
        return ret;
    }
    
    public static String decimal2Chinese(int value) 
    {
        Integer in = new Integer(value);
        double src = in.doubleValue();
        src = src/100;
        
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");
        String srcText = df.format(src);
        String numText = srcText.substring(0, srcText.length() - 3);
        String decText = srcText.substring(srcText.length() - 2);

        int numlen = numText.length();
        for (int k = 0; k < numlen; k++) 
        {
            sb.append(RMB_NUM[Integer.parseInt(String.valueOf(srcText.charAt(k)))]);
            sb.append(RMB_UNIT[numlen - k - 1]);
        }
        if ("00".equals(decText))
        {
            sb.append("��");
        } 
        else
        {
            sb.append(RMB_NUM[Integer.parseInt(String.valueOf(decText.charAt(0)))]);
            sb.append(RMB_DEC[0]);
            sb.append(RMB_NUM[Integer.parseInt(String.valueOf(decText.charAt(1)))]);
            sb.append(RMB_DEC[1]);
        }
        String result = sb.toString();
        result = result.replace("���", "");
        result = result.replace("���", "��");
        result = result.replace("��Ǫ", "��");
        result = result.replace("���", "��");
        result = result.replace("��ʰ", "��");
        result = result.replace("��Բ", "Բ");
        while (true)
        {
            String r = result.replace("����", "��");
            if (r.equals(result))
            {
                break;
            } 
            else 
            {
                result = r;
            }
        }
        result = result.replace("��Բ", "Բ");
        result = result.replace("����", "��");
        result = result.replace("����", "��");
        result = result.replace("����", "��");
        if(result.startsWith("Բ"))
        {
            result="��"+result;
        }
        return result;
    }

    /**
     * ��ȡ��Դ�ļ� path��ʽΪ/com/zoulab/res/abc.js
     * 
     * @param path ·��
     * @return String
     * @throws IOException
     */
    public static String readResource(String path) throws IOException
    {
        return readResource(StringUtil.class, path);
    }
    
    /**
     * ��ȡ��Դ�ļ� path��ʽΪ/com/zoulab/res/abc.js
     * 
     * @param clazz ����
     * @param path ·��
     * @return String
     * @throws IOException
     */
    public static String readResource(Class<?> clazz, String path) throws IOException
    {
        InputStream in = null;
        byte[] buf = null;
        
        in = clazz.getResourceAsStream(path);
        buf = new byte[in.available()];
        in.read(buf);
        in.close();
        in = null;
        
        String res = new String(buf);
        res = res.replaceAll("\r\n"," ");
        res = res.replaceAll("\r", " ");
        res = res.replaceAll("\n", " ");
        return res;
    }
    
    /**
     * ����ת����Сд��ֻ��ASCII��ʽ��UNICODE��ת��
     * 
     * @param ԭ�ַ���
     * @return Ŀ���ַ���
     */
    public static String asciiToLowerCase(String s)
    {
        char[] c = null;
        int i=s.length();

        // look for first conversion
        while (i-->0)
        {
            char c1=s.charAt(i);
            if (c1<=127)
            {
                char c2=LOWER_CASES[c1];
                if (c1!=c2)
                {
                    c=s.toCharArray();
                    c[i]=c2;
                    break;
                }
            }
        }

        while (i-->0)
        {
            if(c[i]<=127)
                c[i] = LOWER_CASES[c[i]];
        }
        
        return c==null?s:new String(c);
    }


    /**
     * ���Դ�Сд��֤startsWith
     * 
     * @param s ����֤�ַ���
     * @param w ��֤�ַ���
     * @return boolean =true��ʾstartsWith, ����=false
     */
    public static boolean startsWithIgnoreCase(String s,String w)
    {
        if (w==null)
            return true;
        
        if (s==null || s.length()<w.length())
            return false;
        
        for (int i=0;i<w.length();i++)
        {
            char c1=s.charAt(i);
            char c2=w.charAt(i);
            if (c1!=c2)
            {
                if (c1<=127)
                    c1=LOWER_CASES[c1];
                if (c2<=127)
                    c2=LOWER_CASES[c2];
                if (c1!=c2)
                    return false;
            }
        }
        return true;
    }
    
    /**
     * ���Դ�Сд��֤endsWith
     * 
     * @param s ����֤�ַ���
     * @param w ��֤�ַ���
     * @return boolean =true��ʾendsWith, ����=false
     */
    public static boolean endsWithIgnoreCase(String s,String w)
    {
        if (w==null)
            return true;
        
        int sl=s.length();
        int wl=w.length();
        
        if (s==null || sl<wl)
            return false;
        
        for (int i=wl;i-->0;)
        {
            char c1=s.charAt(--sl);
            char c2=w.charAt(i);
            if (c1!=c2)
            {
                if (c1<=127)
                    c1=LOWER_CASES[c1];
                if (c2<=127)
                    c2=LOWER_CASES[c2];
                if (c1!=c2)
                    return false;
            }
        }
        return true;
    }
    
    /**
     * ����ַ���תint���֣�֧����λС����Ľ���ַ���
     * @param str  ����ַ���
     * @param defaultValue ȱʡֵ
     * @return int����
     */
    public static int getMoneyTwoRadix(String str, int defaultValue)
    {
        if (str == null)
            return defaultValue;
        
        if (!ValidateUtil.isMoneyTwoRadix(str))
            return defaultValue;
        
        boolean isNegative = false;
        if (str.startsWith("-"))
        {
            isNegative = true;
            str = str.substring(1);
        }
        
        int index = str.indexOf('.');
        if (index == -1)
        {
            int value = Integer.parseInt(str) * 100;//��ԪתΪ��
            return (isNegative)?-value:value;
        }
        
        int integer = Integer.parseInt(str.substring(0, index)) * 100;
        int radix = Integer.parseInt(str.substring(index + 1));
        int value = integer + radix;
        return (isNegative)?-value:value;
    }
}