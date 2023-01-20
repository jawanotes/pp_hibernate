package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

//import java.lang.reflect.Field;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private final String tableName = User.class.getSimpleName();
    public UserDaoHibernateImpl() {

    }

    /***
     * Nikita Nesterenko:
     * final Transaction transaction = session.getTransaction(); транзакция открывается до эксекют апдейт
     *
     * Konstantin Harin:
     * session.beginTransaction(); // Открывает транзакцию
     * session.getTransaction(); // Не открывает тр-ю, а возвращает имеющийся экземпляр транзакции
     * т.е. getTransaction() - это геттер и ошибки нет. Пример ниже демонстрирует это:
     * // autocommit = false
     *
     * final Transaction transaction = session.beginTransaction(); // transaction status ACTIVE
     * session.createSQLQuery(str).executeUpdate();
     * final Transaction transaction2 = session.getTransaction(); // transaction2 status ACTIVE
     * boolean theyAreEquals = (transaction == transaction2) ? true : false; // TRUE
     * transaction.commit(); // transaction status COMMITTED, transaction2 status COMMITTED
     */
    @Override
    public void createUsersTable() {

/*        final Field[] userFields = User.class.getDeclaredFields();
        final String idField = userFields[0].getName();
        final String nameField = userFields[1].getName();
        final String lastnameField = userFields[2].getName();
        final String ageField = userFields[3].getName();*/

        final String idField = "id";
        final String nameField = "name";
        final String lastnameField = "lastName";
        final String ageField = "age";

        String str = "CREATE TABLE IF NOT EXISTS " + tableName +
                " (" +
                idField + " BIGINT NOT NULL auto_increment, " +
                nameField + " VARCHAR(10), " +
                lastnameField + " VARCHAR(10), " +
                ageField + " TINYINT," +
                "PRIMARY KEY (id)) DEFAULT CHARSET=utf8mb4";
                //ageField + " TINYINT) ENGINE=InnoDB   DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT";

        try (org.hibernate.Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            //System.out.println("Transaction status " + transaction.getStatus().name());
            session.createSQLQuery(str).executeUpdate();
/*            final Transaction transaction2 = session.getTransaction();
            System.out.println("Transaction2 status " + transaction2.getStatus().name());
            System.out.println("Is it's the same transaction? " + (transaction == transaction2 ? "true" : "false"));*/
            transaction.commit();
/*            System.out.println("Transaction status " + transaction.getStatus().name());
            System.out.println("Transaction2 status " + transaction2.getStatus().name());*/
        }
    }

    @Override
    public void dropUsersTable() {
        String str = "DROP TABLE IF EXISTS " + tableName;

        try (org.hibernate.Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.createSQLQuery(str).executeUpdate();
            //final Transaction transaction = session.getTransaction();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        try (org.hibernate.Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            //session.save(user);
            //final Transaction transaction = session.getTransaction();
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        User user = new User();

        user.setId(id);
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.delete(user);
            //final Transaction transaction = session.getTransaction();
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;

        try (org.hibernate.Session session = sessionFactory.openSession()) {
            //session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            criteria.from(User.class);
            userList = session.createQuery(criteria).getResultList();

            //final Transaction transaction = session.getTransaction();
            //transaction.commit();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            //final Transaction transaction = session.getTransaction();
            transaction.commit();
        }
    }
}
