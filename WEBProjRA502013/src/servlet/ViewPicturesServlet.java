package servlet;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ContainerController;
import controller.ServletController;
import message.MessageGenerator;
import model.Amenity;
import model.Apartment;
import model.ApartmentStatus;
import model.Comment;
import model.Location;
import model.Reservation;
import model.ReservationStatus;
import model.Role;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/ViewPicturesServlet")
public class ViewPicturesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ViewPicturesServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		ContainerController.populateLists();
		String id = request.getParameter("currentRow").toString();
		Apartment apartment = ContainerController.findApartmentById(Integer.parseInt(id));
		
		ArrayList<String> picNames = apartment.getPictures();
		
		ServletContext sc = getServletContext();
        OutputStream os = response.getOutputStream();
        
        byte[] imageInByte;
        ArrayList<File> files = new ArrayList<File>();
		for(String name : picNames) {
			File file = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + name);
			files.add(file);
			/*
	        try (InputStream is = new FileInputStream(file)) {
	
	            // it is the responsibility of the container to close output stream
	            
                response.setContentType("image/jpeg");
                
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
	            }
	        }
	        */
		}
		
		int imgHeight = 0;
		int imgWidth = 0;
		int imgType = 0;
		
		BufferedImage[] buffImages = new BufferedImage[files.size()];
		for (int i = 0; i < files.size(); i++) {
            buffImages[i] = ImageIO.read(files.get(i));
            imgHeight += buffImages[i].getHeight();
            if(buffImages[i].getWidth() > imgWidth)
            	imgWidth = buffImages[i].getWidth();
            imgType = buffImages[i].getType();
        }
		int currentHeight = 0;
        BufferedImage finalImg = new BufferedImage(imgWidth, imgHeight, imgType);
        for(int i = 0; i < files.size(); i++) {
            finalImg.createGraphics().drawImage(buffImages[i], 0, currentHeight, null);
            currentHeight+= buffImages[i].getHeight();
        }
		
        File file = new File(id+".jpg");
        ImageIO.write(finalImg, "jpeg", file);
        
        response.setContentType("image/jpeg");
        
        //os.write(imageBytes , 0, imageBytes.length);
        InputStream is= new FileInputStream(file);
        
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
		/*
		if(files.size() > 0) {
	    	InputStream is= new FileInputStream(files.get(0));
		    for (int i= 1; i < files.size(); i++) {
		    	is= new SequenceInputStream(is, new FileInputStream(files.get(i)));
		    }
		    
	        response.setContentType("image/jpeg");
	        
	        byte[] buffer = new byte[1024];
	        int bytesRead;
	        while ((bytesRead = is.read(buffer)) != -1) {
	            os.write(buffer, 0, bytesRead);
	        }
		}
		*/
    	/*
    	for(int i = 1; i < picNames.size(); i++) {
    		System.out.println(picNames.get(i));
			File file = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + picNames.get(i));
		    is = new SequenceInputStream(is, new FileInputStream(file));
	    }
	    */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
}
