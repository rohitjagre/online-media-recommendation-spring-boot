package com.app.tvshow.pojos.catchers;

public class Created_by {
	private String id;

	private String name;

	private String gender;

	private String profile_path;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfile_path() {
		return profile_path;
	}

	public void setProfile_path(String profile_path) {
		this.profile_path = profile_path;
	}

	@Override
	public String toString() {
		return "Created_by [id=" + id + ", name=" + name + ", gender=" + gender + ", profile_path=" + profile_path
				+ "]";
	}

}
