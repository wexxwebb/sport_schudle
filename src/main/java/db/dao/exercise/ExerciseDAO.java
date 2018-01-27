package db.dao.exercise;

import common.InsertType;
import common.Result;
import db.pojo.Exercise;

import java.util.List;

public interface ExerciseDAO {

    Result<List<Exercise>> getAll();

    Result<Exercise> insert(Exercise exercise, InsertType insertType);

    Result<List<Exercise>> getByTrainingId(int trainingId);
}