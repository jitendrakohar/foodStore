package com.example.foodorderingapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodorderingapp.Domain.FoodDomain;
import com.example.foodorderingapp.Helper.ManagementCart;
import com.example.foodorderingapp.R;

public class ShowDetailActivity extends AppCompatActivity {
private TextView addToCartBtn;
private TextView titleTxt,feeTxt,descriptionTxt,numberOrderTxt;
private ImageView plusBtn,MinusBtn,picFood;
private FoodDomain object;
int numberOrder=1;
private ManagementCart managementCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        managementCart=new ManagementCart(this);
        initView();
        getBundle();
    }
    private void getBundle(){
        object=(FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(1));
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              numberOrder=numberOrder+1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });
        MinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOrder>1)
                numberOrder=numberOrder-1;
                else{
                    Toast.makeText(ShowDetailActivity.this, "At least 1 object is required", Toast.LENGTH_SHORT).show();
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });


        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
                Toast.makeText(ShowDetailActivity.this, " "+managementCart.getListCart(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initView(){
        addToCartBtn=findViewById(R.id.addtocartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        feeTxt=findViewById(R.id.idPriceTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberOrderTxt);
        plusBtn=findViewById(R.id.plus);
        MinusBtn=findViewById(R.id.minus);
        picFood=findViewById(R.id.pic);

    }
}