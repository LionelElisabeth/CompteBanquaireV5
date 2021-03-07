Mobile Apps Development
=======================

Project : Bank accounts
-----------------------

Lionel ELISABETH

## Demo
When entering in the application we arrive in the login page.

![demo1](/images/demo1.jpg)

In this activity we have to select the correct id of the user owner of the app.

![demo2](/images/demo2.jpg)

 We can do it either by selecting the user in the spinner or by providing directly the id.

Once the login is validated we go into the next activity.

![demo3](/images/demo3.jpg)

This is the main activity of the app. It displays the data of all the bank accounts of the user.

In order to update the accounts, the user will have to press the refresh button.

![demo4](/images/demo4.jpg)

The accounts can be selected with the spinner.

![demo5](/images/demo5.jpg)


## Code obfuscation
For the code obfuscation I used the plugin **Enigma**
_(build.gradle App - l.1)_ 
``` 
    plugins {
        id 'com.android.application'
        id 'kotlin-android'
        id 'com.chrisney.enigma'
    }
    enigma.enabled = true
```
This plugin encrypt all the java files in the project. This is why I stored the urls as static strings in a java file. 
_(staticStrings.java)_

![image url](/images/urlCryptes.png)

This is what we get when decompiling the Apk.


## Safe internet queries
These line of code enable the connections to be done through _HTTPS_ requests.
_(TLSConnection.kt  - l.21)_
```
    val url = URL(url)
    val urlConnection: URLConnection = url.openConnection()
    val inputStream: InputStream = urlConnection.getInputStream()

```
This will encrypt the content of the packets sent between the app and the remote API using a SSL.


## Safe data persistence
The data is stored using these lines of code :
_(LocalStorageManager.kt  - l.52)_
```
    val fileOutputStream: FileOutputStream
    try {
        fileOutputStream = this.context.openFileOutput(filename, Context.MODE_PRIVATE)
        fileOutputStream.write(content.toByteArray())

    } catch (e: Exception) {
        e.printStackTrace()
    }
```
By indicating **Context.MODE_PRIVATE**, we ensure that the file is only accessible by the app itself.

## Authentication
Once the app is installed, the user will have to select his account name or the corresponding **id**. This **id** will then be stored in the local app files when the login button is pressed.
Afterward at each opening of the app, the user will have to provide his **id** in the login activity in order to access the accounts data activity.

This will ensure that a user with the wrong id will not be able to see the main user private information.

