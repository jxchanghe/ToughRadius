package org.toughradius.utils;


import java.util.Calendar;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * ��֤������
 */
public class ValidateUtil
{

    /** �ַ���ȱʡ״̬ */
    private static final boolean DEFAULT_EMPTY_OK = false;
    
    /** ����chars */
    private static final String DIGITS = "0123456789";
    
    /** Сд��ĸchars */
    public static final String LETTERS_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    
    /** Сд��ĸchars + ���� */
    public static final String LETTERS_LOWERCASE_DIGITS = LETTERS_LOWERCASE + DIGITS;

    /** ��д��ĸchars */
    public static final String LETTERS_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /** ��д��ĸchars + ���� */
    public static final String LETTERS_UPPERCASE_DIGITS = LETTERS_UPPERCASE + DIGITS;
    
    /** ȫ����ĸchars */
    public static final String LETTERS = LETTERS_LOWERCASE + LETTERS_UPPERCASE;

    /** ȫ����ĸ���� */
    public static final String LETTERS_DIGITS = LETTERS + DIGITS;
    
    /** �հ׵�chars (�����ո�,\t,\n,\r) */
    private static final String WHITE_SPACE = " \t\n\r";
    
    /** С���� */
    private static final String DECIMAL_POING_DELIMITER = ".";

    /** �绰����������Ĳ������ֵ�chars ,��������,����,�ո�*/
    private static final String PHONE_NUMBER_DELIMITERS = "()- ";

    /** ȫ��绰��������"+"�ŵ�chars*/
    private static final String VALID_PHONE_CHARS_WORLD = "+" + DIGITS + PHONE_NUMBER_DELIMITERS;

    /** �ֻ���������"+"�ź�����,��ֻ�����һ���ַ���+��,��֤�Ǽ���Ƿ��һ����,�����ȥ������֤ */
    private static final String VALID_MSISDN_CHARS = DIGITS;
    
    /** �ֻ������������󳤶� */
    private static final int VALID_MSISDN_MAXLEN = 21;
  
    /** �ֻ������������С���� */
    private static final int VALID_MSISDN_MINLEN = 11;
    
    /** ����12�·ݶ�Ӧ������ */
    private static final int[] DAYS_IN_MONTH = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /** ������������Ƿ���� */
    public static boolean isEqual(Object obj, Object obj2)
    {
        return (obj == null)?(obj2 == null):obj.equals(obj2);
    }
    
    /** ������������Ƿ���� */
    public static boolean isNotEqual(Object obj, Object obj2)
    {
        return (obj == null)?(obj2 != null):!obj.equals(obj2);
    }

    /** ���src�Ƿ����str */
    public static boolean isIndexOf(String src, String str)
    {
        return (src == null)?(str == null):(src.indexOf(str) != -1);
    }
    
    
    /** �������Ƿ�Ϊ�� */
    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
            return true;
        
        if (obj instanceof String)
            return isEmpty((String)obj);
        
        if (obj instanceof Collection)
            return isEmpty((Collection<?>)obj);
        
