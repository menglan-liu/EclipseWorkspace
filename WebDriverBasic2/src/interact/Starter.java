package interact;


public class Starter {
	public static void main(String[] args) {
		
		SelectDateandTime st = new SelectDateandTime();
		st.testDemosite();
		
		mousekeyboad mk = new mousekeyboad();
		mk.MKTest();
		
		seriesmousemove mv = new seriesmousemove();
		mv.SMTest();
		
		upload up = new upload();
		up.UpTest();
		
		Demosite ds = new Demosite("http://demo.guru99.com/test/login.html");
		ds.testDemosite();
		System.out.println(ds.siteName);
		ds.siteName = "gaga";
		System.out.println(ds.siteName);
		for2 loopprint = new for2();
		loopprint.for2();
	}
}
