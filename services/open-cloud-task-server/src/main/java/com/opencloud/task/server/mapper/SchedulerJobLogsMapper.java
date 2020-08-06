package com.opencloud.task.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opencloud.task.client.model.entity.TaskJobLogs;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerJobLogsMapper extends BaseMapper<TaskJobLogs> {
}
