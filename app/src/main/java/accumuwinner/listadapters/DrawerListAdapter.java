package accumuwinner.listadapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accumuwinnerbettingtips.R;

import accumuwinner.storage.UserInfo;

/**
 * Created by mmckillion on 05/10/2014.
 */
public class DrawerListAdapter extends ArrayAdapter {

    private String[] menuItems;

    public DrawerListAdapter(Context context, int resource) {
        super(context, resource);
        menuItems = context.getResources().getStringArray(R.array.menu_items);
    }

    @Override
    public int getCount() {
        return menuItems.length + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            if(position == 0) {
                convertView = inflater.inflate(R.layout.drawer_list_header, parent, false);
            } else {
                convertView = inflater.inflate(R.layout.drawer_list_item, parent, false);
            }
        }

        /**
         * Position 0 in the drawer list corresponds to the larger profile section.
         */
        if(position != 0) {
            TextView menuItemText = (TextView) convertView.findViewById(R.id.menu_item);
            menuItemText.setText(menuItems[position - 1]);
        } else {
            ImageView profileImageView = (ImageView) convertView.findViewById(R.id.profile_image);

            UserInfo userInfo = UserInfo.getInstance();

            TextView usernameField = (TextView) convertView.findViewById(R.id.profile_name);
            TextView emailField = (TextView) convertView.findViewById(R.id.profile_email);

            usernameField.setText(userInfo.getUsername());
            emailField.setText(userInfo.getEmail());

            Bitmap profileImage = roundProfileImage(
                    BitmapFactory.decodeResource(getContext().getResources(),
                            R.drawable.grey_silhoutte));
            profileImageView.setImageBitmap(profileImage);
        }

        return convertView;
    }

    private Bitmap roundProfileImage(Bitmap profileImage) {

        int targetWidth = 100;
        int targetHeight = 100;

        //Set the bitmaps width and resulting quality, and load it into a canvas for manipulation.
        Bitmap resultImage = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(resultImage);
        Path circularPath = new Path();

        circularPath.addCircle(
                ((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth), ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        //Clip the path to ensure the edges of the image outside the circular frame get cut off.
        canvas.clipPath(circularPath);

        canvas.drawBitmap(profileImage,
                new Rect(0, 0, profileImage.getWidth(), profileImage.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);

        return resultImage;
    }
}
