package com.opencloud.base.server.controller;

import com.opencloud.base.client.model.AuthorityMenu;
import com.opencloud.base.client.model.entity.BaseUser;
import com.opencloud.base.server.service.BaseAuthorityService;
import com.opencloud.base.server.service.BaseUserService;
import com.opencloud.common.constants.CommonConstants;
import com.opencloud.common.model.ResultBody;
import com.opencloud.common.security.OpenHelper;
import com.opencloud.common.security.OpenUserDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: liuyadu
 * @date: 2019/5/24 13:31
 * @description:
 */
@Api(tags = "当前登陆用户")
@RestController
public class CurrentUserController {

    @Autowired
    private BaseUserService baseUserService;
    @Autowired
    private BaseAuthorityService baseAuthorityService;
    @Autowired
    private RedisTokenStore redisTokenStore;
    /**
     * 修改当前登录用户密码
     *
     * @return
     */
    @ApiOperation(value = "修改当前登录用户密码", notes = "修改当前登录用户密码")
    @GetMapping("/current/user/rest/password")
    public ResultBody restPassword(@RequestParam(value = "password") String password) {
        baseUserService.updatePassword(OpenHelper.getUser().getUserId(), password);
        return ResultBody.ok();
    }

    /**
     * 修改当前登录用户基本信息
     *
     * @param nickName
     * @param userDesc
     * @param avatar
     * @return
     */
    @ApiOperation(value = "修改当前登录用户基本信息", notes = "修改当前登录用户基本信息")
    @PostMapping("/current/user/update")
    public ResultBody updateUserInfo(
            @RequestParam(value = "nickName") String nickName,
            @RequestParam(value = "userDesc", required = false) String userDesc,
            @RequestParam(value = "avatar", required = false) String avatar
    ) {
        OpenUserDetails openUserDetails = OpenHelper.getUser();
        BaseUser user = new BaseUser();
        user.setUserId(openUserDetails.getUserId());
        user.setNickName(nickName);
        user.setUserDesc(userDesc);
        user.setAvatar(avatar);
        baseUserService.updateUser(user);
        openUserDetails.setNickName(nickName);
        openUserDetails.setAvatar(avatar);
        OpenHelper.updateOpenUser(redisTokenStore,openUserDetails);
        return ResultBody.ok();
    }

    /**
     * 获取登陆用户已分配权限
     *
     * @return
     */
    @ApiOperation(value = "获取当前登录用户已分配菜单权限", notes = "获取当前登录用户已分配菜单权限")
    @GetMapping("/current/user/menu")
    public ResultBody<List<AuthorityMenu>> findAuthorityMenu() {
        List<AuthorityMenu> result = baseAuthorityService.findAuthorityMenuByUser(OpenHelper.getUser().getUserId(), CommonConstants.ROOT.equals(OpenHelper.getUser().getUsername()));
        return ResultBody.ok().data(result);
    }
}
