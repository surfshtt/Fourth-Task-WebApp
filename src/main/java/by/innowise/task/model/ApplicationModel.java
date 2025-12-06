package by.innowise.task.model;

import java.util.Map;
import java.util.Objects;

public class ApplicationModel extends BaseModel {

    public enum Status {
        SUBMITTED,
        ACCEPTED,
        REJECTED
    }

    private long userId;
    private String facultyName;
    private int diplomaScore;
    private String description;
    private String mobilePhone;
    private String email;
    private Status status;

    public long getUserId() {
        return userId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public int getDiplomaScore() {
        return diplomaScore;
    }

    public String getDescription() {
        return description;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public void setDiplomaScore(int diplomaScore) {
        this.diplomaScore = diplomaScore;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "id:" + getId() + "\n" +
                "userId:" + userId + "\n" +
                "facultyName:" + facultyName + "\n" +
                "diplomaScore:" + diplomaScore + "\n" +
                "description:" + description + "\n" +
                "mobilePhone:" + mobilePhone + "\n" +
                "email:" + email + "\n" +
                "status:" + status;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof ApplicationModel other)) {
            return false;
        }

        if (this == object) {
            return true;
        }

        return this.getId() == other.getId() &&
                this.userId == other.userId &&
                this.diplomaScore == other.diplomaScore &&
                Objects.equals(this.facultyName, other.facultyName) &&
                Objects.equals(this.description, other.description) &&
                Objects.equals(this.mobilePhone, other.mobilePhone) &&
                Objects.equals(this.email, other.email) &&
                this.status == other.status;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = prime * result + Long.hashCode(getId());
        result = prime * result + Long.hashCode(userId);
        result = prime * result + Integer.hashCode(diplomaScore);
        result = prime * result + (facultyName == null ? 0 : facultyName.hashCode());
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (mobilePhone == null ? 0 : mobilePhone.hashCode());
        result = prime * result + (email == null ? 0 : email.hashCode());
        result = prime * result + (status == null ? 0 : status.hashCode());

        return result;
    }
}
