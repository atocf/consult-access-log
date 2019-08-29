package br.com.atocf.consultapi.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UploadFileLogResponse {

	private Long id;
	
	private String fileName;
	
	private String fileDownloadUri;
	
	private String fileType;
	
	private long size;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date dt_upload;

	public UploadFileLogResponse(Long id, String fileName, String fileDownloadUri, String fileType, long size,
			Date dt_upload) {
		this.id = id;
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
		this.dt_upload = dt_upload;
	}
}
