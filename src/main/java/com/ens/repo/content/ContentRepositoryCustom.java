package com.ens.repo.content;

import java.util.List;

public interface ContentRepositoryCustom {

    List<String> getFileNames(String userId, String filePath);

}
