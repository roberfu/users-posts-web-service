package cl.springmachine.webservices.post;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cl.springmachine.webservices.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity(name = "post")
public class PostEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 2, message = "Description must have atleast 10 characters")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private UserEntity user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PostEntity [id=" + id + ", description=" + description + ", user=" + user + "]";
	}

	public PostEntity(Integer id, String description, UserEntity user) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
	}

	public PostEntity() {
		super();
	}
	
	
	
}
