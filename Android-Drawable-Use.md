在实际开发的过程中，除了广为人知的利用 Drawable 设置按钮点击特效，我们有时可能会接到一些这样的需求，比如要求我们的头像显示成圆形或者圆角矩形，甚至要加上可变颜色的边框，或者要求你做一套启动、暂停、快进和快退的视频控制按钮并且可以改变按钮图标颜色，这些需求都有一个共同的特点，就是显示的内容需要进行自定义并且为静态资源。可能某些时候第一反应就是用自定义 View 来实现，但是如果熟悉了 Drawable 的用法之后，这些效果同样可以利用它来完成，下面将逐一介绍各种 Drawable 的用法。

## 1. BitmapDrawable

BitmapDrawable 可以看作是对 Bitmap 的一种包装，它可以设定 Bitmap 的显示方式、位置等属性。

### 1.1 语法

BitmapDrawable 对应 **\<bitmap\>** 标签定义，xml 语法如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<bitmap
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:src="@[package:]drawable/drawable_resource"
    android:antialias=["true" | "false"]
    android:dither=["true" | "false"]
    android:filter=["true" | "false"]
    android:gravity=["top" | "bottom" | "left" | "right" | "center_vertical" |
                      "fill_vertical" | "center_horizontal" | "fill_horizontal" |
                      "center" | "fill" | "clip_vertical" | "clip_horizontal"]
    android:mipMap=["true" | "false"]
    android:tileMode=["disabled" | "clamp" | "repeat" | "mirror"]  />
```

其中各个属性的含义分别是：

|   属性    |                             含义                             |
| :-------: | :----------------------------------------------------------: |
|    src    |                    Bitmap 对象的引用路径                     |
| antialias |                     抗锯齿效果，建议开启                     |
|  dither   | 是否防抖动。当位图像素与屏幕像素不匹配（如 ARGB_8888 的位图显示在 RGB_565 的屏幕上）时，防止图片失真，建议开启 |
|  filter   | 是否启用位图过滤。开启后，当图片进行拉伸或者压缩时，能够进行平滑过渡 |
|  gravity  |          当位图尺寸小于容器尺寸时在容器中的摆放位置          |
|  mipMap   |             启用或停用 mipmap 提示，默认为 false             |
| tileMode  | 平铺模式。默认：disable；clamp：复制边沿的颜色；repeat：水平和垂直方向重复绘制图片；mirror：水平和垂直方向交替镜像进行重复绘制 |

### 1.2 用法示例

下面以定义一个使用图片作为背景的 Drawable 为例，展示 BitmapDrawable 的简单实用方法。

**定义：**

[app/src/main/res/drawable/bitmap_drawable.xml](https://github.com/guanpj/DrawableDemo/blob/master/app/src/main/res/drawable/bitmap_drawable.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<bitmap xmlns:android="http://schemas.android.com/apk/res/android"
    android:src="@mipmap/kakarotto"
    android:antialias="true"
    android:dither="true"
    android:filter="true"
    android:gravity="center"
    android:tileMode="repeat"/>
```

**使用：**

