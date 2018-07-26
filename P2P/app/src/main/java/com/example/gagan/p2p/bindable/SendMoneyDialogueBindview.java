package com.example.gagan.p2p.bindable;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gagan.p2p.helpers.CircleTransform;
import com.example.gagan.p2p.helpers.ObjectCreationHelper;
import com.example.gagan.p2p.users.UserModel;
import com.example.gagan.p2p.utils.Utils;
import com.squareup.picasso.Picasso;

/**
 * Created by Gagan on 6/26/2018.
 */

public class SendMoneyDialogueBindview extends BaseObservable implements View.OnClickListener {


    public interface OnClickSend {
        void sendMoney(UserModel toUser, int money);
    }

    private OnClickSend listner;

    public void setListner(OnClickSend send) {
        this.listner = send;
    }

    private final UserModel reciverModel;
    private final Context mContext;
    private String amount;
    private UserModel curentUser = ObjectCreationHelper.getInstance().getCurrentUser();

    public SendMoneyDialogueBindview(@NonNull UserModel reciverModel, Context context) {
        this.reciverModel = reciverModel;
        this.mContext = context;
    }

    public int getAmountInt() {
        return amount == null || amount.trim().length() == 0 ? -1 : Integer.parseInt(amount);
    }

    public void setImage(ImageView iv_pic) {
        Picasso.get().load(reciverModel.getPicUri()).transform(new CircleTransform()).into(iv_pic);
    }

    @Bindable
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Bindable
    public String getSenderName() {
        return Utils.FirstLetterCap(reciverModel.getName());
    }

    @Bindable
    public String getAccountBalanceLeft() {
        return "Account Balance:" + Utils.getMoneyFormat(curentUser.getAmount());
    }

    @Override
    public void onClick(View v) {
        int amountInt = getAmountInt();
        if (amountInt == -1) {
            Toast.makeText(mContext, "Enter some amount to send", Toast.LENGTH_SHORT).show();
            return;
        }
        if (amountInt > curentUser.getAmount()) {
            Toast.makeText(mContext, "Insufficient fund", Toast.LENGTH_LONG).show();
            return;
        }
        if (listner != null)
            listner.sendMoney(curentUser, amountInt);

    }
}
