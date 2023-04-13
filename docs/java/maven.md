# Maven



##  Spring Profile 多环境

多模块项目需要保持环境一致，手工改很累的！

在根 pom.xml 中添加如下配置来支持多环境：

```xml
<project>
    <profiles>
        <profile>
            <id>dev</id>
            <properties>
                <!-- 环境标识，需要与配置文件的名称相对应 -->
                <profiles.active>dev</profiles.active>
            </properties>
            <activation>
                <!-- 默认环境 -->
                <activeByDefault>true</activeByDefault>
            </activation>
        </profile>
        <profile>
            <id>prod</id>
            <properties>
                <profiles.active>prod</profiles.active>
            </properties>
        </profile>
    </profiles>
    <!-- 过滤器可以在将资源文件复制到构建目录（target）之前，将其中包含的变量占位符替换为实际的值  -->
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
</project>
```

 现在就可以用  @profiles.active@ 来配置分支啦！idea 的 maven 栏中也会出现  Profiles ，可以快捷的切换环境咯。 当打包与当前不一致环境的包后，记得点下 maven 的刷新按钮，不然启动之后可能是打包的环境。

```yaml
spring:
  profiles:
    active: @profiles.active@
```

打包指定环境 `mvn clean package -P prod`