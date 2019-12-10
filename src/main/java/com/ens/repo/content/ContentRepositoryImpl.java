package com.ens.repo.content;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ContentRepositoryImpl implements ContentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> getFileNames(String userId, String filePath) {
        return this.entityManager.createNativeQuery(
                "select file_name from content_info where user_id='" + userId + "' and file_path='"
                        + filePath + "'")
                .getResultList();
    }
}