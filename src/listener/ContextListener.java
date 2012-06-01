package listener;


import hibernate.TribusHibernateSessionFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Session;


public class ContextListener implements ServletContextListener {

	

	public static String SYSTEM_ALIAS = "tribus";

	private static String DEFAULT_FILTER_KEY = null;
	
	private String getSystemAlias( ServletContextEvent arg0 ) {
		try {
			ServletContext ctx = arg0.getServletContext( );
			return ContextListener.getContextParameter( ctx, "SystemAlias" );
		} catch ( Exception e ) {
			return null;
		}

	}

	public static String getContextParameter( ServletContext ctx, String name ) {
		String value = ctx.getInitParameter( name );
		if ( value != null ) {
			value = value.trim( );
		}
		return value;
	}

	private static void setDefaultFilterKey( ServletContextEvent arg0 ) {
		try {
			ServletContext ctx = arg0.getServletContext( );
			ContextListener.DEFAULT_FILTER_KEY = getContextParameter( ctx, "DefaultFilterKey" );			
//			cat.info( "load DefaultFilterKey from Context: " + ContextListener.DEFAULT_FILTER_KEY );
		} catch ( Exception e ) {
			ContextListener.DEFAULT_FILTER_KEY = null;
		}
	}

	public static String getDefaultFilterKey( ) {
		return ContextListener.DEFAULT_FILTER_KEY;
	}

	/*
	 * initialize. create instance of system<br><br> if you want to convert
	 * the encoding before insert to DB, you should put config like this:<br>
	 * <pre> &lt;charset&gt; &lt;env&gt;ISO-8859-1&lt;/env&gt;
	 * &lt;db&gt;GBK&lt;/db&gt; &lt;/charset&gt; </pre>
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized( ServletContextEvent arg0 ) {
		try {
			
			ServletContext ctx = arg0.getServletContext( );
			
			String sa = this.getSystemAlias( arg0 );
			if ( sa != null && sa.length( ) > 0 ) {
				ContextListener.SYSTEM_ALIAS = sa;
			}

			/**
			 * initial the log4j properties.
			 */				
			ContextListener.setDefaultFilterKey( arg0 ); 
			Session s = TribusHibernateSessionFactory.currentSession();									 
			String flag = getContextParameter(ctx, "serverOrLocal");			
			String domain = getContextParameter(ctx,"domainName");
			
			
			
			System.out.println("*************DB status is" + s.isConnected() + " ************ ");
			System.out.println("-----Now is on the "+ flag +"-----");
			System.out.println("-----And the --"+domain+"------");
			
			/**
			 * initial the application configuration.
			 */ 
			String configFileName = ContextListener.SYSTEM_ALIAS + ".application.xml";
			String c1onfigFileName = ContextListener.SYSTEM_ALIAS + ".application.xml";
			try {
//				UserManager um = UserManager.getInstance( );
//				factory.addManager( um );
			} catch ( Exception e ) {
				e.printStackTrace( );
			}




		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	/*
	 * destroy.
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed( ServletContextEvent arg0 ) {
//		cat.info( "\n*********************************************************************************************************\n"
//				+ "*********************************************************************************************************\n"
//				+ "                  " + SYSTEM_ALIAS.toUpperCase( ) + " System Destroyed At  " + StringUtils.formatDate( new Date( ) )
//				+ "\n" + "*********************************************************************************************************\n"
//				+ "*********************************************************************************************************\n" );
	}

}
