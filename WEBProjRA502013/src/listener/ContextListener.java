package listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    public static ServletContext context;

    public static final String PATH_TO_IMAGES = "WEBProjRA502013/WebContent/images/apartmentImages";
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = sce.getServletContext();
    	//String rootPath = System.getProperty("catalina.home");
    	//String relativePath = context.getInitParameter("tempfile.dir");
    	//File file = new File(rootPath + File.separator + relativePath);
        String realPath = context.getRealPath(".");
		String cutPath = realPath.split(".metadata")[0];
    	File file = new File(cutPath + PATH_TO_IMAGES);
    	if(!file.exists()) file.mkdirs();
    	System.out.println("File Directory created to be used for storing files");
    	context.setAttribute("FILES_DIR_FILE", file);
    	context.setAttribute("FILES_DIR", cutPath + PATH_TO_IMAGES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        context = null;
    }

}
