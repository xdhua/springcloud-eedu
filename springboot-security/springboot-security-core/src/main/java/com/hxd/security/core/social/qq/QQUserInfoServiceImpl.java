/**
 * 
 */
package com.hxd.security.core.social.qq;

import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 获取第三方登录信息的  当期登录用户信息
 * @author hxd
 *
 * date 2019年11月19日
 * 
 */
public class QQUserInfoServiceImpl extends AbstractOAuth2ApiBinding implements QQUserInfoService {

	// qq 提供获取openId 信息 根据登陆用户的token信息
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    
    // 获取用户信息 token 信息由父类提供 加载
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
     
//    申请QQ登录成功后，分配给应用的appid
    private String appId;
//    用户的ID，与QQ号码一一对应。 
    private String openId;
     
    private ObjectMapper objectMapper = new ObjectMapper();
     
    public QQUserInfoServiceImpl(String accessToken, String appId) {
    	 // https://graph.qq.com/user/get_user_info?access_token=YOUR_ACCESS_TOKEN&oauth_consumer_key=YOUR_APP_ID&openid=YOUR_OPENID
    	// 获取用户信息   qq url 提供的是将参数放在 url 信息中 所以 用 TokenStrategy.ACCESS_TOKEN_PARAMETER
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
         
        this.appId = appId;
        //  %S  字符串替换 详情看String 
        String url = String.format(URL_GET_OPENID, accessToken);
//        获取openId 用默认提供的
        String result = getRestTemplate().getForObject(url, String.class);
         
        System.out.println(result);
         
        	// 截取 openId
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
