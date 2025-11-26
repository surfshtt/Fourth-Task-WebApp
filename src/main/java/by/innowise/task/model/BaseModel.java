package by.innowise.task.model;

public abstract class BaseModel {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("id: ").append(id);

        return builder.toString();
    }

    @Override
    public boolean equals(Object object) {
        if(object == null){
            return false;
        }

        if(!(object instanceof BaseModel other)){
            return false;
        }

        if(object == this){
            return true;
        }

        return other.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
