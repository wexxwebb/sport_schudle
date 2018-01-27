package services.impl;

import com.google.gson.Gson;
import common.Result;
import db.dao.training.TrainingDAO;
import db.pojo.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.TrainingService;

import static common.InsertType.NEW;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingDAO trainingDAO;

    public TrainingDAO getTrainingDAO() {
        return trainingDAO;
    }

    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public Result<String> addTraining(int userId, String date) {

        Training training = new Training(userId, date);
        Result<Training> result;
        if ((result = trainingDAO.insert(training, NEW)).isSuccess()) {
            Gson gson = new Gson();
            return new Result<>(gson.toJson(result.get(), Training.class), true, "Success");
        } else {
            return new Result<>(null, false, "Error");
        }
    }

    @Override
    public Result<String> delTraining(int id) {
        if (trainingDAO.delete(id).isSuccess()) {
            return new Result<>("1", true, "Success");
        }
        return new Result<>("0", false, "Success");
    }

    public Result<Training> getById(int id) {
        Result<Training> result;
        if ((result = trainingDAO.getByid(id)).isSuccess()) {
            return result;
        } else {
            return new Result<>(null, false, "Нет данных для отображения");
        }
    }
}
