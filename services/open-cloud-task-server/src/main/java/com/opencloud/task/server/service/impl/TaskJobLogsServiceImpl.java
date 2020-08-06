package com.opencloud.task.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opencloud.common.model.PageParams;
import com.opencloud.task.client.model.entity.TaskJobLogs;
import com.opencloud.task.server.mapper.SchedulerJobLogsMapper;
import com.opencloud.task.server.service.TaskJobLogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author liuyadu
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TaskJobLogsServiceImpl implements TaskJobLogsService {
    @Autowired
    private SchedulerJobLogsMapper schedulerJobLogsMapper;
    /**
     * 分页查询
     *
     * @param pageParams
     * @return
     */
    @Override
    public IPage<TaskJobLogs> findListPage(PageParams pageParams) {
        TaskJobLogs query = pageParams.mapToObject(TaskJobLogs.class);
        QueryWrapper<TaskJobLogs> queryWrapper = new QueryWrapper();
        queryWrapper.lambda()
                .likeRight(ObjectUtils.isNotEmpty(query.getJobName()), TaskJobLogs::getJobName, query.getJobName());
        queryWrapper.orderByDesc("create_time");
        return schedulerJobLogsMapper.selectPage(new Page(pageParams.getPage(),pageParams.getLimit()),queryWrapper);
    }

    /**
     * 添加日志
     *
     * @param log
     */
    @Override
    public void addLog(TaskJobLogs log) {
        schedulerJobLogsMapper.insert(log);
    }

    /**
     * 更细日志
     *
     * @param log
     */
    @Override
    public void modifyLog(TaskJobLogs log) {
        schedulerJobLogsMapper.updateById(log);
    }

    /**
     * 根据主键获取日志
     *
     * @param logId
     * @return
     */
    @Override
    public TaskJobLogs getLog(String logId) {
        return schedulerJobLogsMapper.selectById(logId);
    }
}
