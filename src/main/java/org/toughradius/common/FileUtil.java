package org.toughradius.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * �ļ�����������
 */
public class FileUtil
{

    /**
     * �ж��ļ��Ƿ����(����Ŀ¼���ļ�)
     * 
     * @param path �ļ�����·��
     * @return true/false �����򷵻�true
     */
    public static boolean isExists(File path)
    {
        if (path == null)
            return false;
        
        return path.exists();
    }
    
    /**
     * �ж��ļ��Ƿ����(����Ŀ¼���ļ�)
     * 
     * @param path �ļ�����·��
     * @return true/false �����򷵻�true
     */
    public static boolean isExists(String path)
    {
        if (ValidateUtil.isEmpty(path))
            return false;

        try
        {
            File file = new File(path);
            return file.exists();
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    /**
     * �ж�·���Ƿ�ΪĿ¼
     * 
     * @param path Ҫ�жϵ�����·��
     * @return true/false �����Ŀ¼������true
     */
    public static boolean isDirectory(String path)
    {
        if (ValidateUtil.isEmpty(path))
            return false;
        
        File file = null;
        try
        {
            file = new File(path);
        }
        catch (Exception ex)
        {
            return false;
        }

        return isDirectory(file);
    }
    
    /**
     * �ж�·���Ƿ�ΪĿ¼
     * 
     * @param file �ļ�����
     * @return true/false �����Ŀ¼������true
     */
    public static boolean isDirectory(File file)
    {
        if (file == null)
            return false;
        
        if (!file.exists())
            return false;
        
        return file.isDirectory();
    }

    /**
     * ��ȡĿ¼�������ļ���Ŀ¼�б�
     * 
     * @param dirPath Ŀ¼·��
     * @param �ļ�Ŀ¼�б�
     */
    public static String[] getDirectoryList(String dirPath)
    {
        if (ValidateUtil.isEmpty(dirPath))
            return null;

        File dir = new File(dirPath);
        if (!dir.exists() || !dir.isDirectory())
            return null;

        String[] fileNames = dir.list();
        dir = null;

        return fileNames;
    }

    /** 
     * �ݹ��ʼ���ļ��б� 
     * 
     * @param fileList ������ļ��б����ã�����new��������Ϊnull;
     * @param dir ָ������Ŀ¼
     * @param suffix ��׺���Ըý�β��ͬ������,����endsWith����ƥ��
     */
    public static void qFileListBySuffix(List<File> fileList, File dir, String suffix)
    {
        File[] list = dir.listFiles();
        for (int i=0;i<list.length;i++)
        {
            File file = list[i];
            if (file.isFile())
            {
                if (file.getName().endsWith(suffix))
                    fileList.add(file);
            }
            else
            {
                qFileListBySuffix(fileList, file, suffix);
            }
        }
    }
    
    /**
     * ����Ŀ¼ �ɹ�����false,����Ŀ¼��dest·����д���󷵻�false
     * 
     * @param src ԴĿ¼
     * @param dest Ŀ��Ŀ¼
     * @return �ɹ�����false,����Ŀ¼��dest·����д���󷵻�false
     */
    public static boolean copyDirectory(File src, String dest) throws Exception
    {
        if ((src == null) || !src.isDirectory())
            return false;

        try
        {
            File destRoot = new File(dest);
            if (!destRoot.exists())
                destRoot.mkdir();

            File[] entries = src.listFiles();
            int len = entries.length;
            for (int i = 0; i < len; i++)
            {
                File entry = entries[i];
                String target = dest + StringUtil.getSystemSeparator()+ entry.getName();
                if (entry.isDirectory())
                {
                    copyDirectory(entry, target); //�ݹ�
                }
                else
                {
                    File toFile = new File(target);
                    FileUtil.copyFile(entry, toFile);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return true;
    }
    
    /**
     * ɾ���ļ���
     * 
     * @param directory �ļ���·��
     * @return true/false
     */
    public static boolean deleteDirectory(String dir, String[] forbidDirs)throws Exception
    {
        if (ValidateUtil.isEmpty(dir))
            return false;

        return deleteDirectory(new File(dir), forbidDirs);
    }

    /**
     * ɾ���ļ���
     * 
     * @param directory �ļ�����
     * @param forbidDirs ��ֹɾ����Ŀ¼
     * @return true/false
     */
    public static boolean deleteDirectory(File dir, String[] forbidDirs) throws Exception
    {
        if ((dir == null) || !dir.isDirectory())
            return false;

        File[] entries = dir.listFiles();
        int sz = entries.length;

        for (int i = 0; i < sz; i++)
        {
            boolean forbid = false;

            if (entries[i].isDirectory())
            {
                if (forbidDirs != null && forbidDirs.length > 0)
                {
                    String folderName = entries[i].getName();
                    for (int j = 0; j < forbidDirs.length; j++)
                    {
                        if (forbidDirs[j].equals(folderName))
                        {
                            forbid = true;
                            break;
                        }
                    }
                }

                if (forbid == true)
                    continue;

                deleteDirectory(entries[i], forbidDirs);
            }
            else
            {
                entries[i].delete();
            }
        }

        dir.delete();
        return true;

    }
    
    /**
     * �ж��ļ��Ƿ����
     * 
     * @param fileName �ļ�����·��
     * @return true/false �����򷵻�true
     */
    public static boolean isFile(String fileName)
    {
        if (ValidateUtil.isEmpty(fileName))
            return false;
        
        File file = null;
        try
        {
            file = new File(fileName);
        }
        catch (Exception ex)
        {
            return false;
        }

        return isFile(file);
    }

    /**
     * �ж��ļ��Ƿ����
     * 
     * @param file File
     * @return true/false �����򷵻�true
     */
    public static boolean isFile(File file)
    {
        if (file == null)
            return false;
        
        if (!file.exists())
            return false;
        
        return file.isFile();
    }

    /**
     * �ļ�����,Ҫ����Ƿ����ļ�
     * 
     * @param fromFile Դ�ļ�����
     * @param toFile Ŀ���ļ�����
     * @return true/false
     */
    public static boolean copyFile(File fromFile, File toFile) throws Exception
    {
        if (!isFile(fromFile) || toFile == null)
            return false;

        FileInputStream fis = new FileInputStream(fromFile);
        int len = fis.available();
        byte[] buf = new byte[len];
        for (int i=0,b=fis.read();b!=-1;i++,b=fis.read())
        {
            buf[i] = (byte)b;
        }
        fis.close();
        fis = null;
        
        FileOutputStream fos = new FileOutputStream(toFile);
        fos.write(buf);
        fos.flush();
        fos.close();
        fos = null;
        return true;
    }
    
    /**
     * �ļ�������,����·��
     * 
     * @param fromFile Դ�ļ�·��
     * @param toFile Ŀ���ļ�·��
     * @return true/false
     */
    public static boolean copyFile(String fromFile, String toFile) throws Exception
    {
        if (ValidateUtil.isEmpty(fromFile) || ValidateUtil.isEmpty(toFile))
            return false;

        File from = new File(fromFile);
        File to = new File(toFile);

        return copyFile(from, to);
    }

    /**
     * �ļ�����,����Դ�ļ�·��,Ŀ��Ŀ¼,�ļ���
     * 
     * @param fromFile Դ�ļ�·��
     * @param toDir Ŀ��Ŀ¼
     * @param fileName Ŀ¼�ļ�
     * @return true/false
     */
    public static boolean copyFile(String fromFile, String toDir, String fileName) throws Exception
    {
        if (ValidateUtil.isEmpty(fromFile) || ValidateUtil.isEmpty(toDir) || ValidateUtil.isEmpty(fileName))
            return false;

        File from = new File(fromFile);
        if (!from.exists() || !from.isFile())
            return false;
        
        //�½�Ŀ¼
        if (!ValidateUtil.isEmpty(toDir))
        {
            File dir = new File(toDir);
            if (!dir.exists())
            {
                dir.mkdirs();
            }
            dir = null;
        }
        
        if (toDir.lastIndexOf(StringUtil.getSystemSeparator()) != toDir.length())
            toDir += StringUtil.getSystemSeparator();
        
        File to = new File(toDir + fileName);
        return copyFile(from, to);
    }

    /**
     * ɾ���ļ�,���·��Ϊ�ջ��ļ��������򷵻�false
     * 
     * @param filePath �ļ�����·��
     * @return true/false �ɹ�ɾ���򷵻�true
     */
    public static boolean deleteFile(String filePath)
    {
        if (ValidateUtil.isEmpty(filePath))
            return false;

        File file = new File(filePath);
        if (!file.exists())
            return false;

        return file.delete();
    }
    
    /**
     * ��ȡ�ļ����ݣ������������ַ�����ʽ����� ����ļ������ڣ���·�������򷵻ؿ��ַ�����
     * 
     * @param fileName �ļ�����
     * @return �ļ�����
     */
    public static String readResource(Class<?> classPath, String resourceName, String encoding)
    {
        if (ValidateUtil.isEmpty(resourceName) || ValidateUtil.isEmpty(encoding))
            return null;

        try
        {
            InputStream is = classPath.getResourceAsStream(resourceName);
            int len = is.available();
            byte[] bytes = new byte[len];
            for (int i=0,b=is.read();b!=-1;i++,b=is.read())
            {
                bytes[i] = (byte)b;
            }
            is.close();
            is = null;
            
            String fileContent = new String(bytes, encoding);
            return fileContent;
        }
        catch (IOException ex)
        {
            return null;
        }
    }

    /**
     * ��ȡ�ļ����ݣ������������ַ�����ʽ����� ����ļ������ڣ���·�������򷵻ؿ��ַ�����
     * 
     * @param fileName �ļ�����
     * @return �ļ�����
     */
    public static String readFile(String fileName, String encoding)
    {
        if (!isFile(fileName))
            return null;

        try
        {
            FileInputStream fis = new FileInputStream(fileName);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();

            byte[] bytes = new byte[1024];int len = -1;
            while ((len = fis.read(bytes)) > 0)
            {
                buf.write(bytes, 0, len);
            }
            
            fis.close();
            fis = null;
            
            String fileContent = new String(buf.toByteArray(), encoding);
            return fileContent;
        }
        catch (IOException ex)
        {
            return null;
        }
    }
    
    /**
     * �����ļ�·��,����,���������ֽ�����ķ�ʽд�뵽ָ�����ļ���
     * 
     * @param fileName �ļ�����·��
     * @param in �ļ���
     * @return true/false д������ɹ�������true
     */
    public static long writeBytesReturnSize(String fileName, InputStream in)
    {
        try
        {
            byte[] content = new byte[in.available()];
            in.read(content);
            
            return writeBytesReturnSize(fileName, in);
        }
        catch(Exception e)
        {
            return 0;
        }
    }
    
    /**
     * �����ļ�·��,����,���������ֽ�����ķ�ʽд�뵽ָ�����ļ���
     * 
     * @param fileName �ļ�����·��
     * @param content �ļ�����
     * @return true/false д������ɹ�������true
     */
    public static long writeBytesReturnSize(String fileName, byte[] content)
    {
        /* ·��������,���� */
        if (ValidateUtil.isEmpty(fileName))
            return 0;

        try
        {
            // ��ȡ�ļ�����Ŀ¼,���,���������Ŀ¼�򴴽�
            String filePath = StringUtil.getFilePath(fileName);
            File file = new File(filePath);
            if (!file.exists())
                file.mkdirs();

            // д���ļ�
            file = new File(fileName);
            synchronized (file)
            {
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(content);
                fos.flush();
                fos.close();
            }
            long size = file.length();
            file = null;

            return size;
        }
        catch (Exception ex)
        {
            return 0;
        }
    }

    /**
     * �����ļ�·��,����,���������ֽ�����ķ�ʽд�뵽ָ�����ļ���
     * 
     * @param fileName �ļ�����·��
     * @param content �ļ�����
     * @return true/false д������ɹ�������true
     */
    public static boolean writeBytes(String fileName, byte[] content)
    {
        if (ValidateUtil.isEmpty(fileName))
            return false;

        try
        {
            // ��ȡ�ļ�����Ŀ¼,���,���������Ŀ¼�򴴽�
            String filePath = StringUtil.getFilePath(fileName);
            File file = new File(filePath);
            if (!file.exists())
                file.mkdirs();

            // д���ļ�
            file = new File(fileName);
            synchronized (file)
            {
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(content);
                fos.flush();
                fos.close();
            }
            file = null;
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    /**
     * �����ļ�·��,����,�����������ķ�ʽд�뵽ָ�����ļ���
     * 
     * @param fileName �ļ�����·��
     * @param content �ļ�����
     * @return true/false д������ɹ�������true
     */
    public static boolean write(String fileName, String content)
    {
        if (ValidateUtil.isEmpty(fileName))
            return false;

        try
        {
            // ��ȡ�ļ�����Ŀ¼,���,���������Ŀ¼�򴴽�
            String filePath = StringUtil.getFilePath(fileName);
            File file = new File(filePath);
            if (!file.exists())
                file.mkdirs();

            // д���ļ�
            file = new File(fileName);
            synchronized (file)
            {
                FileOutputStream fos = new FileOutputStream(fileName);
                byte[] b = content.getBytes();
                fos.write(b);
                fos.flush();
                fos.close();
            }
            file = null;
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    /**
     * �����ļ�·��,����,���������ַ��ķ�ʽд�뵽ָ�����ļ���
     * 
     * @param fileName �ļ�����·��
     * @param content �ļ�����
     * @return true/false д������ɹ�������true
     */
    public static boolean writeString(String fileName, String content)
    {
        if (ValidateUtil.isEmpty(fileName))
            return false;

        try
        {
            // ��ȡ�ļ�����Ŀ¼,���,���������Ŀ¼�򴴽�
            String filePath = StringUtil.getFilePath(fileName);
            File file = new File(filePath);
            if (!file.exists())
                file.mkdirs();

            // д���ļ�
            file = new File(fileName);

            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();

            file = null;
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
    
    /**
     * ��ѹZIP�ļ�
     * 
     * @param zip ZIP�ļ�
     * @param toDir ��ѹĿ¼
     * @return�����ص�ǰ�б�
     * @throws IOException
     */
    public static List<File> unzip(File zip, File toDir) throws IOException 
    {
        List<File> files = new ArrayList<File>();
        ZipFile zf = new ZipFile(zip);
        Enumeration<?> entries = zf.entries();
        while (entries.hasMoreElements()) 
        {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            if (entry.isDirectory()) 
            {
                new File(toDir, entry.getName()).mkdirs();
                continue;
            }

            InputStream input = null;
            OutputStream output = null;
            try 
            {
                File f = new File(toDir, entry.getName());
                input = zf.getInputStream(entry);
                output = new FileOutputStream(f);
                Streams.copy(input, output);
                files.add(f);
            } 
            finally 
            {
                try{if(input != null)input.close();}catch(Exception e){};
                try{if(output != null)output.close();}catch(Exception e){};
            }
        }
        return files;
    }
    
    /**
     * ��ѹGZIP�ļ�
     * 
     * @param gzip
     * @param toDir
     * @return
     * @throws IOException
     */
    public static File ungzip(File gzip, File toDir) throws IOException 
    {
        toDir.mkdirs();
        File out = new File(toDir, gzip.getName());
        GZIPInputStream input = null;
        FileOutputStream output = null;
        
        try 
        {
            FileInputStream fin = new FileInputStream(gzip);
            input = new GZIPInputStream(fin);
            output = new FileOutputStream(out);
            Streams.copy(input, output);
        } 
        finally 
        {
            try{if(input != null)input.close();}catch(Exception e){};
            try{if(output != null)output.close();}catch(Exception e){};
        }
        
        return out;
    }
}