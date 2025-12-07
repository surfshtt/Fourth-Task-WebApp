package by.innowise.task.model;

import java.util.Objects;

public class ApplicationModel extends BaseModel {

    public enum Status {
        SUBMITTED,
        ACCEPTED,
        REJECTED
    }

    private long userId;
    private String facultyName;
    private float diplomaScore;
    private String description;
    private String mobilePhone;
    private String fio;
    private Status status;

    public long getUserId() {
        return userId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public float getDiplomaScore() {
        return diplomaScore;
    }

    public String getDescription() {
        return description;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getFio() {
        return fio;
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

    public void setDiplomaScore(float diplomaScore) {
        this.diplomaScore = diplomaScore;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setFio(String fio) {
        this.fio = fio;
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
                "fio:" + fio + "\n" +
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
                Objects.equals(this.fio, other.fio) &&
                this.status == other.status;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = prime * result + Long.hashCode(getId());
        result = prime * result + Long.hashCode(userId);
        result = prime * result + Float.hashCode(diplomaScore);
        result = prime * result + (facultyName == null ? 0 : facultyName.hashCode());
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (mobilePhone == null ? 0 : mobilePhone.hashCode());
        result = prime * result + (fio == null ? 0 : fio.hashCode());
        result = prime * result + (status == null ? 0 : status.hashCode());

        return result;
    }
}
