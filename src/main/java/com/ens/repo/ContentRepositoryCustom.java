package com.ens.repo;

import java.util.List;

public interface ContentRepositoryCustom {

    List<String> getFileNames(String userId, String filePath);

}
