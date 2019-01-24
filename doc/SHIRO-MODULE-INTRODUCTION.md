# SHIRO用法解析

## shiro支持RESETFUL API更改方法
1. 自定义`BreezeShiroFilter`，自定义过滤器，用于请求过滤
2. 自定义`BreezeRealm`，自定义域，登陆鉴权规则编写
3. 自定义`BreezeToken`，一个token
4. 自定义`BreezeShiroConfig`，绑定过滤器和域。

## 流程
1. `/login`请求之后生成`token`并保存到数据库
2. `/other_request`其他请求进来后，被`BreezeShiroFilter`捕获。

        ```
       内部流程如下：
            `preHandle`->`isAccessAllowed`->`isLoginAttempt`->`executeLogin`->` getSubject(request, response).login(token)`(交给Realm处理)
        ```
3. `BreezeRealm`接收到来自`BreezeShiroFilter`抛过来的请求信息

        ```
       内部流程如下：
            `doGetAuthenticationInfo`(token验证)->`doGetAuthorizationInfo`(权限验证)
        ```

至此Shiro流程完成！具体代码请参考本项目。