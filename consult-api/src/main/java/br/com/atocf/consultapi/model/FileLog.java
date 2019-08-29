package br.com.atocf.consultapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class FileLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id")
	private Long id;

	@Column(nullable = false, columnDefinition = "TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@ApiModelProperty(notes = "Date access")
	private Date dt_access;

	@Column(nullable = false, length = 400)
	@ApiModelProperty(notes = "IP")
	private String ip;

	@Column(nullable = false, length = 100)
	@ApiModelProperty(notes = "Method")
	private String request_method;

	@Column(nullable = false)
	@ApiModelProperty(notes = "Status")
	private int status;

	@Column(nullable = false, length = 400)
	@ApiModelProperty(notes = "User agent")
	private String user_agent;

	public FileLog(Date dt_access, String ip, String request_method, int status, String user_agent) {
		this.dt_access = dt_access;
		this.ip = ip;
		this.request_method = request_method;
		this.status = status;
		this.user_agent = user_agent;
	}
}
