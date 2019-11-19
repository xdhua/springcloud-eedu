/**
 * 
 */
package com.hxd.security.core.social.qq;

import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hxd
 *
 * date 2019年11月19日
 * 
 */
public class QQUserInfoServiceImpl extends AbstractOAuth2ApiBinding implements QQUserInfoService {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
     
    private String appId;
     
    private String openId;
     
    private ObjectMapper objectMapper = new ObjectMapper();
     
    public QQUserInfoServiceImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
         
        this.appId = appId;
        //  %S  字符串替换 详情看String 
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
         
        System.out.println(result);
         
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }
     
    /* (non-Javadoc)
     * @see com.imooc.security.core.social.qq.api.QQ#getUserInfo()
     */
    /**
     *  通过默认 实现restTemplate 获取 当前 QQ 用户 登录信息
     */
    @Override
    public QQUserInfo getUserInfo() {
         
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
         
        System.out.println(result);
         
        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }

}
