# YLScrollView
A smart custom ScrollView for Android, which is able to measure your inside content length and decide whether a rightside vertical text-indicator or graphic-indicator is in need or not. With this smart container, compared with default Android ScrollView, users are given a more visualised notice that more content is hidden under the visible layout and the entire content can be viewed by scrolling.

## Screenshots
<br/>
<p align="center">
<img src="./screenshot/sample_1.png" width="230" />
<img src="./screenshot/sample_2.png" width="230" />
<img src="./screenshot/sample_3.png" width="230" />
</p>

## Demo Project
This library comes with a demo project for you. You can download the latest demo APK from this link:
https://github.com/YinLiWisdom/Android-YLScrollView/blob/master/APK/demo.apk

It's also on Google Play:

<a href="https://play.google.com/store/apps/details?id=com.yinli.yinli_ylscrollview&hl=en_GB">
  <img alt="Get it on Google Play"
       src="https://developer.android.com/images/brand/en_generic_rgb_wo_60.png" />
</a>

Having the demo project installed is a good way to be notified of new releases.

## Useage
### Step 1
#### Gradle (jCenter)
```
dependencies {
    compile "com.yinli:ylscrollview:1.1.0"
}
```
#### Maven
```
<dependency>
	<groupId>com.yinli</groupId>
	<artifactId>ylscrollview</artifactId>
	<version>1.1.0</version>
</dependency>
```

### Step 2
1. Add `YLScrollView` into your project as the sample code below. You have to notice that `YLScrollView` only allows one child view to be its content, so if you have much complicated children rather than a simple `TextView` please make sure you wrap them with a parent `ViewGroup`. 
	```xml
	    <com.yinli.ylscrollview.YLScrollView
	        android:id="@+id/container"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent">
	        
	        <TextView
	            android:id="@+id/content"
	            android:layout_width="match_parent"
	            android:layout_height="match_parent" />
	
	    </com.yinli.ylscrollview.YLScrollView>
	```
	
2. `YLScrollView` also allows you to customize basic styles.

	1. Set indicator type
		* XML: `custom:ylsv_type="text"` or `custom:ylsv_type="graphic"`
		* Code: `ylScrollView.setIndicatorType(YLScrollView.IndicatorType.Graphic);`

	2. Set indicator color (text-indicator / graphic-indicator)
		* XML: `custom:ylsv_indicatorColor="@color/custom_color"`
		* Code: `ylScrollView.setIndicatorColor(getResources().getColor(R.color.custom_color));`

	3. Set indicator size (text-indicator only)
		* XML: `custom:ylsv_textSize="XXsp"`
		* Code: `ylScrollView.setTextSize(getResources().getDimension(R.dimen.custom_size));`

	4. Set indicator text (text-indicator only)
		* XML: `custom:ylsv_text="XXXXXXXXX"`
		* Code: `ylScrollView.setText(getResources().getString(R.string.custom_text));`

Developed By
============
* Yin Li - <li.yinmax@outlook.com>

# License
    The MIT License (MIT)

    Copyright (c) 2015 Yin Li

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
