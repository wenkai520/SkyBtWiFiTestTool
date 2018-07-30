# SkyBtWiFiTestTool
一款安卓TV上使用的蓝牙和WiFi测试工具
1、使用了MVP框架，也是第一次完整的使用MVP,拿来练了一下手；
2、大概花费2周时间，2年前有写过部分，后面demo丢失，需要重新开发；
3、基本是2年前项目的重构，希望可以比当初刚接触android时写的有所提高一点。

痛点或者需求来源：
蓝牙和WiFi厂家提供的模块给到TV厂商的时候并没有给TV平台的测试工具，只给了window版本的测试工具，之前经常发现在在电脑上测试正常的WiFi和蓝牙模块，量产的时候当接到电视电视上跑android系统的时候，发现有的时候模块也无法识别。
所以需要一个工具，能在电视上直接测试WiFi和蓝牙模块是否正常工作，来降低量产过程中蓝牙和WiFi模块异常的概率。

so 这款工具，应运而生。

通过这款工具，你可以在安卓电视上直接测试蓝牙和WiFi模块是否正常工作。

当测试过程中，你的WiFi模块能够搜索到设置的ap、并且信号强度符合一定的标准、mac地址在预设的范围之内的时候，就会显示WiFi模块正常；反之，WiFi模块异常。
当蓝牙模块在检测过程中如果能搜索到设定的device、mac范围合格的话，会判定蓝牙模块合格，反之，蓝牙模块异常。


测试主界面：
1、支持WiFi和蓝牙模块热插拔；
2、能够实时将当前的测试结果反馈出来
（测试通过、mac地址越界、信号强度不合格、部分ap未找到等提示）

![Alt text](https://github.com/wenkai520/SkyBtWiFiTestTool/blob/master/picture/detectView.png)


参数设置主界面：

![Alt text](https://github.com/wenkai520/SkyBtWiFiTestTool/blob/master/picture/mainSetting.png)

WiFi界面设置：

1、支持4路ssid和信号强度设置
2、支持mac地址范围设置；
3、支持信号强度百分比设置

![Alt text](https://github.com/wenkai520/SkyBtWiFiTestTool/blob/master/picture/wifiSetting.png)

蓝牙界面设置：

1、支持2路device设置；
2、支持mac地址范围设置

![Alt text](https://github.com/wenkai520/SkyBtWiFiTestTool/blob/master/picture/btSetting.png)

希望可以帮到有用的童鞋!
