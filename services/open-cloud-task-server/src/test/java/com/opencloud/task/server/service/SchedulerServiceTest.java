package com.opencloud.task.server.service;

import com.google.common.collect.Maps;
import com.opencloud.common.test.BaseTest;
import com.opencloud.task.client.model.TaskInfo;
import com.opencloud.task.server.job.HttpExecuteJob;
import com.opencloud.task.server.service.feign.EmailServiceClient;
import org.junit.Test;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: liuyadu
 * @date: 2019/3/29 14:59
 * @description:
 */
public class SchedulerServiceTest extends BaseTest {

    @Autowired
    private TaskService taskService;
    @Autowired
    private EmailServiceClient emailServiceClient;
    @Test
    public void getJobList() throws Exception {
        List<TaskInfo> list= taskService.getJobList();
    }

    @Test
    public void addSimpleJob() throws Exception {
    }

    @Test
    public void addCronJob() throws Exception {
        String cron = "3 * * * * ? *";
        TaskInfo taskInfo = new TaskInfo();
        Map data= Maps.newHashMap();
        String serviceId = "upm-server";
        data.put("serviceId",serviceId);
        data.put("method","get");
        data.put("path","/test");
        data.put("contentType","application/x-www-form-urlencoded");
        data.put("alarmMail","515608851@qq.com");
        taskInfo.setData(data);
        taskInfo.setJobName("定时任务测试");
        taskInfo.setJobDescription("定时任务描述3秒执行一次");
        taskInfo.setJobClassName(HttpExecuteJob.class.getName());
        taskInfo.setJobGroupName(Scheduler.DEFAULT_GROUP);
        taskInfo.setCronExpression(cron);
        taskService.addCronJob(taskInfo);
    }

    @Test
    public void editJob() throws Exception {
        String cron = "3 * * * * ? *";
        TaskInfo taskInfo = new TaskInfo();
        Map data= Maps.newHashMap();
        String serviceId = "upm-server";
        data.put("serviceId",serviceId);
        data.put("method","get");
        data.put("path","/test");
        data.put("contentType","application/x-www-form-urlencoded");
        data.put("alarmMail","515608851@qq.com");
        taskInfo.setData(data);
        taskInfo.setJobName("定时任务测试");
        taskInfo.setJobDescription("定时任务描述3秒2222222执行一次");
        taskInfo.setJobClassName(HttpExecuteJob.class.getName());
        taskInfo.setJobGroupName(Scheduler.DEFAULT_GROUP);
        taskInfo.setCronExpression(cron);
        taskService.editCronJob(taskInfo);
    }

    @Test
    public void deleteJob() throws Exception {
        taskService.deleteJob("定时任务测试",Scheduler.DEFAULT_GROUP);
    }

    @Test
    public void pauseJob() throws Exception {
        taskService.pauseJob("定时任务测试",Scheduler.DEFAULT_GROUP);
    }

    @Test
    public void resumeJob() throws Exception {
        taskService.resumeJob("定时任务测试",Scheduler.DEFAULT_GROUP);
    }

    @Test
    public void sendEmail() throws IOException {
        MockMultipartFile file1 = new MockMultipartFile("attachments","diagram.png",null,new FileInputStream("D:\\diagram.png"));
        emailServiceClient.send("515608851@qq.com",null,"测试","测试内容",new MultipartFile[]{file1});
    }


    @Test
    public void sendEmail2() throws IOException {
        MockMultipartFile file1 = new MockMultipartFile("file","diagram.png",null,new FileInputStream("D:\\diagram.png"));
        emailServiceClient.send2(file1);
    }
}