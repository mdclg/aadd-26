package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {
    private static EntityManagerFactory entityManagerFactory;

    private static final ThreadLocal<EntityManager> entityManagerHolder;


    static {    

        entityManagerFactory = Persistence.createEntityManagerFactory("editoriales");

        entityManagerHolder = new ThreadLocal<EntityManager>();

    }


    public static EntityManager getEntityManager() {

        EntityManager entityManager = entityManagerHolder.get();

        if (entityManager == null || !entityManager.isOpen()) {

            entityManager = entityManagerFactory.createEntityManager();

            entityManagerHolder.set(entityManager);

        }

        return entityManager;

    }


    public static void closeEntityManager() {

        EntityManager entityManager = entityManagerHolder.get();

        if (entityManager != null) {

            entityManagerHolder.set(null);

            entityManager.close();

        }

    }
}
