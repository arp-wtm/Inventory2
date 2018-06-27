/*
 * made by Antonella on Jun/25/2018 using ud845 ABND Udacity
 * course examples that are under this license:
 *
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.antonella.inventory2;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.antonella.inventory2.data.ProductContract.ProductEntry;

/**
 * {@link ProductCursorAdapter} is an adapter for a {@link Cursor} of product data
 * as its data source. This adapter knows how to create list items
 * for each row of product data in the {@link Cursor}.
 */
class ProductCursorAdapter extends CursorAdapter {

    private static final String TAG = ProductCursorAdapter.class.getSimpleName();

    /**
     * Constructs a new {@link ProductCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent,
                false);
    }

    /**
     * This method binds the product data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = view.findViewById(R.id.product_name);
        TextView priceTextView = view.findViewById(R.id.product_price);
        TextView quantityTextView = view.findViewById(R.id.product_quantity);
        TextView saleTextView = view.findViewById(R.id.sale_button);


        // Find the columns of product attributes that we're interested in
        int id = cursor.getInt(cursor.getColumnIndex(ProductEntry._ID));
        int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);

        // Read the products attributes from the Cursor for the current product
        final Long productId = cursor.getLong(cursor.getColumnIndexOrThrow(ProductEntry._ID));
        String productName = cursor.getString(nameColumnIndex);
        float price = cursor.getFloat(priceColumnIndex);
        String productPrice = String.valueOf(price);
        final int quantity = cursor.getInt(quantityColumnIndex);
        final String productQuantity = String.valueOf(quantity);


        // If the product name is empty string or null, then use some default text
        // that says "insert valid name", so the TextView isn't blank.
        if (TextUtils.isEmpty(productName)) {
            productName = context.getString(R.string.product_name);
        }

        final Uri currentProductUri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, id);
        // Update the TextViews with the attributes for the current product
        nameTextView.setText(productName);
        priceTextView.setText(productPrice);
        quantityTextView.setText(productQuantity);
        //  set an onClickListener for the sale button
        saleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when SALE button is clicked the quantity get -1
                //call the productSale of CatalogActivity
                CatalogActivity Activity = (CatalogActivity) context;
                Activity.productSale(productId, Integer.valueOf(productQuantity));

            }
        });

    }
}
