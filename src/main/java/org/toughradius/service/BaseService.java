package org.toughradius.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.toughradius.data.RadBlacklistMapper;
import org.toughradius.data.RadClientMapper;
import org.toughradius.model.RadBlacklist;
import org.toughradius.model.RadClient;

public class BaseService
{
    private static Log logger = LogFactory.getLog(BaseService.class);
    private SqlSessionFactory sessionFactory;
    
    public BaseService(SqlSessionFactory ssf)
    {
        sessionFactory = ssf;
    }
    
    /**
     * ��ѯ�����ͻ���
     * @param address
     * @return
     */
    public RadClient getClient(String address)
    {
        SqlSession session = sessionFactory.openSession();
        try
        {
            RadClientMapper mapper = session.getMapper(RadClientMapper.class);
            RadClient rc = mapper.selectByPrimaryKey(address);
            return rc;
        }
        finally
        {
            session.close();
        }
    }
    
    /**
     * ��ѯ�ͻ��˼���
     * @return
     */
    public List<RadClient> getClients()
    {
        SqlSession session = sessionFactory.openSession();
        try
        {
            RadClientMapper mapper = session.getMapper(RadClientMapper.class);
            List<RadClient> cs= mapper.selectByExample(null);
            return cs;
        }
        finally
        {
            session.close();
        }
    }
    
    /**
     * ��ѯ����������
     * @param macaddr
     * @return
     */
    public RadBlacklist getBlacklist(String macaddr)
    {
        SqlSession session = sessionFactory.openSession();
        try
        {
            RadBlacklistMapper mapper = session.getMapper(RadBlacklistMapper.class);
            RadBlacklist rbl = mapper.selectByPrimaryKey(macaddr);
            return rbl;
        }
        finally
        {
            session.close();
        }
    }
    
    /**
     * ��ѯ����������
     * @return
     */
    public List<RadBlacklist> getBlacklists()
    {
        SqlSession session = sessionFactory.openSession();
        try
        {
            RadBlacklistMapper mapper = session.getMapper(RadBlacklistMapper.class);
            List<RadBlacklist> bs = mapper.selectByExample(null);
            return bs;
        }
        finally
        {
            session.close();
        }
    }
    
}