[app/src/main/res/layout/activity_bitmap_drawable.xml](https://github.com/guanpj/DrawableDemo/blob/master/app/src/main/res/layout/activity_bitmap_drawable.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/bitmap_drawable"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

 **效果图：**

![bitmap-drawable](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/Blog-Article/Android-Drawable-Use/bitmap-drawable.jpg)

## 2. ShapeDrawable 和 GradientDrawable

官方文档中对 [ShapeDrawable](<https://developer.android.com/reference/android/graphics/drawable/ShapeDrawable.html?hl=zh-CN>) 的定义是这样的：

> A Drawable object that draws primitive shapes. A ShapeDrawable takes a `Shape` object and manages its presence on the screen.  If no Shape is given, then the ShapeDrawable will default to a `RectShape`.
>
> This object can be defined in an XML file with the `<shape>` element.

即它是一个用来绘制原始形状的 Drawable 对象。

而对 [GradientDrawable](<https://developer.android.com/reference/android/graphics/drawable/GradientDrawable?hl=zh-CN>) 的定义是：

> A Drawable with a color gradient for buttons, backgrounds, etc.
>
> It can be defined in an XML file with the `<shape>` element. For more information, see the guide to [Drawable Resources](https://developer.android.com/guide/topics/resources/drawable-resource.html?hl=zh-CN).

根据描述可知，它是一个具有色彩梯度（color gradient）的 Drawable。

### 2.1 语法

GradientDrawable 和 ShapeDrawable 都采用 *shape*  标签来定义，和 ShapeDrawable 最大的不同的就是它拥有 *gradient* 属性，下面以 GradientDrawable 为例，讲解 *shape* 标签的用法，它的语法如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape=["rectangle" | "oval" | "line" | "ring"] >
    <corners
        android:radius="integer"
        android:topLeftRadius="integer"
        android:topRightRadius="integer"
        android:bottomLeftRadius="integer"
        android:bottomRightRadius="integer" />
    <gradient
        android:angle="integer"
        android:centerX="integer"
        android:centerY="integer"
        android:centerColor="integer"
        android:endColor="color"
        android:gradientRadius="integer"
        android:startColor="color"
        android:type=["linear" | "radial" | "sweep"]
        android:usesLevel=["true" | "false"] />
    <padding
        android:left="integer"
        android:top="integer"
        android:right="integer"
        android:bottom="integer" />
    <size
        android:width="integer"
        android:height="integer" />
    <solid
        android:color="color" />
    <stroke
        android:width="integer"
        android:color="color"
        android:dashWidth="integer"
        android:dashGap="integer" />
</shape>
```

其中各个属性标签的含义分别是：

**android:shape 属性：** 表示形状，它的值可以是 rectangle（矩形）、oval（椭圆）、line（横线）和 ring（圆环），默认为 rectangle。 此外，当形状值是 *ring* 的时候，还有一下几个属性可配置：

|           属性           |                             含义                             |
| :----------------------: | :----------------------------------------------------------: |
|   android:innerRadius    | 圆环的内半径。与 innerRadiusRatio 同时设置时，以 innerRadiusRatio 为准 |
| android:innerRadiusRatio |                  圆环的内半径占环宽度的比率                  |
|    android:thickness     | 圆环厚度。与 thicknessRatio同时设置时，以 thicknessRatio 为准 |
|  android:thicknessRatio  |                   圆环的厚度占环宽度的比率                   |
|     android:useLevel     | 一般为 false，否则可能达不到预期显示效果，除非把它当作 LevelListDrawable 来使用 |

**\<corners\> 标签：**

```xml
<corners
    android:radius="integer"
    android:topLeftRadius="integer"
    android:topRightRadius="integer"
    android:bottomLeftRadius="integer"
    android:bottomRightRadius="integer" />
```

指图形的圆角半径，**仅当 shape 属性为 rectangle 即形状是矩形时生效**，数值越小越接近直角，*android:radius* 同时设置四个角的半径，其他四个属性则可单独设置某个角的半径。

**\<gradient\> 标签：** 

```xml
<gradient
    android:angle="integer"
    android:centerX="integer"
    android:centerY="integer"
    android:centerColor="integer"
    android:endColor="color"
    android:gradientRadius="integer"
    android:startColor="color"
    android:type=["linear" | "radial" | "sweep"]
    android:usesLevel=["true" | "false"] />
```

表示颜色渐变，它的各个属性值的含义分别是：

|          属性          |                             含义                             |
| :--------------------: | :----------------------------------------------------------: |
|     android:angle      | 渐变的角度。必须是 45 的倍数，默认值为 0。0 为从左到右，90 为从上到下 |
|    android:centerX     |              渐变中心的相对 X 轴位置 (0 - 1.0)               |
|    android:centerY     |              渐变中心的相对 Y 轴位置 (0 - 1.0)               |
|   android:startColor   |                        渐变的起始颜色                        |
|  android:centerColor   |                        渐变的中间颜色                        |
|    android:endColor    |                        渐变的结束颜色                        |
| android:gradientRadius |     渐变的半径，**仅在 `android:type="radial"` 时适用**      |
|    android:useLevel    | 一般为 false，否则可能达不到预期显示效果，除非把它当作 LevelListDrawable 来使用 |
|      android:type      | 渐变类别。它的值可以为：linear(线性)，默认值、radial（径内渐变）和sweep（扫描渐变） |

**\<padding\> 标签：**

距离内容或者子元素的内边距，每个方向可以单独设置。

**\<size\> 标签：**

设置 shape 大小，width 表示宽度，height 表示高度。需要注意的是，这个一般并不是 shape 的最终大小，如果用作 View 的背景，它的大小是由 View 的大小来决定的。

**\<solid\> 标签：**

表示纯色填充，color 属性为填充的颜色。

**\<stroke\> 标签：**

边框描述，它的各个属性值的含义分别是：

|       属性        |        含义        |
| :---------------: | :----------------: |
|   android:width   |      边框宽度      |
|   android:color   |      边框颜色      |
| android:dashWidth |   虚线的线段宽度   |
|  android:dashGap  | 虚线之间的空白间隔 |

需要注意的是，如果需要设置边框虚线效果，则需要同时设置 dashWidth 和 dashGap 的值不为 0，否则无法显示虚线效果。

### 2.2 用法示例

下面以定义一个圆角并带有其他效果的 Drawable 为例，展示 GradientDrawable 的简单用法。

**定义：**

[app/src/main/res/drawable/gradient_drawable](https://github.com/guanpj/DrawableDemo/blob/master/app/src/main/res/drawable/gradient_drawable.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
       android:shape="rectangle">

    <corners
            android:topLeftRadius="15dp"
            android:topRightRadius="15dp"
            android:bottomLeftRadius="15dp"
            android:bottomRightRadius="15dp"/>

    <padding
            android:left="10dp"
            android:top="10dp"
            android:right="10dp"
            android:bottom="10dp" />

    <gradient android:angle="45"
              android:type="linear"
              android:startColor="#ff0000"
              android:centerColor="#00ff00"
              android:endColor="#0000ff" />

    <size
        android:width="200dp"
        android:height="100dp" />

    <stroke
            android:width="2dp"
            android:color="#000000"
            android:dashWidth="7dp"
            android:dashGap="3dp" />

</shape>
```

**使用：**

[app/src/main/res/layout/activity_gradient_drawable.xml](https://github.com/guanpj/DrawableDemo/blob/master/app/src/main/res/layout/activity_gradient_drawable.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".GradientDrawableActivity">

    <Button
            android:text="Button"
            android:layout_width="200dp"
            android:layout_height="100dp"
            android:background="@drawable/gradient_drawable"
            android:id="@+id/textView"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

**效果图：**

![gradient-drawable](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/Blog-Article/Android-Drawable-Use/gradient-drawable.jpg)

## 3. StateListDrawable

StateListDrawable 可以根据对象的状态并使用不同的 Drawable 对象来表示同一个图形。如可以根据 Button 的状态（按下、获取焦点等）来显示不同的 Drawable 从而实现点击的效果。

### 3.1 语法

定义 StateListDrawable 的语法格式如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android"
    android:constantSize=["true" | "false"]
    android:dither=["true" | "false"]
    android:variablePadding=["true" | "false"] >
    <item
        android:drawable="@[package:]drawable/drawable_resource"
        android:state_pressed=["true" | "false"]
        android:state_focused=["true" | "false"]
        android:state_hovered=["true" | "false"]
        android:state_selected=["true" | "false"]
        android:state_checkable=["true" | "false"]
        android:state_checked=["true" | "false"]
        android:state_enabled=["true" | "false"]
        android:state_activated=["true" | "false"]
        android:state_window_focused=["true" | "false"] />
</selector>
```



### 3.2 用法示例

**定义：**

**使用：**

**效果图：**

## 3. LayerDrawable

LayerDrawable 是管理其他 Drawable 阵列的 Drawable。列表中的每个 item 按照列表的顺序绘制，列表中的最后 item 绘于顶部。

### 3.1 语法

定义 LayerDrawable 的语法格式如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list
    xmlns:android="http://schemas.android.com/apk/res/android" >
    <item
        android:drawable="@[package:]drawable/drawable_resource"
        android:id="@[+][package:]id/resource_name"
        android:top="dimension"
        android:right="dimension"
        android:bottom="dimension"
        android:left="dimension" />
</layer-list>
```

LayerDrawable 顶层标签为 **\<layer-list\>**，它可以包含多个 **\<item\>** 标签，每个 item 表示一个 Drawable，item 的属性含义分别是：

|                           属性                           |                             含义                             |
| :------------------------------------------------------: | :----------------------------------------------------------: |
|                     android:drawable                     |            drawable 资源，可引用现有的的 Drawable            |
|                        android:id                        | iitem 的 id，使用"@+id/*name*"的形式表示。可通过 View.findViewById() 或者 Activity.findViewById() 方法查找到这个 Drawable |
| android:top、android:right、android:bottom、android:left |           Drawable 相对于 View 在各个方向的偏移量            |

### 3.2 用法示例

下面以定义一个圆角并带阴影效果的 Drawable 为例，展示 LayerDrawable 的简单使用。

**定义：**

[app/src/main/res/drawable/layer_drawable](https://github.com/guanpj/DrawableDemo/blob/master/app/src/main/res/drawable/layer_drawable.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item
            android:left="2dp"
            android:top="4dp">
        <shape>
            <solid android:color="@android:color/darker_gray" />
            <corners android:radius="10dp" />
        </shape>
    </item>

    <item
            android:bottom="4dp"
            android:right="2dp">
        <shape>
            <solid android:color="#FFFFFF" />
            <corners android:radius="10dp" />
        </shape>
    </item>
</layer-list>
```

**使用：**

[app/src/main/res/layout/activity_layer_drawable.xml](https://github.com/guanpj/DrawableDemo/blob/master/app/src/main/res/layout/activity_layer_drawable.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@android:color/holo_orange_dark"
    tools:context=".LayerDrawableActivity">

    <LinearLayout
            android:orientation="vertical"
            android:background="@drawable/layer_drawable"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="10dp"
            android:layout_margin="20dp"
            tools:layout_editor_absoluteY="331dp"
            tools:layout_editor_absoluteX="190dp"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent">

        <TextView android:layout_width="match_parent"
                  android:layout_height="wrap_content"
                  android:textColor="#000000"
                  android:text="I'm a title......."
                  android:textSize="20sp" />

        <TextView android:layout_width="match_parent"
                  android:layout_height="wrap_content"
                  android:textColor="@android:color/darker_gray"
                  android:text="content content content content content content content content..."
                  android:textSize="16sp" />

    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
```

**效果图：**

![gradient-drawable](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/Blog-Article/Android-Drawable-Use/layer-drawable.jpg)