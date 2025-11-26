package by.innowise.task.model.application;

import by.innowise.task.model.BaseModel;

import java.util.Map;

public class ApplicationModel extends BaseModel {

    public enum Status {
        SUBMITTED,
        ACCEPTED,
        REJECTED
    }

    private long userId;
    private long facultyId;
    private Map<String, Integer> subjectScores;
    private int diplomaScore;
    private String description;
    private Status status;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public Map<String, Integer> getSubjectScores() {
        return subjectScores;
    }

    public void setSubjectScores(Map<String, Integer> subjectScores) {
        this.subjectScores = subjectScores;
    }

    public int getDiplomaScore() {
        return diplomaScore;
    }

    public void setDiplomaScore(int diplomaScore) {
        this.diplomaScore = diplomaScore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "id:" + getId() + "\n" +
                "userId:" + userId + "\n" +
                "facultyId:" + facultyId + "\n" +
                "diplomaScore:" + diplomaScore + "\n" +
                "status:" + status;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null){
            return false;
        }

        if(!(object instanceof ApplicationModel other)){
            return false;
        }

        if (this == object) {
            return true;
        }

        return this.getId() == other.getId() &&
                this.userId == other.userId &&
                this.facultyId == other.facultyId &&
                this.diplomaScore == other.diplomaScore &&
                this.status == other.status;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = prime * result + Long.hashCode(getId());
        result = prime * result + Long.hashCode(userId);
        result = prime * result + Long.hashCode(facultyId);
        result = prime * result + Integer.hashCode(diplomaScore);
        result = prime * result + (status == null ? 0 : status.hashCode());

        return result;
    }
}
