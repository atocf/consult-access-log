package br.com.atocf.consultapi.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.atocf.consultapi.model.FileLog;
import br.com.atocf.consultapi.repository.FileLogRepository;
import br.com.atocf.consultapi.service.FileLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/accesslog")
@Api(tags = "Log", description = "Endpoint´s para atualizar, buscar, criar e deletar log.")
public class FileLogController {

	@Autowired
	private FileLogRepository repository;
	
	@Autowired
	private FileLogService service;

	@GetMapping
	@ApiOperation("Retorna a lista de log.")
	public Page<?> findAll(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "50") int size,
			@RequestParam(value = "ip", required = false) String ip,
			@RequestParam(value = "dt_de", required = false) Date dt_de,
			@RequestParam(value = "dt_ate", required = false) Date dt_ate) {
		return service.findAll(page, size, ip, dt_de, dt_ate);
	}

	@GetMapping(path = { "/{id}" })
	@ApiOperation("Retorna o log pelo {id}.")
	public ResponseEntity<?> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ApiOperation("Cria um novo log.")
	public ResponseEntity<?> create(@RequestBody FileLog accessLog) {
		return new ResponseEntity<FileLog>(repository.save(accessLog), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	@ApiOperation("Atualiza um log pelo {id}.")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody FileLog accessLog) {
		return repository.findById(id).map(record -> {
			record.setDt_access(accessLog.getDt_access());
			record.setIp(accessLog.getIp());
			record.setRequest_method(accessLog.getRequest_method());
			record.setStatus(accessLog.getStatus());
			record.setUser_agent(accessLog.getUser_agent());
			FileLog updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	@ApiOperation("Deleta um log pelo {id}.")
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
