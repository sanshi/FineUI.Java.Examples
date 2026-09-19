# FineUI.Java.Examples

FineUI.Java.Examples 是 FineUI 官方完整示例项目。本仓库是该项目的唯一真相源，欢迎通过 Issue 和 Pull Request 参与技术讨论与改进。

## 依赖方式

项目文件已声明从公共软件包仓库获取的 Maven 包 `com.fineui:fineui-java`。正常联网构建时，包管理器会自动还原依赖；仓库不包含 FineUI.Core.dll、FineUI.Pro.dll、fineui-java.jar，也不包含 FineUI 框架源码。

## 构建

安装 JDK 17 与 Maven 后，在仓库根目录运行：

```bash
mvn package
```

## 运行

在仓库根目录启动：

```bash
mvn spring-boot:run
```

启动后打开 **http://localhost:8080/** —— 端口来自 `src/main/resources/application.properties` 里的 `server.port=8080`。

也可以先打包再以独立进程运行：

```bash
mvn package
java -jar target/fineui-java-examples-*.jar
```

JDK 要求见 `pom.xml` 的 `<java.version>17</java.version>`；本项目已在 JDK 21 上验证运行。

**不需要授权文件**：本仓库引用的是公共 Maven 包 `com.fineui:fineui-java`（社区版），社区版不做授权校验，克隆下来就能直接跑。

## 许可边界

本仓库中由合肥三生石上软件有限公司拥有著作权的示例或应用项目源代码采用 [MIT 许可证](LICENSE)。FineUI 各端框架源码、二进制软件包、内嵌的 FineUI.js 运行时以及 FineUI 名称、标识和商标不属于 MIT 授权范围，仍适用各自的商业或社区版许可。具体边界见 [NOTICE.md](NOTICE.md)。

## 参与贡献

请先阅读 `CONTRIBUTING.md`。安全问题请按 `SECURITY.md` 私下报告。
