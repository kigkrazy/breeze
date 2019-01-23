# 多数据源配置

### 1. 添加数据库配置
在`spring.datasource.druid`节点下添加相关配置。例如：
```
first:  # 数据源1
    url: jdbc:maria://127.0.0.1:3306/breeze-prod?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8
    username: root
    password: 123456
```

### 2. `com.reizx.breeze.datasources.DynamicDataSourceConfig`添加数据源
```
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.first")
    public DataSource firstDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
    
    //生成实例
    @Bean
    @ConfigurationProperties("spring.datasource.druid.second")
    public DataSource secondDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
    
    //添加secondDataSource数据源
    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource firstDataSource, DataSource secondDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.FIRST, firstDataSource);
        targetDataSources.put(DataSourceNames.SECOND, secondDataSource);
        return new DynamicDataSource(firstDataSource, targetDataSources);
    }
}
```

### 3. 使用
在需要切换数据源的函数前面添加注解。例如
```java

@DataSource(name = DataSourceNames.SECOND)
public SysUserEntity queryUser2(Long userId){
    return sysUserService.selectById(userId);
}
```