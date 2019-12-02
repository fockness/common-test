# 基于java:8镜像进行扩展
FROM java:8
# 指定制作该镜像的作者和联系方式
MAINTAINER shibin
# 指定容器内进程对外开放的端口
EXPOSE 8090
# 定义数据卷位置---存放容器共享文件的位置
VOLUME /tmp
# 修改镜像的命令---安装或配置，每一步会创建一个新的镜像层
RUN mkdir /extensible
RUN mkdir /extensible/files
RUN mkdir /extensible/logs
# 将maven打成的jar包复制到镜像目录中（相对路径---默认"."就是Dockerfile所在目录）
ADD target/demo-0.0.1-SNAPSHOT.jar demo.jar
# 指定容器启动时，需要执行的命令(运行该jar程序)
ENTRYPOINT java -server -jar demo.jar




