/*
 * ��Ȩ���� (C) 2001-2009 ���������ǿƼ����޹�˾����������Ȩ����
 * �汾��
 * �޸ļ�¼��
 *		1��2009-3-10��wangjuntao������ 
 */
package org.toughradius.constant;
final public class IntConst
{
    private int value = -1;
    private String desc = "δ֪";
    
    public IntConst(int status,String desc)
    {
        this.value = status;
        this.desc = desc;
    }
    public int value()
    {
        return value;
    }
    public String desc()
    {
        return desc;
    }
    
    public String toString()
    {
        return String.valueOf(value);
    }

}
