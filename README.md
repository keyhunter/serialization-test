## serialization-test

Java serialization test.

FastJSON Jackson XML JDK ProtoBuffer Kyro 几种序列化方式的 demo 和简单的性能测试及对比。

## 序列化简介

![image](https://cloud.githubusercontent.com/assets/7654175/23894440/169d21e8-08dd-11e7-964a-aeffd5bb7211.png)

对象是由行为和状态，序列化代表我们可以将一个对象的状态转化成数据流，可以通过网络传输，也可以存储成一个文件或者数据库中，同样可以把数据流反序列化成对象。

## 文本序列化

- 优点：文本型序列化的优点在于跨平台跨语言，简单易读易调试，扩展性强
- 缺点：占空间，不过可以压缩，会有一小部分性能损耗
- JSON：比较常用，简单，常用的库有 Gson，Jackson，FastJson
- XML：主要用于基于 SOAP 协议的应用，序列化后内容太多

## 二进制序列化

- 优点：省空间，一般用于系统内部序列化
- 缺点：扩展性较差，不能够很好的跨语言
- ProtoBuffer：Google 提供的跨语言跨平台的序列化框架
- Thirft：是由 Facebook 为 “大规模跨语言服务开发” 而开发的，它被用来定义和创建跨语言的服务，包含一部分二进制的序列化功能
- kryo：序列化大对象时性能较好

## 优缺点

- Kryo：使用 Direct ByteBuffer，对于复杂的对象，序列化快，占用容量小
- Proto Buffer：简单对象的序列化比较快
- JSON：一般用于 web 服务，可读性较强，建议使用 fastjson
- JAVA：开箱即用，不需要依赖第三方包

## 测试结果

### 简单对象序列化结果

![简单对象序列化结果](https://cloud.githubusercontent.com/assets/7654175/23894248/5065f7fc-08dc-11e7-95ce-9e5bcfdeac43.png)

### 复杂对象序列化结果

![复杂对象序列化结果](https://cloud.githubusercontent.com/assets/7654175/23894258/5fbfd970-08dc-11e7-8f5a-707f308e5d1f.png)

