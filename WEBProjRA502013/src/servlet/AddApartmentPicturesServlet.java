package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import controller.ContainerController;
import controller.ServletController;
import message.MessageGenerator;
import model.Amenity;
import model.Apartment;
import model.Location;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/AddApartmentPicturesServlet")
public class AddApartmentPicturesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
    
    public AddApartmentPicturesServlet() {
        super();
    }
	
	public void init() {
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		
		Location location = ServletController.createLocationFromSession(request.getSession());
		//ContainerController.locations.add(location);
		Apartment apartment = ServletController.createApartmentFromSessionAndLocation(request.getSession(), location);
		if(ContainerController.addLocationAndApartment(location, apartment)) {
			ServletController.createAmenityListForApartmentFromSession(apartment, request.getSession());
			//ContainerController.apartments.add(apartment);
			//ContainerController.apartmentPictureReferencePairings.put(apartment, new ArrayList<String>());
			
			if(!ServletFileUpload.isMultipartContent(request)){
				throw new ServletException("Content type is not multipart/form-data");
			}
			
			//response.setContentType("text/html");
			//PrintWriter out = response.getWriter();
			//out.write("<html><head></head><body>");
			try {
				List<FileItem> fileItemsList = uploader.parseRequest(request);
				Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
				int i = 0;
				String filename;
				while(fileItemsIterator.hasNext()){
					FileItem fileItem = fileItemsIterator.next();
					System.out.println("FieldName="+fileItem.getFieldName());
					System.out.println("FileName="+fileItem.getName());
					System.out.println("ContentType="+fileItem.getContentType());
					System.out.println("Size in bytes="+fileItem.getSize());
					File file;
					do
					{
						filename = apartment.getId() + "_" + i++;
						file = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + filename);
					} while(file.exists());
					System.out.println("Absolute Path at server="+file.getAbsolutePath());
					fileItem.write(file);
					apartment.getPictures().add(filename);
					//ContainerController.apartmentPictureReferencePairings.get(apartment).add(filename);
					/*
					out.write("File "+fileItem.getName()+ " uploaded successfully.");
					out.write("<br>");
					out.write("<a href=\"UploadDownloadFileServlet?fileName="+fileItem.getName()+"\">Download "+fileItem.getName()+"</a>");
					*/
				}
				ContainerController.saveApartmentList();
				ContainerController.saveLocationList();
				ContainerController.saveApartmentAmenitiyPairingList();
				ContainerController.saveApartmentPicturePairingList();
				ServletController.putSuccessMessageInSession(request, MessageGenerator.generateSuccessfulCreateMessage("apartment"));
				ServletController.forwardToHome(request, response);
			} catch (FileUploadException e) {
				//out.write("Exception in uploading file.");
				e.printStackTrace();
			} catch (Exception e) {
				//out.write("Exception in uploading file.");
				e.printStackTrace();
			}
		} else {
			ServletController.sendBadRequest(response, MessageGenerator.generateEntryAlreadyExistsMessage("Location and Apartment pair"));
		}
		//out.write("</body></html>");
	}
	
}
