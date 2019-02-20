# JRSpinner
[ ![Download](https://api.bintray.com/packages/juliannoorrizani/maven/JRSpinner/images/download.svg) ](https://bintray.com/juliannoorrizani/maven/JRSpinner/_latestVersion)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Custom spinner that inspired by instagram language chooser

#### [View Releases and Changelogs](https://github.com/jrizani/JRSpinner/releases)


# Table of Content
1. [Gradle install](#gradle-install)
2. [How to use](#how-to)

---

## Gradle install
This version is not included in jcenter yet, you must add this library's bintray to your project-level build.gradle

```gradle
allprojects {
    repositories {
        ..
        maven { url "https://dl.bintray.com/juliannoorrizani/maven"}
    }
}
```

And implement the dependency to your app-level build-gradle

```gradle
dependencies {
  ..
  implementation 'jrizani:jrspinner:$version'
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
                app:jrs_icon_tint="@color/colorPrimary"/>
```

### Parameter
| Parameter | Description | Default Value |
| --- | --- | --- |
| android:hint | hint of spinner | nothing |
| android:textColor | color of text | default hint color |
| app:backgroundTint | tint of spinner, in default it is change underline color of spinner | default edittext background tint |
| android:background | if you want your own background of spinner, you can use it | default edittext background |
| app:jrs_title | there is the title of the spinner dialog | "Choose" |
| app:jrs_icon_tint | color tint of expand icon | #99000000 |

You also can set the parameter programmatically
```java
private JRSpinner mySpinner;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mJRSpinner = findViewById(R.id.JRSpinner);

    mJRSpinner.setItems(getResources().getStringArray(R.array.tesItems)); //this is important, you must set it to set the item list
    mJRSpinner.setTitle("Choose item programmatically"); //change title of spinner-dialog programmatically
    mJRSpinner.setExpandTint(R.color.color_default); //change expand icon tint programmatically

    mJRSpinner.setOnItemClickListener(new JRSpinner.OnItemClickListener() { //set it if you want the callback
        @Override
        public void onItemClick(int position) {
            //do what you want to the selected position
        }
    });
}
```
