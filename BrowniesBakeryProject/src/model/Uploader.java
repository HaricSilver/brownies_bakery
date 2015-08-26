package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class Uploader implements Serializable {
	private static final long serialVersionUID = -6309268989240506709L;
	private UploadedFile uploadedFile;
	private String fileName;

	public Uploader() {
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void handleFileUpload() {
		System.out.println("upload file");
		try {
			File targetFolder = new File(
					"D:\\Git Repository\\brownies_bakery\\BrowniesBakeryProject\\WebContent\\images");

			this.fileName = System.currentTimeMillis() + "_"
					+ uploadedFile.getFileName();

			InputStream inputStream = uploadedFile.getInputstream();
			OutputStream out = new FileOutputStream(new File(targetFolder,
					this.fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// not use
	public void handleFileUpload(FileUploadEvent event) {
		try {
			File targetFolder = new File(
					"D:\\Git Repository\\brownies_bakery\\BrowniesBakeryProject\\WebContent\\images");
			InputStream inputStream = event.getFile().getInputstream();
			OutputStream out = new FileOutputStream(new File(targetFolder,
					event.getFile().getFileName()));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();

			this.fileName = event.getFile().getFileName();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
