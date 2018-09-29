package com.kkb.mybatis.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class User {

	@Id
	private int id;
	private String name;

	private String email;
	private String password;
	
}
