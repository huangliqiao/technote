package burp;

import java.awt.Window;


public class hre
{

    private static final String z[] = {"ExpiredLicense:","CryptoFailureException"};

    public static whd a(htd htd)
    {
        try {
            String s = hle.a();
            if(s == null)
                return a(null, htd, 0);
            whd whd1 = null;
            try {
                whd1 = new whd(s, xab.c(s));
//                Object[] objs = new Object[]{new String("WENYINGDISHUOMAIBUQIFANGZIKEYIZU"), new String("licensed to Larry_Lau"), new Long(1580694400000L), new Integer(1)};
//                whd1 = new whd(s, objs);
            } catch(Exception e) {
                r9f.a(e, cad.f);
                String msg = e.getMessage();
                if(z[1].equals(msg)) {
                    return a(null, htd, 1);
                }
                if(msg != null && msg.startsWith(z[0])) {
                    hle.b();
                    hle.b(msg.substring(msg.indexOf(":") + 1));
                    return a(null, htd,  4);
                }
                //112
                return a(null, htd,  8);
            }
            //119
            if(pf.a(whd1.c)) {
                hle.b();
                hle.b(whd1.c);
                return a(null, htd, 0);
            }
            //146
            String s3 = hle.c(whd1.c);
            if(s3 == null) {
                return a(null, htd, whd1, 2);
            }
            //166
            try {
                xab.b(s3);
            } catch(Exception e) {
                r9f.a(e, cad.f);
                hle.b();
                if(z[1].equals(e.getMessage())) {
                    return a(null, htd, whd1, 3);
                }
                //210 提示在线注册
                return a(null, htd, whd1, 2);
            }
            //218  注册成功
            return whd1;
        } catch(Exception e) {
            r9f.a(e, cad.f);
            return a(null, htd, 0);
        }
        
    }

    public static whd a(Window window, htd htd, int i)
    {
        return (new ihh(window, htd, 1, null, i)).c();
    }

    private static whd a(Window window, htd htd, whd whd1, int i)
    {
        return (new ihh(window, htd, 2, whd1, i)).c();
    }
    
}
