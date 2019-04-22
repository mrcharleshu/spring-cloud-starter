## 启动命令
```
mvn spring-boot:run -Pservice-1
mvn spring-boot:run -Pservice-2
mvn spring-boot:run -Pservice-3
mvn spring-boot:run -Pservice-4
```

## 脚本run.sh
```
# 启动
.run.sh
# 停止
ps -ef | grep run.sh | awk '{print $2}' | xargs kill -9
```