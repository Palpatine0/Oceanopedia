package com.oceanopedia.service;

import com.oceanopedia.vo.BaseResult;

public interface SearchService {
    BaseResult searchByKeyWord(String content);

    void esinit();
}
