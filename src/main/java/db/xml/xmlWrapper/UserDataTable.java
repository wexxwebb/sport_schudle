package db.xml.xmlWrapper;

import db.connectionManager.ConnectionManagerImpl;
import db.dao.user.UserDataDAO;
import db.dao.user.UserDataDAOImpl;
import db.pojo.UserData;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static common.InsertType.RESTORE;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDataTable extends Table {

    @XmlElement(name = "UserData")
    List<UserData> userDataList;

    @XmlTransient
    private UserDataDAO userDataDAO = new UserDataDAOImpl(ConnectionManagerImpl.getInstance());

    @XmlTransient
    private Logger logger = Logger.getLogger(UserData.class);

    public UserDataTable() {
    }

    public UserDataTable(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    public List<UserData> getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    @Override
    public Boolean call() throws Exception {
        for (UserData userData : userDataList) {
            if (userDataDAO.insert(userData, RESTORE).isSuccess()) {
                dispatch(userData.getId());
            }
        }
        logger.info("All rows inserted");
        return true;
    }
}
