package com.example.gagan.p2p;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gagan.p2p.adapter.UsersAdapter;
import com.example.gagan.p2p.bindable.SendMoneyDialogueBindview;
import com.example.gagan.p2p.databinding.MoneyDialogueBinding;
import com.example.gagan.p2p.helpers.ObjectCreationHelper;
import com.example.gagan.p2p.helpers.RecyclerItemClick;
import com.example.gagan.p2p.helpers.TransactionHelper;
import com.example.gagan.p2p.helpers.UsersList;
import com.example.gagan.p2p.users.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements RecyclerItemClick, SendMoneyDialogueBindview.OnClickSend {
    private final UsersList userListListner = new UsersList() {
        @Override
        public void usersUpdated(List<UserModel> usersLists) {
            MainActivity.this.usersLists.clear();
            MainActivity.this.usersLists.addAll(usersLists);
            usersAdapter.notifyItemRangeChanged(0, MainActivity.this.usersLists.size());
        }
    };
    private final List<UserModel> usersLists = new ArrayList<>();
    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    private UsersAdapter usersAdapter = new UsersAdapter(usersLists);
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        ObjectCreationHelper.getInstance().getUseresRef(userListListner);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersAdapter.setItemClickListner(this);
        recyclerView.setAdapter(usersAdapter);
    }

    @Override
    public void onItemClickListner(int position) {
        MoneyDialogueBinding moneyDilogue = DataBindingUtil.inflate(LayoutInflater.from(this),
                R.layout.money_dialogue, null, false);
        SendMoneyDialogueBindview sendMoneyDialogueBindview =
                new SendMoneyDialogueBindview(usersLists.get(position),
                        this);
        moneyDilogue.setItems(sendMoneyDialogueBindview);
        ImageView iv_pic = moneyDilogue.getRoot().findViewById(R.id.iv_pic);
        sendMoneyDialogueBindview.setImage(iv_pic);
        sendMoneyDialogueBindview.setListner(this);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(moneyDilogue.getRoot());
        dialog.show();
    }

    @Override
    public void sendMoney(UserModel toUser, int money) {
        TransactionHelper.getInstance().sentMoneyTo(toUser,money);
    }
}
