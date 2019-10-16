package com.parse.email.Model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "email")
@EntityListeners(AuditingEntityListener.class)
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long emailid;

	@NotBlank
	private String to;

	@NotBlank
	private String from;

	@NotBlank
	private String subject;

	public Long getEmailid() {
		return emailid;
	}

	public void setEmailid(Long emailid) {
		this.emailid = emailid;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
