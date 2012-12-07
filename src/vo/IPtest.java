package vo;
import junit.framework.TestCase;

public class IPtest extends TestCase {   
       
    public void testIp(){   
                //指定纯真数据库的文件名，所在文件夹   
        IPSeeker ip=new IPSeeker("qqwry.dat","C:/Users/Brian/Workspaces/tribus/tribus/src");   
         //测试IP 58.20.43.13   
System.out.println(ip.getIPLocation("59.49.233.158").getCountry()+":"+ip.getIPLocation("59.49.233.158").getArea());   
    }   
}  