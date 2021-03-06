package consoleTest.persist;

import common.Result;
import db.dao.state.StateDAO;
import db.dao.state.StateDAOImpl;
import db.pojo.State;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

public class TestState {
    public static void main(String[] args) {
        StateDAO stateDAO = new StateDAOImpl(
                ConnectionManagerImpl.getInstance()
        );

        {
            State state = new State("normal");
            Result<String> result;
            if ((result = stateDAO.insert(state, NEW)).isSuccess()) {
                System.out.println(result.get());
            } else {
                System.out.println(result.getMessage());
            }
        }

        {
            State state = new State(25, "blocked");
            Result<String> result;
            if ((result = stateDAO.insert(state, RESTORE)).isSuccess()) {
                System.out.println(result.get());
            } else {
                System.out.println(result.getMessage());
            }
        }

        Result<List<State>> result = stateDAO.getAll();
        if (result != null && result.isSuccess()) {
            for (State state : result.get()) {
                System.out.println(state.getId() + " " + state.getState());
            }
        } else {
            System.out.println(result.getMessage());
        }

    }
}
