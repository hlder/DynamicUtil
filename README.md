# 1.工具(动态加载so文件):com.dynamicutils.util.JniPlugUtil.java
  使用方法，先将需要加载so文件copy到data/data/packagename/下，然后调用JniPlugUtil.installSoDir就可以了
# 2.插件化开发框架
  插件化开发工具,主要分为宿主程序和插件程序。宿主程序为工程主程序，插件程序为开发的插件apk（目前只支持activity，暂不支持server）
  开发方法:
  1)宿主程序，需要在manifest中注册DynamicChildActivity，然后在程序中调用DynamicUtil.startDynamicActivity()启动插件程序
        需要注意的是DynamicChildActivity继承自AppCompatActivity，则需要使用Theme.AppCompat下的样式，需使用v7兼容包
  2)插件程序
       所有插件的activity都需要继承DynamicParantActivity
       需要注意的是
       1.context不能使用this，需要使用getContext
       2.资源文件名不能和宿主文件名冲突
       3.layout.xml布局文件中不能使用自定义控件，包括系统自定义控件，若要使用，请在java中new
       其他一切OK
  
  
