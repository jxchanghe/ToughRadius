package org.toughradius.common;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;

public class Streams
{
    /** ������������,��putBytes������ͬ */
    public static long copy(InputStream input, OutputStream output) throws IOException
    {
        return putBytes(input, output);
    }

    /** �������ֽ��� */
    public static byte[] getBytesFilePath(File file) throws IOException
    {
        if (file == null || !file.isFile() || !file.canRead())
            return null;

        FileInputStream input = new FileInputStream(file);
        int len = input.available();
        int readcount = 0, ret = 0;
        byte[] buf = new byte[len];
        while (readcount < len)
        {
            ret = input.read(buf, readcount, len - readcount);
            if (ret == -1)
                throw new EOFException("�����ȶ���Ϣʱ,���Ȳ�����������β��");
            readcount += ret;
        }
        return buf;
    }

    /** �������ַ���,ָ������ */
    public static String getString(InputStream stream, String charset) throws IOException
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0)
            {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        }
        finally
        {
            if (stream != null)
            {
                stream.close();
            }
        }
    }

    /** �������ֽ��� */
    public static byte[] getBytes(InputStream input, int len) throws IOException
    {
        int readcount = 0, ret = 0;
        byte[] buf = new byte[len];
        while (readcount < len)
        {
            ret = input.read(buf, readcount, len - readcount);
            if (ret == -1)
                throw new EOFException("�����ȶ���Ϣʱ,���Ȳ�����������β��");
            readcount += ret;
        }
        return buf;
    }

    /** ��������Դ */
    public static long putBytes(InputStream input, OutputStream output) throws IOException
    {
        byte[] buffer = new byte[1024];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer)))
        {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    /** ��CLASSPATH����Դ */
    public static byte[] getBytesClassPath(Class<?> clazz, String path) throws IOException
    {
        InputStream in = clazz.getResourceAsStream(path);
        int len = in.available();
        byte[] buf = new byte[len];
        int off = 0;
        while (off < len)
        {
            int readLen = (len - off > 32) ? 32 : len - off;
            int ret = in.read(buf, off, readLen);
            if (ret == -1)
                throw new EOFException("��[" + path + "]ʱ�����ȶ���Ϣʱ,���Ȳ�����������β��");
            off += ret;
        }
        return buf;
    }

    /** ��CLASSPATH����Դ */
    public static void putBytesClassPath(Class<?> clazz, String path, OutputStream output) throws IOException
    {
        InputStream in = clazz.getResourceAsStream(path);
        int len = in.available();
        byte[] buf = new byte[1024];
        int off = 0;
        while (off < len)
        {
            int readLen = (len - off > 1024) ? 1024 : len - off;
            int ret = in.read(buf, 0, readLen);
            if (ret == -1)
                throw new EOFException("��[" + path + "]ʱ�����ȶ���Ϣʱ,���Ȳ�����������β��");
            off += ret;

            output.write(buf, 0, ret);
        }
    }

    /** ������ȡһ�� */
    public static int readLine(InputStream input, byte[] b, int off, int len) throws IOException
    {
        if (len <= 0)
            return 0;

        int count = 0, c;
        while ((c = input.read()) != -1)
        {
            b[off++] = (byte) c;
            count++;
            if (c == '\n' || count == len)
                break;
        }

        return count > 0 ? count : -1;
    }
}
