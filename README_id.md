[English](https://github.com/jrizani/JRSpinner/blob/master/README.md) | Indonesian

# JRSpinner
[![](https://jitpack.io/v/jrizani/JRSpinner.svg)](https://jitpack.io/#jrizani/JRSpinner)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-JRSpinner-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7528)

Custom spinner yang terinspirasi dengan spinner pemilihan bahasa pada instagram

#### [Lihat daftar release dan changelog](https://github.com/jrizani/JRSpinner/releases)
<img src="https://github.com/jrizani/JRSpinner/raw/master/ss/example_spinner.png" width="200px"/> <img src="https://github.com/jrizani/JRSpinner/raw/master/ss/example_spinner_dialog.png" width="200px"/> <img src="https://github.com/jrizani/JRSpinner/raw/master/ss/example_spinner_dialog_selected.png" width="200px"/> <img src="https://github.com/jrizani/JRSpinner/raw/master/ss/example_spinner_dialog_search.png" width="200px"/> <img src="https://github.com/jrizani/JRSpinner/raw/master/ss/example_spinner_selected_item.png" width="200px"/>


# Table of Content
1. [Apa yang baru](#apa-yang-baru)
2. [Install](#install)
3. [Cara menggunakan](#cara-menggunakan)
4. [Tambahan](#tambahan)
5. [Contoh](#contoh)

---

## Apa yang baru
Apa yang baru pada versi 1.0.0
```
1. mengganti repository ke jitpack.io
2. menambah fitur memilih multiple
```

---

## Install
Pastikan terdapat repository `jitpack.io` pada build.gradle level project anda.

```gradle
allprojects {
    repositories {
        ..
        maven { url "https://jitpack.io" }
    }
}
```

Implementasi JRSpinner pada build.gradle level app anda.

```gradle
dependencies {
  ..
  implementation 'com.github.jrizani:JRSpinner:$version'
}
```

## Cara menggunakan
Deklarasikan view pada layout anda.
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

### Atribut
| Atribut | Deskripsi | Isi Default |
| --- | --- | --- |
| android:hint | hint pada spinner | kosong |
| android:textColor | warna teks | warna teks default |
| app:backgroundTint | warna spinner, biasanya ini adalah warna garis bawah pada spinner. | warna background default edittext |
| android:background | jika ingin menggunakan background sendiri, gunakan atribut ini | background default edittext |
| app:jrs_title | judul dari spinner dialog yang akan muncul | "Choose" |
| app:jrs_icon_tint | warna dari ikon expand | #99000000 |
| app:jrs_multiple | pilih apakah menggunakan pemilihan multiple atau tidak | false |

Kamu juga bisa mengubah atribut secara programmatically.
```java
private JRSpinner mySpinner;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mySpinner = findViewById(R.id.spn_my_spinner);

    mySpinner.setItems(getResources().getStringArray(R.array.tesItems)); //penting, harus diisi agar spinner tidak kosong
    mySpinner.setTitle("Choose item programmatically"); //mengubah judul dialog secara programmatically
    mySpinner.setExpandTint(R.color.color_default); //mengubah warna icon secara programmatically

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

## Tambahan
Jika ingin spinner mempunyai hint yang berubah jadi label, kamu bisa menambahkan JRSpinner kedalam TextInputLayout

`android.support.design.widget.TextInputLayout` atau `com.google.android.material.textfield.TextInputLayout` jika menggunakan artifak androidx

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

## Contoh
Lihat kode contoh [`disini`](https://github.com/jrizani/JRSpinner/tree/master/example).

Ini adalah gif sampel

<img src="https://github.com/jrizani/JRSpinner/raw/master/ss/sample.gif" width="200px"/>
