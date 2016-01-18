package by.reghor.installedapps.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import by.reghor.installedapps.R;
import by.reghor.installedapps.entity.AppInfo;
import by.reghor.installedapps.entity.Status;

/**
 * Created by reghor on 1/18/16.
 */
public class AppInfoListAdapter extends ArrayAdapter<AppInfo> {
    private String dateFormat="yyyy-MM-dd hh:mm:ss";
    public AppInfoListAdapter(Context context, List<AppInfo> items) {
        super(context, R.layout.fragment_app_list, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        DateFormat dateFormatter=new SimpleDateFormat(dateFormat);
        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_fragment_app_list, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.appIcon = (ImageView) convertView.findViewById(R.id.appIcon);
            viewHolder.name = (TextView) convertView.findViewById(R.id.appName);
            viewHolder.date=(TextView) convertView.findViewById(R.id.scanDate);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        AppInfo item = getItem(position);
        Status status = item.getStatus();
        switch (status) {
            case NEW: {
                viewHolder.name.setTextColor(Color.GREEN);
                break;
            }
            case DELETED: {
                viewHolder.name.setTextColor(Color.RED);
                break;
            }
            default:{
                viewHolder.name.setTextColor(Color.BLACK);
            }
        }


        viewHolder.appIcon.setImageDrawable(item.getIcon());
        viewHolder.name.setText(item.getAppName());
        viewHolder.date.setText( dateFormatter.format(item.getLastCheckedDate()));

        return convertView;
    }

    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */
    private static class ViewHolder {
        ImageView appIcon;
        TextView name;
        TextView date;

    }
}
