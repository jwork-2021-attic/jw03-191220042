## 1.对代码工作原理的理解

与上次代码不同之处在于首先将排序的一个类编码进图片，在运行Scene时将图片解码得到java的class字节码并加载到jvm中，因此，此时的排序类不再重要（因为不需要通过解析它来获得排序类的字节码）。

阅读classloader源码可知，想要自定义ClassLoader必须重载findClass方法用于寻找类，在这个方法中解码图片中的字节码。

对字节码的隐藏稍显复杂，没太看懂，但是很明显解码是隐藏的逆操作。通过对encodeFile打断点可知，在编码时，不仅编入了bytes(以class类魔数开头)，还有sizeNameBytes，sizeBytes，nameBytes，而decodeFile()方法恰好完成了去除前面多余字节的任务，因此对decodeFile稍作修改，即可完成解码。

```
SteganographyClassLoader loader = new SteganographyClassLoader(
                new URL(""));

        Class c = loader.loadClass("");

        Sorter sorter = (Sorter) c.newInstance();
```

如上代码：在Sence中实例化一个SteganographyClassLoader对象，传入参数为图片的URL，loadclass为SteganographyClassLoader继承父类的方法，调用这个方法会调用包含findclass等一系列方法以完成类加载，最后调用newInstance获得一个sorter的实例。

SteganographyFactory 类编译排序类，读入图片并进行编码。在编码过程中，从图片中读入RGB的数据与类的字节码相运算，再将结果setRGB进新的图片。

## 2.两张图片的URL为
（1）https://github.com/jwork-2021/jw03-191220042/blob/main/example.QuickSorter.png

（2）https://github.com/jwork-2021/jw03-191220042/blob/main/example.SelectSorter.png

## 3.动画如下：
[![asciicast](https://asciinema.org/a/ZiHIuIsH5ot8Gt5zegYHJ5fBp.svg)](https://asciinema.org/a/ZiHIuIsH5ot8Gt5zegYHJ5fBp)

## 4.
我使用了李广源同学的图片，结果正确。