import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings {
	static ApplicationContext ctx;

	@Override
	public void onStart(Application app)
	{
	    String springConfigurationName = "Beans.xml";
	    ctx = new ClassPathXmlApplicationContext(springConfigurationName);
	}

	@Override
	public <A> A getControllerInstance(Class<A> clazz)
	{
	    return ctx.getBean(clazz);
	}

}
