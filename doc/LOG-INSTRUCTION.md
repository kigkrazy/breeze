# 日志使用说明
## 使用步骤
1. 在类的开头定义`logger`
```
private final static Logger logger = LoggerFactory.getLogger("BreezeApplication");
```
2. 使用`logger`

```
logger.debug("xxxx");
```

## 具体示例
```java
public class BreezeApplication {
    private final static Logger logger = LoggerFactory.getLogger("BreezeApplication");
    public static void main(String[] args) {
        logger.debug("hello log...");//使用例子
        SpringApplication.run(BreezeApplication.class, args);
    }
}
```