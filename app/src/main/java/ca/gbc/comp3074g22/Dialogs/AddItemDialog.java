package ca.gbc.comp3074g22.Dialogs;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.gbc.comp3074g22.R;

public class AddItemDialog {

    public interface ItemAddedListener {
        void onItemAdded(String sectionName,String itemPrice);
    }

    private ItemAddedListener listener;

    public void setItemAddedListener(ItemAddedListener listener) {
        this.listener = listener;
    }

    public void showDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.add_item_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        EditText inputItemName = dialog.findViewById(R.id.item_name_input);
        EditText inputItemPrice = dialog.findViewById(R.id.item_price_input);


        Button dialogBtn_Enter = dialog.findViewById(R.id.item_button_add);
        Button dialogBtn_cancel = dialog.findViewById(R.id.item_button_cancel);

        dialogBtn_Enter.setOnClickListener(v -> {
            String inputItemString = inputItemName.getText().toString();
            String inputItemPriceString = inputItemPrice.getText().toString();

            if (inputItemString.isEmpty() || inputItemPriceString.isEmpty()) {
                Toast.makeText(activity.getApplicationContext(), "Fill required fields.", Toast.LENGTH_SHORT).show();
            } else {
                if (listener != null) {
                    listener.onItemAdded(inputItemString,inputItemPriceString);
                }
                dialog.dismiss();
            }
        });

        dialogBtn_cancel.setOnClickListener(v -> {
            Toast.makeText(activity.getApplicationContext(), "Canceled.", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }
}