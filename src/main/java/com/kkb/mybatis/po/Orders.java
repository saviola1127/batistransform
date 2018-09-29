package com.kkb.mybatis.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Orders {

	@Id
	private Integer id;
	private Integer number ;
	private String note;
	private Date createtime;
}
