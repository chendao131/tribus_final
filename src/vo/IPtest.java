package vo;
import junit.framework.TestCase;

public class IPtest extends TestCase {   
       
    public void testIp(){   
                //ָ���������ݿ���ļ����������ļ���   
        IPSeeker ip=new IPSeeker("qqwry.dat","C:/Users/Brian/Workspaces/tribus/tribus/src");   
         //����IP 58.20.43.13   
System.out.println(ip.getIPLocation("59.49.233.158").getCountry()+":"+ip.getIPLocation("59.49.233.158").getArea());   
    }   
}  