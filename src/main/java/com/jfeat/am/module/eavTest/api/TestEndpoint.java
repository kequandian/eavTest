package com.jfeat.am.module.eavTest.api;


import com.jfeat.crud.plus.META;
import com.jfeat.am.core.jwt.JWTKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.am.module.eavTest.services.domain.dao.QueryTestDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.am.module.eavTest.api.permission.*;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.crud.plus.annotation.EavAnnotation;

import java.math.BigDecimal;

import com.jfeat.am.module.eavTest.services.domain.service.*;
import com.jfeat.am.module.eavTest.services.domain.model.TestRecord;
import com.jfeat.am.module.eavTest.services.gen.persistence.model.Test;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

/**
 * <p>
 * api
 * </p>
 *
 * @author Code generator
 * @since 2021-03-24
 */
@RestController

@Api("Test")
@RequestMapping("/api/crud/test/tests")
public class TestEndpoint {

    @Resource
    TestService testService;


    @Resource
    QueryTestDao queryTestDao;

    @BusinessLog(name = "Test", value = "create Test")
    @Permission(TestPermission.TEST_NEW)
    @PostMapping
    @ApiOperation(value = "新建 Test", response = Test.class)
    public Tip createTest(@RequestBody Test entity) {

        Integer affected = 0;
        try {
            affected = testService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Permission(TestPermission.TEST_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 Test", response = Test.class)
    public Tip getTest(@PathVariable Long id) {
        return SuccessTip.create(testService.queryMasterModel(queryTestDao, id));
    }

    @BusinessLog(name = "Test", value = "update Test")
    @Permission(TestPermission.TEST_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 Test", response = Test.class)
    public Tip updateTest(@PathVariable Long id, @RequestBody Test entity) {
        entity.setId(id);
        return SuccessTip.create(testService.updateMaster(entity));
    }

    @BusinessLog(name = "Test", value = "delete Test")
    @Permission(TestPermission.TEST_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 Test")
    public Tip deleteTest(@PathVariable Long id) {
        return SuccessTip.create(testService.deleteMaster(id));
    }

    @Permission(TestPermission.TEST_VIEW)
    @ApiOperation(value = "Test 列表信息", response = TestRecord.class)
    @GetMapping
    @EavAnnotation("Test")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "name", dataType = "String"),
            @ApiImplicitParam(name = "orgId", dataType = "Long"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryTests(Page<TestRecord> page,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                          @RequestParam(name = "search", required = false) String search,
                          @RequestParam(name = "id", required = false) Long id,

                          @RequestParam(name = "name", required = false) String name,

                          @RequestParam(name = "orgId", required = false) Long orgId,
                          @RequestParam(name = "orderBy", required = false) String orderBy,
                          @RequestParam(name = "sort", required = false) String sort) {

        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String pattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(pattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        TestRecord record = new TestRecord();
        record.setId(id);
        record.setName(name);
        if (META.enabledSaas()) {
            record.setOrgId(JWTKit.getOrgId());
        }


        List<TestRecord> testPage = queryTestDao.findTestPage(page, record, search, orderBy, null, null);

        page.setRecords(testPage);

        return SuccessTip.create(page);
    }
}
