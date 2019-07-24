package ua.lviv.lgs.domain;

import java.util.Date;

public class Subscription {

	private int id;
	private int userId;
	private int magazineId;
	private Date releaseDate;

	public Subscription(int id, int userId, int magazineId, Date releaseDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.magazineId = magazineId;
		this.releaseDate = releaseDate;
	}

	public Subscription(int userId, int magazineId, Date releaseDate) {
		super();
		this.userId = userId;
		this.magazineId = magazineId;
		this.releaseDate = releaseDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMagazineId() {
		return magazineId;
	}

	public void setMagazineId(int magazineId) {
		this.magazineId = magazineId;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + magazineId;
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscription other = (Subscription) obj;
		if (id != other.id)
			return false;
		if (magazineId != other.magazineId)
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", userId=" + userId + ", magazineId=" + magazineId + ", releaseDate="
				+ releaseDate + "]";
	}

}
