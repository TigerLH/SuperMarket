# SuperMarket
SuperMarket Pratices

##Requirement
###Entity

  Supermarket
  
  Cashier
  
  Customer
  
  Good
  
###流程如下

  -启动 Supermarket 
  
  -Supermarket 初始化3个 Cashier
  
  -Supermarket  针对Apple, Macbook, Cookie三种Good每种初始化15个到商店库存
  
  -每隔1~3秒 产生一个 Customer随机购买一个商品
  
  -每隔5~10秒 Cashier 处理一个 Customer 购买请求
  
  -重复以上过程直到所有商品售罄
  
  -统计出每个顾客平均等待时间          //排队结账等待时间
  
  -统计出每个商品平均售出时间          //顾客入场到商品售罄的总时间除以货物总的数量
  
  -统计出从开始销售到售罄总共时间      //SuperMarket启动到最后一件商品付款完毕的时间
  
  -统计出每个 Cashier 接待的顾客数量  //每个收银员处理的顾客数
 
##设计分析

Cashier需要长期运行,需要实现统计处理的顾客数目以及通知超市处理完毕的功能,使用线程实现。

Supermarket 程序主体,包含管理货物及Cashier的功能 做为被观察者通知Cashier货物剩余情况,同时做为观察者接收Cashier处理顾客请求的情况

Apple/MackBook/Cookie都属于抽象类Goods的子类,拥有相同的属性:name/price/id

##统计方法

  统计出每个顾客平均等待时间: 
  
  单个顾客的等待时间：顾客所在Cashier队伍的位置前面所有顾客等待的时间之和。
  
  所有顾客的平均等待时间：三个队伍所有顾客的所需等待时间的集合除以所有的顾客数
  
  
##类说明

Customer:顾客

CustomerManager:导购,具有引导顾客入场，及停止入场的功能

SuperMarket:超市,具有上架商品/获取货物/管理收银员/导向顾客排队的功能

Cashier:收银员,处理顾客请求/统计处理的顾客数目的功能

##Console:

商品上架完成

收银员已就位

导购已就位

超市开业

商品剩余:45

...
商品剩余:2

商品剩余:1

商品已选购完毕

所有顾客请求已处理完成

从开始销售到售罄总共时间为:124秒

张阿姨处理了:15个顾客请求,耗费了:121秒

罗小妹处理了:15个顾客请求,耗费了:114秒

李大叔处理了:15个顾客请求,耗费了:109秒

顾客平均等待时间为:53S

每个商品平均售出时间为:2.7555555555555555S

Process finished with exit code 0

  
##项目管理：
Maven

##单元测试框架：

测试框架：TestNG

断言:hamcrest

运行:mvn clean test 

##报告框架：

ReportNG

目录:target/surefire-reports

  
