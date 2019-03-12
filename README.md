English | [Indonesian](https://github.com/jrizani/JRSpinner/blob/master/README_id.md)

# JRSpinner
[![](https://jitpack.io/v/jrizani/JRSpinner.svg)](https://jitpack.io/#jrizani/JRSpinner)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-JRSpinner-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7528)

Custom spinner that inspired by instagram language chooser

#### [View Releases and Changelogs](https://github.com/jrizani/JRSpinner/releases)
<img src="https://github.com/jrizani/JRSpinner/raw/master/ss/example_spinner.png" width="200px"/> <img src="https://github.com/jrizani/JRSpinner/raw/master/ss/example_spinner_dialog.png" width="200px"/> <img src="https://github.com/jrizani/JRSpinner/raw/master/ss/example_spinner_dialog_selected.png" width="200px"/> <img src="https://github.com/jrizani/JRSpinner/raw/master/ss/example_spinner_dialog_search.png" width="200px"/> <img src="https://github.com/jrizani/JRSpinner/raw/master/ss/example_spinner_selected_item.png" width="200px"/>


# Table of Content
1. [What's new](#whats-new)
2. [Gradle install](#gradle-install)
3. [How to use](#how-to-use)
4. [Additional](#additional)
5. [Example](#example)

---

## What's new
What's new in version 1.0.0
```
1. change repository to jitpack.io
2. add multiple select
```

## Gradle install
Make sure there is `jitpack.io` repository in your project level build.gradle

```gradle
allprojects {
    repositories {
        ..
        maven { url "https://jitpack.io" }
    }
}
```

Implement the dependency to your app-level build.gradle

```gradle
dependencies {
  ..
  implementation 'com.github.jrizani:JRSpinner:$version'
}
```

## How to use
Declare the view in your layout
```xml
<jrizani.jrspinner.JRSpinner
                android:id="@+id/spn_my_spinner"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:hint="@string/choose"
                android:textColor="@color/colorPrimary"
                app:backgroundTint="@color/colorPrimary"
                app:jrs_title="Choose"
                app:jrs_icon_tint="@color/colorPrimary"
                app:jrs_multiple="true"/>
```

### Attribute
| Attribute | Description | Default Value |
| --- | --- | --- |
| android:hint | hint of spinner | nothing |
| android:textColor | color of text | default text color |
| app:backgroundTint | tint of spinner, in default it is change underline color of spinner | default edittext background tint |
| android:background | if you want your own background of spinner, you can use it | default edittext background |
| app:jrs_title | there is the title of the spinner dialog | "Choose" |
| app:jrs_icon_tint | color tint of expand icon | #99000000 |
| app:jrs_multiple | choose is it use multiple select or no | false |

You also can set the parameter programmatically
```java
private JRSpinner mySpinner;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mySpinner = findViewById(R.id.spn_my_spinner);

    mySpinner.setItems(getResources().getStringArray(R.array.tesItems)); //this is important, you must set it to set the item list
    mySpinner.setTitle("Choose item programmatically"); //change title of spinner-dialog programmatically
    mySpinner.setExpandTint(R.color.color_default); //change expand icon tint programmatically

//    mySpinner.setOnItemClickListener(new JRSpinner.OnItemClickListener() { //set it if you want the callback
//        @Override
//        public void onItemClick(int position) {
//            //do what you want to the selected position
//        }
//    });

      mJRSpinner.setOnSelectMultipleListener(new JRSpinner.OnSelectMultipleListener() {
          @Override
          public void onMultipleSelected(List<Integer> selectedPosition) {
              //do what you want to selected position list
          }
      }); //use this listener instead if you use multiple
}
```

## Additional
If you want the spinner has a floating label, you can wrap this spinner in Text input layout:

`android.support.design.widget.TextInputLayout` or `com.google.android.material.textfield.TextInputLayout` if you use androidx artifact

```xml
 <com.google.android.material.textfield.TextInputLayout
            android:id="@+id/til"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:hintEnabled="true">

            <jrizani.jrspinner.JRSpinner
                android:id="@+id/spn_my_spinner"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:hint="@string/my_hint"
                android:textColor="@color/colorPrimary"
                app:backgroundTint="@color/colorPrimary"
                app:jrs_title="Choose"
                app:jrs_icon_tint="@color/colorPrimary"/>

</com.google.android.material.textfield.TextInputLayout>
```

## Example
You can found the example code [`here`](https://github.com/jrizani/JRSpinner/tree/master/example).

There is the sample gif

<img src="https://github.com/jrizani/JRSpinner/raw/master/ss/sample.gif" width="200px"/>
