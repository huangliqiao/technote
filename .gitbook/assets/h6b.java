package burp;
import java.io.File;
import java.security.Security;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class h6b implements htd, stf{
    
    private final cbf g;
    private final List<euf> p = new ArrayList();
    private final wie u;
    private final ibb a;
    private final zoc e;
    private final gmf s;
    private final mch k;
    private final qbe t;
    private final bu m;
    private final zec o;
    private final xx b;
    private final File n;
    private final r l;
    private final zcf j;
    private final mnh q;
    private final jjf h;
    private whd d;
    private ArrayList c;
    private Object f = null;
    private final r i;
    private chg r;
    private static final String[] z =
        {
            "Temporary Project",
            "tempDirPendingDeletion",
            "jdk.certpath.disabledAlgorithms",
            "false",
            "disablesniextension",
            "jdk.tls.disabledAlgorithms",
            "enableblockedsslalgorithms",
            "jsse.enableSNIExtension",
            "true",
            "java.io.tmpdir",
            "user_options",
            "tempDir",
            "This will close all Burp projects and shutdown - are you sure you want to exit?"
        };
    
    public h6b(cbf paramcbf) throws pmc {
        int i1 = wzb.a;
        this.g = paramcbf;
        this.q = paramcbf.g();
        this.o = new zec(this.q);
        this.a = new vk(egb.a);
        this.k = new mch(this.a);
        boolean bool = _b();
        if (bool)
            a(this.a);
        opc.a(opc.a((bool) ? this.a : null), opc.b((bool) ? this.a : null));
        this.h = new jjf();
        n5e localn5e = new n5e();
        stb localstb = new stb(z[1], this.a);
        List localList = localstb.a();
        String str = _j();
        lib.a(localList);
        localList.clear();
        localstb.a(localList);
        this.n = b(paramcbf.d(), str);
        lib.b(this);
        mzc localmzc = paramcbf.d();
        this.m = new bu(paramcbf.h(), this.q);
        this.i = burp.r.a(this, localmzc, egb.a);
        upc localupc = new upc(paramcbf.b(), this.i, hm.d);
        this.l = burp.r.a(this, localmzc, egb.a);
        edg localedg = localupc.a();
        this.r = new chg(new h2e(), localedg);
        e4 locale4 = new e4(this.r, localedg, xuf.a);
        m4b localm4b = new m4b(this.a, xuf.a);
        this.u = new lec(localn5e, new geb(), localm4b);
        this.u.a(localm4b);
        this.u.a(locale4);
        ehd localehd = new ehd(this.u);
        localm4b.a(localehd);
        w localw = new w(this.u, localedg);
        this.s = new x3d(this.u, paramcbf, localedg, locale4, this.r, new n1g(this.u, paramcbf, localw), localw);
        g();
        this.e = new zoc(paramcbf, this.u, this.s, egb.a, this.q);
        wfc localwfc = new wfc(this.r, this.e);
        this.t = new fwg(this.k, this.u, localwfc).a();
        this.b = new xx(this);
        this.j = new zcf(paramcbf, this, localm4b);
        if (i1 == 0)
            return;
        yib.a = !(yib.a);
    }
    private String _j() {
        String str = this.a.a(z[11]);
        if (str == null)
            str = eid.b.a(z[11]);
        return str;
    }
    private void _a(ibb paramibb) {
        if (!(z[3].equals(paramibb.a(z[6])))) {
            Security.setProperty(z[2], "");
            Security.setProperty(z[5], "");
        }
        if (!(z[8].equals(paramibb.a(z[4]))))
            return;
        System.setProperty(z[7], z[3]);
    }
    public void b() throws b4g, pmc, puh {
        _a();
        if (!(StartBurp.b)) {
            _h();
            xgf.a(this);
        }
        _d();
        this.b.a();
        this.o.a(); /* delete file popup */
    }
    public void a(int paramInt) {
        this.t.a(2000);
        System.exit(paramInt);
    }
    private void _d() throws pmc, puh {
        int i2 = wzb.a;
        vig localvig = new vig();
        int i1 = 0;
        Object localObject1 = this.g.e().i().iterator();
        Object localObject2;
        Object localObject3;
        do {
            if (!(((Iterator) localObject1).hasNext()))
                break;
            localObject2 = (String) ((Iterator) localObject1).next();
            localObject3 = new File((String) localObject2);
            if ((!(((File) localObject3).exists())) || (!(((File) localObject3).canRead())))
                throw new puh(vae.D, ((File) localObject3).getAbsolutePath());
            i1 = 1;
            localvig.b((File) localObject3);
        } while (i2 == 0);
        if (this.g.e().a()) {
            localObject1 = this.g.e().d();
            localObject2 = new File((String) localObject1);
            label188 : {
                if (((File) localObject2).exists()) {
                localvig.a((File) localObject2);
                if (i2 == 0)
                    break label188;
            }
            localObject2 = new File(dhd.a((String) localObject1));
            localvig.c((File) localObject2);
            }
            localvig.a(((File) localObject2).getName());
            localvig.a(!(StartBurp.b));
            localvig.b((StartBurp.b) && (i1 == 0));
            localObject3 = this.j.a(localvig.a(), new tth(this.d), this.h);
            if (localObject3 == null)
                a(false);
            this.p.add((euf)localObject3);
        } else if (StartBurp.b) {
            localObject1 = localvig.c(true).a(z[0]).b(true).a();
            this.p.add(this.j.a((clh) localObject1, new ekh(), this.h));
        } else {
            a(localvig.a(), this.a);
        }
    }
    private void a(clh paramclh, ibb paramibb) {
        new vne(this, this.g, this, this.d, this.j, paramclh, paramibb, this.h);
    }
    public cbf f() {
        return this.g;
    }
    public qbe c() {
        return this.t;
    }
    public zoc m() {
        return this.e;
    }
    public mch j() {
        return this.k;
    }
    public ibb o() {
        return this.a;
    }
    public gmf i() {
        return this.s;
    }
    public void a(long paramLong1, long paramLong2, Runnable paramRunnable) {
        this.m.a(paramLong1, paramLong2, paramRunnable);
    }
    public void a(Runnable paramRunnable) {
        this.o.a(paramRunnable);
    }
    public sse l() {
        if (this.p.isEmpty())
            return null;
        return ((euf) this.p.get(0)).e();
    }
    public wie d() {
        return this.u;
    }
    public void e() {
        int i1 = wzb.a;
        Iterator<euf> localIterator = this.p.iterator();
        do {
            if (!(localIterator.hasNext()))
                return;
            euf localeuf = (euf) localIterator.next();
            localeuf.c();
        } while (i1 == 0);
    }
    public xx k() {
        return this.b;
    }
    public r n() {
        return this.l;
    }
    public void a(boolean paramBoolean) {
        int i1 = wzb.a;
        if ((paramBoolean) && (!(StartBurp.b)) && (!(oed.b(l(), z[12]))))
            return;
        e();
        Iterator<euf> localIterator = this.p.iterator();
        do {
            if (!(localIterator.hasNext()))
                break;
            euf localeuf = (euf) localIterator.next();
            localeuf.a(false);
        } while (i1 == 0);
        this.l.a(this.a);
        this.i.a(this.a);
        a(0);
    }
    public void a(euf parameuf) {
        this.p.remove(parameuf);
        if (!(this.p.isEmpty()))
            return;
        a(false);
    }
    public File g() {
        return this.n;
    }
    private void _g() {
        if (_b()) {
            this.u.d(this.a);
            if (wzb.a == 0)
                return;
        }
        this.u.a((moe)this.h.a(new String[]{z[10]}));
    }
    private boolean _b() {
        return (!(this.g.e().g()));
    }
    private void _a() throws b4g {
        this.d = new oyg().a(this);
        _i();
        _f();
        _c();
    }
    private void _h() {
    }
    private void _f() {
        vsg.a(this, this.r, this.q);
        this.m.a(30000L, 86400000L, new x6e(this));
    }
    private void _c() {
        this.m.a(900000L, 900000L, new ded(this, this.q));
    }
    private void _i() throws b4g {
        if (cnc.a(this))
            return;
        throw new b4g();
    }
    private File b(mzc parammzc, String paramString) {
        File localFile = a(parammzc, paramString);
        if (localFile != null)
            return localFile;
        return parammzc.a(System.getProperty(z[9]));
    }
    private File a(mzc parammzc, String paramString) {
        File localFile = null;
        try {
            if ((paramString != null) && (!(paramString.isEmpty()))) {
                localFile = parammzc.a(paramString);
                if (!(localFile.exists()))
                    localFile.mkdir();
                if ((!(localFile.isDirectory())) || (!(localFile.canWrite())))
                    localFile = null;
            }
        } catch (Exception localException) {
            r9f.a(localException, cad.c);
            localFile = null;
        }
        return localFile;
    }
    public whd h() {
        return this.d;
    }
    public void a(whd paramwhd) {
        int i1 = wzb.a;
        this.d = paramwhd;
        Iterator localIterator = this.p.iterator();
        do {
            if (!(localIterator.hasNext()))
                return;
            euf localeuf = (euf) localIterator.next();
            localeuf.a();
        } while (i1 == 0);
    }
    public void a(Object paramObject) {
        this.f = paramObject;
    }
    public ArrayList a() {
        return this.c;
    }
    public void b(Object paramObject) {
        this.c = ((ArrayList) paramObject);
    }
    public void b(euf parameuf) {
        this.p.add(parameuf);
    }
    static chg a(h6b paramh6b) {
        return paramh6b.r;
    }
    static mnh b(h6b paramh6b) {
        return paramh6b.q;
    }
}
