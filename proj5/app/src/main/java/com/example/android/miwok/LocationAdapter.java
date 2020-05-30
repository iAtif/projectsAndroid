/*
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
package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link LocationAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Location} objects.
 */
public class LocationAdapter extends ArrayAdapter<Location> {

    /**
     * Resource ID for the background color for this list of locations
     */
    private int mColorResourceId;

    /**
     * Create a new {@link LocationAdapter} object.
     *
     * @param context         is the current context (i.e. Activity) that the adapter is being created in.
     * @param hikes           is the list of {@link Hike}s to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of hikes
     */
    public LocationAdapter(Context context, ArrayList<Location> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Location currentLocation = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID hike_name_text_view.
        TextView locationNameTextView = (TextView) listItemView.findViewById(R.id.location_name_text_view);
        // Get the hike location name of the currentHike object and set this text on
        // the Hike TextView.
        locationNameTextView.setText(currentLocation.getLocationName());


        // Find the TextView in the list_item.xml layout with the ID hike_address_text_view.
        TextView locationAddressTextView = (TextView) listItemView.findViewById(R.id.location_address_text_view);
        // Get the address of the hike's of the currentHike object and set this text on
        // the Hike TextView.
        locationAddressTextView.setText(currentLocation.getLocationAddress());


        // Find the TextView in the list_item.xml layout with the ID hike_address_text_view.
        TextView locationDescriptionTextView = (TextView) listItemView.findViewById(R.id.location_description_text_view);
        // Get the address of the hike's of the currentHike object and set this text on
        // the Hike TextView.
        locationDescriptionTextView.setText(currentLocation.getLocationDescription());


        // Find the TextView in the list_item.xml layout with the ID of the difficulty View.
        TextView difficultyView = (TextView) listItemView.findViewById(R.id.location_difficulty_text_view);
        // Check if an image is provided for this word or not
        if (currentLocation.hasDifficulty()) {
            // If an image is available, display the provided image based on the resource ID
            difficultyView.setText(currentLocation.getDifficulty());
            // Make sure the view is visible
            difficultyView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            difficultyView.setVisibility(View.GONE);
        }


        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        // Check if an image is provided for this word or not
        if (currentLocation.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentLocation.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }


        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 3 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}