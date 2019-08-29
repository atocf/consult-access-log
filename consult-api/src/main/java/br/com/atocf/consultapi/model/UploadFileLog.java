package br.com.atocf.consultapi.model;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UploadFileLog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id")
	private Long id;
	
	@Column(nullable=false, length=200) 
	@ApiModelProperty(notes = "File name")
    private String fileName;
	
	@Column(nullable=false, length=200) 
	@ApiModelProperty(notes = "File type")
    private String fileType;
	
	@Column(nullable=false) 
	@ApiModelProperty(notes = "File size")
    private long size;	
	
	@Column(nullable=false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP") 
	@ApiModelProperty(notes = "Upload date")
	private Date dt_upload;
    
    @Column(nullable=false, columnDefinition="boolean default false")
	@ApiModelProperty(notes = "Import status")
	private Boolean status;
    
    @Column(nullable=true, columnDefinition="TIMESTAMP") 
	@ApiModelProperty(notes = "Import date")
	private Date dt_import;
    
    @Column(nullable=true) 
	@ApiModelProperty(notes = "Qtd Success")
    private Integer qtd_success;
    
    @Column(nullable=true) 
	@ApiModelProperty(notes = "Qtd Error")
    private Integer qtd_error;	

    @ApiModelProperty(notes = "File")
    private byte[] data;

    public UploadFileLog(String fileName, String fileType, long size, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.size = size;
        this.status = false;
        this.dt_upload = Date.from(Instant.now());	
    }
}
