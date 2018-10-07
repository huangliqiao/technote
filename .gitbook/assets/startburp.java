package burp;

import java.awt.GraphicsEnvironment;

import burp.add;
import burp.b4g;
import burp.buc;
import burp.cad;
import burp.duc;
import burp.ehg;
import burp.h6b;
import burp.htd;
import burp.ieg;
import burp.l5d;
import burp.lub;
import burp.mlh;
import burp.ogg;
import burp.pmc;
import burp.puh;
import burp.qdh;
import burp.r9f;
import burp.tu;
import burp.wsd;
import burp.xgf;
import burp.xoh;
import burp.yib;
import burp.zkc;

public class StartBurp_v1 {
    
    public static htd a;
    
    public static boolean b;
    
    public static boolean c;
    
    private static final String[] z = new String[]
            {"apple.eawt.quitStrategy",
             "CLOSE_ALL_WINDOWS",
             "os.name",
             "true",
             "jdk.tls.allowUnsafeServerCertChange",
             "Burp Suite Professional",
             "mac",
             "com.apple.mrj.application.apple.menu.about.name"
            };
    
    public static void main(String[] args) {
        boolean flag = c;
        wsd.a(false);
        ieg.a(false);
        qdh qdh1 = new qdh(args);
        if(qdh1.e()) {
            r9f.a.a(new add(), new tu());
            xgf.a();
            String s2 = new zkc(qdh1.h()).a();
            xoh x3 = new ogg(s2).a();
            new duc(x3).a();
            if(!flag) {
                return;
            }
            yib.a = !yib.a;
        }
        //#109
        buc.a();
        String s2 = System.getProperty(z[2]);
        if( s2 != null && s2.toLowerCase().contains(z[6]) ) {
            System.setProperty(z[7],z[5]);
            System.setProperty(z[0],z[1]);
        } 
        //#170
        System.setProperty(z[4], z[3]);
        
        try {
            try {
                b = GraphicsEnvironment.isHeadless();
            } catch (Exception exception) {
                r9f.a(exception, cad.c);
            }
            ehg.a(); // check java.version
            l5d l5d1 = new l5d(b, qdh1);
            a = new h6b(l5d1);
            a.b();
            if (qdh1.f())
                System.out.println(lub.a(a.j()));
        } catch (b4g b4g1) {
            r9f.a(b4g1, cad.f);
            a.a(0);
        } catch (pmc pmc1) {
            r9f.a(pmc1, cad.c);
            System.err.println(mlh.a(pmc1));
            a.a(0);
        } catch (puh puh1) {
            r9f.a(puh1, cad.c);
            System.err.println(mlh.a(puh1));
            a.a(0);
        } catch (Throwable throwable) {
            r9f.a(throwable, cad.c);
            xgf.a();
            a.a(0);
        }
    }

}
