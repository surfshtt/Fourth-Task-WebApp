package by.innowise.task.model.application;

import by.innowise.task.model.BaseModel;

import java.util.Objects;

public class ApplicationSubjectModel extends BaseModel {

    private long applicationId;
    private String subjectName;
    private int score;

    public long getApplicationId() {
        return applicationId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getScore() {
        return score;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "id:" + getId() + "\n" +
                "applicationId:" + applicationId + "\n" +
                "subjectName:" + subjectName + "\n" +
                "score:" + score;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null){
            return false;
        }

        if( !(object instanceof ApplicationSubjectModel other)){
            return false;
        }

        if (this == object) return true;

        return this.getId() == other.getId() &&
                this.applicationId == other.applicationId &&
                Objects.equals(this.subjectName, other.subjectName) &&
                this.score == other.score;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = prime * result + Long.hashCode(getId());
        result = prime * result + Long.hashCode(applicationId);
        result = prime * result + (subjectName == null ? 0 : subjectName.hashCode());
        result = prime * result + Integer.hashCode(score);

        return result;
    }
}
