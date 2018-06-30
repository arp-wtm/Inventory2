# Inventory2
Inventory Stage 2 project 9 UDACITY ABND course
This app contains activities  for the user to:
- Add Inventory
- See Product Details
- Edit Product Details
- See a list of all inventory in the first screen
- Add Product with a great button
- Delete all entries
- Insert dummy data
In the Catalog Activity each list item displays the Product Name, Price, and Quantity. With a button "SALE" to decrease quantity of the product saled. CatalogActivity Class controls that no negative values are displayed. When zero quantity is reached a Toast advices to order the product saled!
Product Name, Price, Quantity, Supplier Name, and Supplier Phone Number are the field stored in the database and shown in the EditorActivity. This screen has also an overlay menu that as Delete if is an existing product, and Save for both existing and new.
There are also two button to delete the product record entirely or to order calling the supplier phone number.
To better perform functionality, queries on SQLite database are made in background thread, implementing a Loader, a Content Provider and a ProductCursorAdapter that will populate the list on the first screen.
In the Editor Activity, the button "-" and "+"  used for change the quantity of product edited, don't send update to database. It will be made when user save the product.
The ProductContract Class establishes a contract between the ProductProvider and other applications. It ensures that  ProductProvider can be accessed correctly even if there are changes to the actual values of URIs, column names etc.
Since it provides mnemonic names for its constants, developers are less likely to use incorrect values for column names or URIs. It's easy to make the Javadoc documentation available to the clients that want to use ProductProvider.
ProductProvider is just for sharing app's data. When you want to access it, even in another app, it uses the Content Resolver to send commands with specific methods query(), insert(), update(), delete(), and getType(). The last one is for match all  Products table or a single record in Products table. 
## TEST
The code runs without errors on HUAWEI JMM-L22 Android 7.0 Api 24.
The Android Project is built  for Phone and Tablet with LEVEL API 15: Android 4.0.3 (IceCreamSandwich)
The code runs without errors. For example, when user inputs product information (quantity, price, name), instead of erroring out, the app includes logic to validate that no null values are accepted. 
If a null value is inputted, a Toast prompts the user to input the correct information before they can continue.
### build 
```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:27.1.1'
    implementation 'com.android.support.constraint:constraint-layout:1.1.2'
    implementation 'com.android.support:design:27.1.1'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
}
```
### LICENCE 
 made by Antonella on jun.28.2018<br>
 for news stage 2 app exercise for project 7<br>
 in Udacity ABND course. It uses as model the<br>
 Quake Report app of the lesson on JSON Parsing and Settings Preference that is under this licence:<br>

 Copyright (C) 2018 The Android Open Source Project<br>
 
 Licensed under the Apache License, Version 2.0 (the "License");<br>
 you may not use this file except in compliance with the License.<br>
 You may obtain a copy of the License at<br>
 
      http://www.apache.org/licenses/LICENSE-2.0
      
 Unless required by applicable law or agreed to in writing, software<br>
 distributed under the License is distributed on an "AS IS" BASIS,<br>
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.<br>
 See the License for the specific language governing permissions and<br>
 limitations under the License.
