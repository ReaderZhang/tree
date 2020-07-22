package com.summer.tree.shiro;/*
@Author qqz
@create 2020-07-20  19:09
*/
import com.summer.tree.pojo.JwtToken;
import com.summer.tree.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

@Slf4j
public class JwtRealm extends AuthorizingRealm {
    /*
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken
     * 不负责验证其他的token（UsernamePasswordToken）
     * */
    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //认证
    //这个token就是从过滤器中传入的jwtToken
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwt = (String)token.getPrincipal ();
        if (jwt == null)
            throw new NullPointerException ("jwtToken不允许为空");
        JwtUtil jwtUtil = new JwtUtil ();
        //判断
        if (!jwtUtil.isVerify ( jwt )){
            throw new UnknownAccountException ();
        }
        //下面验证这个user是否真实存在
        String username = (String)jwtUtil.decode ( jwt ).get ( "username" );//判断数据库中username是否存在
        log.info ( "在使用token登录"+username );
        return new SimpleAuthenticationInfo (jwt,jwt,"JwtRealm");
        //这里返回的是类似账号密码的东西，但是jwtToken都是jwt字符串。还需要一个该Realm的类名
    }
}
