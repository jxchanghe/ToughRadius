ToughRadius
===========

### 最新发布

[ToughRadiusV1.0 Bate](http://pan.baidu.com/share/link?uk=604278103&shareid=1758865663)

ToughRadius首个beta版本发布,已经具备了基本的认证计费功能,支持ros,h3c,huawei设备接入,只需要扩展字典便可支持更多接入类型,支持用户过期策略,时段策略,绑定MAC策略,并发数控制等策略,支持预付费包月和计时资费.

注意，当前版本仅提供测试用，不建议在生产环境中使用。

后续版本规划,保证核心版本的精简,通过模块接口扩展来实现更丰富的业务功能.

### ToughRadius 功能特性

ToughRadius 是基于开源TinyRadius开发的一个支持高并发的Radius服务器。TinyRadius提供了Radius协议实现基础，ToughRadius增加了多线程的支持，可以支持高并发的认证计费请求。

ToughRadius 使用java开发，支持跨平台的部署。

ToughRadius增加了数据库存储支持，通过jdbc驱动支持多种数据库，比如Mysql,Hsqldb,Oracle等，当前ToughRadius内置Hsqldb的支持，Hsqldb是一个高性能的java嵌入式数据库，强烈建议你使用它来存储您的用户数据。

ToughRadius实现了一个Web管理界面，提供用户管理，用户组管理，客户端管理等功能，支持灵活的属性扩展。

### 安装配置

请参见 [ToughRadius文档](https://github.com/jamiesun/ToughRadius/tree/master/docs)

### 开发者和贡献者

@jamiesun  欢迎更多的开发者加入。

### 问题反馈

您在使用本软件的过程中遇到问题，可以通过电子邮件反馈 jamiesun.net@gmail.com

ToughRadius也为提供更简单的咨询讨论渠道，您可加入我们的技术QQ群：247860313

### 技术支持

ToughRadius是一个开源和免费的产品，使用ToughRadius，您不需要为此付出购买费用，我们提供长期的版本维护，同时根据使用者的实际需求，我们提供有偿的技术服务。
