package com.xusm.forum.service;

import com.xusm.forum.vo.SourceResult;
import com.xusm.forum.vo.SourceResponse;

import java.util.Map;

public interface SourceService {
//    SourcePageResult<SourceResponse>querySourceList(Integer page, Integer row);

    SourceResult<SourceResponse> querySourceList( );

    Map<String,Object> querySourceDetail(Long id);

    void updateSourceClick(Long id,Integer count);

    Boolean selectPay(Long id);
}
