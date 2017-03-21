package com.aka.entitys;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "lectures", schema = "public", catalog = "newdb")
public class LecturesEntity {
    private long id;
    private Timestamp startAt;
    private String name;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start_at")
    public Timestamp getStartAt() {
        return startAt;
    }

    public void setStartAt(Timestamp startAt) {
        this.startAt = startAt;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LecturesEntity that = (LecturesEntity) o;

        if (id != that.id) return false;
        if (startAt != null ? !startAt.equals(that.startAt) : that.startAt != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
