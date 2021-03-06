/*
 * Yalp Store
 * Copyright (C) 2018 Sergey Yeriomin <yeriomin@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.github.yeriomin.yalpstore;

import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

class OnAppClickListener implements AdapterView.OnItemClickListener {

    private AppListActivity activity;

    public OnAppClickListener(AppListActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DetailsActivity.app = activity.getAppByListPosition(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ImageView iconView = view.findViewById(R.id.icon);
            String transitionName = activity.getString(R.string.details_transition_view_name);
            iconView.setTransitionName(transitionName);
            activity.startActivity(
                DetailsActivity.getDetailsIntent(activity, DetailsActivity.app.getPackageName()),
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity, iconView, transitionName).toBundle()
            );
        } else {
            activity.startActivity(DetailsActivity.getDetailsIntent(activity, DetailsActivity.app.getPackageName()));
        }
    }
}
