package br.com.atocf.consultapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.atocf.consultapi.model.UploadFileLog;
import br.com.atocf.consultapi.response.UploadFileLogResponse;
import br.com.atocf.consultapi.service.UploadFileLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Upload e download de log", description = "Endpoint´s para upload e download de log.")
public class UploadFileLogController {

	@Autowired
	private UploadFileLogService service;
	
	@PostMapping("/upload")
	@ApiOperation("Upload de um unico arquivo.")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		UploadFileLog uploadAccessLog = service.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
				.path(uploadAccessLog.getId().toString()).toUriString();

		return new ResponseEntity<UploadFileLogResponse>(new UploadFileLogResponse(uploadAccessLog.getId(), uploadAccessLog.getFileName(), fileDownloadUri,
				file.getContentType(), file.getSize(), uploadAccessLog.getDt_upload()), HttpStatus.CREATED);
	}

	@GetMapping("/download/{id}")
	@ApiOperation("Download de arquivos pelo id.")
	public ResponseEntity<Resource> downloadFile(@PathVariable long id) {
		UploadFileLog uploadAccessLog = service.getFile(id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(uploadAccessLog.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + uploadAccessLog.getFileName() + "\"")
				.body(new ByteArrayResource(uploadAccessLog.getData()));
	}

}
