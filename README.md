# arcProgressBar

##这是一个仿QQ健康圆弧进度条的demo

##效果：


 ![](https://github.com/quhung/arcProgressBar/blob/master/a.gif)


![](https://github.com/quhung/arcProgressBar/blob/master/b.gif)

##XML
```
    <com.qhung.library.widget.CircleProgressBar
        android:id="@+id/progressbar"
        android:layout_width="250dp"
        android:layout_height="250dp"
        custom:progress_num_color="#f041ff"
        custom:progressbar_color="#f041ff"
        custom:max_progressbar_angle="360"
        custom:progressbar_width="15dp"
        custom:text_unit="步"
        custom:title_text="今天走了" />
```

##code

```
    arcProgressBar.setMaxValue(100);
    arcProgressBar.setProgress(100);
```


**具体实现请看**[仿QQ健康ArcProgressbar](http://www.jianshu.com/p/cc7b8a1ee994)