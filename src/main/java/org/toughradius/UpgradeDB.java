package org.toughradius;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.toughradius.utils.FileUtil;

public class UpgradeDB {

    
    static{
         try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
    }
    
    
    public  String  readIn() throws IOException{
        System.out.print(">> ");
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
    
    public  Connection getConnection(String dbname) throws SQLException{
        

        Connection conn  = DriverManager.getConnection("jdbc:hsqldb:./data/"
                + dbname,
                "SA", 
                "");  
        conn.setAutoCommit(false);
        
        return conn;
    }
    
    public void createTales(Connection conn) throws SQLException
    {
        Statement st = conn.createStatement();
        
        System.out.println("���ڴ�����");

        String script = FileUtil.readFile("./docs/create.sql", "GBK");
        script = script.replaceAll("--.*", "");
        String[] sqls = script.split(";");
        
        for (String sql : sqls) {
            if("".equals(sql.trim()))
                continue;
            System.out.println(sql.trim());
            try {
                st.execute(sql.trim());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("������ʱ��������"+e+sql);
            }
            conn.commit();
        }
        st.close();
    }
    


    
    public void importSql(Connection conn, File sqlfile) throws SQLException {
        System.out.println("���ڵ����ⲿsql:" + sqlfile.getAbsolutePath());

        Statement st = conn.createStatement();
        String sql = FileUtil.readFile(sqlfile.getPath(), "GBK");
        if(!"".equals(sql))
        {
            st.execute(sql);
            conn.commit();
        }
        
    }

    
    public void shutdown(Connection conn) throws SQLException
    {
        System.out.println("����shutdown");
        Statement st = conn.createStatement();
        st.execute("SHUTDOWN SCRIPT");
        conn.close();
    }
    

    public static void main(String[] args) throws Exception {
        String dbname = null;
        String importSql = null;
        String deleteOld = null;
        if(args.length>=3)
        {
            dbname = args[0];
            deleteOld = args[1];
            importSql = args[2];
        }
        
        UpgradeDB install = new UpgradeDB();
        
        if(dbname == null){
            System.out.println("������ʼ�����ݿ�,���������ݿ���");
            dbname = install.readIn();
        }
            
        if("".equals(dbname)){
            System.out.println("���ݿ�������Ϊ��");
            return;
        }

        if(FileUtil.isExists(String.format("./data/%s.properties", dbname)))
        {
            if(deleteOld==null)
            {
                System.out.println("���ݿ��Ѿ����ڣ��Ƿ�ɾ���� y/n");
                deleteOld = install.readIn();
            }

            if(deleteOld.equalsIgnoreCase("y"))
            {
                FileUtil.deleteFile(String.format("./data/%s.properties", dbname));
                FileUtil.deleteFile(String.format("./data/%s.script", dbname));
                FileUtil.deleteFile(String.format("./data/%s.log", dbname));
                FileUtil.deleteFile(String.format("./data/%s.loc", dbname));
            }
            
        }
        

        
        Connection conn = install.getConnection(dbname);
        install.createTales(conn);

        if(importSql==null)
        {
            System.out.println("�Ƿ����ⲿsql�� y/n");
            importSql = install.readIn();

        }
        if(importSql.equalsIgnoreCase("y"))
        {
            File sqldir = new File("./data/import");
            File[] files = sqldir.listFiles();
            
            for (File sfile : files) {
                if(sfile.getPath().endsWith("sql")){
                    try {
                        install.importSql(conn,sfile);
                    } catch (Exception e) {
                        System.out.println("����sqlʧ�ܣ�"+sfile.getPath()+e);
                    }
                    
                }
            }
        }

        install.shutdown(conn);
        
        
        
    }

}
