package org.toughradius;

import java.io.File;
import java.util.HashMap;
import java.util.Properties;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.toughradius.data.RadBlacklistMapper;
import org.toughradius.data.RadClientMapper;
import org.toughradius.data.RadGroupMapper;
import org.toughradius.data.RadGroupMetaMapper;
import org.toughradius.data.RadUserMapper;
import org.toughradius.data.RadUserMetaMapper;
import org.toughradius.server.ToughServer;
import org.toughradius.service.BaseService;
import org.toughradius.service.UserService;

public class Project
{
    public static final String LOG4J_FILE = "conf/log4j.xml";
    public static final String CONFIG_FILE = "conf/system.xml";
    private static final HashMap<Class<?>, Object> objectMap = new HashMap<Class<?>, Object>();
    private static Log log = LogFactory.getLog(Project.class);
    private XMLConfiguration config = null;
    private ToughServer server;
    
    /**
     * ע��ϵͳ��������
     * @param clasz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObject(Class<T> clasz)
    {
        return (T) objectMap.get(clasz);
    }

    /**
     * ��ʼ����־����
     * @return
     */
    public boolean initLogger()
    {
        File file = new File(LOG4J_FILE);
        if (file == null || !file.isFile())
        {
            System.out.println("��ʼ��Log�쳣,����conf/log4j.xml�ļ�");
            return false;
        }
        
        try
        {
            DOMConfigurator.configure(LOG4J_FILE);
        }
        catch(Exception e)
        {
            System.out.println("��ʼ��Log�쳣,����conf/log4j.xml�ļ�,"+e.getMessage());
            return false;
        }
        return true;
        
    }
    
    /**
     * ��ʼ��ϵͳ����
     * @return
     */
    private boolean loadConfig()
    {
        try
        {
            config = new XMLConfiguration(CONFIG_FILE);
            objectMap.put(XMLConfiguration.class, config);
            log.info("����ϵͳ�������");
        }
        catch (ConfigurationException e)
        {
            e.printStackTrace();
            System.out.println("��ʼ�������쳣,����conf/system.xml�ļ�;"+e.getMessage());
            return false;
        }
        return true;
    }
    

    
    
    private boolean initDB()
    {
        try
        {
            PooledDataSourceFactory dataSource = new PooledDataSourceFactory();
            Properties dbpr = new Properties();
            dbpr.setProperty("driver", config.getString("database.driver", "org.hsqldb.jdbc.JDBCDriver"));
            dbpr.setProperty("url", config.getString("database.url", "jdbc:hsqldb:./data/radius"));
            dbpr.setProperty("username", config.getString("database.username", "sa"));
            dbpr.setProperty("password", config.getString("database.password", ""));
            dataSource.setProperties(dbpr);
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("development", transactionFactory, dataSource.getDataSource());
            Configuration configuration = new Configuration(environment);
            configuration.setCacheEnabled(true);
            configuration.addMapper(RadUserMapper.class);
            configuration.addMapper(RadUserMetaMapper.class);
            configuration.addMapper(RadGroupMapper.class);
            configuration.addMapper(RadGroupMetaMapper.class);
            configuration.addMapper(RadClientMapper.class);
            configuration.addMapper(RadBlacklistMapper.class);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
            objectMap.put(SqlSessionFactory.class, sqlSessionFactory);
            log.info("��ʼ�����ݿ����");
        }
        catch (Exception e)
        {
            System.out.println("��ʼ�����ݿ�ʧ�ܣ�"+e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * ����RADIUS������
     */
    private void startRadiusServer()
    {
        log.info("���� RadiusServer...");
        server = new ToughServer();
        server.setAuthPort(config.getInt("radius.authPort", 1812));
        server.setAcctPort(config.getInt("radius.acctPort", 1813));
        objectMap.put(ToughServer.class, server);
        server.start(true, true);
    }
    
    /**
     * ��ʼ��ϵ�y�h�������ӷ�����
     */
    public void start()
    {
        if(!initLogger())
            System.exit(1);
        
        if(!loadConfig())
            System.exit(1);
        
        if(!initDB())
            System.exit(1);
        
        objectMap.put(BaseService.class, new BaseService(getObject(SqlSessionFactory.class)));
        objectMap.put(UserService.class, new UserService(getObject(SqlSessionFactory.class)));
        
        startRadiusServer();
    }
    
    public static SqlSession getSession()
    {
        SqlSessionFactory ssf = getObject(SqlSessionFactory.class);
        return ssf.openSession();
    }
    
    public static BaseService getBaseService()
    {
        return getObject(BaseService.class);
    }
    
    public static UserService getUserService()
    {
        return getObject(UserService.class);
    }
    
    public static void main(String[] args)
    {
        Project startup = new Project();
        startup.start();
    }
}
