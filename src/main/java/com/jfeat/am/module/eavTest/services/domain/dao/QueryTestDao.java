package com.jfeat.am.module.eavTest.services.domain.dao;

import com.jfeat.am.module.eavTest.services.domain.model.TestRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.am.module.eavTest.services.gen.persistence.model.Test;
import com.jfeat.am.module.eavTest.services.gen.crud.model.TestModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2021-03-24
 */
public interface QueryTestDao extends QueryMasterDao<Test> {
   /*
    * Query entity list by page
    */
    List<TestRecord> findTestPage(Page<TestRecord> page, @Param("record") TestRecord record,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    TestModel queryMasterModel(@Param("id") Long id);
}