        return false;
    }
    
    /** ����ַ����Ƿ�Ϊ�� */
    public static boolean isEmpty(String s)
    {
        return ((s == null) || (s.trim().length() == 0));
    }

    /** ��鼯���Ƿ�Ϊ�� */
    public static boolean isEmpty(Collection<?> c)
    {
        return ((c == null) || (c.size() == 0));
    }

    /** ����ַ����Ƿ�Ϊ�� */
    public static boolean isNotEmpty(String s)
    {
        return ((s != null) && (s.trim().length() > 0));
    }

       /** ��鼯���Ƿ�Ϊ�� */
    public static boolean isNotEmpty(Collection<?> c)
    {
        return ((c != null) && (c.size() > 0));
    }
    
    /** ����Ƿ�Ϊ�� */
    public static boolean isNotEmpty(Object obj)
    {
        if (obj == null)
            return false;
        
        if (obj instanceof String)
            return isNotEmpty((String)obj);
        
          if (obj instanceof Collection)
                return isNotEmpty((Collection<?>)obj);
          
        return true;
    }

    /**
     * ���s�д���c,�򷵻�true,���򷵻�false
     */
    public static boolean isCharInString(char c, String s)
    {
        return (s.indexOf(c) != -1);
    }    

    /** ����ַ��Ƿ��Ǵ�д��ĸ,(ע:A-Z֮��) */
    public static boolean isLetterUppercase(char c)
    {
        return LETTERS_UPPERCASE.indexOf(c) != -1;
    }
    
    /** ����ַ��Ƿ��Ǵ�д��ĸ������,(ע:A-Z,0-9֮��) */
    public static boolean isLetterUppercaseDigits(char c)
    {
        return LETTERS_UPPERCASE_DIGITS.indexOf(c) != -1;
    }
    
    /** ����ַ��Ƿ���Сд��ĸ,(ע:a-z֮��) */
    public static boolean isLetterLowercase(char c)
    {
        return LETTERS_LOWERCASE.indexOf(c) != -1;
    }
    
    /** ����ַ��Ƿ���Сд��ĸ������,(ע:a-z,0-9֮��) */
    public static boolean isLetterLowercaseDigits(char c)
    {
        return LETTERS_LOWERCASE_DIGITS.indexOf(c) != -1;
    }
    
    /** ����ַ��Ƿ�����ĸ,(ע:a-z,A-Z֮��) */
    public static boolean isLetter(char c)
    {
        return LETTERS.indexOf(c) != -1;
    }

    /** ����ַ��Ƿ������� */
    public static boolean isDigit(char c)
    {
        return DIGITS.indexOf(c) != -1;
    }

    /** ����ַ��Ƿ������ֻ���ĸ */
    public static boolean isLetterOrDigit(char c)
    {
        return LETTERS_DIGITS.indexOf(c) != -1;
    }

    /** 
     * 1\����ַ���Ϊ�ջ�ȫ��whitespace�е�ֵ�򷵻�true,����һ�������򷵻�false
     * 2\��whitespace���� whitespace = " \t\n\r";(�ո�,\t,\n,\r)
     */
    public static boolean isWhitespace(String s)
    {
        if (isEmpty(s))
            return true;

        // ����ַ����,�������һ������whitespace,�򷵻�false
        for (int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);

            if (WHITE_SPACE.indexOf(c) == -1)
                return false;
        }

        return true;
    }
    
    /** ����Ƿ���ָ���ĳ��ȣ�ע:��s=null || s="",min=0ʱΪtrue */
    public static boolean isLen(String s, int min, int max)
    {
        if (isEmpty(s))
            return min == 0?true:false;
        
        return (s.length() >= min && s.length() <= max);
    }
    
    /** ����Ƿ�GBK���볤�ȣ����ݿ�һ����һ�����������ֽ� */
    public static boolean isByteLen(String s, int min, int max)
    {
        if (isEmpty(s))
            return min == 0?true:false;
        
        int len = 0;
        try
        {
            len = s.getBytes("GBK").length;
        }
        catch(Exception e)
        {
            len = s.getBytes().length;
        }
        return (len >= min && len <= max);
    }
    
    /** ����Ƿ�ָ�����볤�ȣ�UTF-8��һ������3���ֽڣ�GBK������ */
    public static boolean isByteLen(String s, int min, int max, String encoding)
    {
        if (isEmpty(s))
            return min == 0?true:false;
        
        int len = 0;
        try
        {
            len = s.getBytes(encoding).length;
        }
        catch(Exception e)
        {
            len = s.getBytes().length;
        }
        return (len >= min && len <= max);
    }
    
    /** ����Ƿ������� */
    public static boolean isInteger(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        // ������,�������һ���ַ����������򷵻�false
        for (int i = 0; i < s.length(); i++)
        {
            if (!isDigit(s.charAt(i)))
                return false;
        }

        return true;
    }

    /** ����Ƿ��Ǵ����ŵ�����(�����һ���ַ�Ϊ"+,-",�����ܸ���".",ָ��"E"�� */
    public static boolean isSignedInteger(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        try
        {
            Integer.parseInt(s);

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /** ����Ƿ��Ǵ����ŵĳ�����(�����һ���ַ�Ϊ"+,-",�����ܸ���".",ָ��"E"�� */
    public static boolean isSignedLong(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        try
        {
            Long.parseLong(s);

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /** ����Ƿ���һ�������� */
    public static boolean isPositiveInteger(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        try
        {
            long temp = Long.parseLong(s);

            if (temp > 0)
                return true;
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /** ����Ƿ���һ���Ǹ����� */
    public static boolean isNonnegativeInteger(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        try
        {
            int temp = Integer.parseInt(s);

            if (temp >= 0)
                return true;
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /** ����Ƿ���һ�������� */
    public static boolean isNegativeInteger(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        try
        {
            int temp = Integer.parseInt(s);

            if (temp < 0)
                return true;
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /** ����Ƿ���һ���������� */
    public static boolean isNonpositiveInteger(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        try
        {
            int temp = Integer.parseInt(s);

            if (temp <= 0)
                return true;
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    /** ����ַ����Ƿ�������,����a,b֮��,>=a,<=b */
    public static boolean isIntegerInRange(String s, int a, int b)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        if (!isSignedInteger(s))
            return false;

        int num = Integer.parseInt(s);

        return ((num >= a) && (num <= b));
    }
    
    /** ����ַ����Ƿ���������,����a,b֮��,>=a,<=b */
    public static boolean isIntegerInRangeLen(String s, int a, int b)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        if (!isInteger(s))
            return false;

        return ((s.length() >= a) && (s.length() <= b));
    }
    
    /** �Ƿ���Unicode�� */
    public static final boolean isUnicode(String str)
    {
        if (isEmpty(str))
            return false;

        int len = str.length();
        for (int i = 0; i < len; i++)
        {
            if ((int) str.charAt(i) > 128)
                return true;
        }
        if (str.length() == 1)
        {
            if (((int) str.charAt(0)) > 128)
                return true;
        }
        return false;

    }

    /** ����Ƿ���һ���޷��ŵĸ�����,��֧��ָ��E */
    public static boolean isFloat(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        if (s.startsWith(DECIMAL_POING_DELIMITER))
            return false;
        
        //ֻ����һ����.
        boolean seenDecimalPoint = false;
        
        // ����ַ����
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (isDigit(c))
                continue;
            
            if (c == DECIMAL_POING_DELIMITER.charAt(0))
            {
                if (!seenDecimalPoint)
                {
                    seenDecimalPoint = true;
                    continue;
                }
            }
            
            return false;
        }

        return true;
    }

    /** ����Ƿ���һ��������ŵĸ�����,�������"+","-" */
    public static boolean isSignedFloat(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        try
        {
            float temp = Float.parseFloat(s);

            if (temp <= 0)
                return true;
            
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /** ����Ƿ���һ��������ŵ�˫���ȸ�����,�������"+","-" */
    public static boolean isSignedDouble(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        try
        {
            Double.parseDouble(s);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /** ����ַ����Ƿ�������ĸ��� */
    public static boolean isAlphabetic(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (!isLetter(c))
                return false;
        }

        return true;
    }
    
    /** ����ַ����Ƿ�����Сд��ĸ��� */
    public static boolean isAlphabeticLowercase(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (!isLetterLowercase(c))
                return false;
        }

        return true;
    }
    
    /** ����ַ����Ƿ����ɴ�д��ĸ��� */
    public static boolean isAlphabeticUppercase(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (!isLetterUppercase(c))
                return false;
        }

        return true;
    }
    
    /** ����ַ����Ƿ�������ĸ����ҳ�����min,max��Χ�� */
    public static boolean isAlphabeticLen(String s, int min, int max)
    {
        if (isEmpty(s))
            return min == 0?true:false;
        
        if (!isAlphabetic(s))
            return false;
        
        if (s.length() < min || s.length() > max)
            return false;
        
        return true;
    }
    
    /** ����ַ����Ƿ�����Сд��ĸ����ҳ�����min,max��Χ�� */
    public static boolean isAlphabeticLowercaseLen(String s, int min, int max)
    {
        if (isEmpty(s))
            return min == 0?true:false;
        
        if (!isAlphabeticLowercase(s))
            return false;
        
        if (s.length() < min || s.length() > max)
            return false;
        
        return true;
    }
    
    /** ����ַ����Ƿ����ɴ���ĸ����ҳ�����min,max��Χ�� */
    public static boolean isAlphabeticUpperLen(String s, int min, int max)
    {
        if (isEmpty(s))
            return min == 0?true:false;
        
        if (!isAlphabeticUppercase(s))
            return false;
        
        if (s.length() < min || s.length() > max)
            return false;
        
        return true;
    }

    /** ����ַ����Ƿ�������ĸ��������� */
    public static boolean isAlphanumeric(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (!isLetterOrDigit(c))
                return false;
        }

        return true;
    }
    
       /** ����ַ����Ƿ�������ĸ����������ҳ�����min,max��Χ�� */
    public static boolean isAlphanumericLen(String s, int min, int max)
    {
        if (isEmpty(s))
            return min == 0?true:false;
        
        if (!isAlphanumeric(s))
            return false;
        
        if (s.length() < min || s.length() > max)
            return false;
        
        return true;
    }
    
    /** ����ַ����Ƿ����ɴ�д��ĸ��������� */
    public static boolean isAlphaUpperNumeric(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (!isLetterUppercaseDigits(c))
                return false;
        }

        return true;
    }
    
    /** ����ַ����Ƿ����ɴ�д��ĸ��������� */
    public static boolean isAlphaLowerNumeric(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (!isLetterLowercaseDigits(c))
                return false;
        }

        return true;
    }

    /** ����ַ����Ƿ����ɴ�д��ĸ��������� */
    public static boolean isAlphaUpperNumericLen(String s, int min, int max)
    {
        if (isEmpty(s))
            return min == 0?true:false;
        
        if (!isAlphaUpperNumeric(s))
            return false;
        
        if (s.length() < min || s.length() > max)
            return false;
        
        return true;
    }
    
    /** ����ַ����Ƿ�����Сд��ĸ��������� */
    public static boolean isAlphaLowerNumericLen(String s, int min, int max)
    {
        if (isEmpty(s))
            return min == 0?true:false;
        
        if (!isAlphaLowerNumeric(s))
            return false;
        
        if (s.length() < min || s.length() > max)
            return false;
        
        return true;
    }

    /** ����ַ����Ƿ���ȷ���������� */
    public static boolean isZipCode(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        if (s.length() != 6 || !isInteger(s))
            return false;
            
        return true;
    }
    
    public static boolean isMoneyTwoRadix(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;
           
        //ȥ������
        if (s.startsWith("-"))
            s = s.substring(1);
        
        int ind = s.indexOf(".");
        if (ind == -1)
            return isInteger(s);//���û�е�ţ����ж��Ƿ�������
        
        if (ind == 0)
            return false;
        
        String integer = s.substring(0, ind);
        String radix = s.substring(ind + 1);
        if (!isInteger(integer) || !isIntegerInRangeLen(radix, 2, 2))
            return false;//����������ֲ���������С�����ֲ�����������С�����ֲ�����λ
        
        return true;
    }

    /** ����ַ����Ƿ���ȷ���ʼ���ַ(ע:Ҫ�����@�ַ�,�Ҳ��ǳ����ڵ�һ��,���һ��λ��,���ڲ�����Ƿ����".") */
    public static boolean isEmail(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        if (isWhitespace(s))
            return false;

        int indexLeft = s.indexOf('@');
        int indexRight = s.lastIndexOf('@');
        
        //���������@,��ֹһ��,���һ��,�����һ��
        if (indexLeft < 1 || indexLeft != indexRight || indexLeft == s.length())
            return false;
        
        return true;
    }

    /** ����Ƿ�����ȷ���� */
    public static boolean isYear(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        if (!isNonnegativeInteger(s))
            return false;
        
        return ((s.length() == 2) || (s.length() == 4));
    }

    /** �ж��Ƿ�����ĩ yyyy-MM-dd */
    public static boolean isWeekend(String date)
    {
        Calendar calendar = DateTimeUtil.toCalendar(date+" 00:00:01");
        return calendar.get(Calendar.DAY_OF_WEEK) == 1;
    }
    
    /** �ж��Ƿ񼾶�ĩ yyyy-MM-dd */
    public static boolean isMonthQuarter(String date)
    {
        if (!isDate(date))
            return false;
        
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8);
        
        if (!isMonthLastDay(year, month, day))
            return false;
        
        if (month.equals("03") || month.equals("06") || month.equals("09") || month.equals("12"))
            return true;
        
        return false;
    }
    
    /** �ж��Ƿ���ĩ yyyy-MM-dd */
    public static boolean isYearLastDay(String date)
    {
        if (!isDate(date))
            return false;
        
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8);
        
        if (!isMonthLastDay(year, month, day))
            return false;
        
        if (month.equals("12"))
            return true;
        
        return false;
    }
    
    /** ����Ƿ�����ȷ���� */
    public static boolean isMonth(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;
        
        return isIntegerInRange(s, 1, 12);
    }

    /** ����Ƿ�����ȷ���� */
    public static boolean isDay(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;
        
        return isIntegerInRange(s, 1, 31);
    }

    /** ����Ƿ����� */
    public static boolean isLeapYear(int year)
    {
        return (year % 4 == 0) && ((!(year % 100 == 0)) || (year % 400 == 0));
    }
    
       /** ����Ƿ�����ĩ yyyy-MM-dd HH:mm:ss */
    public static boolean isMonthLastDay(String date)
    {
        if (!isDate(date))
            return false;
        
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8);
        return isMonthLastDay(year, month, day);
    }
    
    /** ����Ƿ�����ĩ */
    public static boolean isMonthLastDay(String year, String month, String day)
    {
        if (!isDate(year, month, day))
            return false;
        
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);
        int dayInt = Integer.parseInt(day);
        return isMonthLastDay(yearInt, monthInt, dayInt);
    }
    
    /** ����Ƿ�����ĩ */
    public static boolean isMonthLastDay(int year, int month, int day)
    {
        if (year < 1000 || year > 9999 || month > 12 || month < 1 || day > 31 || day < 1)
            return false;
        
        switch (month)
        {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return day == 31;
        case 4:
        case 6:
        case 9:
        case 11:
            return day == 30;
        default://2��
            boolean isLeapYear = ValidateUtil.isLeapYear(year);
            return isLeapYear?day==29:day==28;
        }
    }

    /** ����Ƿ�����ȷ��ʱ */
    public static boolean isHour(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;
        
        return isIntegerInRange(s, 0, 23);
    }

    /** ����Ƿ�����ȷ�ķ� */
    public static boolean isMinute(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;
        return isIntegerInRange(s, 0, 59);
    }

    /** ����Ƿ�����ȷ���� */
    public static boolean isSecond(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;
        return isIntegerInRange(s, 0, 59);
    }

    /** ����Ƿ�����ȷ������ */
    public static boolean isDate(String year, String month, String day)
    {
        if (!(isYear(year) && isMonth(month) && isDay(day)))
            return false;

        int intYear = Integer.parseInt(year);
        int intMonth = Integer.parseInt(month);
        int intDay = Integer.parseInt(day);

        if (intDay > DAYS_IN_MONTH[intMonth - 1])
            return false;
        
        if ((intMonth == 2) && (intDay > (isLeapYear(intYear)?29:28)))
            return false;
        
        return true;
    }

    /** ����Ƿ�����ȷ������ */
    public static boolean isDate(String date)
    {
        if (isEmpty(date))
            return DEFAULT_EMPTY_OK;

        if (date.length() != 10)
            return DEFAULT_EMPTY_OK;
        
        int dateSlash1 = date.indexOf("-");
        int dateSlash2 = date.lastIndexOf("-");

        if (dateSlash1 <= 0 || dateSlash1 == dateSlash2)
            return false;
        
        String year = date.substring(0, dateSlash1);
        String month = date.substring(dateSlash1 + 1, dateSlash2);
        String day = date.substring(dateSlash2 + 1);

        return isDate(year, month, day);
    }
    
    /** �ж��ǲ���ָ����ʱ���ʽ */
    public static boolean isDateTime(String datetime)
    {
        if (isEmpty(datetime))
            return false;
        
        datetime = datetime.trim();
        String[] strs = datetime.split(" ");
        if (strs.length != 2)
            return false;
        
        return isDate(strs[0]) && isTime(strs[1]);
    }
    
    /** �ж��ǲ���ָ����ʱ���ʽ, speΪ���ڷָ��� */
    public static boolean isDateTime(String datetime, String spe)
    {
        if (isEmpty(datetime))
            return false;
        
        datetime = datetime.trim();
        String[] strs = datetime.split(" ");
        if (strs.length != 2)
            return false;
        
        return isDate(strs[0].replaceAll(spe, "-")) && isTime(strs[1]);
    }
    
    /** ����Ƿ���������ȷ������ */
    public static boolean isEnglishDate(String date)
    {
        if (isEmpty(date))
            return DEFAULT_EMPTY_OK;

        int dateSlash1 = date.indexOf("/");
        int dateSlash2 = date.lastIndexOf("/");

        if (dateSlash1 <= 0 || dateSlash1 == dateSlash2)
            return false;
        
        String month = date.substring(0, dateSlash1);
        String day = date.substring(dateSlash1 + 1, dateSlash2);
        String year = date.substring(dateSlash2 + 1);

        return isDate(year, month, day);
    }

    /** ����Ƿ������ڱȽ���� */
    public static boolean isDateAfterToday(String date)
    {
        if (!isDate(date))
            return DEFAULT_EMPTY_OK;
        
        String currentDate = DateTimeUtil.getDateString();
        return date.compareTo(currentDate) > 0;
    }
    
    /** ����Ƿ������ڱȽ������� */
    public static boolean isDateAfterEqualToday(String date)
    {
        if (!isDate(date))
            return DEFAULT_EMPTY_OK;
        
        String currentDate = DateTimeUtil.getDateString();
        return date.compareTo(currentDate) >= 0;
    }
    
    public static void main(String[] args)
    {
        System.out.println(isDateAfterToday("2012-06-07"));
    }

    /** ����Ƿ�����ȷ��ʱ�� */
    public static boolean isTime(String hour, String minute, String second)
    {
        if (isHour(hour) && isMinute(minute) && isSecond(second))
            return true;

        return false;
    }

    /** ����Ƿ�����ȷ��ʱ�� */
    public static boolean isTime(String time)
    {
        if (isEmpty(time))
            return DEFAULT_EMPTY_OK;

        int timeColon1 = time.indexOf(":");
        int timeColon2 = time.lastIndexOf(":");

        if (timeColon1 <= 0 || timeColon1 == timeColon2)
            return false;
        
        String hour = time.substring(0, timeColon1);
        String minute = time.substring(timeColon1 + 1, timeColon2);
        String second = time.substring(timeColon2 + 1);
        
        return isTime(hour, minute, second);
    }

    /** ����Ƿ�����ȷ�ĵ绰���� */
    public static boolean isPhone(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        // ����ַ����,�������һ������whitespace,�򷵻�false
        for (int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);

            if (!isCharInString(c,VALID_PHONE_CHARS_WORLD))
                return false;
        }
        
        return true;
    }
    
    /** ����Ƿ�����ȷ���ֻ����� */
    public static boolean isMsisdn(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;
            
        //�����һ����+��,��ȥ��
        if (s.charAt(0) == '+')
            s = s.substring(1);
        
        if (s.length() > VALID_MSISDN_MAXLEN || s.length() < VALID_MSISDN_MINLEN)
            return false;
        
        // ����ַ����,�������һ������VALID_MSISDN_CHARS,�򷵻�false
        for (int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);

            if (!isCharInString(c,VALID_MSISDN_CHARS))
                return false;
        }
        
        return true;
    }
    
    /**
     * �жϺ����Ƿ���������ļ���������
     * 
     * @param phone �����ַ���
     * @oaram prefixs �̶�ǰ������ǰ׺,��135,136,159��,����ö��Ÿ���
     * @return boolean =true ���ֻ�����,=false ���ֻ�����
     */
    public static boolean isMsisdn11(String phone, String prefixs)
    {
        if(!isIntegerInRangeLen(phone, 11, 11))
            return false;
        
        String [] prefixArr = prefixs.split(",");
        for(int i=0; i<prefixArr.length; i++)
        {                    
            if(phone.startsWith(prefixArr[i]))
                return true;            
        }        
        
        return false;
    }
    
    /**
     * �жϺ����Ƿ���������ļ���������
     * 
     * @param phone �����ַ���
     * @param prefixs ǰ׺���飬��135,137,+86,0086,17951135��,����ö��Ÿ���
     * @return boolean =true ���ֻ�����,=false ���ֻ�����
     */
    public static boolean isMsisdn21(String phone, String prefixs)
    {       
        if(!isMsisdn(phone))
            return false;
        
        String[] prefixArr = prefixs.split(",");
        for(int i=0; i<prefixArr.length; i++)
        {                    
            if (phone.length() != prefixArr[i].length() + 8)
                continue;
            
            if(phone.startsWith(prefixArr[i]))
                return true;            
        }        
        
        return false;
    }
    
    /** ����Ƿ���IP��ַ,ipΪ�շ���false; */
    public static boolean isIP(String ip)
    {
        return isIP(ip, false);
    }
    
    /** ����Ƿ���IP��ַ */
    public static boolean isIP(String ip, boolean allowEmpty)
    {
        if (isEmpty(ip))
            return allowEmpty;
        
        try
        {
            int ind1 = ip.indexOf('.');
            if (ind1 == -1)
                return false;
            
            String str1 = ip.substring(0, ind1);
            if (!ValidateUtil.isIntegerInRange(str1, 0, 255))
                return false;
                
            int ind2 = ip.indexOf('.', ind1+1);
            if (ind2 == -1)
                return false;
            
            String str2 = ip.substring(ind1+1, ind2);
            if (!ValidateUtil.isIntegerInRange(str2, 0, 255))
                return false;
            
            int ind3 = ip.indexOf('.', ind2+1);
            if (ind3 == -1)
                return false;
            
            String str3 = ip.substring(ind2+1, ind3);
            if (!ValidateUtil.isIntegerInRange(str3, 0, 255))
                return false;
            
            String str4 = ip.substring(ind3+1);
            if (!ValidateUtil.isIntegerInRange(str4, 0, 255))
                return false;
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }
    
    /** ����Ƿ���macAddress,macAddressΪ�շ���false; */
    public static boolean isMacAddress(String macAddress)
    {
        return isMacAddress(macAddress, false);
    }
    
    /** ����Ƿ���IP��ַ */
    public static boolean isMacAddress(String macAddress, boolean allowEmpty)
    {
        if (isEmpty(macAddress))
            return allowEmpty;
        
        return isRegExp(macAddress, "^[0-9A-Fa-f]{2}:[0-9A-Fa-f]{2}:[0-9A-Fa-f]{2}:[0-9A-Fa-f]{2}:[0-9A-Fa-f]{2}:[0-9A-Fa-f]{2}$");
    }
    
    /** ����Ƿ��ʱ� */
    public static boolean isPostalCode(String s)
    {
        if (!isInteger(s) || s.trim().length() != 6)
            return false;
        
        return true;
    }
    
    /** ����Ƿ���ָ�����ַ����� */
    public static boolean isScope(String s, String scope)
    {
        if (ValidateUtil.isEmpty(s))
            return ValidateUtil.DEFAULT_EMPTY_OK;
            
        // ����ַ����,�������һ������specifyStr,�򷵻�false
        for (int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);

            if (!ValidateUtil.isCharInString(c, scope))
                return false;
        }
        
        return true;
    }
    
    /** ���value�Ƿ����ָ����pattern */
    public static boolean isRegExp(String value, String regExp)
    {
        if (regExp.startsWith("/"))
            regExp = regExp.substring(1);
        if (regExp.endsWith("/"))
            regExp = regExp.substring(0, regExp.length() - 1);
        return Pattern.matches(regExp, value);
    }
    
    /** ���src�Ƿ�����ַ��������κ�һ�� */
    public static boolean isStrContainStrArr(String src, String[] strs)
    {
        for (String str : strs)
        {
            if (src.contains(str.trim()))
                return true;
        }
        
        return false;
    }
    
    /** ���src�Ƿ�����ַ��������κ�һ�� */
    public static boolean isStrContainStrArr(String src, String strArr, String delm)
    {
        return isStrContainStrArr(src, strArr.split(delm));
    }

    /** ���long�Ƿ���long[]�� */
    public static boolean isLongContain(long[] ls, long lo)
    {
        for (long l : ls)
        {
            if (l == lo)
                return true;
        }
        
        return false;
    }
    
    /** ���int�Ƿ���int[]�� */
    public static boolean isIntContain(int[] is, int in)
    {
        for (int i : is)
        {
            if (i == in)
                return true;
        }
        
        return false;
    }
}
