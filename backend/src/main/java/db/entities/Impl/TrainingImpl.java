package db.entities.Impl;


import db.entities._inter.Training;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@XmlRootElement
@Entity(name = "Training")
@Table(name = "training")
public class TrainingImpl implements Training {

    private long id;
    private UserDataImpl userData;
    private Date createDate;
    private Date trainingDate;
    private Collection<ExerciseImpl> exerciseCollection = new ArrayList<>();

    public TrainingImpl() {
    }

    public TrainingImpl(long id) {
        this.id = id;
    }

    public TrainingImpl(long id, UserDataImpl userData, Date createDate, Date trainingDate) {
        this.id = id;
        this.userData = userData;
        this.createDate = createDate;
        this.trainingDate = trainingDate;
    }

    public TrainingImpl(UserDataImpl userData, Date createDate, Date trainingDate) {
        this.userData = userData;
        this.createDate = createDate;
        this.trainingDate = trainingDate;
    }

    @ManyToOne(fetch = EAGER)
    @Override
    public UserDataImpl getUserData() {
        return userData;
    }

    @Override
    public void setUserData(UserDataImpl userData) {
        this.userData = userData;
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "training_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "hibernateSeq")
    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getTrainingDate() {
        return trainingDate;
    }

    @Override
    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    @OneToMany(mappedBy = "training", fetch = LAZY)
    @Override
    public Collection<ExerciseImpl> getExerciseCollection() {
        return exerciseCollection;
    }

    @Override
    public void setExerciseCollection(Collection<ExerciseImpl> exerciseCollection) {
        this.exerciseCollection = exerciseCollection;
    }

    @Override
    public String toString() {
        return String.format("{\"id\": \"%d\", \"createDate\": \"%s\", \"trainingDate\": \"%s\"}",
                id, createDate, trainingDate);
    }
}
