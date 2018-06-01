package accounts;

import accounts.dbService.DBException;
import accounts.dbService.DBService;
import accounts.dbService.dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class AccountService {
    private final DBService profiles;
    private final Map<String, Long> sessionIdToUserId;

    public AccountService() {
        profiles = new DBService();
        sessionIdToUserId = new HashMap<>();
    }

    public long addNewUser(String login, String password, String email) throws DBException {
        return profiles.addUser(login, password, email);
    }

    public UsersDataSet getUserByLogin(String login) throws DBException {
        return profiles.getUser(login);
    }

    public UsersDataSet getUserByUserId(Long id) throws DBException {
        return profiles.getUser(id);
    }

    public UsersDataSet getUserBySessionId(String sessionId) throws DBException {
        return profiles.getUser(sessionIdToUserId.get(sessionId));
    }

    public void addSession(String sessionId, long userId) {
        sessionIdToUserId.put(sessionId, userId);
    }

    public void deleteSession(String sessionId) {
        sessionIdToUserId.remove(sessionId);
    }
}
